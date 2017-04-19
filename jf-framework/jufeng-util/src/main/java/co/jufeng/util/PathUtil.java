package co.jufeng.util;


import java.io.File;
import java.io.IOException;
import java.net.URL;

public class PathUtil {
	
	public static String OS_NAME = System.getProperties().getProperty("os.name");
	
	public static String OS_ARCH = System.getProperties().getProperty("os.arch");
	
	public static String OS_VERSION = System.getProperties().getProperty("os.version");
	
	public static String separator(){
		if(OS_NAME.indexOf("Win") >= 0){
			return "";
		}else{
			return "/";
		}
//		return System.getProperties().getProperty("file.separator");
	}
	public static void main(String[] args) {
		System.out.println(getClassesPath());
		System.out.println(getWEBINFPath());
		System.out.println("===========操作系统的名称:"+System.getProperties().getProperty("os.name"));  
		System.out.println("===========操作系统的架构:"+System.getProperties().getProperty("os.arch"));  
		System.out.println("===========操作系统的版本:"+System.getProperties().getProperty("os.version"));  
		System.out.println("===========file.separator:"+System.getProperties().getProperty("file.separator"));  
		System.out.println(File.separator);
	}
	
	/**
	 * 获取WEB-INF/classes/
	 * @return String
	 */
	public static String getClassesPath(){
		return  separator() + Thread.currentThread().getContextClassLoader().getResource("").toString().split("file:/")[1].replace("%20", " ");
	}
	
	/**
	 * 获取WebRoot
	 * @return String
	 */
	public static String getWebRootPath(){
		return separator() + Thread.currentThread().getContextClassLoader().getResource("").toString().split("file:/")[1].split("WEB-INF/classes/")[0];
	}
	
	/**
	 * 获取WebRoot
	 * @return String
	 */
	public static String getWEBINFPath(){
		return separator() + Thread.currentThread().getContextClassLoader().getResource("").toString().split("file:/")[1].split("classes/")[0];
	}
	
	/**
	 * 获取项目名
	 * @return String
	 */
	public static String getProjectName(){
		String[] str = System.getProperty("user.dir").split("\\\\");
		return str[str.length-1];
	}
	
	public void showURL() throws IOException {

		// 第一种：获取类加载的根路径 D:\git\daotie\daotie\target\classes
		File f = new File(this.getClass().getResource("/").getPath());
		System.out.println(f);

		// 获取当前类的所在工程路径; 如果不加“/” 获取当前类的加载目录
		// D:\git\daotie\daotie\target\classes\my
		File f2 = new File(this.getClass().getResource("").getPath());
		System.out.println(f2);

		// 第二种：获取项目路径 D:\git\daotie\daotie
		File directory = new File("");// 参数为空
		String courseFile = directory.getCanonicalPath();
		System.out.println(courseFile);

		// 第三种： file:/D:/git/daotie/daotie/target/classes/
		URL xmlpath = this.getClass().getClassLoader().getResource("");
		System.out.println(xmlpath);

		// 第四种： D:\git\daotie\daotie
		System.out.println(System.getProperty("user.dir"));
		/*
		 * 结果： C:\Documents and Settings\Administrator\workspace\projectName
		 * 获取当前工程路径
		 */

		// 第五种： 获取所有的类路径 包括jar包的路径
		System.out.println(System.getProperty("java.class.path"));

	}
}
