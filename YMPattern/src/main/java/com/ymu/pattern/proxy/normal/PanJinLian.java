package com.ymu.pattern.proxy.normal;

/**
 * 潘金莲。被代理人
 * @author Administrator
 *
 */
public class PanJinLian implements ISaoWoman {

	@Override
	public void throwEysWithMan() {
		System.out.println("潘金莲向男人抛媚眼……");
		
	}

	@Override
	public void loveWithMan() {
		System.out.println("潘金莲和男人happy……");
	}

}
