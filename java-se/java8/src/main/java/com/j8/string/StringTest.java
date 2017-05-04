package com.j8.string;

public class StringTest {

	public static void main(String[] args) {
		// 字符串比较
		String name = null;
		// 没有判断name是否为null，否异常
		// try {
		// if (name.equals("张三")) {
		//
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// 解决方法。这样子不用先判断name是否为null
		if ("张三".equals(name)) {
			System.out.println("---name=" + "张三");
		} else {
			System.out.println("---name不是：" + "张三");
		}

		// 枚举和字符串比较
		TaskStatus taskStatus = null;
		TaskStatus taskStatus2 = TaskStatus.All;
		// 不先判断null,异常
//		if (taskStatus.equals("All")) {
//
//		}
		// 解决。不用判断null
		if (("All").equals(taskStatus)) {
			System.out.println("---taskStatus=All");
		} else {
			System.out.println("-----taskStatus!=All");
		}
	}

}
