package com.ymu.pattern.chain.structure.impl;

import java.util.ArrayList;
import java.util.List;

import com.ymu.pattern.chain.structure.IHandler;

/**
 * 具体处理者：北京。
 */
public class BeiJing implements IHandler {

	/**
	 * 后继的处理者
	 */
	private IHandler handler;
	
	private List<String> numberList;
	
	public BeiJing() {
		//模拟身份证号码
		numberList = new ArrayList<String>();
		numberList.add("11131456753246543");
		numberList.add("21021456753246543");
		numberList.add("31014567532465434");
		numberList.add("10114567532465436");
	}

	@Override
	public void handleRequest(String number) {
		if (numberList.contains(number)) {
			System.out.println("该人在北京居住");
		} else {
			System.out.println("该人不在北京居住");
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
