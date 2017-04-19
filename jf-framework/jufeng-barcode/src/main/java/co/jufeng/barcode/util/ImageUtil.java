package co.jufeng.barcode.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import co.jufeng.barcode.PngEncoder;
import co.jufeng.barcode.gif.Gif89Encoder;

public class ImageUtil {
	
	public static final String JPEG = "jpeg";
	public static final String PNG = "png";
	public static final String GIF = "gif";
	
	public static final int DEFAULT_DPI = 96;
	
	public static byte[] encode(BufferedImage image, String format) throws IOException{
		return encode(image, format, 96, 96);
	}
	
	public static byte[] encode(BufferedImage image, String format, int xDpi, int yDpi) throws IOException{
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			encodeAndWrite(image, format, out, xDpi, yDpi);
			return out.toByteArray();
	}
	
	public static void encodeAndWrite(BufferedImage image, String format, OutputStream out) throws IOException{
		encodeAndWrite(image, format, out, DEFAULT_DPI, DEFAULT_DPI);
	}
	
	public static void encodeAndWrite(BufferedImage image, String format, OutputStream out, int xDpi, int yDpi) throws IOException{
		if(PNG.equals(format)){
			PngEncoder pngEncoder = new PngEncoder(image);
			pngEncoder.setDpi(xDpi, yDpi);
			out.write(pngEncoder.pngEncode());
		} else if(JPEG.equals(format)){
			ImageIO.write(image, format, out);
		} else if(GIF.equals(format)){
			Gif89Encoder gifenc = new Gif89Encoder();
			gifenc.addFrame(image);
			gifenc.setComments("JBarcode (http://jbarcode.ronisons.com)");
			gifenc.encode(out);
		}else {
			throw new IOException("Unsupported image type");
		}
	}

}
