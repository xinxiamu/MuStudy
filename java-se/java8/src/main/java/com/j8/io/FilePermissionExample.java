package com.j8.io;

import java.io.File;

/**
 * 文件权限许可
 * 
 * @author mutou
 *
 */
public class FilePermissionExample {

	public static void main(String[] args) {
		try {
			File file = new File(System.getProperty("user.dir"), "new.sh");

			if (file.exists()) {
				System.out.println("Is Execute allow : " + file.canExecute());
				System.out.println("Is Write allow : " + file.canWrite());
				System.out.println("Is Read allow : " + file.canRead());
			}

			file.setExecutable(false);
			file.setReadable(false);
			file.setWritable(false);

			System.out.println("Is Execute allow : " + file.canExecute());
			System.out.println("Is Write allow : " + file.canWrite());
			System.out.println("Is Read allow : " + file.canRead());

			if (file.createNewFile()) {
				System.out.println("---创建文件成功");
			} else {
				System.out.println("----文件已经存在");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
