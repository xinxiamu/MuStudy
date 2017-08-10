package com.j8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class SimpleStream {

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("java");
		list.add("php");
		list.add("python");
		list.add("lisp");
		list.add("c++");
 
		//filter function
		Stream<String> stream = list.stream().filter(p -> p.length() > 3);
		String[] arr = stream.toArray(String[]::new);
 
		System.out.println("------------数据源：");
		System.out.println(list.toString());
		System.out.println("-----------stram处理后：");
		System.out.println(Arrays.toString(arr));
		System.out.println("------------stream没有改变数据源："); 
		System.out.println(list.toString());
	}

}
