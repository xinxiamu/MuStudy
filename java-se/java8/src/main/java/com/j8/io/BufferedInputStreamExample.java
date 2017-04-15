package com.j8.io;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 缓冲读取文件数据到程序
 * @author mutou
 *
 */
public class BufferedInputStreamExample {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		File file = new File("/home/mutou/tmp/newFile.txt");
		
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		DataInputStream dis = null;
		
		try {
			fis = new FileInputStream(file);
			
			bis = new BufferedInputStream(fis);
			dis = new DataInputStream(bis);
			
			while (dis.available() != 0) {
				System.out.println(dis.readLine());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
				bis.close();
				dis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
