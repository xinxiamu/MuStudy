package com.j8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReduceStream {

	public static void main(String[] args) {
//		reduceTest3();
		collectTest3();
	}
	
	public static void reduceTest() {
		List<String> list = new ArrayList<String>();
		list.add("java");
		list.add("php");
		list.add("python");
		list.add("perl");
		list.add("c");
		list.add("lisp");
		list.add("c#");
		Stream<String> wordStream = list.stream();
		int s = wordStream.map(p-> p.length()).mapToInt(Integer::new).sum();
		System.out.println("===reduceTest:" + s);
	}
	
	public static void reduceTest2() {
		List<String> list = new ArrayList<String>();
		list.add("java");
		list.add("php");
		list.add("python");
		list.add("perl");
		list.add("c");
		list.add("lisp");
		list.add("c#");
		
		Stream<String> wordStream = list.stream();
		Stream<Integer> lengthStream = wordStream.map(s -> s.length());
		Optional<Integer> sum = lengthStream.reduce((x, y) -> x + y);
		sum.ifPresent(System.out::println);
	}
	
	public static void reduceTest3() {
		List<String> list = new ArrayList<String>();
		list.add("java");
		list.add("php");
		list.add("python");
		list.add("perl");
		list.add("c");
		list.add("lisp");
		list.add("c#");
		
		//Provide Identify Value
		Stream<String> wordStream = list.stream();
		Stream<Integer> lengthStream = wordStream.map(s -> s.length());
		int sum = lengthStream.reduce(0, (x, y) -> x + y);
		System.out.println(sum);
	}
	
	public static void collectTest() {
		List<String> list = new ArrayList<String>();
		list.add("java");
		list.add("php");
		list.add("python");
		Stream<String> wordStream = list.stream();
		 
		// Collect Stream Results to Array
		Stream<Integer> lengthStream = wordStream.map(s -> s.length());
		//Note that toArray() is a terminal operation. After toArray() is invoked, the stream is not available to use anymore.
		//当stream执行终端操作后将不再可用，所以不能再做流操作
		Integer[] lenArr = lengthStream.toArray(Integer[]::new);
		System.out.println(Arrays.toString(lenArr));
		
		//Collect Stream Results to List/Set
		//stream已经做终端转换
//		List<Integer> intList= lengthStream.collect(Collectors.toList());
//		Set<Integer> intSet= lengthStream.collect(Collectors.toSet());
//		System.out.println(intList);
//		System.out.println(intSet);
	}
	
	public static void collectTest2() {
		List<String> list = new ArrayList<String>();
		list.add("java");
		list.add("php");
		list.add("python");
		Stream<String> wordStream = list.stream();
		 
		Stream<Integer> lengthStream = wordStream.map(s -> s.length());
		
		//Collect Stream Results to List/Set
//		List<Integer> intList= lengthStream.collect(Collectors.toList());
//		System.out.println(intList);
		Set<Integer> intSet= lengthStream.collect(Collectors.toSet());
//		TreeSet<Integer> intSet= lengthStream.collect(Collectors.toCollection(TreeSet::new));
		System.out.println(intSet);
		
	}
	
	public static void collectTest3() {
		List<String> list = new ArrayList<String>();
		list.add("java");
		list.add("php");
		list.add("python");
		Stream<String> wordStream = list.stream();
		 
		// to map
		Map<String, Integer> map = wordStream.collect(Collectors.toMap(Function.identity(), s->s.length()));
		System.out.println(map);
		
	}
}
