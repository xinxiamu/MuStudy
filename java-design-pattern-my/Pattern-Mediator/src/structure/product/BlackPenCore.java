package structure.product;

/**
 * 具体产品。黑色笔芯
 */
public class BlackPenCore extends PenCoreAbstract {

	public BlackPenCore() {
		color = "黑色";
	}

	@Override
	public void writeWord(String s) {
		System.out.println("写出" + color + "的字：" + s);
	}

}
