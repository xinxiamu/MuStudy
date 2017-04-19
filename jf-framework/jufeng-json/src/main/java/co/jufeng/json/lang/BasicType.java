package co.jufeng.json.lang;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public enum BasicType {
	BYTE, SHORT, INT, INTEGER, LONG, DOUBLE, FLOAT, BOOLEAN, CHAR, CHARACTER, STRING, DATE;
	
	public static final Map<Class<?>, Class<?>> wrapperPrimitiveMap = new HashMap<Class<?>, Class<?>>(10);
	public static final Map<Class<?>, Class<?>> primitiveWrapperMap = new HashMap<Class<?>, Class<?>>(10);
	
	static {
		wrapperPrimitiveMap.put(Boolean.class, boolean.class);
		wrapperPrimitiveMap.put(Byte.class, byte.class);
		wrapperPrimitiveMap.put(Character.class, char.class);
		wrapperPrimitiveMap.put(Double.class, double.class);
		wrapperPrimitiveMap.put(Float.class, float.class);
		wrapperPrimitiveMap.put(Integer.class, int.class);
		wrapperPrimitiveMap.put(Long.class, long.class);
		wrapperPrimitiveMap.put(Short.class, short.class);
		wrapperPrimitiveMap.put(Date.class, Date.class);
		wrapperPrimitiveMap.put(java.sql.Date.class, java.sql.Date.class);

		for (Map.Entry<Class<?>, Class<?>> entry : wrapperPrimitiveMap.entrySet()) {
			primitiveWrapperMap.put(entry.getValue(), entry.getKey());
		}
	}
}
