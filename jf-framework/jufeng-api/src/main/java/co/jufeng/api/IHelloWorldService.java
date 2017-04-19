package co.jufeng.api;

import java.util.Map;

import co.jufeng.api.annotation.Api;
import co.jufeng.api.annotation.Param;

/**
 * HelloWorld API
 * @author jufeng
 *
 */
@Api("helloWorldService")
public interface IHelloWorldService extends IService{
	
	/**
	 * sayHello 
	 * @return Map
	 */
	Map<Object, Object> hello();
	
	/**
	 * sayHello 
	 * @param jsonString＝{"userName":"admin"}
	 * @return Map
	 */
	Map<Object, Object> sayHello(@Param("jsonString") String jsonString);
	
	
	
}
