package com.j8.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 测试数组遍历。
 * 
 * @author Administrator
 *
 */
public class LoopTest {

	public static void main(String[] args) {

		List<String> list = Arrays.asList("a", "b", "c", "d", "e", "f");
		// 两种输出方式，各自优势并不明显
		list.forEach(s -> print(s)); // lambda表达式,按顺序输出
		System.out.println();
		for (String string : list) {
			System.out.print(string + ",");
		}
		// 用流输出
		System.out.println();
		list.stream().forEach(s -> print(s)); // 按顺序输出
		System.out.println();
		list.parallelStream().forEach(s -> print(s));// 不按顺序输出，并发的，提高遍历性能，在不要求顺序的场景中选用。
		System.out.println();
		
		//测试速度
//		List<String> list2 = new ArrayList<>();
//		for (int i = 0; i < 50000; i++) {  //改为10万再测试，并发遍历反而慢
//			list2.add("a" + i);
//		}
//		long a = System.currentTimeMillis();
//		list2.forEach(s -> System.out.print(s));
//		System.out.println();
//		System.out.println("diff:" + (System.currentTimeMillis() - a));
		
		//大数据量速度反而慢
//		System.out.println();
//		long b = System.currentTimeMillis();
//		list2.parallelStream().forEach(s -> System.out.print(s));
//		System.out.println();
//		System.out.println("diff2:" + (System.currentTimeMillis() - b));
	}
	
	public static void print(String s) {
		System.out.print(s + ",");
	}

}
