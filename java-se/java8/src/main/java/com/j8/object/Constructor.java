package com.j8.object;

public class Constructor {
	
	public static void main(String[] args) {
		Sub1 s = new Sub1();
	}
}

class Super1 {
	String s;

	public Super1() {
		System.out.println("Super");
	}
}

class Sub1 extends Super1 {
	//先执行父类构造函数
	public Sub1() {
		System.out.println("Sub");
	}

}
