package com.j8.erro;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * 学习异常处理的一些问题。
 * 
 * @author Administrator
 *
 */
public class ExceptionsShow {

	public static void main(String[] args) {
		// test2();
		// test3(null);
		System.out.println(test6());
	}
	
	/**
	 * 构造函数也可以抛出异常
	 * @throws Exception
	 */
	public ExceptionsShow() throws Exception {
		throw new NullPointerException();
	}

	// 在try块中定义的变量无法在catch中和finally中用。
	public static void test1() {
		/*
		 * try { File file = new File("path"); FileInputStream fis = new
		 * FileInputStream(file); String s = "inside"; } catch
		 * (FileNotFoundException e) { e.printStackTrace();
		 * System.out.println(s);//编译通不过 }
		 */
	}

	/**
	 * 两种抛出不同的异常类型。 出现这种问题，应该是jdk是由不同的开发者开发，没统一。
	 */
	public static void test2() {
		// Integer.parseInt(null);//抛出java.lang.NumberFormatException
		Double.parseDouble(null);// 抛出java.lang.NullPointerException
	}

	/**
	 * 使用运行时异常。
	 * 
	 * @param s
	 */
	public static void test3(String s) {
		if (s == null) {
			throw new IllegalArgumentException("s不能为null");
		}
	}

	/**
	 * 构造函数也能抛出异常。但是一般在构造函数不抛出，要直接捕捉并处理异常。
	 */
	public static void test4() {

	}

	/**
	 * 在finally中处理异常
	 */
	public static void test5() {
		File file1 = new File("path1");
		File file2 = new File("path2");
		try {
			FileInputStream fis = new FileInputStream(file1);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			methodThrowException(file2);// 在另外方法中展示，提高代码可读性。
		}
	}

	private static void methodThrowException(File file2) {
		try {
			FileInputStream fis = new FileInputStream(file2);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 在finally中使用return。可以作为面试题目。
	 * @return
	 */
	@SuppressWarnings("finally")
	public static int test6() {
		try {
//			int a = 1/0;
			int a = 1;
			return a + 1;//尽管这里return，但是依然会执行finally中的return，所以不会返回2，而是4
		} catch (Exception e) {
			System.out.println("----catch");
			e.printStackTrace();
		} finally {
			System.out.println("----finally");
			int b = 3;
			return b + 1;
		}
	}

}
