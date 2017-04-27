package structure.creator;

import structure.product.PenCoreAbstract;
import structure.product.RedPenCore;

/**
 * 具体构造者。红色笔芯的圆珠笔
 */
public class BallPenRed extends BallPenAbstract {

	@Override
	public PenCoreAbstract getPenCore() {
		return new RedPenCore();
	}

}
