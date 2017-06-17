package com.ymu.pattern.proxy.normal;

/**
 * 西门庆有钱有势老色鬼粉墨登场。
 * 
 * @author Administrator
 *
 */
public class XiMenQing {

	public static void main(String[] args) {
		// 王婆，我下面……，给我出来
		// 找哪个好呢，就先潘金莲吧
		WangPoProxy wangPoProxy = new WangPoProxy();
		wangPoProxy.throwEysWithMan();// 好像王婆向西门庆开始抛媚眼了，实际是背后潘金莲那骚逼在干的
		wangPoProxy.loveWithMan();// 好的，你们开始吧

		// 多样性，很重要
		JinPingMei jinPingMei = new JinPingMei();
		WangPoProxy wangPoProxy2 = new WangPoProxy(jinPingMei);
		// 呵呵，你们懂得
		wangPoProxy2.throwEysWithMan();
		wangPoProxy2.loveWithMan();

	}

}
