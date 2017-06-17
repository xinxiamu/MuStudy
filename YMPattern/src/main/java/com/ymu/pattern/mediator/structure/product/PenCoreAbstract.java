package com.ymu.pattern.mediator.structure.product;

/**
 * 抽象产品。笔芯。
 */
public abstract class PenCoreAbstract implements IPenCore {
	
	/**
	 * 笔芯颜色
	 */
	protected String color;
	
	public abstract void writeWord(String s);

	@Override
	public String getColor() {
		return color;
	}
}
