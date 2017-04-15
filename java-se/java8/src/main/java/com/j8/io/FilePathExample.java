package com.j8.io;

import java.io.File;
import java.io.IOException;

/**
 * 正确构造文件路径
 * 
 * @author mutou
 *
 */
public class FilePathExample {

	public static void main(String[] args) throws IOException {
		String filename = "newFile11.txt";
		String workingDirectory = System.getProperty("user.dir");	//文件目录	
		
		//****************//
		
		String absoluteFilePath = "";	//文件绝对路径
		// absoluteFilePath = workingDirectory + System.getProperty("file.separator") + filename;
		absoluteFilePath = workingDirectory + File.separator + filename;
		
		System.out.println("Final filepath : " + absoluteFilePath);
		
		//****************//
		
		//创建文件
//		File file = new File(absoluteFilePath);
		File file = new File(workingDirectory, filename); 

		if (file.createNewFile()) {
			System.out.println("File is created!");
		} else {
			System.out.println("File is already existed!");
		}
	}

}
