package com.ymu.pattern.chain.structure.impl;

import java.util.ArrayList;
import java.util.List;

import com.ymu.pattern.chain.structure.IHandler;

/**
 * 具体处理者：上海
 */
public class ShangHai implements IHandler {

	/**
	 * 后继的处理者
	 */
	private IHandler handler;
	
	private List<String> numberList;
	
	public ShangHai() {
		//模拟身份证号码
		numberList = new ArrayList<String>();
		numberList.add("2201456753246543");
		numberList.add("2221456753246543");
		numberList.add("22414567532465434");
		numberList.add("22314567532465436");
	}

	@Override
	public void handleRequest(String number) {
		if (numberList.contains(number)) {
			System.out.println("该人在上海居住");
		} else {
			System.out.println("该人不在上海居住");
			if (null != handler) {
				//将请求传递给下一个处理者
				handler.handleRequest(number);
			}
		}
	}

	@Override
	public void setNextHandler(IHandler handler) {
		this.handler = handler;
	}

}
