package com.j8.io.nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 文件复制
 * @author Administrator
 *
 */
public class FileCopyTest {
	
	public static void main(String[] args) {
		
		//java7复制文件
		Path source = Paths.get("F:/mu/github/java-se/java8/src/main/resources/com/j8/file1.txt"); 
		Path destination = Paths.get("F:/mu/github/java-se/java8/src/main/resources/com/j8/file2.txt");   
 
		try {
			Files.copy(source, destination);
			System.out.println("复制内容成功");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
