package com.j8.object;

public class FieldShow {

	public static void main(String[] args) {
		Sub sub = new Sub();
		System.out.println("---s:" + sub.s);
		
		Super sub2 = new Sub();
		System.out.println("----s:" + sub2.s);
	}
}

class Super {
	String s = "Super";
}

class Sub extends Super {
	String s = "Sub";
}
