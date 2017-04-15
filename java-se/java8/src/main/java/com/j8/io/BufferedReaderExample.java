package com.j8.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 缓冲读取文件数据到程序,推荐该方式
 * 
 * @author mutou
 * @version jdk7
 */
public class BufferedReaderExample {

	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new FileReader(
				"/home/mutou/tmp/newFile.txt"))) {

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
