package co.jufeng.util.enums;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
public enum ReflectionUtil {
	
	INSTANCE;
	
	public Map<String, Object> getObjectNameValuePair(Class<?> c, Object object) {
		Map<String, Object> map = null;
		try {
			c = Class.forName(c.getName());
			Field[] fields = c.getDeclaredFields();
			for (Field f : fields) {
				f.setAccessible(true);
			}
			map = new HashMap<String, Object>();
			for (Field f : fields) {
				String fielsString = f.toString();
				int lastIndexOf = fielsString.lastIndexOf(".") + 1;
				String fieldName =  fielsString.substring(lastIndexOf);
				Object value = f.get(object);
				map.put(fieldName, value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}