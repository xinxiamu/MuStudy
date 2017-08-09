package com.j8.lambda;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * lambda常用语法。
 * 
 * @author Administrator
 *
 */
public class LambdaSyntax {

	public static void main(String[] args) {
		// 1. Standard Syntax
		String[] arr = { "program", "creek", "is", "a", "java", "site" };
		Arrays.sort(arr, (String m, String n) -> Integer.compare(m.length(), n.length()));
		System.out.println(Arrays.toString(arr));

		// 2、Parameter Type Can be Inferred 参数可推断
		String[] arr1 = { "program", "creek", "is", "a", "java", "site" };
		Arrays.sort(arr1, (m, n) -> Integer.compare(m.length(), n.length()));
		System.out.println(Arrays.toString(arr1));
		
		//3、Multiple Lines of Code in Lambda Expression 表达式体多行
		String[] arr3 = { "program", "creek", "is", "a", "java", "site" };
		Arrays.sort(arr3, (String m, String n) -> {
			if (m.length() > n.length()) {
				return -1;
			} else {
				return 0;
			}
		});
		System.out.println(Arrays.toString(arr3));
		
		//4. Single Parameter with Inferred Type
		System.out.println("4. Single Parameter with Inferred Type");
		String[] arr4 = { "program", "creek", "is", "a", "java", "site" };
		Stream<String> stream = Stream.of(arr4);
		stream.forEach(x -> System.out.println(x));
		
		//5. Method References
		System.out.println("5. Method References");
		Stream<String> stream1 = Stream.of(arr4);
		stream1.forEach(System.out::println);
		
		//6. No Parameter
//		() -> {for(int i=0; i<10; i++) doSomthing();}
	}

}
