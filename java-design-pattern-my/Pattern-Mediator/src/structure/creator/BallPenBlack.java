package structure.creator;

import structure.product.BlackPenCore;
import structure.product.PenCoreAbstract;

/**
 * 具体构造者。黑色笔芯的圆珠笔。
 */
public class BallPenBlack extends BallPenAbstract {

	@Override
	public PenCoreAbstract getPenCore() {
		return new BlackPenCore();
	}

}
