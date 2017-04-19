package co.jufeng.json;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import co.jufeng.json.util.ObjectUtil;

public class JSON {
	
	private static final String EMPTY = "";
	public static TimeZone         defaultTimeZone      = TimeZone.getDefault();
    public static Locale           defaultLocale        = Locale.getDefault();
	public static String           DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	
	public static Object parse(String jsonString) {
		return new JSONObject(jsonString);
	}
	
	public static JSONObject parseObject(String jsonString) {
		return new JSONObject(jsonString);
	}
	
	public static JSONObject parseJSONObject(String jsonString) {
		return new JSONObject(jsonString);
	}


	public static JSONArray parseArray(String jsonString) {
		return new JSONArray(jsonString);
	}
	
	public static Object toJSON(Object object) {
		try {
			if (object == null) {
				return JSONNull.NULL;
			}
			if (object instanceof JSONObject || object instanceof JSONArray || JSONNull.NULL.equals(object) || object instanceof JSONString || ObjectUtil.isBasicType(object)) {
				return object;
			}

			if (object instanceof Collection) {
				Collection<?> coll = (Collection<?>) object;
				return new JSONArray(coll);
			}
			if (ObjectUtil.isArray(object)) {
				return new JSONArray(object);
			}
			if (object instanceof Map) {
				Map<?, ?> map = (Map<?, ?>) object;
				return new JSONObject(map);
			}
			Package objectPackage = object.getClass().getPackage();
			String objectPackageName = objectPackage != null ? objectPackage.getName() : "";
			if (objectPackageName.startsWith("java.") || objectPackageName.startsWith("javax.") || object.getClass().getClassLoader() == null) {
				return object;
			}
			return new JSONObject(object);
		} catch (Exception exception) {
			return null;
		}
	}
	
//	public static Object toJSONString(Object object) {
//		return toJSON(object);
//	}
	
	public static String toJSONString(Object object) {
		return toJSON(object).toString();
	}
	
	public static String toJSONString(JSONArray jsonArray) {
		return jsonArray.toString();
	}
	
	public static String toJSONPrettyString(JSONObject jsonObject) {
		return jsonObject.toJSONString(4);
	}
	
	public static String toJSONPrettyString(JSONArray jsonArray) {
		return jsonArray.toJSONString(4);
	}
	
	public static <T> T toJavaObject(JSONObject json, Class<T> beanClass) {
		return null == json ? null : json.toBean(beanClass);
	}
	
	public static <T> T toJavaObject(String jsonString, Class<T> beanClass) {
		return null == jsonString ? null : JSONObject.parseObject(jsonString).toBean(beanClass);
	}
	
	public static String quote(String string) {
		StringWriter sw = new StringWriter();
		synchronized (sw.getBuffer()) {
			try {
				return quote(string, sw).toString();
			} catch (IOException ignored) {
				return "";
			}
		}
	}

	public static Writer quote(String string, Writer writer) throws IOException {
		if (string == null || string.length() == 0) {
			writer.write("\"\"");
			return writer;
		}

		char b;		
		char c = 0; 
		String hhhh;
		int i;
		int len = string.length();

		writer.write('"');
		for (i = 0; i < len; i++) {
			b = c;
			c = string.charAt(i);
			switch (c) {
				case '\\':
				case '"':
					writer.write('\\');
					writer.write(c);
					break;
				case '/':
					if (b == '<') {
						writer.write('\\');
					}
					writer.write(c);
					break;
				case '\b':
					writer.write("\\b");
					break;
				case '\t':
					writer.write("\\t");
					break;
				case '\n':
					writer.write("\\n");
					break;
				case '\f':
					writer.write("\\f");
					break;
				case '\r':
					writer.write("\\r");
					break;
				default:
					if (c < ' ' || (c >= '\u0080' && c < '\u00a0') || (c >= '\u2000' && c < '\u2100')) {
						writer.write("\\u");
						hhhh = Integer.toHexString(c);
						writer.write("0000", 0, 4 - hhhh.length());
						writer.write(hhhh);
					} else {
						writer.write(c);
					}
			}
		}
		writer.write('"');
		return writer;
	}

	protected static final Writer writeValue(Writer writer, Object value, int indentFactor, int indent) throws JSONException, IOException {
		if (value == null || value.equals(null)) {
			writer.write("null");
			
		} else if (value instanceof JSONStreamAware) {
			((JSONStreamAware) value).write(writer, indentFactor, indent);
			
		}else if (value instanceof Map) {
			new JSONObject((Map<?, ?>) value).write(writer, indentFactor, indent);
			
		} else if (value instanceof Collection) {
			new JSONArray((Collection<?>) value).write(writer, indentFactor, indent);
			
		} else if (value.getClass().isArray()) {
			new JSONArray(value).write(writer, indentFactor, indent);
			
		} else if (value instanceof Number) {
			writer.write(numberToString((Number) value));
			
		} else if (value instanceof Boolean) {
			writer.write(value.toString());
			
		} else if (value instanceof JSONString) {
			Object o;
			try {
				o = ((JSONString) value).toJSONString();
				
			} catch (Exception e) {
				throw new JSONException(e);
			}
			writer.write(o != null ? o.toString() : quote(value.toString()));
		} else {
			quote(value.toString(), writer);
		}
		return writer;
	}

	protected static final void indent(Writer writer, int indent) throws IOException {
		for (int i = 0; i < indent; i += 1) {
			writer.write(' ');
		}
	}
	
	protected static String numberToString(Number number) throws JSONException {
		if (number == null) {
			throw new JSONException("Null pointer");
		}
		
		testValidity(number);

		String string = number.toString();
		if (string.indexOf('.') > 0 && string.indexOf('e') < 0 && string.indexOf('E') < 0) {
			while (string.endsWith("0")) {
				string = string.substring(0, string.length() - 1);
			}
			if (string.endsWith(".")) {
				string = string.substring(0, string.length() - 1);
			}
		}
		return string;
	}
	
	protected static void testValidity(Object obj) throws JSONException {
		if(false == ObjectUtil.isValidIfNumber(obj)){
			throw new JSONException("JSON does not allow non-finite numbers.");
		}
	}
	
	protected static String valueToString(Object value) throws JSONException {
		if (value == null || value.equals(null)) {
			return "null";
		}
		if (value instanceof JSONString) {
			Object object;
			try {
				object = ((JSONString) value).toJSONString();
			} catch (Exception e) {
				throw new JSONException(e);
			}
			if (object instanceof String) {
				return (String) object;
			}
			throw new JSONException("Bad value from toJSONString: " + object);
		}
		if (value instanceof Number) {
			return numberToString((Number) value);
		}
		if (value instanceof Boolean || value instanceof JSONObject || value instanceof JSONArray) {
			return value.toString();
		}
		if (value instanceof Map) {
			Map<?, ?> map = (Map<?, ?>) value;
			return new JSONObject(map).toString();
		}
		if (value instanceof Collection) {
			Collection<?> coll = (Collection<?>) value;
			return new JSONArray(coll).toString();
		}
		if (value.getClass().isArray()) {
			return new JSONArray(value).toString();
		}
		return quote(value.toString());
	}
	
	protected static Object stringToValue(String string) {
		Double d;
		if(null == string || "null".equalsIgnoreCase(string)){
			return JSONNull.NULL;
		}
		
		if (EMPTY.equals(string)) {
			return string;
		}
		if ("true".equalsIgnoreCase(string)) {
			return Boolean.TRUE;
		}
		if ("false".equalsIgnoreCase(string)) {
			return Boolean.FALSE;
		}

		char b = string.charAt(0);
		if ((b >= '0' && b <= '9') || b == '-') {
			try {
				if (string.indexOf('.') > -1 || string.indexOf('e') > -1 || string.indexOf('E') > -1) {
					d = Double.valueOf(string);
					if (!d.isInfinite() && !d.isNaN()) {
						return d;
					}
				} else {
					Long myLong = new Long(string);
					if (string.equals(myLong.toString())) {
						if (myLong == myLong.intValue()) {
							return myLong.intValue();
						} else {
							return myLong;
						}
					}
				}
			} catch (Exception ignore) {
			}
		}
		return string;
	}
}
