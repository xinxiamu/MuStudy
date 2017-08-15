package com.j8.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

public class BeanUtils {

	public static void main(String[] args) throws Exception {
		A  a = new A();
		a.setAge(18);
		a.setName("mumu");
		
		B  b = new B();
		b.setAge(18);
		b.setName("mumu");
		b.setMan(true);
		
		BeanInfo aInfo = Introspector.getBeanInfo(b.getClass());
		PropertyDescriptor[] aPropertys = aInfo.getPropertyDescriptors();
		for (PropertyDescriptor propertyDescriptor : aPropertys) {
			if ("class".equals(propertyDescriptor.getName())) {
				continue;
			}
			
			System.out.println(propertyDescriptor.getName());
			
			Class<?> pType = propertyDescriptor.getPropertyType(); 
			System.out.println(pType.getSimpleName());
			
			Method readM = propertyDescriptor.getReadMethod();
			System.out.println("===readM:" + readM.getName());
			getProperty(b,propertyDescriptor.getName());
			
			Method writeM = propertyDescriptor.getWriteMethod();
			System.out.println("===writeM:" + writeM.getName());
		}
	}
	
	 private static void setProperty(B a, String proName) throws Exception {     
	       PropertyDescriptor proDescriptor = new PropertyDescriptor(proName, B.class);     
	       Method methodSetX = proDescriptor.getWriteMethod();     
	       methodSetX.invoke(a, 8);     
	       System.out.println(a.getAge());// 8     
	   }  
	 
	 private static void getProperty(B a, String proName) throws Exception {     
	       PropertyDescriptor proDescriptor = new PropertyDescriptor(proName, B.class);     
	       Method methodGetX = proDescriptor.getReadMethod();     
	       Object objx = methodGetX.invoke(a);     
	       System.out.println(objx);// 2     
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
