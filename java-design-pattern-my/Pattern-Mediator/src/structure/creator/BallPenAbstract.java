package structure.creator;

import structure.product.PenCoreAbstract;

/**
 * 构造者。圆珠笔
 */
public abstract class BallPenAbstract {

	public BallPenAbstract() {
		System.out.println("生产了一只装有" + getPenCore().getColor() + "笔芯的圆珠笔");
	}

	/**
	 * 工厂方法。
	 * 
	 * @return 具体产品。
	 */
	public abstract PenCoreAbstract getPenCore();
}
