package com.j8.tmp;

import java.util.Date;

public class Test1 {

	public static long sum(long a) {
		long sum = 0;
		for (long i = 0; i < a; i++) {
			sum += i;
		}
		return sum;

	}

	public static void main(String[] args) throws Exception {

		// String a = "学Java";
		// char[] b = a.toCharArray();
		// System.out.println(b.length);
		// byte[] c = a.getBytes();
		// String g = new String(c,"gbk");
		// System.out.println(c.length);
		// System.out.println(g.getBytes().length);
		//
		// char f = 'c';

		long t1 = System.currentTimeMillis();
		Date date1 = new Date(t1);
		System.out.println("---date1:" + date1);
		sum(9000000000l);
		long t2 = System.currentTimeMillis();
		Date date2 = new Date(t2);
		System.out.println("---date1:" + date2);
		System.out.println("-----time:" + (t2-t1));
	}

}
