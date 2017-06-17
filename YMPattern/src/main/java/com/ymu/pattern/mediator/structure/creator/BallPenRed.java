package com.ymu.pattern.mediator.structure.creator;

import com.ymu.pattern.mediator.structure.product.PenCoreAbstract;
import com.ymu.pattern.mediator.structure.product.RedPenCore;

/**
 * 具体构造者。红色笔芯的圆珠笔
 */
public class BallPenRed extends BallPenAbstract {

	@Override
	public PenCoreAbstract getPenCore() {
		return new RedPenCore();
	}

}
