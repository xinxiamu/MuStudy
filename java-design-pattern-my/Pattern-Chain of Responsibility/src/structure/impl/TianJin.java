package structure.impl;

import java.util.ArrayList;
import java.util.List;

import structure.IHandler;

/**
 * 具体处理者：天津
 */
public class TianJin implements IHandler {

	/**
	 * 后继的处理者
	 */
	private IHandler handler;
	
	private List<String> numberList;
	
	public TianJin() {
		//模拟身份证号码
		numberList = new ArrayList<String>();
		numberList.add("5501456753246543");
		numberList.add("5511456753246543");
		numberList.add("55214567532465434");
		numberList.add("55314567532465436");
	}

	@Override
	public void handleRequest(String number) {
		if (numberList.contains(number)) {
			System.out.println("该人在天津居住");
		} else {
			System.out.println("该人不在天津居住");
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
