package com.j8.io.nio;

import java.nio.Buffer;
import java.nio.CharBuffer;

/**
 * 缓冲区的填充与释放
 * @author Administrator
 *
 */
public class BufferFillDrain {

	public static void main(String[] args) {Buffer
		CharBuffer buffer = CharBuffer.allocate(100); //分配新的缓冲区,容量100
		while (fillBuffer(buffer)) {
			buffer.flip();
			drainBuffer(buffer);
			buffer.clear();
		}

	}

	private static void drainBuffer(CharBuffer buffer) {
		while (buffer.hasRemaining()) {
			System.out.print(buffer.get());
		}
		System.out.println("");
	}

	/**
	 * 填充缓冲区
	 */
	private static boolean fillBuffer(CharBuffer buffer) {
		if (index >= strings.length) {
			return (false);
		}
		String string = strings[index++];
		for (int i = 0; i < string.length(); i++) {
			buffer.put(string.charAt(i));
		}
		return (true);
	}

	private static int index = 0;

	private static String[] strings = { "A random string value", "The product of an infinite number of monkeys",
			"Hey hey we're the Monkees", "Opening act for the Monkees: Jimi Hendrix", "'Scuse me while I kiss this fly",
			"Help Me! Help Me!", };

}
