package com.j8.io;

import java.io.File;

/**
 * 创建文件
 * @author mutou
 *
 */
public class CreateFileExample {

	public static void main(String[] args) {
		try {
			File file = new File("/home/mutou/tmp/newFile.txt");
			if (file.createNewFile()) {
				System.out.println("创建文件成功");
			} else {
				System.out.println("创建文件失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
