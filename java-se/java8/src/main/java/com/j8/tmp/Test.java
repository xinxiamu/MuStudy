package com.j8.tmp;

public class Test {

	public static void main(String[] args) {
		User u1 = new User();
		User u2 = new User();
		
		u1.setName("李");
		u1.setAge(13);
		u1.setSex("男");
		
//		u2.setAge(99);
//		u2.setName("张");
		
		System.out.println("--------u1" + u1.getSex()) ;
		System.out.println("-------u2" + u2.getSex());
		u2.setSex("dddd");
		System.out.println("--------u1" + u1.getSex()) ;
		System.out.println("-------u2" + u2.getSex());
	}

}
