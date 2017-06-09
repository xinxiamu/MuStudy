package com.j8.collection;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * 如何有效检查数组中是否包含某个值。
 * 验证结果：
 * 通常情况下方式三use loop最佳。
 * 但是如果数组非常大，且数组在排序情况下，方式四二分法查找最佳。
 * 
 * 所以：
 * 一般选择方式三
 * 
 * @author Administrator
 *
 */
public class CheckInArray {

	public static void main(String[] args) {
		
		//用不同的量级大数组测试区别性能
		String[] arr = new String[100000];
		Random s = new Random();
		for (int i = 0; i < 100000; i++) {
			arr[i] = String.valueOf(s.nextInt());
		}

//		String[] arr = new String[] { "CD", "BC", "EF", "DE", "AB" };

		// use list
		long startTime = System.nanoTime();
		for (int i = 0; i < 100000; i++) {
			useList(arr, "A");
		}
		long endTime = System.nanoTime();
		long duration = endTime - startTime;
		System.out.println("useList: " + duration / 1000000);

		// use set
		startTime = System.nanoTime();
		for (int i = 0; i < 100000; i++) {
			useSet(arr, "A");
		}
		endTime = System.nanoTime();
		duration = endTime - startTime;
		System.out.println("useSet: " + duration / 1000000);

		// use loop
		startTime = System.nanoTime();
		for (int i = 0; i < 100000; i++) {
			useLoop(arr, "A");
		}
		endTime = System.nanoTime();
		duration = endTime - startTime;
		System.out.println("useLoop: " + duration / 1000000);

		// use Arrays.binarySearch()
		startTime = System.nanoTime();
		for (int i = 0; i < 100000; i++) {
			useArraysBinarySearch(arr, "A");
		}
		endTime = System.nanoTime();
		duration = endTime - startTime;
		System.out.println("useArrayBinary: " + duration / 1000000);
	}

	// 方式一
	// Using List:
	public static boolean useList(String[] arr, String targetValue) {
		return Arrays.asList(arr).contains(targetValue);
	}

	// 方式二
	// Using Set:
	public static boolean useSet(String[] arr, String targetValue) {
		Set<String> set = new HashSet<String>(Arrays.asList(arr));
		return set.contains(targetValue);
	}

	// 方式三
	// Using a simple loop:
	public static boolean useLoop(String[] arr, String targetValue) {
		for (String s : arr) {
			if (s.equals(targetValue))
				return true;
		}
		return false;
	}

	// 方式四,二分查找,数组排序情况下才能用
	// Using Arrays.binarySearch(): * The code below is wrong, it is listed here
	// for
	// completeness. binarySearch() can ONLY be used on sorted arrays. You will
	// see the
	// result is weird when running the code below.
	public static boolean useArraysBinarySearch(String[] arr, String targetValue) {
		int a = Arrays.binarySearch(arr, targetValue);
		if (a > 0)
			return true;
		else
			return false;
	}

}
