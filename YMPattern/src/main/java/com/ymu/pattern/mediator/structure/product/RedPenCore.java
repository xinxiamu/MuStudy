package com.ymu.pattern.mediator.structure.product;

/**
 * 具体产品。红色笔芯。
 */
public class RedPenCore extends PenCoreAbstract {

	public RedPenCore() {
		color = "红色";
	}

	@Override
	public void writeWord(String s) {
		System.out.println("写出" + color + "的字：" + s);
	}

}
