package com.j8.tmp;

public class User {
	
	private String name;
	private static String sex;
	private int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public static String getSex() {
		return sex;
	}
	public static void setSex(String sex) {
		User.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
}
