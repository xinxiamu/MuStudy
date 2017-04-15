package com.j8.io;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * 管道流PipedInputStream和PipedOutputStream
 * 
 * @author mutou
 *
 */
public class PipeExample {

	public static void main(String[] args) throws IOException {
		final PipedOutputStream output = new PipedOutputStream();
		final PipedInputStream input = new PipedInputStream(output);

		// 管道输出流和管道输入流执行时不能互相阻塞,所以一般要开启独立线程分别执行
		Thread thread1 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					System.out.println("-----write");
					output.write("Hello world, pipe!".getBytes());
				} catch (IOException e) {
				} finally {
					try {
						output.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});

		Thread thread2 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					System.out.println("----read");
					int data = input.read();
					while (data != -1) {
						System.out.print((char) data);
						data = input.read();
					}
				} catch (IOException e) {
					
				} finally {
					try {
						input.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});

		thread1.start();
		thread2.start();

	}

}
