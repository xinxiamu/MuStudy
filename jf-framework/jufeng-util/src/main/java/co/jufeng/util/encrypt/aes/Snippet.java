package co.jufeng.util.encrypt.aes;

import co.jufeng.util.encrypt.Key;

//http://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html
public class Snippet {
	
	public static void main(String[] args) {
		AES aes = new AES(Key.DES_KEY);
		String string = aes.encode("123456");
		System.out.println(string);
		System.out.println(aes.decode(string));
	}
	
}
