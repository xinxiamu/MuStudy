package com.ymu.pattern.singleton;

/**
 * 饿汉模式.马上创建对象，线程安全。
 * <p>
 * 若创建过程new SingletonClassE1非常耗时 且又没有使用到则性能上需要优化
 * 
 * @author tqcenglish
 * @author mutian
 *
 */
public class SingletonClassE1 {

	/**
	 * 静态实例化对象。
	 */
	private static final SingletonClassE1 mInstance = new SingletonClassE1();

	/**
	 * 私有构造函数。其他类无法初始化。
	 */
	private SingletonClassE1() {
	}

	public static SingletonClassE1 getInstance() {
		return mInstance;
	}
}
