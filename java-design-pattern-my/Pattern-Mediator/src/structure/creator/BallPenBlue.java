package structure.creator;

import structure.product.BluePenCore;
import structure.product.PenCoreAbstract;

/**
 * 具体构造者。蓝色笔芯圆珠笔
 */
public class BallPenBlue extends BallPenAbstract {

	@Override
	public PenCoreAbstract getPenCore() {
		return new BluePenCore();
	}

}
