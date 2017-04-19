package co.jufeng.util;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import co.jufeng.util.encrypt.Key;

public class MD5EncryptUtil {

	public static String getMd5(String string) {
		byte[] input = string.getBytes();
		String output = null;
		// 声明16进制字母
		char[] hexChar = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			// 获得一个MD5摘要算法的对象
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(input);
			/*
			 * MD5算法的结果是128位一个整数,在这里javaAPI已经把结果转换成字节数组了
			 */
			byte[] tmp = md.digest();// 获得MD5的摘要结果
			char[] str = new char[32];
			byte b = 0;
			for (int i = 0; i < 16; i++) {
				b = tmp[i];
				str[2 * i] = hexChar[b >>> 4 & 0xf];// 取每一个字节的低四位换成16进制字母
				str[2 * i + 1] = hexChar[b & 0xf];// 取每一个字节的高四位换成16进制字母
			}
			output = new String(str);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
//		return output.toUpperCase();
		return output;
	}

	public static String getMd5KL(String string) {
		String md5 = getMd5(string);
		return getKL(md5);
	}
	
	public static String getKL(String string) {
		if(string.length() < Key.AES_KEY.length()){
			string += Key.AES_KEY;
		}
		char[] a = string.toCharArray();
		for (int i = 0; i < a.length; i++) {
			a[i] = (char) (a[i] ^ 's');
		}
		String s = new String(a);
		return s;
	}

	public static String getDecrypt(String kl) {
		char[] a = kl.toCharArray();
		for (int i = 0; i < a.length; i++) {
			a[i] = (char) (a[i] ^ 's');
		}
		String k = new String(a);
		if(kl.length() > 32){
			k = k.replace(Key.AES_KEY, "");
		}
		return k;
	}
	
	public static void main(String[] args) {
		String md5kl = getMd5KL("a123456");//GK@KCDCJDBKJD@ED@JAG
										   //GK@KCDCJDBKJD@ED@JAG
		System.out.println(md5kl);
		String md5 = getMd5("123456");
		System.out.println(md5);
		System.out.println(md5.length());
		System.out.println(getKL("a1234567@!"));
		System.out.println(md5kl.length());
		
		String decryptMd5 = getDecrypt(md5kl);
		System.out.println(decryptMd5);
		System.out.println(decryptMd5.length());
		System.out.println("===========================================================================================================");
		
		String str = "test@fangchou";
		System.out.println(str);
		String kl = getKL(str);
		System.out.println(kl);
		System.out.println(kl.length());
		
		String de = getDecrypt(kl);
		System.out.println(de);
		System.out.println(de.length());
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
