package com.j8.string;

/**
 * 验证字符串的不可变长。String是final类型
 * @author Administrator
 *
 */
public class ImmutabilityStr {

	public static void main(String[] args) {
		// 声明字符串,在堆内存中是不可变的
		String s = "abcd";

		// 赋值给另外一个字符串。s2执行s，是同一字符串对象。
		String s2 = s;
		System.out.println(s == s2);// 同一个对象，在内存中是一份。

		// 拼接字符串，生产另外的字符串对象。
		String s3 = s.concat("ef");
		System.out.println(s == s3);
		
		//-------------- Summary

		/*
		 * Once a string is created in memory(heap), it can not be changed. We
		 * should note that all methods of String do not change the string
		 * itself, but rather return a new String.
		 */
	}

}
