package com.j8.stream;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GoodsUse {

	public static void main(String[] args) {
//		convertStreamToArray();
		java8Counter();
	}

	public static void java8Counter() {
		String[] arr = { "program", "creek", "program", "creek", "java", "web", "program" };
		Stream<String> stream = Stream.of(arr).parallel();
		Map<String, Long> counter = stream.collect(Collectors.groupingBy(String::toString, Collectors.counting()));
		System.out.println(counter.get("creek"));
	}
	
	public static void convertStreamToArray() {
		String[] stringArr = { "a", "b", "c", "d" };
		Stream<String> stream = Stream.of(stringArr);
		String[] arr = stream.toArray(size -> new String[size]);
//		String[] arr = stream.toArray(String[]:: new);
		System.out.println(Arrays.toString(arr));
	}
	
	public static void mergeStreams1() {
		String[] arr1 = { "a", "b", "c", "d" };
		String[] arr2 = { "e", "f", "g" };
		Stream<String> stream1 = Stream.of(arr1);
		Stream<String> stream2 = Stream.of(arr2);
		 
		Stream<String> stream3 = Stream.concat(stream1, stream2);
		String[] arr = stream3.toArray(String[]::new);
		System.out.println(Arrays.toString(arr));
		
	}
	
	public static void mergeStreams2() {
		String[] arr1 = { "abc", "bcd", "cdef", "defgh" };
		String[] arr2 = { "af", "fg", "gh" };
		Stream<String> stream1 = Stream.of(arr1);
		Stream<String> stream2 = Stream.of(arr2);
		 
		Stream<String> stream3 = Stream.concat(stream1.filter(x -> x.length()<4), stream2);
		String[] arr = stream3.toArray(String[]::new);
		System.out.println(Arrays.toString(arr));
	}
	
	//Stream.concat(Stream.concat(stream1, stream2), stream3); //[a, b, c, d, e, f, g, h, i, j]
	//Stream.concat(Stream.concat(stream2, stream1), stream3); //[e, f, g, a, b, c, d, h, i, j]
	public static void mergeStreams3() {
		String[] arr1 = { "a", "b", "c", "d" };
		String[] arr2 = { "e", "f", "g" };
		String[] arr3 = { "h", "i", "j" };
		Stream<String> stream1 = Stream.of(arr1);
		Stream<String> stream2 = Stream.of(arr2);
		Stream<String> stream3 = Stream.of(arr3);
		 
		Stream<String> stream = Stream.concat(Stream.concat(stream1, stream2), stream3);
		String[] arr = stream.toArray(String[]::new);
		System.out.println(Arrays.toString(arr));
	}
}
