package structure;

import structure.creator.BallPenAbstract;
import structure.creator.BallPenBlack;
import structure.creator.BallPenBlue;
import structure.creator.BallPenRed;
import structure.product.PenCoreAbstract;

/**
 * 应用程序在使用工厂方法模式时，不需要关心具体产品。
 * 只需要和抽象产品、构造者以及具体构造者打交道即可。
 */
public class Application {
	
	public static void main(String args[]) {
		PenCoreAbstract penCoreAbstract;
		
		BallPenAbstract b = new BallPenBlack();
		penCoreAbstract = b.getPenCore();
		penCoreAbstract.writeWord("你好，很高兴认识你");
		
		BallPenAbstract r = new BallPenRed();
		penCoreAbstract = r.getPenCore();
		penCoreAbstract.writeWord("How are you");
		
		BallPenAbstract blue = new BallPenBlue();
		penCoreAbstract = blue.getPenCore();
		penCoreAbstract.writeWord("nice to meet you");
	}

}
