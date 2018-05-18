package com.j8.utils;

public class ClassUtils {

	public static void main(String[] args) {
		ClassUtils classUtils = new ClassUtils();
		System.out.println(classUtils.getClassPath());
	}
	
	//获取文件资源,加载类路径下文件
	public String getFileAsString() {
		this.getClass().getResourceAsStream("");
		return null;
	}

	/**
	 * 获取类所在路径
	 * @return
	 */
	public String getClassPath() {
		return this.getClass().getClassLoader().getResource("").getPath();
	}

}
