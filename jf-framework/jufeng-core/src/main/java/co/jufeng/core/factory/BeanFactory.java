package co.jufeng.core.factory;

import java.util.HashMap;
import java.util.Map;

/**
 * Bean factory management
 * @author jufeng
 *
 */
public class BeanFactory {
	
	/**
	 * All bean collections under the scanning annotation @RestController
	 */
	private static Map<String, Object> controllerPool = new HashMap<String, Object>();
	
	/**
	 * All bean service under the scanning annotation @Service
	 */
	private static Map<String, Object> servicePool = new HashMap<String, Object>();
	
	/**
	 * All bean Accessor under the scanning annotation @Service
	 */
	private static Map<String, Object> accessorPool = new HashMap<String, Object>();
	
    private BeanFactory(){
    }
    
	public static Map<String, Object> getControllerPool() {
		return controllerPool;
	}

	public static void setControllerPool(String key, Object value) {
		controllerPool.put(key, value);
	}
	
	public static Map<String, Object> getServicePool() {
		return servicePool;
	}

	public static void setServicePool(String key, Object value) {
		servicePool.put(key, value);
	}
	
	public static Map<String, Object> getAccessorPool() {
		return accessorPool;
	}

	public static void setAccessorPool(String key, Object value) {
		accessorPool.put(key, value);
	}

	public static Object getBean(String name) throws Exception {
		
		if(isServicePool(name)){
			return servicePool.get(name);
		}
		
		if(isAccessorPool(name)){
			return accessorPool.get(name);
		}
		
		if(isControllerPool(name)){
			return controllerPool.get(name);
		}
		
		
		return null;
	}

	public static <T> T getBean(String name, Class<T> requiredType) throws Exception {
		return requiredType.cast(getBean(name));
	}

	public static boolean isControllerPool(String name){
		return controllerPool.containsKey(name);
	}
	
	
	public static boolean isServicePool(String name){
		return controllerPool.containsKey(name);
	}
	
	public static boolean isAccessorPool(String name){
		return controllerPool.containsKey(name);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}

