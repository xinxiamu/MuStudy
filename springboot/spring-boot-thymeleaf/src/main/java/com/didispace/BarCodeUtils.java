package com.didispace;

import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;
import org.springframework.util.StringUtils;

import java.awt.image.BufferedImage;
import java.io.*;

/**
 * 条形码处理。
 * 
 * @author Administrator
 *
 */
public class BarCodeUtils {

	private BarCodeUtils() {
	}

	public static void main(String[] args) {
		String msg = "1ztBc123456789";
//        String path = "barcode.png";
//        generateFile(msg, path,100);

        byte[] bs = generate(msg,100);
        String str = Base64Utils.base64Encode(bs);
        System.out.println(str);
	}

	/**
	 * 生成条形码文件
	 *
	 * @param msg
	 * @param path
	 * @return
	 */
	public static File generateFile(String msg, String path,int dpi) {
		File file = new File(path);
		try {
			generate(msg, new FileOutputStream(file),dpi);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		return file;
	}

	/**
	 * 生成字节
	 *
	 * @param msg
	 * @return
	 */
	public static byte[] generate(String msg,int dpi) {
		ByteArrayOutputStream ous = new ByteArrayOutputStream();
		generate(msg, ous,dpi);
		return ous.toByteArray();
	}

	/**
	 * 生成到流
	 *
	 * @param msg
	 * @param ous
	 * @param dpi 精细度
	 */
	public static void generate(String msg, OutputStream ous,final int dpi) {
		if (StringUtils.isEmpty(msg) || ous == null) {
			return;
		}

		Code39Bean bean = new Code39Bean();

		// module宽度
		final double moduleWidth = UnitConv.in2mm(1.0f / dpi);

		// 配置对象
		bean.setModuleWidth(moduleWidth);
		bean.setWideFactor(3);
		bean.doQuietZone(false);

		String format = "image/png";
		try {

			// 输出到流
			BitmapCanvasProvider canvas = new BitmapCanvasProvider(ous, format, dpi, BufferedImage.TYPE_BYTE_BINARY,
					false, 0);

			// 生成条形码
			bean.generateBarcode(canvas, msg);

			// 结束绘制
			canvas.finish();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}


}
