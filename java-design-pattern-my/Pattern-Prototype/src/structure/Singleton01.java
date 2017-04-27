package structure;

/**
 * 单件类。
 * 在单件类提供的方法中创建这个唯一实例。
 * 用户需要的时候才创建单件类实例。
 * 即如果不去创建该类的实例，那么单件类变量中的值就为null。
 * 而一旦第一次创建实例，则，内存中将一直存有该类的唯一实例，
 * 并可通过getInstance方法获取该实例。
 * @author Administrator
 *
 */
public class Singleton01 {
	
	private static Singleton01 uniqueSingleton;
	
	private  Singleton01() {
		
	}
	
	/**
	 * 
	 * @return
	 */
	public static synchronized Singleton01 getInstance() {
		if (uniqueSingleton == null) {
			uniqueSingleton = new Singleton01();
		}
		return uniqueSingleton;
	}
}
