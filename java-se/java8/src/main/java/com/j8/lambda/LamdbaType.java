package com.j8.lambda;

import java.util.function.BinaryOperator;

//辨别Lamdba表达式
public class LamdbaType {

	public static void main(String[] args) {

		// 类型一,没有参数
		Runnable noArguments = () -> System.out.println("hellow world");
		noArguments.run();

		// 类型二，代码块
		Runnable multiStatment = () -> {
			System.out.println("---hello");
			System.out.println("----world");
		};
		multiStatment.run();

		// 类型三，有一个参数，一个参数可以省掉扩号
		OneArgument oneArgument = (x) -> System.out.println("---" + x);
		oneArgument.show("dsfdsf");

		// 类型四，多个参数
		BinaryOperator<Long> add = (x, y) -> x + y;
		Long a = add.apply(1L, 2L);
		System.out.println("---a=" + a);

		// 类型五，显式申明参数
		BinaryOperator<Long> addExplict = (Long x, Long y) -> x + y;
		Long b = addExplict.apply(1L, 2L);
		System.out.println("---b=" + b);
	}

	@FunctionalInterface
	public interface OneArgument {

		public void show(Object x);
	}

}