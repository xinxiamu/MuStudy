package co.jufeng.barcode.gif;

public class IndexGif89Frame extends Gif89Frame {

	public IndexGif89Frame(int width, int height, byte ci_pixels[]) {
		theWidth = width;
		theHeight = height;
		ciPixels = new byte[theWidth * theHeight];
		System.arraycopy(ci_pixels, 0, ciPixels, 0, ciPixels.length);
	}

	Object getPixelSource() {
		return ciPixels;
	}
}