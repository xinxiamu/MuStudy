package com.j8.string;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * 字符串值传递演示
 * 注意和对象传递的区别。
 * @author Administrator
 *
 */
public class StrPassByValue {

	public static void main(String[] args) {
		//--------------- 基本类型复值传递 -----------
		String x = new String("ab");
		change(x);// 复值传递，复制一份值过去，不是指向相同对象。
		System.out.println(x);// 仍然输出ab
		
		int a = 3;
		changeInt(a);
		System.out.println(a);
		

		// String x = "ab";
		// change(x);
		// System.out.println(x);//仍然输出ab
		
		//---------------- 对象是地址传递 ----------------//

		//如果参数是对象，则是指针传递，指向同一个对象
		List<String> list = new ArrayList<>();
		list.add("ab");
		list.add("cd");
		list(list);
		System.out.println(list.toString());
		
		User user = new User();
		user.setUserName("木头");
		user.setAge(18);
		objecPar(user);
		user.toString();
	}

	public static void change(String x) {
//		System.out.println(x);
		x = "cd";
//		System.out.println(x);
	}
	
	public static void changeInt(int a) {
		a = a + 3;
	}

	public static void list(List<String> list) {
		System.out.println(list);
		list.set(0, "cd");
	}
	
	public static void objecPar(User user) {
		user.setUserName("新名字");
	}
	
	static class User {
		private String userName;
		private int age;
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		
		@Override
		public String toString() {
			String s = JSONObject.toJSONString(this);
			System.out.println("user:" + s);
			return s;
		}
	}

}
