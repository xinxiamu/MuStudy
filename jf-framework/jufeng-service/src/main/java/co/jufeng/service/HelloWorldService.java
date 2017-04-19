package co.jufeng.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import co.jufeng.api.IHelloWorldService;
import co.jufeng.core.lang.ServiceException;

@Service
public class HelloWorldService extends BaseService implements IHelloWorldService {

	private static final long serialVersionUID = -7015395610904948249L;
	
	@Override
	public Map<Object, Object> hello() {
		try{
			Map<Object, Object> map = new HashMap<Object, Object>();
			map.put("hello", "Welcome to API world");
			return map;
		}catch(Exception e){
			if(e instanceof ServiceException){
            	throw new ServiceException(e);
            	
            }else{
            	throw new ServiceException(EXCEPTION_MESSAGE);
            }
		}
	}


	@Override
	public Map<Object, Object> sayHello(String jsonString) {
		try{
			Map<Object, Object> map = this.parseObject(jsonString);
			map.put("welcome", map.get("userName"));
			map.remove("userName");
			return map;
		}catch(Exception e){
			if(e instanceof ServiceException){
            	throw new ServiceException(e);
            	
            }else{
            	throw new ServiceException(EXCEPTION_MESSAGE);
            }
		}
		
	}
}
