package com.ymu.pattern.mediator.structure.product;

/**
 * 具体产品。蓝色笔芯
 */
public class BluePenCore extends PenCoreAbstract {
	
	public BluePenCore() {
		color = "蓝色";
	}

	@Override
	public void writeWord(String s) {
		System.out.println("写出" + color + "的字：" + s);
	}

}
