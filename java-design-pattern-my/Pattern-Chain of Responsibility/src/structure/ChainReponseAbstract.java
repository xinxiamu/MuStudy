package structure;

import java.util.ArrayList;

/**
 * 创建责任链并响应用户请求
 */
public abstract class ChainReponseAbstract {

	private IHandler createChain(ArrayList<IHandler> handlers) {
		for (int i = 0; i < handlers.size() - 1; i++) {
			IHandler handler = handlers.get(i);
			IHandler handlerNext = handlers.get(i + 1);
			handler.setNextHandler(handlerNext);
		}
		
		//返回责任链中的第一个对象，从第一个开始处理
		return handlers.get(0);
	}
	
	public void reponseClient(ArrayList<IHandler> handlers,String number) {
		IHandler handler = createChain(handlers);
		handler.handleRequest(number);
	}
}
