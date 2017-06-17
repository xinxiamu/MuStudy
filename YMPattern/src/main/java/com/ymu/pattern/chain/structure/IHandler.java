package com.ymu.pattern.chain.structure;

/**
 * 处理者接口。
 */
public interface IHandler {

	/**
	 * 处理用户请求
	 * @param number
	 */
	public void handleRequest(String number);
	
	/**
	 * 设置下一个处理者。
	 * @param handler
	 */
	public void setNextHandler(IHandler handler);
}
