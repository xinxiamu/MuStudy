package com.j8.lambda;



public class Demo {

	public static void main(String[] args) {
		
		/*
		 * 这样子是不能异步的。
		 */
		Runnable aRunnable = () -> {
			System.out.println("safsdf");
			System.out.println("哈哈");
			int k = 0;
			for (int i = 0; i < 100000000; i++) {
				k = k + i;
			}
			System.out.println(k);
		};
		aRunnable.run();
		
		Runnable aRunnable2 = () -> {int a = 1;int b = 4;System.out.println(a + b);};
		aRunnable2.run();
		
		StrToIntMapper strToIntMapper = (String str) -> str.length();
//			return str.length();
//		};
		System.out.println(strToIntMapper.map("你妹的"));
		
	}
	
	@FunctionalInterface
	interface StrToIntMapper {
		int map(String str);
	}
}
