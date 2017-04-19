package co.jufeng.json;

import java.util.Map;


public interface IJSONObject extends Map<Object, Object>{
	
	public Object get(String key, String defaultValue);
	

}
