package com.j8.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * 为目录下的空文件夹添加文件。解决git不允许上传空文件夹的问题。
 * @author Administrator
 *
 */
public class DealEmptyFolder {

	public static void main(String[] args) {
		
		//E:/MuStudy/trunk/jf-framework/jufeng-accessor
		String PATH = "";
		System.out.print("Please input folder path :");
		Scanner scan = new Scanner(System.in);
		PATH = scan.nextLine(); // 读入文件路径

		File root = new File(PATH);

		DealEmptyFolder(root);
		System.out.println("End.");
	}

	// 遍历文件夹
	private static void DealEmptyFolder(File root) {
		// TODO Auto-generated method stub
		File[] fs = root.listFiles();
		for (int i = 0; i < fs.length; i++) {
			System.out.println(fs[i].getAbsolutePath());

			if (fs[i].isDirectory() && fs[i].listFiles().length > 0) {
				DealEmptyFolder(fs[i]);
			} else if (fs[i].isDirectory() && fs[i].listFiles().length == 0) {
				try {
					WriteTxt(fs[i].getAbsolutePath()); // 空文件夹写入
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	// 文件写入
	private static void WriteTxt(String absolutePath) throws IOException {
		// TODO Auto-generated method stub
		String str = "This folder is Empty."; // 写入内容
		File newFile = new File(absolutePath + "/readme.txt");
		if (!newFile.exists()) {
			newFile.createNewFile();

		}

		FileWriter fw = new FileWriter(newFile.getName(), true);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(str);
		bw.close();
	}

}
