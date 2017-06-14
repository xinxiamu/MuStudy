package com.j8.object;

/**
 * 域的隐藏
 * 
 * Within a class, a field that has the same name as a field in the superclass
 * hides the superclass’s field, even if their types are different. Within the
 * subclass, the field in the superclass cannot be referenced by its simple
 * name. Instead, the field must be accessed through super. Generally speaking,
 * we don’t recommend hiding fields as it makes code difficult to read.
 * 
 * @author Administrator
 *
 */
public class FieldShow {

	public static void main(String[] args) {
		Sub sub = new Sub();
		System.out.println("---s:" + sub.s);

		Super sub2 = new Sub();
		sub2.s="abcdef";
		System.out.println("----s:" + sub2.s);
		
		Super sub3 = new Sub2();
		System.out.println("----s:" + sub3.s);
	}
}

class Super {
	String s = "Super";
	static String g = "g";
}

class Sub extends Super {
	String s = "Sub";// 子类实际声明了一个新的域，没有覆盖父类的,父类的同名域隐藏
}

class Sub2 extends Super {
	String s = "Sub2";
}
