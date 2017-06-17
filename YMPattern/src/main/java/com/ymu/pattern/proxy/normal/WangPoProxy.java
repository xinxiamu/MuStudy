package com.ymu.pattern.proxy.normal;

/**
 * 丑陋的老王婆-代理人，委托人。骚女人委托她去干坏事。
 * 
 * @author Administrator
 *
 */
public class WangPoProxy implements ISaoWoman {

	private ISaoWoman saoWoman = null; // 定义一个骚货

	public WangPoProxy() {
		this.saoWoman = new PanJinLian();// 默认代理潘金莲
	}

	// 王婆可是是任何一个骚女人的代理人
	public WangPoProxy(ISaoWoman saoWoman) {
		this.saoWoman = saoWoman;
	}

	/**
	 * 帮那群骚东西去love前我要先做点什么
	 */
	private void before() {
		System.out.println("你们好好happy，我王婆出去给你们看人");
	}

	private void after() {
		System.out.println("事情给你办好，赶紧给好处我王婆吧");
	}

	@Override
	public void throwEysWithMan() {
		this.saoWoman.throwEysWithMan();// 让背后的年轻骚逼去做吧
	}

	@Override
	public void loveWithMan() {
		before();
		this.saoWoman.loveWithMan();// 让背后的年轻骚逼去做吧
		after();
	}

}
