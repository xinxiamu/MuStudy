package com.j8.io;

import java.util.Properties;

/**
 * Properties文件操作
 * @author Administrator
 *
 */
public class PropertiesUtils {
	
	private PropertiesUtils(){
		
	}
	
	public static void main(String[] args) {
		Properties p = getProperties("application.properties");
		p.setProperty("a", "abc");
		System.out.println(p.getProperty("hoss", "a")); 
	}
	
	/**
	 * 获取Properties文件对象。
	 * @param loadPath ClassLoader���ļ�·��
	 * @return
	 */
	public static Properties getProperties(String loadPath) {
		Properties configFile = new Properties();
		try {
			configFile.load(PropertiesUtils.class.getClassLoader().getResourceAsStream(loadPath));
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		return configFile;
	}

}
