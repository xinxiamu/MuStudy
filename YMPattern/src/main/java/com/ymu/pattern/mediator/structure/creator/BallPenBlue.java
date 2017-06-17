package com.ymu.pattern.mediator.structure.creator;

import com.ymu.pattern.mediator.structure.product.BluePenCore;
import com.ymu.pattern.mediator.structure.product.PenCoreAbstract;

/**
 * 具体构造者。蓝色笔芯圆珠笔
 */
public class BallPenBlue extends BallPenAbstract {

	@Override
	public PenCoreAbstract getPenCore() {
		return new BluePenCore();
	}

}
