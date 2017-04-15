package com.j8.io;

import java.io.File;

/**
 * 文件删除
 * @author mutou
 *
 */
public class DeleteFileExample {

	public static void main(String[] args) {
		File file = new File(System.getProperty("user.dir"),"new.txt");
		try {
			if (file.delete()) {
				System.out.println("成功删除文件");
			} else {
				System.out.println("删除文件失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
