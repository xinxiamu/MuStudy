package com.j8.io.nio;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CreatPath {

	public static void main(String[] args) throws IOException {

		// Path p1 = Paths.get("/tmp/foo");
		// Path p2 = Paths.get(args[0]);
		// Path p3 = Paths.get(URI.create("file:///Users/joe/FileTest.java"));

		// linux
		// Path path = Paths.get("/home/mutou/tmp");
		// Microsoft Windows syntax
		// Path path = Paths.get("C:\\home\\joe\\foo");
		Path path = FileSystems.getDefault().getPath("/home/mutou/tmp");

		// 检索路径信息
		System.out.format("toString: %s%n", path.toString());
		System.out.format("getFileName: %s%n", path.getFileName());
		System.out.format("getName(0): %s%n", path.getName(0));
		System.out.format("getNameCount: %d%n", path.getNameCount());
		System.out.format("subpath(0,2): %s%n", path.subpath(0, 2));
		System.out.format("getParent: %s%n", path.getParent());
		System.out.format("getRoot: %s%n", path.getRoot());

		// 转换路径
		System.out.format("%s%n", path.toUri());
		Path fullPath = path.toAbsolutePath(); // 转换成绝对路径
		System.out.println("----绝对路径" + fullPath);
		Path fp = path.toRealPath();
		System.out.println("-----真实路径" + fp);
		
		//拼接路径
		System.out.format("拼接路径：%s%n", path.resolve("bar"));
		System.out.format("去掉部分路径：%s%n", Paths.get("tmp").resolve("/home/mutou"));
		// Microsoft Windows
//		Path p1 = Paths.get("C:\\home\\joe\\foo");
		// Result is C:\home\joe\foo\bar
//		System.out.format("%s%n", p1.resolve("bar"));
		
		Path p1 = Paths.get("joe");
		Path p2 = Paths.get("sally");
		
		// Result is ../sally
		Path p1_to_p2 = p1.relativize(p2);
		// Result is ../joe
		Path p2_to_p1 = p2.relativize(p1);
		System.out.println("p1_to_p2" + p1_to_p2);
		System.out.println("p2_to_p1" + p2_to_p1);
		
		Path p3 = Paths.get("home");
		Path p4 = Paths.get("home/sally/bar");
		// Result is sally/bar
		Path p3_to_p4 = p1.relativize(p4);
		// Result is ../..
		Path p4_to_p3 = p4.relativize(p3);
		System.out.println("p3_to_p4" + p3_to_p4);
		System.out.println("p4_to_p3" + p4_to_p3);
		
		//路径比较
		Path p = Paths.get("...");
		Path otherPath = Paths.get("...");
		Path beginning = Paths.get("/home");
		Path ending = Paths.get("foo");

		if (p.equals(otherPath)) {
		    // equality logic here
			System.out.println("equality logic here");
		} else if (p.startsWith(beginning)) {
		    // path begins with "/home"
			System.out.println("path begins with /home");
		} else if (p.endsWith(ending)) {
		    // path ends with "foo"
			System.out.println("path ends with foo");
		}
		for (Path name: path) {
		    System.out.println("name:" + name);
		}
	}

}
