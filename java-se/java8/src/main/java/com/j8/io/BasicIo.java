package com.j8.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Scanner;

public class BasicIo {

	public static void main(String[] args) throws IOException {
		BasicIo basicIo = new BasicIo();

		// basicIo.copyByte();
		// basicIo.copyCharacters();

		// basicIo.copyLines();

//		basicIo.ScanXan();
		
		basicIo.ScanSum();
	}

	/**
	 * byte字节流复制。
	 * 
	 * @throws IOException
	 */
	public void copyByte() throws IOException {
		FileInputStream in = null;
		FileOutputStream out = null;

		try {
			in = new FileInputStream("file/xanadu.txt");
			out = new FileOutputStream("file/outagain.txt");
			int c; // hold 8bit
			while ((c = in.read()) != -1) {
				System.out.println("" + c);
				out.write(c);
			}
		} finally {
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
		}
	}

	/**
	 * 字符流复制。两个byte为单位。
	 * 
	 * @throws IOException
	 */
	public void copyCharacters() throws IOException {
		// FileReader inputStream = null;
		// FileWriter outputStream = null;

		BufferedReader inputStream = null;
		BufferedWriter outputStream = null;

		try {
			// 不缓存
			// inputStream = new FileReader("file/xanadu.txt");
			// outputStream = new FileWriter("file/characteroutput.txt");

			// 缓存
			inputStream = new BufferedReader(new FileReader("file/xanadu.txt"));
			outputStream = new BufferedWriter(new FileWriter(
					"file/characteroutput.txt"));
			int c; // hold 16bit
			while ((c = inputStream.read()) != -1) {
				System.out.println("----" + c);
				outputStream.write(c);
			}
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
			if (outputStream != null) {
				outputStream.close();
			}
		}
	}

	/**
	 * Line-Oriented I/O。 一行一行读取文本复制。"\n"换行
	 * 
	 * @throws IOException
	 */
	public void copyLines() throws IOException {
		BufferedReader inputStream = null;
		PrintWriter outputStream = null;

		try {
			inputStream = new BufferedReader(new FileReader("file/xanadu.txt"));
			outputStream = new PrintWriter(new FileWriter(
					"characteroutput2.txt"));

			String l;
			while ((l = inputStream.readLine()) != null) {
				System.out.println("==========" + l);
				// System.out.println("-------" + inputStream.re);
				outputStream.write(l);
			}
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
			if (outputStream != null) {
				outputStream.close();
			}
		}
	}

	public void ScanXan() {

		Scanner s = null;

		try {
			try {
				s = new Scanner(new BufferedReader(new FileReader(
						"file/xanadu.txt")));
				while (s.hasNext()) {
					System.out.println(s.next());
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} finally {
			if (s != null) {
				s.close();
			}
		}
	}
	
	/**
	 * 扫描数字.
	 */
	public void ScanSum() {
		Scanner s = null;
        double sum = 0;

        try {
            s = new Scanner(new BufferedReader(new FileReader("file/usnumbers.txt")));
            s.useLocale(Locale.US);

            while (s.hasNext()) {
                if (s.hasNextDouble()) {
                    sum += s.nextDouble();
				} else if (s.hasNextByte()) {
					System.out.println(s.nextByte());
				}
                else {
                    s.next();
                }   
            }
        } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            s.close();
        }

        System.out.println(sum);
	}

}
