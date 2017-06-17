package com.ymu.pattern.singleton.structure;

/**
 * 单例模式是所有设计模式中最简单的一种。只有
 * 一个角色：单件类。
 * 这个类就是创建了唯一的月亮。
 * @author Administrator
 *
 */
public class Moon {
	
	private static Moon uniqueMoon;
	
	private double radius;
	private double distanceToEarth;
	
	/**
	 * 注意：单件类的构造方法必须是私有的，这样其它任何类都
	 * 无法使用该单件类来创建对象。
	 */
	private Moon() {
		uniqueMoon = this;
	}
	
	private Moon(double radius,double distanceToEarth) {
		this.radius = radius;
		this.distanceToEarth = distanceToEarth;
		uniqueMoon = this;
	}
	
	/**
	 * 这是一个同步方法。
	 * @return
	 */
	public static synchronized Moon getMoon() {
		if (uniqueMoon == null) {
			uniqueMoon = new Moon();
		}
		return uniqueMoon;
	}
	
	public String show() {
		String s = "月亮半径是" + radius + "km,距离地球是" + distanceToEarth + "km";
		return s;
	}
}
