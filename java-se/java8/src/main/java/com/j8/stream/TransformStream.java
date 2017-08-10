package com.j8.stream;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class TransformStream {

	public static void main(String[] args) throws IOException {
//		 mapTest();
//		mapTest1();
//		filterTest();
//		flatMapTest1();
		flatmapTest2();
	}

	public static void mapTest() {
		List<String> list = new ArrayList<String>();
		list.add("java");
		list.add("php");
		list.add("python");

		// map function
		Stream<Integer> stream = list.stream().map(p -> p.length());

		Integer[] lengthArr = stream.toArray(Integer[]::new);

		for (int a : lengthArr) {
			System.out.println(a);
		}
	}

	public static void mapTest1() {
		List<Object> list = new ArrayList<>();
		list.add("java");
		list.add("php");
		list.add("python");
		list.add(true);
		list.add(2);

		// map function
		Stream<Object> stream = list.stream().map(p -> {
			if (p instanceof Boolean) {
				return true;
			} else if (p instanceof String) {
				String pStr = (String) p;
				return pStr.length();
			} else {
				return p;
			}
		});

		Object[] lengthArr = stream.toArray(Object[]::new);

		for (Object a : lengthArr) {
			System.out.println(a);
		}
	}

	public static void filterTest() {
		List<String> list = new ArrayList<String>();
		list.add("java");
		list.add("php");
		list.add("python");
		list.add("lisp");
		list.add("c++");

		// filter function
		Stream<String> stream = list.stream().filter(p -> p.length() > 3);
		String[] arr = stream.toArray(String[]::new);

		System.out.println(Arrays.toString(arr));
	}
	
	//The flatMap() method can be used for combining a stream of streams to one stream of objects.
	public static void flatMapTest1() {
		ArrayList<Order> orders = new ArrayList<Order>();
		for (int i = 0; i < 3; i++) {
			Order order = new Order();
			order.setOrderId("orderId_" + i);
			ArrayList<Item> items = new ArrayList<>();
			for (int j = 0; j < 3; j++) {
				Item item = new Item();
				item.setItemId("itemId_" + i + "_" + j);
				item.setItemName("itemName_" + i + "_" + j);
				items.add(item);
			}
			order.setItems(items);
			orders.add(order);
		}
		
		//合并所有订单下所有的item
		Stream<Item> itemStream = orders.stream().flatMap(order -> order.getItems().stream());
		itemStream.forEach(item -> {
			String s = item.getItemId();
			System.out.println(s);
		}); 
	}
	
	public static void flatmapTest2() throws IOException {
		Path path = Paths.get("F:/1zt/svn");
		Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8);
		Stream<String> words = lines.flatMap(line -> Stream.of(line.split("\\W+"))); //Split by non-word characters
	}
	
	static class Order{
		String orderId;
		ArrayList<Item> items = new ArrayList<Item>();
		public String getOrderId() {
			return orderId;
		}
		public void setOrderId(String orderId) {
			this.orderId = orderId;
		}
		public ArrayList<Item> getItems() {
			return items;
		}
		public void setItems(ArrayList<Item> items) {
			this.items = items;
		}
	}
	 
	static class Item{
		String itemId;
		String itemName;
		public String getItemId() {
			return itemId;
		}
		public void setItemId(String itemId) {
			this.itemId = itemId;
		}
		public String getItemName() {
			return itemName;
		}
		public void setItemName(String itemName) {
			this.itemName = itemName;
		}
	 
	}

}
