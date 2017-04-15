package com.j8.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.j8.annotations.web.Param;

public class Demo1 {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		Demo1 demo1 = new Demo1();
//
		UserVo userVo = new UserVo();
		 userVo.setPassword("a123");
		 userVo.setUserName("木木");
		 userVo.setYear(88);
		 userVo.setMutou(true);
		try {
			demo1.getFields(userVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Method[] m = Demo1.class.getDeclaredMethods();
		
		Annotation[][] an = null;
		for (Method method : m) {
			
//			Parameter[] aaa = method.getParameters();	//version-1.8
//			for (Parameter parameter : aaa) {
//				Param dd = parameter.getAnnotation(Param.class);
//				if (dd == null) {
//					continue;
//				}
//				System.out.println(dd.value() + "====" + dd.required() + "===" + dd.defaultValue());
//			}
			
//			if (method.getName().equals("test1")) {
//				an = method.getParameterAnnotations();
//				if (an.length > 0) {
//					System.out.println(an.length);
//					for (int i = 0; i < an.length; i++) {
//						Annotation[] ann = an[i];
//						if (ann.length == 0) {
//							continue;
//						}
//						for (int j = 0; j < ann.length; j++) {
//							Param t = (Param) an[i][j];
//							System.out.println(method.getName() + ":"
//									+ t.value() + "," + t.required() + ","
//									+ t.defaultValue());
//							
//							
//						}
//					}
//				}
//				break;
//			}
			
			//--------

		}
	}

	public void test1(
			@Param(value = "aa", required = true, defaultValue = "iiiii") int a,
			@Param(value="fff",required=false,defaultValue="") String kk,double d) {

	}

	public void test2(
			@Param(value = "bb", required = true, defaultValue = "") String b,UserVo userVo) {

	}

	public void test3(
			@Param(value = "cc", required = true, defaultValue = "") double c) {

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void getFields(Object object) throws Exception {
		Class class1 = object.getClass();

		Field[] fiels = class1.getDeclaredFields();
		for (Field field : fiels) {
			String fildName = field.getName();
			System.out.print("fieldName:" + fildName + "----");
			
			// 注解
			Param param = field.getAnnotation(Param.class);
			String paramValue = param.value();
			boolean paramRe = param.required();
			String paramDefault = param.defaultValue();
			System.out.println(paramValue + "==" + paramRe + "=="
					+ paramDefault);

			String methodName = null;
			if (fildName.charAt(0) == 'i') {
				methodName = fildName;
			} else {
				methodName = "get" + fildName.substring(0, 1).toUpperCase()
						+ fildName.substring(1, fildName.length());
			}
			Method method = class1.getMethod(methodName);
			Object object2 = method.invoke(object);
			System.out.println(object2);

			if (paramRe) {

			}
			// System.out.println(paramValue=);
		}
	}

}
