package com.j8.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 往文件写内容。
 * @author mutou
 * @version jdk7
 *
 */
public class WriteFileExample {

	public static void main(String[] args) {
		fileWriter();
	}
	
	public static void fileOutputStram() {
		File file = new File(System.getProperty("user.dir"),"new.txt");
		String content = "小鱼儿在水里游呀，游，多自由！";
		try(FileOutputStream fop = new FileOutputStream(file)) {
			//字符串转换成byte
			byte[] b = content.getBytes();
			
			fop.write(b);
			fop.flush();
			fop.close();
			
			System.out.println("---写入文件成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void fileWriter() {
		try {

			String content = "This is the content to write into file";

			File file = new File(System.getProperty("user.dir"),"a.txt");

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();

			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
