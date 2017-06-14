package com.j8.string;

/**
 * 创建字符串的方法比较。
 * 结论：
 * 通常，使用String x = "abc";方式创建字符串。除非必要，否则不new，避免创建没必要object消耗内存。
 * @author Administrator
 *
 */
public class StrCreate {

	public static void main(String[] args) {
		//创建字符串对象的两种方式
		String x = "abc";
		String y = new String("abc");
		System.out.println(x == y);//不同的对象，false
		System.out.println(x.equals(y));//字符内容相同,true
		
		//同一个对象，指向同一个method area
		String a = "abcd";
		String b = "abcd";
		System.out.println(a == b);  // True
		System.out.println(a.equals(b)); // True
		
		//在Heap
		String c = new String("abcd");
		String d = new String("abcd");
		System.out.println(c == d);  // False,不同的对象
		System.out.println(c.equals(d)); // True，内容相同
		
		String e = new String("abcd").intern();
		String f = new String("abcd").intern();
		System.out.println(e == f);  // Now true
		System.out.println(e.equals(f)); // True
	}

}
