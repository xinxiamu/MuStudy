package com.j8.stream;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * 创建stream的五种方法
 * 
 * @author Administrator
 *
 */
public class CreateStreamWays {

	public static void main(String[] args) {
		// test1();
		// test2();
		// test3();
		// test4();
		test5();
	}

	// 1. From Arrays
	public static void test1() {
		String[] arr = { "program", "creek", "program", "creek", "java", "web", "program" };
		Stream<String> stream = Stream.of(arr);
		stream.forEach(a -> System.out.println("==stream:" + a));

		// Stream<String> stream1 = Stream.of("program", "creek", "program",
		// "creek", "java", "web", "program");

		// String[] stringArr = { "a", "b", "c", "d" };
		// Stream<String> stream2 = Arrays.stream(stringArr);
	}

	// 2. From Collections
	public static void test2() {
		// from collection
		List<Object> list = new ArrayList<>();
		list.add("java");
		list.add("php");
		list.add("python");
		list.add(true);
		list.add(3);
		User user = new User();
		user.setAge(18);
		user.setUsername("mutou");
		list.add(user);
		Stream<Object> stream = list.stream();
		stream.forEach(a -> {
			System.out.println("----value:" + a);
			if (a instanceof User) {
				User u = (User) a;
				System.out.println("---username:" + u.getUsername());
			}
		});
	}

	// 3. Use Stream.generate()
	public static void test3() {
		// generate()
		Stream<String> stream = Stream.generate(() -> "test").limit(5);
		String[] strArr = stream.toArray(String[]::new);
		System.out.println(Arrays.toString(strArr));
	}

	// 4. Use Stream.iterate()
	public static void test4() {
		// iterate()
		Stream<BigInteger> bigIntStream = Stream.iterate(BigInteger.ZERO, n -> n.add(BigInteger.ONE)).limit(10);
		BigInteger[] bigIntArr = bigIntStream.toArray(BigInteger[]::new);
		System.out.println(Arrays.toString(bigIntArr));
	}

	// 5. From Popular APIs
	public static void test5() {
		// stream method from APIs
		String sentence = "Program creek is a Java site.";
		Stream<String> wordStream = Pattern.compile("\\W").splitAsStream(sentence);
		String[] wordArr = wordStream.toArray(String[]::new);
		System.out.println(Arrays.toString(wordArr));
	}

	// ----------------

	static class User {
		private String username;
		private int age;

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		@Override
		public String toString() {
			return super.toString();
		}
	}

}
