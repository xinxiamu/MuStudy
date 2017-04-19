package co.jufeng.json;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import co.jufeng.json.util.TypeUtil;

public class JSONArray implements JSONStreamAware, Iterable<Object>{

	private final ArrayList<Object> myArrayList;

	public JSONArray() {
		this.myArrayList = new ArrayList<Object>();
	}

	public JSONArray(JSONTokener x) throws JSONException {
		this();
		if (x.nextClean() != '[') {
			throw x.syntaxError("A JSONArray text must start with '['");
		}
		if (x.nextClean() != ']') {
			x.back();
			for (;;) {
				if (x.nextClean() == ',') {
					x.back();
					this.myArrayList.add(JSONNull.NULL);
				} else {
					x.back();
					this.myArrayList.add(x.nextValue());
				}
				switch (x.nextClean()) {
					case ',':
						if (x.nextClean() == ']') {
							return;
						}
						x.back();
						break;
					case ']':
						return;
					default:
						throw x.syntaxError("Expected a ',' or ']'");
				}
			}
		}
	}

	public JSONArray(String source) throws JSONException {
		this(new JSONTokener(source));
	}

	public JSONArray(Collection<?> collection) {
		this.myArrayList = new ArrayList<Object>();
		if (collection != null) {
			for (Object o : collection) {
				this.myArrayList.add(JSON.toJSON(o));
			}
		}
	}

	public JSONArray(Object array) throws JSONException {
		this();
		if (array.getClass().isArray()) {
			int length = Array.getLength(array);
			for (int i = 0; i < length; i += 1) {
				this.put(JSON.toJSON(Array.get(array, i)));
			}
		} else {
			throw new JSONException("JSONArray initial value should be a string or collection or array.");
		}
	}

	@Override
	public Iterator<Object> iterator() {
		return myArrayList.iterator();
	}

	public boolean isNull(int index) {
		return JSONNull.NULL.equals(this.get(index));
	}

	public String join(String separator) throws JSONException {
		int len = this.length();
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < len; i += 1) {
			if (i > 0) {
				sb.append(separator);
			}
			sb.append(JSON.valueToString(this.myArrayList.get(i)));
		}
		return sb.toString();
	}

	public int length() {
		return this.myArrayList.size();
	}

	public Object get(int index) {
		return this.myArrayList.get(index);
	}
	
	public Object get(Integer index, Object defaultValue) {
		return (index < 0 || index >= this.length()) ? defaultValue : this.myArrayList.get(index);
	}
	
	public <E extends Enum<E>> E getEnum(Class<E> clazz, int index) {
		return this.getEnum(clazz, index, null);
	}

	public <E extends Enum<E>> E getEnum(Class<E> clazz, int index, E defaultValue) {
		return TypeUtil.toEnum(clazz, this.get(index), defaultValue);
	}

	public JSONArray getJSONArray(int index) {
		Object o = this.get(index);
		return o instanceof JSONArray ? (JSONArray) o : null;
	}

	public JSONObject getJSONObject(int index) {
		Object o = this.get(index);
		return o instanceof JSONObject ? (JSONObject) o : null;
	}

	public JSONArray put(boolean value) {
		this.put(value ? Boolean.TRUE : Boolean.FALSE);
		return this;
	}

	public JSONArray put(Collection<?> value) {
		this.put(new JSONArray(value));
		return this;
	}

	public JSONArray put(double value) throws JSONException {
		Double d = new Double(value);
		JSON.testValidity(d);
		this.put(d);
		return this;
	}

	public JSONArray put(int value) {
		this.put(new Integer(value));
		return this;
	}

	public JSONArray put(long value) {
		this.put(new Long(value));
		return this;
	}

	public JSONArray put(Map<?, ?> value) {
		this.put(new JSONObject(value));
		return this;
	}

	public JSONArray put(Object value) {
		this.myArrayList.add(value);
		return this;
	}

	public JSONArray put(int index, boolean value) throws JSONException {
		this.put(index, value ? Boolean.TRUE : Boolean.FALSE);
		return this;
	}

	public JSONArray put(int index, Collection<?> value) throws JSONException {
		this.put(index, new JSONArray(value));
		return this;
	}

	public JSONArray put(int index, double value) throws JSONException {
		this.put(index, new Double(value));
		return this;
	}

	public JSONArray put(int index, int value) throws JSONException {
		this.put(index, new Integer(value));
		return this;
	}

	public JSONArray put(int index, long value) throws JSONException {
		this.put(index, new Long(value));
		return this;
	}

	public JSONArray put(int index, Map<?, ?> value) throws JSONException {
		this.put(index, new JSONObject(value));
		return this;
	}

	public JSONArray put(int index, Object value) throws JSONException {
		JSON.testValidity(value);
		if (index < 0) {
			throw new JSONException("JSONArray[" + index + "] not found.");
		}
		if (index < this.length()) {
			this.myArrayList.set(index, value);
		} else {
			while (index != this.length()) {
				this.put(JSONNull.NULL);
			}
			this.put(value);
		}
		return this;
	}

	public Object remove(int index) {
		return index >= 0 && index < this.length() ? this.myArrayList.remove(index) : null;
	}

	public JSONObject toJSONObject(JSONArray names) throws JSONException {
		if (names == null || names.length() == 0 || this.length() == 0) {
			return null;
		}
		JSONObject jo = new JSONObject();
		for (int i = 0; i < names.length(); i += 1) {
			jo.put(names.get(i), this.get(i));
		}
		return jo;
	}

	/**
	 * Make a JSON text of this JSONArray. For compactness, no unnecessary whitespace is added. If it is not possible to produce a syntactically correct JSON text then null will be returned instead.
	 * This could occur if the array contains an invalid number.
	 * <p>
	 * Warning: This method assumes that the data structure is acyclical.
	 *
	 * @return a printable, displayable, transmittable representation of the array.
	 */
	@Override
	public String toString() {
		try {
			return this.toJSONString(0);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public String toJSONString(int indentFactor) throws JSONException {
		StringWriter sw = new StringWriter();
		synchronized (sw.getBuffer()) {
			return this.write(sw, indentFactor, 0).toString();
		}
	}

	@Override
	public Writer write(Writer writer) throws JSONException {
		return this.write(writer, 0, 0);
	}

	@Override
	public Writer write(Writer writer, int indentFactor, int indent) throws JSONException {
		try {
			boolean commanate = false;
			int length = this.length();
			writer.write('[');

			if (length == 1) {
				JSON.writeValue(writer, this.myArrayList.get(0), indentFactor, indent);
			} else if (length != 0) {
				final int newindent = indent + indentFactor;

				for (int i = 0; i < length; i += 1) {
					if (commanate) {
						writer.write(',');
					}
					if (indentFactor > 0) {
						writer.write('\n');
					}
					JSON.indent(writer, newindent);
					JSON.writeValue(writer, this.myArrayList.get(i), indentFactor, newindent);
					commanate = true;
				}
				if (indentFactor > 0) {
					writer.write('\n');
				}
				JSON.indent(writer, indent);
			}
			writer.write(']');
			return writer;
		} catch (IOException e) {
			throw new JSONException(e);
		}
	}
}
