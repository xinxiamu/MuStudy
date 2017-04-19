package co.jufeng.barcode;

import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.image.PixelGrabber;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;

public class PngEncoder {

    public static final boolean ENCODE_ALPHA = true;
    public static final boolean NO_ALPHA = false;

    public static final int FILTER_NONE = 0;
    public static final int FILTER_SUB = 1;
    public static final int FILTER_UP = 2;
    public static final int FILTER_LAST = 2;

    protected static final byte[] IHDR = {73, 72, 68, 82};
    protected static final byte[] IDAT = {73, 68, 65, 84};
    protected static final byte[] IEND = {73, 69, 78, 68};
    protected static final byte[] PHYS = {(byte)'p', (byte)'H', (byte)'Y', (byte)'s'};

    protected byte[] pngBytes;
    protected byte[] priorRow;
    protected byte[] leftBytes;

    protected Image image;

    protected int width;
    protected int height;
    protected int bytePos;
    protected int maxPos;
    
    protected CRC32 crc = new CRC32();
    
    protected long crcValue;
    
    protected boolean encodeAlpha;

    protected int filter;
    protected int bytesPerPixel;
    private int xDpi = 0;
    private int yDpi = 0;

    static private float INCH_IN_METER_UNIT = 0.0254f;

    protected int compressionLevel;

    public PngEncoder() {
        this(null, false, FILTER_NONE, 0);
    }

    public PngEncoder(Image image) {
        this(image, false, FILTER_NONE, 0);
    }

    public PngEncoder(Image image, boolean encodeAlpha) {
        this(image, encodeAlpha, FILTER_NONE, 0);
    }

    public PngEncoder(Image image, boolean encodeAlpha, int whichFilter) {
        this(image, encodeAlpha, whichFilter, 0);
    }


    public PngEncoder(Image image, boolean encodeAlpha, int whichFilter,
            int compLevel) {
        this.image = image;
        this.encodeAlpha = encodeAlpha;
        setFilter(whichFilter);
        if (compLevel >= 0 && compLevel <= 9) {
            this.compressionLevel = compLevel;
        }
    }

    public void setImage(Image image) {
        this.image = image;
        this.pngBytes = null;
    }

    public Image getImage() {
      return image;
    }

    public byte[] pngEncode(boolean encodeAlpha) {
        byte[]  pngIdBytes = {-119, 80, 78, 71, 13, 10, 26, 10};

        if (this.image == null) {
            return null;
        }
        this.width = this.image.getWidth(null);
        this.height = this.image.getHeight(null);

        /*
         * start with an array that is big enough to hold all the pixels
         * (plus filter bytes), and an extra 200 bytes for header info
         */
        this.pngBytes = new byte[((this.width + 1) * this.height * 3) + 200];

        /*
         * keep track of largest byte written to the array
         */
        this.maxPos = 0;

        this.bytePos = writeBytes(pngIdBytes, 0);
        //hdrPos = bytePos;
        writeHeader();
        writeResolution();
        //dataPos = bytePos;
        if (writeImageData()) {
            writeEnd();
            this.pngBytes = resizeByteArray(this.pngBytes, this.maxPos);
        }
        else {
            this.pngBytes = null;
        }
        return this.pngBytes;
    }

    public byte[] pngEncode() {
        return pngEncode(this.encodeAlpha);
    }

    public void setEncodeAlpha(boolean encodeAlpha) {
        this.encodeAlpha = encodeAlpha;
    }

    public boolean getEncodeAlpha() {
        return this.encodeAlpha;
    }

    public void setFilter(int whichFilter) {
        this.filter = FILTER_NONE;
        if (whichFilter <= FILTER_LAST) {
            this.filter = whichFilter;
        }
    }

    public int getFilter() {
        return this.filter;
    }

    public void setCompressionLevel(int level) {
        if (level >= 0 && level <= 9) {
            this.compressionLevel = level;
        }
    }

    public int getCompressionLevel() {
        return this.compressionLevel;
    }

    protected byte[] resizeByteArray(byte[] array, int newLength) {
        byte[]  newArray = new byte[newLength];
        int     oldLength = array.length;

        System.arraycopy(array, 0, newArray, 0, Math.min(oldLength, newLength));
        return newArray;
    }

    protected int writeBytes(byte[] data, int offset) {
        this.maxPos = Math.max(this.maxPos, offset + data.length);
        if (data.length + offset > this.pngBytes.length) {
            this.pngBytes = resizeByteArray(this.pngBytes, this.pngBytes.length
                    + Math.max(1000, data.length));
        }
        System.arraycopy(data, 0, this.pngBytes, offset, data.length);
        return offset + data.length;
    }

    protected int writeBytes(byte[] data, int nBytes, int offset) {
        this.maxPos = Math.max(this.maxPos, offset + nBytes);
        if (nBytes + offset > this.pngBytes.length) {
            this.pngBytes = resizeByteArray(this.pngBytes, this.pngBytes.length
                    + Math.max(1000, nBytes));
        }
        System.arraycopy(data, 0, this.pngBytes, offset, nBytes);
        return offset + nBytes;
    }

    protected int writeInt2(int n, int offset) {
        byte[] temp = {(byte) ((n >> 8) & 0xff), (byte) (n & 0xff)};
        return writeBytes(temp, offset);
    }

    protected int writeInt4(int n, int offset) {
        byte[] temp = {(byte) ((n >> 24) & 0xff),
                       (byte) ((n >> 16) & 0xff),
                       (byte) ((n >> 8) & 0xff),
                       (byte) (n & 0xff)};
        return writeBytes(temp, offset);
    }

    protected int writeByte(int b, int offset) {
        byte[] temp = {(byte) b};
        return writeBytes(temp, offset);
    }

    protected void writeHeader() {

        int startPos = this.bytePos = writeInt4(13, this.bytePos);
        this.bytePos = writeBytes(IHDR, this.bytePos);
        this.width = this.image.getWidth(null);
        this.height = this.image.getHeight(null);
        this.bytePos = writeInt4(this.width, this.bytePos);
        this.bytePos = writeInt4(this.height, this.bytePos);
        this.bytePos = writeByte(8, this.bytePos); // bit depth
        this.bytePos = writeByte((this.encodeAlpha) ? 6 : 2, this.bytePos);
            // direct model
        this.bytePos = writeByte(0, this.bytePos); // compression method
        this.bytePos = writeByte(0, this.bytePos); // filter method
        this.bytePos = writeByte(0, this.bytePos); // no interlace
        this.crc.reset();
        this.crc.update(this.pngBytes, startPos, this.bytePos - startPos);
        this.crcValue = this.crc.getValue();
        this.bytePos = writeInt4((int) this.crcValue, this.bytePos);
    }

    protected void filterSub(byte[] pixels, int startPos, int width) {
        int offset = this.bytesPerPixel;
        int actualStart = startPos + offset;
        int nBytes = width * this.bytesPerPixel;
        int leftInsert = offset;
        int leftExtract = 0;

        for (int i = actualStart; i < startPos + nBytes; i++) {
            this.leftBytes[leftInsert] =  pixels[i];
            pixels[i] = (byte) ((pixels[i] - this.leftBytes[leftExtract])
                     % 256);
            leftInsert = (leftInsert + 1) % 0x0f;
            leftExtract = (leftExtract + 1) % 0x0f;
        }
    }

    protected void filterUp(byte[] pixels, int startPos, int width) {

        final int nBytes = width * this.bytesPerPixel;

        for (int i = 0; i < nBytes; i++) {
            final byte currentByte = pixels[startPos + i];
            pixels[startPos + i] = (byte) ((pixels[startPos  + i]
                    - this.priorRow[i]) % 256);
            this.priorRow[i] = currentByte;
        }
    }

    protected boolean writeImageData() {
        int rowsLeft = this.height;  // number of rows remaining to write
        int startRow = 0;       // starting row to process this time through
        int nRows;              // how many rows to grab at a time

        byte[] scanLines;       // the scan lines to be compressed
        int scanPos;            // where we are in the scan lines
        int startPos;           // where this line's actual pixels start (used
                                // for filtering)

        byte[] compressedLines; // the resultant compressed lines
        int nCompressed;        // how big is the compressed area?

        //int depth;              // color depth ( handle only 8 or 32 )

        PixelGrabber pg;

        this.bytesPerPixel = (this.encodeAlpha) ? 4 : 3;

        Deflater scrunch = new Deflater(this.compressionLevel);
        ByteArrayOutputStream outBytes = new ByteArrayOutputStream(1024);

        DeflaterOutputStream compBytes = new DeflaterOutputStream(outBytes,
                scrunch);
        try {
            while (rowsLeft > 0) {
                nRows = Math.min(32767 / (this.width
                        * (this.bytesPerPixel + 1)), rowsLeft);
                nRows = Math.max(nRows, 1);

                int[] pixels = new int[this.width * nRows];

                pg = new PixelGrabber(this.image, 0, startRow,
                        this.width, nRows, pixels, 0, this.width);
                try {
                    pg.grabPixels();
                }
                catch (Exception e) {
                    System.err.println("interrupted waiting for pixels!");
                    return false;
                }
                if ((pg.getStatus() & ImageObserver.ABORT) != 0) {
                    System.err.println("image fetch aborted or errored");
                    return false;
                }

                /*
                 * Create a data chunk. scanLines adds "nRows" for
                 * the filter bytes.
                 */
                scanLines = new byte[this.width * nRows * this.bytesPerPixel
                                     + nRows];

                if (this.filter == FILTER_SUB) {
                    this.leftBytes = new byte[16];
                }
                if (this.filter == FILTER_UP) {
                    this.priorRow = new byte[this.width * this.bytesPerPixel];
                }

                scanPos = 0;
                startPos = 1;
                for (int i = 0; i < this.width * nRows; i++) {
                    if (i % this.width == 0) {
                        scanLines[scanPos++] = (byte) this.filter;
                        startPos = scanPos;
                    }
                    scanLines[scanPos++] = (byte) ((pixels[i] >> 16) & 0xff);
                    scanLines[scanPos++] = (byte) ((pixels[i] >>  8) & 0xff);
                    scanLines[scanPos++] = (byte) ((pixels[i]) & 0xff);
                    if (this.encodeAlpha) {
                        scanLines[scanPos++] = (byte) ((pixels[i] >> 24)
                                & 0xff);
                    }
                    if ((i % this.width == this.width - 1)
                            && (this.filter != FILTER_NONE)) {
                        if (this.filter == FILTER_SUB) {
                            filterSub(scanLines, startPos, this.width);
                        }
                        if (this.filter == FILTER_UP) {
                            filterUp(scanLines, startPos, this.width);
                        }
                    }
                }

                /*
                 * Write these lines to the output area
                 */
                compBytes.write(scanLines, 0, scanPos);

                startRow += nRows;
                rowsLeft -= nRows;
            }
            compBytes.close();

            /*
             * Write the compressed bytes
             */
            compressedLines = outBytes.toByteArray();
            nCompressed = compressedLines.length;

            this.crc.reset();
            this.bytePos = writeInt4(nCompressed, this.bytePos);
            this.bytePos = writeBytes(IDAT, this.bytePos);
            this.crc.update(IDAT);
            this.bytePos = writeBytes(compressedLines, nCompressed,
                    this.bytePos);
            this.crc.update(compressedLines, 0, nCompressed);

            this.crcValue = this.crc.getValue();
            this.bytePos = writeInt4((int) this.crcValue, this.bytePos);
            scrunch.finish();
            return true;
        }
        catch (IOException e) {
            System.err.println(e.toString());
            return false;
        }
    }

    protected void writeEnd() {
        this.bytePos = writeInt4(0, this.bytePos);
        this.bytePos = writeBytes(IEND, this.bytePos);
        this.crc.reset();
        this.crc.update(IEND);
        this.crcValue = this.crc.getValue();
        this.bytePos = writeInt4((int) this.crcValue, this.bytePos);
    }


    public void setXDpi(int xDpi) {
        this.xDpi = Math.round(xDpi / INCH_IN_METER_UNIT);

    }

    public int getXDpi() {
        return Math.round(xDpi * INCH_IN_METER_UNIT);
    }

    public void setYDpi(int yDpi) {
        this.yDpi = Math.round(yDpi / INCH_IN_METER_UNIT);
    }

    public int getYDpi() {
        return Math.round(yDpi * INCH_IN_METER_UNIT);
    }

    public void setDpi(int xDpi, int yDpi) {
        this.xDpi = Math.round(xDpi / INCH_IN_METER_UNIT);
        this.yDpi = Math.round(yDpi / INCH_IN_METER_UNIT);
    }

    protected void writeResolution() {
        if (xDpi > 0 && yDpi > 0) {

            final int startPos = bytePos = writeInt4(9, bytePos);
            bytePos = writeBytes(PHYS, bytePos);
            bytePos = writeInt4(xDpi, bytePos);
            bytePos = writeInt4(yDpi, bytePos);
            bytePos = writeByte(1, bytePos); // unit is the meter.

            crc.reset();
            crc.update(pngBytes, startPos, bytePos - startPos);
            crcValue = crc.getValue();
            bytePos = writeInt4((int) crcValue, bytePos);
        }
    }
}