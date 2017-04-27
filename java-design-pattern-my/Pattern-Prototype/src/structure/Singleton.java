package structure;

/**
 * 单件类。
 * 在JVM加载该单件类时创建它的唯一实例。
 * @author Administrator
 *
 */
public class Singleton {
	
	//JVM加载该类时创建uniqueInstance
	private static Singleton uniqueInstance = new Singleton();
	
	private Singleton() {
		
	}
	
	public static Singleton getInstance() {
		return uniqueInstance;
	}
}
