package co.jufeng.barcode.gif;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Vector;

public class Gif89Encoder {

	private Dimension dispDim = new Dimension(0, 0);
	private GifColorTable colorTable;
	private int bgIndex = 0;
	private int loopCount = 1;
	private String theComments;
	private Vector<Gif89Frame> vFrames = new Vector<Gif89Frame>();

	public Gif89Encoder() {
		colorTable = new GifColorTable();
	}

	public Gif89Encoder(Image static_image) throws IOException {
		this();
		addFrame(static_image);
	}

	public Gif89Encoder(Color[] colors) {
		colorTable = new GifColorTable(colors);
	}

	public Gif89Encoder(Color[] colors, int width, int height, byte ci_pixels[])
			throws IOException {
		this(colors);
		addFrame(width, height, ci_pixels);
	}

	public int getFrameCount() {
		return vFrames.size();
	}

	public Gif89Frame getFrameAt(int index) {
		return isOk(index) ? (Gif89Frame) vFrames.elementAt(index) : null;
	}

	public void addFrame(Gif89Frame gf) throws IOException {
		accommodateFrame(gf);
		vFrames.addElement(gf);
	}

	public void addFrame(Image image) throws IOException {
		addFrame(new DirectGif89Frame(image));
	}

	public void addFrame(int width, int height, byte ci_pixels[])
			throws IOException {
		addFrame(new IndexGif89Frame(width, height, ci_pixels));
	}

	public void insertFrame(int index, Gif89Frame gf) throws IOException {
		accommodateFrame(gf);
		vFrames.insertElementAt(gf, index);
	}

	public void setTransparentIndex(int index) {
		colorTable.setTransparent(index);
	}

	public void setLogicalDisplay(Dimension dim, int background) {
		dispDim = new Dimension(dim);
		bgIndex = background;
	}

	public void setLoopCount(int count) {
		loopCount = count;
	}

	public void setComments(String comments) {
		theComments = comments;
	}

	public void setUniformDelay(int interval) {
		for (int i = 0; i < vFrames.size(); ++i)
			((Gif89Frame) vFrames.elementAt(i)).setDelay(interval);
	}

	public void encode(OutputStream out) throws IOException {
		int nframes = getFrameCount();
		boolean is_sequence = nframes > 1;

		// N.B. must be called before writing screen descriptor
		colorTable.closePixelProcessing();

		// write GIF HEADER
		Put.ascii("GIF89a", out);

		// write global blocks
		writeLogicalScreenDescriptor(out);
		colorTable.encode(out);
		if (is_sequence && loopCount != 1)
			writeNetscapeExtension(out);
		if (theComments != null && theComments.length() > 0)
			writeCommentExtension(out);

		// write out the control and rendering data for each frame
		for (int i = 0; i < nframes; ++i)
			((Gif89Frame) vFrames.elementAt(i)).encode(out, is_sequence,
					colorTable.getDepth(), colorTable.getTransparent());

		// write GIF TRAILER
		out.write((int) ';');

		out.flush();
	}

	

	private void accommodateFrame(Gif89Frame gf) throws IOException {
		dispDim.width = Math.max(dispDim.width, gf.getWidth());
		dispDim.height = Math.max(dispDim.height, gf.getHeight());
		colorTable.processPixels(gf);
	}

	private void writeLogicalScreenDescriptor(OutputStream os)
			throws IOException {
		Put.leShort(dispDim.width, os);
		Put.leShort(dispDim.height, os);

		// write 4 fields, packed into a byte (bitfieldsize:value)
		// global color map present? (1:1)
		// bits per primary color less 1 (3:7)
		// sorted color table? (1:0)
		// bits per pixel less 1 (3:varies)
		os.write(0xf0 | colorTable.getDepth() - 1);

		// write background color index
		os.write(bgIndex);

		// Jef Poskanzer's notes on the next field, for our possible
		// edification:
		// Pixel aspect ratio - 1:1.
		// Putbyte( (byte) 49, outs );
		// Java's GIF reader currently has a bug, if the aspect ratio byte is
		// not zero it throws an ImageFormatException. It doesn't know that
		// 49 means a 1:1 aspect ratio. Well, whatever, zero works with all
		// the other decoders I've tried so it probably doesn't hurt.

		// OK, if it's good enough for Jef, it's definitely good enough for us:
		os.write(0);
	}

	// ----------------------------------------------------------------------------
	private void writeNetscapeExtension(OutputStream os) throws IOException {
		// n.b. most software seems to interpret the count as a repeat count
		// (i.e., interations beyond 1) rather than as an iteration count
		// (thus, to avoid repeating we have to omit the whole extension)

		os.write((int) '!'); // GIF Extension Introducer
		os.write(0xff); // Application Extension Label

		os.write(11); // application ID block size
		Put.ascii("NETSCAPE2.0", os); // application ID data

		os.write(3); // data sub-block size
		os.write(1); // a looping flag? dunno

		// we finally write the relevent data
		Put.leShort(loopCount > 1 ? loopCount - 1 : 0, os);

		os.write(0); // block terminator
	}

	// ----------------------------------------------------------------------------
	private void writeCommentExtension(OutputStream os) throws IOException {
		os.write((int) '!'); // GIF Extension Introducer
		os.write(0xfe); // Comment Extension Label

		int remainder = theComments.length() % 255;
		int nsubblocks_full = theComments.length() / 255;
		int nsubblocks = nsubblocks_full + (remainder > 0 ? 1 : 0);
		int ibyte = 0;
		for (int isb = 0; isb < nsubblocks; ++isb) {
			int size = isb < nsubblocks_full ? 255 : remainder;

			os.write(size);
			Put.ascii(theComments.substring(ibyte, ibyte + size), os);
			ibyte += size;
		}

		os.write(0); // block terminator
	}

	// ----------------------------------------------------------------------------
	private boolean isOk(int frame_index) {
		return frame_index >= 0 && frame_index < vFrames.size();
	}
}

// ==============================================================================
class GifColorTable {

	// the palette of ARGB colors, packed as returned by Color.getRGB()
	private int[] theColors = new int[256];

	// other basic attributes
	private int colorDepth;
	private int transparentIndex = -1;

	// these fields track color-index info across frames
	private int ciCount = 0; // count of distinct color indices
	private ReverseColorMap ciLookup; // cumulative rgb-to-ci lookup table

	// ----------------------------------------------------------------------------
	GifColorTable() {
		ciLookup = new ReverseColorMap(); // puts us into "auto-detect mode"
	}

	// ----------------------------------------------------------------------------
	GifColorTable(Color[] colors) {
		int n2copy = Math.min(theColors.length, colors.length);
		for (int i = 0; i < n2copy; ++i)
			theColors[i] = colors[i].getRGB();
	}

	// ----------------------------------------------------------------------------
	int getDepth() {
		return colorDepth;
	}

	// ----------------------------------------------------------------------------
	int getTransparent() {
		return transparentIndex;
	}

	// ----------------------------------------------------------------------------
	// default: -1 (no transparency)
	void setTransparent(int color_index) {
		transparentIndex = color_index;
	}

	// ----------------------------------------------------------------------------
	void processPixels(Gif89Frame gf) throws IOException {
		if (gf instanceof DirectGif89Frame)
			filterPixels((DirectGif89Frame) gf);
		else
			trackPixelUsage((IndexGif89Frame) gf);
	}

	// ----------------------------------------------------------------------------
	void closePixelProcessing() // must be called before encode()
	{
		colorDepth = computeColorDepth(ciCount);
	}

	// ----------------------------------------------------------------------------
	void encode(OutputStream os) throws IOException {
		// size of palette written is the smallest power of 2 that can accomdate
		// the number of RGB colors detected (or largest color index, in case of
		// index pixels)
		int palette_size = 1 << colorDepth;
		for (int i = 0; i < palette_size; ++i) {
			os.write(theColors[i] >> 16 & 0xff);
			os.write(theColors[i] >> 8 & 0xff);
			os.write(theColors[i] & 0xff);
		}
	}

	// ----------------------------------------------------------------------------
	// This method accomplishes three things:
	// (1) converts the passed rgb pixels to indexes into our rgb lookup table
	// (2) fills the rgb table as new colors are encountered
	// (3) looks for transparent pixels so as to set the transparent index
	// The information is cumulative across multiple calls.
	//
	// (Note: some of the logic is borrowed from Jef Poskanzer's code.)
	// ----------------------------------------------------------------------------
	private void filterPixels(DirectGif89Frame dgf) throws IOException {
		if (ciLookup == null)
			throw new IOException("RGB frames require palette autodetection");

		int[] argb_pixels = (int[]) dgf.getPixelSource();
		byte[] ci_pixels = dgf.getPixelSink();
		int npixels = argb_pixels.length;
		for (int i = 0; i < npixels; ++i) {
			int argb = argb_pixels[i];

			// handle transparency
			if ((argb >>> 24) < 0x80) // transparent pixel?
				if (transparentIndex == -1) // first transparent color
											// encountered?
					transparentIndex = ciCount; // record its index
				else if (argb != theColors[transparentIndex]) // different pixel
																// value?
				{
					// collapse all transparent pixels into one color index
					ci_pixels[i] = (byte) transparentIndex;
					continue; // CONTINUE - index already in table
				}

			// try to look up the index in our "reverse" color table
			int color_index = ciLookup.getPaletteIndex(argb & 0xffffff);

			if (color_index == -1) // if it isn't in there yet
			{
				if (ciCount == 256)
					throw new IOException("can't encode as GIF (> 256 colors)");

				// store color in our accumulating palette
				theColors[ciCount] = argb;

				// store index in reverse color table
				ciLookup.put(argb & 0xffffff, ciCount);

				// send color index to our output array
				ci_pixels[i] = (byte) ciCount;

				// increment count of distinct color indices
				++ciCount;
			} else
				// we've already snagged color into our palette
				ci_pixels[i] = (byte) color_index; // just send filtered pixel
		}
	}

	// ----------------------------------------------------------------------------
	private void trackPixelUsage(IndexGif89Frame igf) throws IOException {
		byte[] ci_pixels = (byte[]) igf.getPixelSource();
		int npixels = ci_pixels.length;
		for (int i = 0; i < npixels; ++i)
			if (ci_pixels[i] >= ciCount)
				ciCount = ci_pixels[i] + 1;
	}

	// ----------------------------------------------------------------------------
	private int computeColorDepth(int colorcount) {
		// color depth = log-base-2 of maximum number of simultaneous colors,
		// i.e.
		// bits per color-index pixel
		if (colorcount <= 2)
			return 1;
		if (colorcount <= 4)
			return 2;
		if (colorcount <= 16)
			return 4;
		return 8;
	}
}

// ==============================================================================
// We're doing a very simple linear hashing thing here, which seems sufficient
// for our needs. I make no claims for this approach other than that it seems
// an improvement over doing a brute linear search for each pixel on the one
// hand, and creating a Java object for each pixel (if we were to use a Java
// Hashtable) on the other. Doubtless my little hash could be improved by
// tuning the capacity (at the very least). Suggestions are welcome.
// ==============================================================================
class ReverseColorMap {

	private static class ColorRecord {
		int rgb;
		int ipalette;

		ColorRecord(int rgb, int ipalette) {
			this.rgb = rgb;
			this.ipalette = ipalette;
		}
	}

	// I wouldn't really know what a good hashing capacity is, having missed out
	// on data structures and algorithms class :) Alls I know is, we've got a
	// lot
	// more space than we have time. So let's try a sparse table with a maximum
	// load of about 1/8 capacity.
	private static final int HCAPACITY = 2053; // a nice prime number

	// our hash table proper
	private ColorRecord[] hTable = new ColorRecord[HCAPACITY];

	// ----------------------------------------------------------------------------
	// Assert: rgb is not negative (which is the same as saying, be sure the
	// alpha transparency byte - i.e., the high byte - has been masked out).
	// ----------------------------------------------------------------------------
	int getPaletteIndex(int rgb) {
		ColorRecord rec;

		for (int itable = rgb % hTable.length; (rec = hTable[itable]) != null
				&& rec.rgb != rgb; itable = ++itable % hTable.length)
			;

		if (rec != null)
			return rec.ipalette;

		return -1;
	}

	// ----------------------------------------------------------------------------
	// Assert: (1) same as above; (2) rgb key not already present
	// ----------------------------------------------------------------------------
	void put(int rgb, int ipalette) {
		int itable;

		for (itable = rgb % hTable.length; hTable[itable] != null; itable = ++itable
				% hTable.length)
			;

		hTable[itable] = new ColorRecord(rgb, ipalette);
	}
}