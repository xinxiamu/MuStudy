package com.j8.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

public class BeanUtils {

	public static void main(String[] args) throws IntrospectionException {
		A  a = new A();
		a.setAge(18);
		a.setName("mumu");
		BeanInfo aInfo = Introspector.getBeanInfo(a.getClass());
		PropertyDescriptor[] aPropertys = aInfo.getPropertyDescriptors();
		for (PropertyDescriptor propertyDescriptor : aPropertys) {
			System.out.println(propertyDescriptor.getName());
			Class<?> pType = propertyDescriptor.getPropertyType(); 
			System.out.println(pType.getSimpleName());
			Method readM = propertyDescriptor.getReadMethod();
//			readM.in
		}
	}

	static class A {

		private String name;
		private int age;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public A(String name, int age) {
			super();
			this.name = name;
			this.age = age;
		}
		
		public A() {
		}

	}

	static class B extends A {
		private boolean man;

		public boolean isMan() {
			return man;
		}

		public void setMan(boolean man) {
			this.man = man;
		}

		public B(String name, int age, boolean man) {
			super(name, age);
			this.man = man;
		}
		
		public B() {
			
		}

	}
}
