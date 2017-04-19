package co.jufeng.barcode.gif;

import java.awt.Image;
import java.awt.image.PixelGrabber;
import java.io.IOException;

public class DirectGif89Frame extends Gif89Frame {

	private int[] argbPixels;

	public DirectGif89Frame(Image img) throws IOException {
		PixelGrabber pg = new PixelGrabber(img, 0, 0, -1, -1, true);

		String errmsg = null;
		try {
			if (!pg.grabPixels())
				errmsg = "can't grab pixels from image";
		} catch (InterruptedException e) {
			errmsg = "interrupted grabbing pixels from image";
		}

		if (errmsg != null)
			throw new IOException(errmsg + " (" + getClass().getName() + ")");

		theWidth = pg.getWidth();
		theHeight = pg.getHeight();
		argbPixels = (int[]) pg.getPixels();
		ciPixels = new byte[argbPixels.length];
	}

	public DirectGif89Frame(int width, int height, int argb_pixels[]) {
		theWidth = width;
		theHeight = height;
		argbPixels = new int[theWidth * theHeight];
		System.arraycopy(argb_pixels, 0, argbPixels, 0, argbPixels.length);
		ciPixels = new byte[argbPixels.length];
	}

	Object getPixelSource() {
		return argbPixels;
	}
}