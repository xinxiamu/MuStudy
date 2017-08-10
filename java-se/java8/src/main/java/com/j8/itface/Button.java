package com.j8.itface;

/**
 * 使用接口的默认方法，达到类似多重继承的目的。
 * 不过遗憾的是，不能继承接口的属性
 * 
 * @author Administrator
 *
 */
public class Button implements Clickable, Accessible {

	public static void main(String[] args) {
		Button button = new Button();
		button.click();
		button.access();
		button.print();
	}

	/**
	 * 当两个接口有同名的默认方法时候，实现类必须要重写。编译器会自动提示
	 */
	@Override
	public void print() {
		Accessible.super.print();
		Clickable.super.print();
	}

}
