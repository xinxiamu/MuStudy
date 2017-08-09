package com.j8.lambda.sort;

import java.util.Arrays;
import java.util.Comparator;

public class AppSort {

	public static void main(String[] args) {
		Dog d1 = new Dog("Max", 2, 50);
		Dog d2 = new Dog("Rocky", 1, 30);
		Dog d3 = new Dog("Bear", 3, 40);
 
		Dog[] dogArray = { d1, d2, d3 };
		printDogs(dogArray);
 
		//按重量小到大排序
	/*	Arrays.sort(dogArray, new Comparator<Dog>() {
			@Override
			public int compare(Dog o1, Dog o2) {
				return o1.getWeight() - o2.getWeight();
			}
		});
		printDogs(dogArray);*/
		
		//lambda表达式法，推荐
		Arrays.sort(dogArray, (Dog m, Dog n) -> m.getWeight() - n.getWeight());
		printDogs(dogArray);

	}
	
	public static void printDogs(Dog[] dogs) {
		System.out.println("--Dog List--");
		for (Dog d : dogs) {
			System.out.print(d);
		}
		System.out.println();
	}

}
