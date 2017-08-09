package com.j8.lambda;

public class Main {
	
	public static void main(String[] args) {
		
		String name = "哈哈";
		FinalVar finalVar = a->System.out.println(a + name);
		finalVar.tes(name);
		
		//error
//		FinalVar finalVar2 = a->{
//			name = "小鱼儿";	//error,name是事实的final变量，换句话说，Lamdba表达式引用的是值，而不是变量
//			System.out.println(a + name);
//		};
		
		FinalVar finalVar3 = a->{
			System.out.println(a);
		};
		finalVar3.tes("小月");
		finalVar3.hello();
	}
	
	@FunctionalInterface
	public interface FinalVar {
		
		void tes(String a);
		
		//在接口中实现方法，接口默认方法
		default void hello() {
			System.out.println("---hellow world");
			welcom("啦啦啦");
		}
		
		static void welcom(String name) {
			System.out.println("welcome " + name);
		}
	}

}
