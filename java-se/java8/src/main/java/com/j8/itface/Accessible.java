package com.j8.itface;

public interface Accessible {
	default void access() {
		System.out.println("access");
		test1();
	}

	default void print() {
		System.out.println("Accessible");
	}
	
	//接口可以定义静态方法
	static void test1() {
		System.out.println("===test1");
	}
}
