package com.j8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

public class TestDemo {
	
	/**
	 * 测试换行
	 */
	@Test
	public void testSystem() {
		String ls = System.getProperty("line.separator");
		System.out.println("ab\tcd" + ls + "dsfsdf"); 
	}

	@Test
	public void t() {
		float f = 1.4f;
		float f1 = 2.1f;
		Float f2 = 1.41F;
		if (f > f1) {
			System.out.println("f > f1");
		} else {
			System.out.println("f1 > f");
		}

		if (f > f2.floatValue()) {
			System.out.println("f > f2");
		} else {
			System.out.println("f2 > f");
		}
	}

	@Test
	public void sort() {
		List<Float> l = new ArrayList<>();
		l.add(1.6f);
		l.add(1.8f);
		l.add(3.6f);
		l.add(1.5f);
		l.add(3.6f);
		for (Float float1 : l) {
			System.out.println("old l:" + float1.floatValue());
		}
		Collections.sort(l);
		for (Float float2 : l) {
			System.out.println("---new l:" + float2.floatValue());
		}

		List<Float> maxs = new ArrayList<>();
		for (int i = 0; i < l.size(); i++) {
			float max = l.get(l.size() - 1);
			// System.out.println("max=" + max);
			float f = l.get(i);
			if (f >= max) {
				maxs.add(f);
			}
		}
		for (Float max : maxs) {
			System.out.println("----max=" + max);
		}
	}

	@Test
	public void sort2() {
		List<User> users = new ArrayList<>();
		User user = new User("张三", 18.5f);
		User user2 = new User("李四", 12.5f);
		User user3 = new User("晓璐", 12.4f);
		User user4 = new User("刘德华", 18.5f);
		users.add(user);
		users.add(user2);
		users.add(user3);
		users.add(user4);

		for (User u : users) {
			System.out.println("---old:" + u.toString());
		}

		// 排序
		Collections.sort(users,new Comparator<User>() {

			@Override
			public int compare(User o1, User o2) {
				if (o1.equals(o2)) {
					return 0;
				} else if (o1.age >= o2.age) {
					return -1;
				} else {
					return 1;
				}
			}
		});
		
		for (User u : users) {
			System.out.println("---new user:" + u.toString());
		}
	}

	static class User {
		private String name;
		private Float age;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Float getAge() {
			return age;
		}
		public void setAge(Float age) {
			this.age = age;
		}
		public User(String name, Float age) {
			super();
			this.name = name;
			this.age = age;
		}
		@Override
		public String toString() {
			return "User [name=" + name + ", age=" + age + "]";
		}

		

	}
}
