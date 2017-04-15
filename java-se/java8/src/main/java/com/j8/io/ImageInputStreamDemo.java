package com.j8.io;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

/**
 * 图片裁剪。
 * 
 * @author mutou
 *
 */
public class ImageInputStreamDemo {

	public static void main(String[] args) {
		cutImage("/home/mutou/Pictures/maotouying.jpg",
				"/home/mutou/Pictures/maotouying_new.jpg", 800, 800, 800, 600);
	}

	/**
	 * 图片裁剪。
	 * 
	 * @param path1
	 *            图片原路径
	 * @param path2
	 *            裁剪后存储的路径
	 * @param x
	 *            x轴
	 * @param y
	 *            y轴
	 * @param w
	 * @param h
	 */
	public static void cutImage(String path1, String path2, int x, int y,
			int w, int h) {
		FileInputStream fileInputStream = null;
		ImageInputStream iis = null;

		try {
			// 读取图片文件，建立文件输入流
			fileInputStream = new FileInputStream(path1);
			// 创建图片的文件流 迭代器
			Iterator<ImageReader> it = ImageIO
					.getImageReadersByFormatName("jpg");
			ImageReader reader = it.next();
			// 获取图片流 建立文图 文件流
			iis = ImageIO.createImageInputStream(fileInputStream);
			// 获取图片默认参数
			reader.setInput(iis, true);
			ImageReadParam param = reader.getDefaultReadParam();
			// 定义裁剪区域
			Rectangle rect = new Rectangle(x, y, w, h);
			param.setSourceRegion(rect);
			BufferedImage bi = reader.read(0, param);
			ImageIO.write(bi, "jpg", new File(path2));
			System.out.println("----裁剪成功");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("裁剪失败");
		} finally {
			try {
				if (fileInputStream != null) {
					fileInputStream.close();
				}
				if (iis != null) {
					iis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
}
