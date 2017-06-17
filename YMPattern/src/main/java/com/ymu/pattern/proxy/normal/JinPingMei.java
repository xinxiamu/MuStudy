package com.ymu.pattern.proxy.normal;

/**
 * 金瓶梅。被代理人
 * @author Administrator
 *
 */
public class JinPingMei implements ISaoWoman {

	@Override
	public void throwEysWithMan() {
		System.out.println("金瓶梅向男人抛媚眼……");
		
	}

	@Override
	public void loveWithMan() {
		System.out.println("金瓶梅和男人happy……");  
	}

}
