package com.j8.object;

/**
 * 域的隐藏。fiel的不能覆盖。
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
		System.out.println("---super.s:" + ((Super)sub).s);//这样子才能拿到父类的同名域

		Super sub2 = new Sub();
		Sub.g = "33";  
		System.out.println("----s:" + sub2.s); 
		System.out.println("---g:" + Sub.g);
		
		Super sub3 = new Sub2();
		System.out.println("----s:" + sub3.s);
		System.out.println("---g:" + Sub2.g);
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
