package co.jufeng.json;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import co.jufeng.json.util.BeanUtil;
import co.jufeng.json.util.DateUtil;
import co.jufeng.json.util.TypeUtil;

public class JSONObject extends JSON implements JSONStreamAware, IJSONObject {
	
	private static final int          DEFAULT_INITIAL_CAPACITY = 8;

	private final Map<Object, Object> map;

	public JSONObject(){
        this(DEFAULT_INITIAL_CAPACITY, false);
    }

    public JSONObject(boolean ordered){
        this(DEFAULT_INITIAL_CAPACITY, ordered);
    }

    public JSONObject(int initialCapacity){
        this(initialCapacity, false);
    }
	
	public JSONObject(int initialCapacity, boolean ordered){
        if (ordered) {
        	this.map = new LinkedHashMap<Object, Object>(initialCapacity);
        } else {
        	this.map = new HashMap<Object, Object>(initialCapacity);
        }
    }
	
	public JSONObject(Map<?, ?> map) {
		this();
		if (map != null) {
			for (final Entry<?, ?> e : map.entrySet()) {
				final Object value = e.getValue();
				if (value != null) {
					this.map.put(e.getKey(), JSON.toJSON(value));
				}
			}
		}
	}

	public JSONObject(JSONObject jsonObject, String... names) {
		this();
		for (String name : names) {
			try {
				this.putOnce(name, jsonObject.get(name));
			} catch (Exception ignore) {
			}
		}
	}

	public JSONObject(JSONTokener x) throws JSONException {
		this();
		char c;
		String key;

		if (x.nextClean() != '{') {
			throw x.syntaxError("A JSONObject text must begin with '{'");
		}
		for (;;) {
			c = x.nextClean();
			switch (c) {
				case 0:
					throw x.syntaxError("A JSONObject text must end with '}'");
				case '}':
					return;
				default:
					x.back();
					key = x.nextValue().toString();
			}

			c = x.nextClean();
			if (c != ':') {
				throw x.syntaxError("Expected a ':' after a key");
			}
			this.putOnce(key, x.nextValue());

			switch (x.nextClean()) {
				case ';':
				case ',':
					if (x.nextClean() == '}') {
						return;
					}
					x.back();
					break;
				case '}':
					return;
				default:
					throw x.syntaxError("Expected a ',' or '}'");
			}
		}
	}

	public JSONObject(Object bean) {
		this();
		this.populateMap(bean);
	}

	public JSONObject(Object object, String names[]) {
		this();
		Class<?> c = object.getClass();
		for (int i = 0; i < names.length; i += 1) {
			String name = names[i];
			try {
				this.putOpt(name, c.getField(name).get(object));
			} catch (Exception ignore) {
			}
		}
	}

	public JSONObject(String jsonString) throws JSONException {
		this(new JSONTokener(jsonString));
	}

	public JSONObject(String baseName, Locale locale) throws JSONException {
		this();
		ResourceBundle bundle = ResourceBundle.getBundle(baseName, locale, Thread.currentThread().getContextClassLoader());

		Enumeration<String> keys = bundle.getKeys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			if (key != null) {
				String[] path = ((String) key).split("\\.");
				int last = path.length - 1;
				JSONObject target = this;
				for (int i = 0; i < last; i += 1) {
					String segment = path[i];
					JSONObject nextTarget = target.getJSONObject(segment);
					if (nextTarget == null) {
						nextTarget = new JSONObject();
						target.put(segment, nextTarget);
					}
					target = nextTarget;
				}
				target.put(path[last], bundle.getString((String) key));
			}
		}
	}

	@Override
	public int size() {
		return this.map.size();
	}

	@Override
	public boolean isEmpty() {
		return this.map.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		return this.map.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return this.map.containsValue(value);
	}

	@Override
	public Object remove(Object key) {
		return this.map.remove(key);
	}

	@Override
	public void putAll(Map<? extends Object, ? extends Object> m) {
		this.map.putAll(m);
	}
	
	@Override
	public void clear() {
		this.map.clear();
	}

	@Override
	public Collection<Object> values() {
		return this.map.values();
	}

	@Override
	public Set<java.util.Map.Entry<Object, Object>> entrySet() {
		return this.map.entrySet();
	}

	public boolean isNull(String key) {
		return JSONNull.NULL.equals(this.get(key));
	}

	public Set<Object> keySet() {
		return this.map.keySet();
	}
	
	public <E extends Enum<E>> E getEnum(Class<E> clazz, String key) {
		return this.getEnum(clazz, key, null);
	}

	public <E extends Enum<E>> E getEnum(Class<E> clazz, String key, E defaultValue) {
		return TypeUtil.toEnum(clazz, this.get(key), defaultValue);
	}

	@Override
	public Object get(Object key) {
		return this.map.get(key);
		
	}
	
	@Override
	public Object get(String key, String defaultValue){
		Object obj = this.map.get(key);
		return null == obj ? defaultValue : obj;
	}
	
	public JSONObject getJSONObject(String key) {
		Object obj = this.get(key);
		return obj instanceof JSONObject ? (JSONObject) obj : null;
	}
	
	public JSONArray getJSONArray(String key) {
		Object obj = this.get(key);
		return obj instanceof JSONArray ? (JSONArray) obj : null;
	}
	
	public <T> T getObject(String key, Class<T> clazz) {
        Object obj = map.get(key);
        return TypeUtil.castToJavaBean(obj, clazz);
    }

    public Boolean getBoolean(String key) {
        Object value = get(key);

        if (value == null) {
            return null;
        }
        return TypeUtil.castToBoolean(value);
    }

    public byte[] getBytes(String key) {
        Object value = get(key);

        if (value == null) {
            return null;
        }

        return TypeUtil.castToBytes(value);
    }

    public boolean getBooleanValue(String key) {
        Object value = get(key);

        if (value == null) {
            return false;
        }

        return TypeUtil.castToBoolean(value).booleanValue();
    }

    public Byte getByte(String key) {
        Object value = get(key);

        return TypeUtil.castToByte(value);
    }

    public byte getByteValue(String key) {
        Object value = get(key);

        if (value == null) {
            return 0;
        }

        return TypeUtil.castToByte(value).byteValue();
    }

    public Short getShort(String key) {
        Object value = get(key);

        return TypeUtil.castToShort(value);
    }

    public short getShortValue(String key) {
        Object value = get(key);

        if (value == null) {
            return 0;
        }

        return TypeUtil.castToShort(value).shortValue();
    }

    public Integer getInteger(String key) {
        Object value = get(key);

        return TypeUtil.castToInt(value);
    }

    public int getIntValue(String key) {
        Object value = get(key);

        if (value == null) {
            return 0;
        }

        return TypeUtil.castToInt(value).intValue();
    }

    public Long getLong(String key) {
        Object value = get(key);

        return TypeUtil.castToLong(value);
    }

    public long getLongValue(String key) {
        Object value = get(key);

        if (value == null) {
            return 0L;
        }

        return TypeUtil.castToLong(value).longValue();
    }

    public Float getFloat(String key) {
        Object value = get(key);

        return TypeUtil.castToFloat(value);
    }

    public float getFloatValue(String key) {
        Object value = get(key);

        if (value == null) {
            return 0F;
        }

        return TypeUtil.castToFloat(value).floatValue();
    }

    public Double getDouble(String key) {
        Object value = get(key);

        return TypeUtil.castToDouble(value);
    }

    public double getDoubleValue(String key) {
        Object value = get(key);

        if (value == null) {
            return 0D;
        }

        return TypeUtil.castToDouble(value);
    }

    public BigDecimal getBigDecimal(String key) {
        Object value = get(key);

        return TypeUtil.castToBigDecimal(value);
    }

    public BigInteger getBigInteger(String key) {
        Object value = get(key);

        return TypeUtil.castToBigInteger(value);
    }

    public String getString(String key) {
        Object value = get(key);

        if (value == null) {
            return null;
        }

        return value.toString();
    }

    public Date getDate(String key) {
        Object value = get(key);

        return TypeUtil.castToDate(value);
    }

    public java.sql.Date getSqlDate(String key) {
        Object value = get(key);

        return TypeUtil.castToSqlDate(value);
    }

    public java.sql.Timestamp getTimestamp(String key) {
        Object value = get(key);

        return TypeUtil.castToTimestamp(value);
    }

    private void populateMap(Object bean) {
		Class<?> klass = bean.getClass();

		boolean includeSuperClass = klass.getClassLoader() != null;

		Method[] methods = includeSuperClass ? klass.getMethods() : klass.getDeclaredMethods();
		for (int i = 0; i < methods.length; i += 1) {
			try {
				Method method = methods[i];
				if (Modifier.isPublic(method.getModifiers())) {
					String name = method.getName();
					String key = "";
					if (name.startsWith("get")) {
						if ("getClass".equals(name) || "getDeclaringClass".equals(name)) {
							key = "";
						} else {
							key = name.substring(3);
						}
					} else if (name.startsWith("is")) {
						key = name.substring(2);
					}
					if (key.length() > 0 && Character.isUpperCase(key.charAt(0)) && method.getParameterTypes().length == 0) {
						if (key.length() == 1) {
							key = key.toLowerCase();
						} else if (!Character.isUpperCase(key.charAt(1))) {
							key = key.substring(0, 1).toLowerCase() + key.substring(1);
						}
						
						Object result = null;
						if(method.toString().indexOf("java.util.Date") >= 0){
							Date date = (Date) method.invoke(bean);
							result = DateUtil.format(date, DateUtil.NORM_DATETIME_PATTERN);
						
						}else if(method.toString().indexOf("java.sql.Date") >= 0){
							java.sql.Date date = (java.sql.Date) method.invoke(bean);
							result = DateUtil.format(date, DateUtil.NORM_DATETIME_PATTERN);
						
						}else if(method.toString().indexOf("java.sql.Timestamp") >= 0){
							java.sql.Timestamp date = (java.sql.Timestamp) method.invoke(bean);
							result = DateUtil.format(date, DateUtil.NORM_DATETIME_MS_PATTERN);
						
						}else{
							result = method.invoke(bean, (Object[]) null);
						}

						if (result != null) {
							this.map.put(key, JSON.toJSON(result));
						}
					}
				}
			} catch (Exception ignore) {
			}
		}
	}

	public JSONObject put(String key, boolean value) throws JSONException {
		this.put(key, value ? Boolean.TRUE : Boolean.FALSE);
		return this;
	}

	public JSONObject put(String key, Collection<?> value) throws JSONException {
		this.put(key, new JSONArray(value));
		return this;
	}

	public JSONObject put(String key, double value) throws JSONException {
		this.put(key, new Double(value));
		return this;
	}

	public JSONObject put(String key, int value) throws JSONException {
		this.put(key, new Integer(value));
		return this;
	}

	public JSONObject put(String key, long value) throws JSONException {
		this.put(key, new Long(value));
		return this;
	}

	public JSONObject put(String key, Map<?, ?> value) throws JSONException {
		this.put(key, JSON.toJSON(value));
		return this;
	}

	@Override
	public JSONObject put(Object key, Object value) throws JSONException {
		if (key == null) {
			throw new NullPointerException("Null key.");
		}
		if (value != null) {
			JSON.testValidity(value);
			this.map.put(key, value);
		} else {
			this.remove(key);
		}
		return this;
	}
	
	

	public JSONObject putOnce(String key, Object value) throws JSONException {
		if (key != null && value != null) {
			if (map.containsKey(key)) {
				throw new JSONException("Duplicate key \"" + key + "\"");
			}
			this.put(key, value);
		}
		return this;
	}

	public JSONObject putOpt(String key, Object value) throws JSONException {
		if (key != null && value != null) {
			this.put(key, value);
		}
		return this;
	}

	public <T> T toBean(Class<T> clazz){
		return BeanUtil.mapToBean(this.map, clazz);
	}
	
	public <T> T toBean(T bean){
		return BeanUtil.fillBeanWithMap(this.map, bean);
	}

	@Override
	public String toString() {
		try {
			return this.toJSONString(0);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public String toJSONString(int indentFactor) {
		StringWriter w = new StringWriter();
		synchronized (w.getBuffer()) {
			return this.write(w, indentFactor, 0).toString();
		}
	}

	@Override
	public Writer write(Writer writer) throws JSONException {
		return this.write(writer, 0, 0);
	}

	@Override
	public Writer write(Writer writer, int indentFactor, int indent) {
		try {
			boolean commanate = false;
			final int length = this.size();
			Iterator<Object> keys = this.map.keySet().iterator();
			writer.write('{');

			if (length == 1) {
				Object key = keys.next();
				writer.write(JSON.quote(key.toString()));
				writer.write(':');
				if (indentFactor > 0) {
					writer.write(' ');
				}
				JSON.writeValue(writer, this.map.get(key), indentFactor, indent);
			} else if (length != 0) {
				final int newindent = indent + indentFactor;
				while (keys.hasNext()) {
					Object key = keys.next();
					if (commanate) {
						writer.write(',');
					}
					if (indentFactor > 0) {
						writer.write('\n');
					}
					JSON.indent(writer, newindent);
					writer.write(JSON.quote(key.toString()));
					writer.write(':');
					if (indentFactor > 0) {
						writer.write(' ');
					}
					JSON.writeValue(writer, this.map.get(key), indentFactor, newindent);
					commanate = true;
				}
				if (indentFactor > 0) {
					writer.write('\n');
				}
				JSON.indent(writer, indent);
			}
			writer.write('}');
			return writer;
		} catch (IOException exception) {
			throw new JSONException(exception);
		}
	}
}