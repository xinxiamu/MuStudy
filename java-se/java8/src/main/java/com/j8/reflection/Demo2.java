package com.j8.reflection;

import com.j8.annotations.AdapterLayout;


public class Demo2 {

	public static void main(String[] args) {
		Demo2 demo2 = new Demo2();
		UserVo userVo = new UserVo();
		try {
			demo2.getFields(userVo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@SuppressWarnings("unchecked")
	public void getFields(Object object) throws Exception {
		Class class1 = object.getClass();
		AdapterLayout a = (AdapterLayout) class1.getAnnotation(AdapterLayout.class);
		System.out.println( a.layoutValue());
	}

}
