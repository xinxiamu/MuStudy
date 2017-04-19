package co.jufeng.json.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import co.jufeng.json.JSONObject;
import co.jufeng.json.lang.BasicType;

public class TypeUtil {
	
	private TypeUtil() {
	}
	
	public static Object parse(Class<?> clazz, Object value) {
		try {
			return clazz.cast(value);
		} catch (ClassCastException e) {
//			if(Date.class == value){
//				return value;
//			}
//			
//			if(java.sql.Date.class == value){
//				return value;
//			}
//			
//			if(Boolean.class == value){
//				return value;
//			}
			
			String valueStr = String.valueOf(value);
			Object result = parseBasic(clazz, valueStr);
			if(result != null) {
				return result;
			}
			if(Timestamp.class.isAssignableFrom(clazz)){
				Date date = DateUtil.parse(valueStr);
				Timestamp timestamp = new Timestamp(date.getTime());
				return timestamp;
				
			}else if(Date.class.isAssignableFrom(clazz)) {
				
				return DateUtil.parse(valueStr);
				
			}else if(clazz == BigInteger.class){
				return new BigInteger(valueStr);
				
			}else if(clazz == BigDecimal.class) {
				return new BigDecimal(valueStr);
				
			}else if(clazz == byte[].class) {
				return valueStr.getBytes();
				
			}else if(clazz == Timestamp.class) {
				return valueStr.getBytes();
				
			}
			return value;
		}
	}
	
	public static Object parseBasic(Class<?> clazz, String valueStr) {
		if(null == clazz || null == valueStr) {
			return null;
		}
		
		if(clazz.isAssignableFrom(String.class)){
			return valueStr;
		}
		
		BasicType basicType = null;
		try {
			basicType = BasicType.valueOf(clazz.getSimpleName().toUpperCase());
			System.out.println(basicType);
		} catch (Exception e) {
			return null;
		}
		
		switch (basicType) {
			case BYTE:
				if(clazz == byte.class) {
					return Byte.parseByte(valueStr);
				}
				return Byte.valueOf(valueStr);
			case SHORT:
				if(clazz == short.class) {
					return Short.parseShort(valueStr);
				}
				return Short.valueOf(valueStr);
			case INT:
				return Integer.parseInt(valueStr);
			case INTEGER:
				return Integer.valueOf(valueStr);
			case LONG:
				if(clazz == long.class) {
					return new BigDecimal(valueStr).longValue();
				}
				return Long.valueOf(valueStr);
			case DOUBLE:
				if(clazz == double.class) {
					return new BigDecimal(valueStr).doubleValue();
				}
			case FLOAT:
				if(clazz == float.class) {
					return Float.parseFloat(valueStr);
				}
				return Float.valueOf(valueStr);
			case BOOLEAN:
				if(clazz == boolean.class) {
					return Boolean.parseBoolean(valueStr);
				}
				return Boolean.valueOf(valueStr);
			case CHAR:
				return valueStr.charAt(0);
			case DATE:
				return DateUtil.parse(valueStr, DateUtil.NORM_DATETIME_PATTERN);
			case CHARACTER:
				return Character.valueOf(valueStr.charAt(0));
			default:
				return null;
		}
	}

	public static String toStr(Object value, String defaultValue) {
		if(null == value) {
			return defaultValue;
		}
		if(value instanceof String) {
			return (String)value;
		}
		return value.toString();
	}
	
	public static Character toChar(Object value, Character defaultValue) {
		if(null == value) {
			return defaultValue;
		}
		if(value instanceof Character) {
			return (Character)value;
		}
		
		final String valueStr = toStr(value, null);
		return StringUtil.isEmpty(valueStr) ? defaultValue : valueStr.charAt(0);
	}

	public static Byte castToByte(Object value) {
		return castToByte(value, null);
    }
	public static Byte castToByte(Object value, Byte defaultValue) {
		if (value == null){
			return defaultValue;
		}
		if(value instanceof Byte) {
			return (Byte)value;
		}
		if(value instanceof Number){
			return ((Number) value).byteValue();
		}
		final String valueStr = toStr(value, null);
		if (StringUtil.isBlank(valueStr)){
			return defaultValue;
		}
		try {
			return Byte.parseByte(valueStr);
		} catch (Exception e) {
			return defaultValue;
		}
	}
	
	public static byte[] castToBytes(Object value) {
		return castToBytes(value, null);
    }
	
	public static byte[] castToBytes(Object value, byte[] defaultValue) {
		if (value == null){
			return defaultValue;
		}
		
        if (StringUtil.isBlank(value.toString())){
			return defaultValue;
		}
        
        try {
			if (value instanceof byte[]) {
			    return (byte[]) value;
			}
			
			if (value instanceof String) {
//			    return Base64.getDecoder().decode((String) value);
			}
			return defaultValue;
		} catch (Exception e) {
			return defaultValue;
		}
    }
	
	public static Short castToShort(Object value) {
		return castToShort(value, null);
	}
	public static Short castToShort(Object value, Short defaultValue) {
		if (value == null){
			return defaultValue;
		}
		if(value instanceof Short) {
			return (Short)value;
		}
		if(value instanceof Number){
			return ((Number) value).shortValue();
		}
		final String valueStr = toStr(value, null);
		if (StringUtil.isBlank(valueStr)){
			return defaultValue;
		}
		try {
			return Short.parseShort(valueStr.trim());
		} catch (Exception e) {
			return defaultValue;
		}
	}
	
	public static Number toNumber(Object value, Number defaultValue) {
		if (value == null){
			return defaultValue;
		}
		if(value instanceof Number) {
			return (Number)value;
		}
		final String valueStr = toStr(value, null);
		if (StringUtil.isBlank(valueStr)){
			return defaultValue;
		}
		try {
			return NumberFormat.getInstance().parse(valueStr);
		} catch (Exception e) {
			return defaultValue;
		}
	}
	
	public static Integer castToInt(Object value) {
		return castToInt(value, null);
	}
	
	public static Integer castToInt(Object value, Integer defaultValue) {
		if (value == null){
			return defaultValue;
		}
		if(value instanceof Integer) {
			return (Integer)value;
		}
		if(value instanceof Number){
			return ((Number) value).intValue();
		}
		final String valueStr = toStr(value, null);
		if (StringUtil.isBlank(valueStr)){
			return defaultValue;
		}
		try {
			return Integer.parseInt(valueStr.trim());
		} catch (Exception e) {
			return defaultValue;
		}
	}
	
	public static Integer[] toIntArray(boolean isIgnoreConvertError, Object... values) {
		if(CollectionUtil.isEmpty(values)) {
			return new Integer[]{};
		}
		final Integer[] ints = new Integer[values.length];
		for(int i = 0; i < values.length; i++) {
			final Integer v = castToInt(values[i], null);
			if(null == v && isIgnoreConvertError == false) {
				throw new RuntimeException(StringUtil.format("Convert [{}] to Integer error!", values[i]));
			}
			ints[i] = v;
		}
		return ints;
	}

	public static Long castToLong(Object value) {
		return Long.valueOf(value.toString());
	}
	
	public static Long castToLong(Object value, Long defaultValue) {
		if (value == null){
			return defaultValue;
		}
		if(value instanceof Long) {
			return (Long)value;
		}
		if(value instanceof Number){
			return ((Number) value).longValue();
		}
		final String valueStr = toStr(value, null);
		if (StringUtil.isBlank(valueStr)){
			return defaultValue;
		}
		try {
			return new BigDecimal(valueStr.trim()).longValue();
		} catch (Exception e) {
			return defaultValue;
		}
	}
	
	public static Long[] toLongArray(boolean isIgnoreConvertError, Object... values) {
		if(CollectionUtil.isEmpty(values)) {
			return new Long[]{};
		}
		final Long[] longs = new Long[values.length];
		for(int i = 0; i < values.length; i++) {
			final Long v = castToLong(values[i], null);
			if(null == v && isIgnoreConvertError == false) {
				throw new RuntimeException(StringUtil.format("Convert [{}] to Long error!", values[i]));
			}
			longs[i] = v;
		}
		return longs;
	}
	
	public static Double castToDouble(Object value) {
		return castToDouble(value, null);
	}

	public static Double castToDouble(Object value, Double defaultValue) {
		if (value == null){
			return defaultValue;
		}
		if(value instanceof Double) {
			return (Double)value;
		}
		if(value instanceof Number){
			return ((Number) value).doubleValue();
		}
		final String valueStr = toStr(value, null);
		if (StringUtil.isBlank(valueStr)){
			return defaultValue;
		}
		try {
			return new BigDecimal(valueStr.trim()).doubleValue();
		} catch (Exception e) {
			return defaultValue;
		}
	}
	
	public static Double[] toDoubleArray(boolean isIgnoreConvertError, Object... values) {
		if(CollectionUtil.isEmpty(values)) {
			return new Double[]{};
		}
		final Double[] doubles = new Double[values.length];
		for(int i = 0; i < values.length; i++) {
			final Double v = castToDouble(values[i], null);
			if(null == v && isIgnoreConvertError == false) {
				throw new RuntimeException(StringUtil.format("Convert [{}] to Double error!", values[i]));
			}
			doubles[i] = v;
		}
		return doubles;
	}
	
	public static Float castToFloat(Object value) {
		return castToFloat(value);
	}
	
	public static Float castToFloat(Object value, Float defaultValue) {
		if (value == null){
			return defaultValue;
		}
		if(value instanceof Float) {
			return (Float)value;
		}
		if(value instanceof Number){
			return ((Number) value).floatValue();
		}
		final String valueStr = toStr(value, null);
		if (StringUtil.isBlank(valueStr)){
			return defaultValue;
		}
		try {
			return Float.parseFloat(valueStr.trim());
		} catch (Exception e) {
			return defaultValue;
		}
	}
	
	public static <T> Float[] toFloatArray(boolean isIgnoreConvertError, Object... values) {
		if(CollectionUtil.isEmpty(values)) {
			return new Float[]{};
		}
		final Float[] floats = new Float[values.length];
		for(int i = 0; i < values.length; i++) {
			final Float v = castToFloat(values[i], null);
			if(null == v && isIgnoreConvertError == false) {
				throw new RuntimeException(StringUtil.format("Convert [{}] to Float error!", values[i]));
			}
			floats[i] = v;
		}
		return floats;
	}

	public static Boolean castToBoolean(Object value) {
		return castToBoolean(value, null);
	}
	public static Boolean castToBoolean(Object value, Boolean defaultValue) {
		if (value == null){
			return defaultValue;
		}
		if(value instanceof Boolean) {
			return (Boolean)value;
		}
		final String valueStr = toStr(value, null);
		if (StringUtil.isBlank(valueStr)){
			return defaultValue;
		}
		try {
			return Boolean.parseBoolean(valueStr.trim());
		} catch (Exception e) {
			return defaultValue;
		}
	}
	
	public static Boolean[] toBooleanArray(boolean isIgnoreConvertError, Object... values) {
		if(CollectionUtil.isEmpty(values)) {
			return new Boolean[]{};
		}
		final Boolean[] bools = new Boolean[values.length];
		for(int i = 0; i < values.length; i++) {
			final Boolean v = castToBoolean(values[i], null);
			if(null == v && isIgnoreConvertError == false) {
				throw new RuntimeException(StringUtil.format("Convert [{}] to Boolean error!", values[i]));
			}
			bools[i] = v;
		}
		return bools;
	}
	
	public static <E extends Enum<E>> E toEnum(Class<E> clazz, Object value, E defaultValue) {
		if (value == null){
			return defaultValue;
		}
		if (clazz.isAssignableFrom(value.getClass())) {
			@SuppressWarnings("unchecked")
			E myE = (E) value;
			return myE;
		}
		final String valueStr = toStr(value, null);
		if (StringUtil.isBlank(valueStr)){
			return defaultValue;
		}
		try {
			return Enum.valueOf(clazz,valueStr);
		} catch (Exception e) {
			return defaultValue;
		}
	}
	
	public static BigInteger castToBigInteger(Object value) {
		return castToBigInteger(value, null);
	}
	
	public static BigInteger castToBigInteger(Object value, BigInteger defaultValue) {
		if (value == null){
			return defaultValue;
		}
		if(value instanceof BigInteger) {
			return (BigInteger)value;
		}
		if(value instanceof Long) {
			return BigInteger.valueOf((Long)value);
		}
		final String valueStr = toStr(value, null);
		if (StringUtil.isBlank(valueStr)){
			return defaultValue;
		}
		try {
			return new BigInteger(valueStr);
		} catch (Exception e) {
			return defaultValue;
		}
	}
	
	public static BigDecimal castToBigDecimal(Object value) {
		return castToBigDecimal(value, null);
	}
	
	public static BigDecimal castToBigDecimal(Object value, BigDecimal defaultValue) {
		if (value == null){
			return defaultValue;
		}
		if(value instanceof BigDecimal) {
			return (BigDecimal)value;
		}
		if(value instanceof Long) {
			return new BigDecimal((Long)value);
		}
		if(value instanceof Double) {
			return new BigDecimal((Double)value);
		}
		if(value instanceof Integer) {
			return new BigDecimal((Integer)value);
		}
		final String valueStr = toStr(value, null);
		if (StringUtil.isBlank(valueStr)){
			return defaultValue;
		}
		try {
			return new BigDecimal(valueStr);
		} catch (Exception e) {
			return defaultValue;
		}
	}
	
	public static Date castToDate(Object value){
		return castToDate(value, DateStyle.YYYY_MM_DD_HH_MM_SS.getValue(), null);
	}
	
	public static Date castToDate(Object value, String dateFormat){
		return castToDate(value, dateFormat, null);
	}
	
	public static Date castToDate(Object value, String dateFormat, Date defaultValue){
		try {
			if(value == null){
				return defaultValue;
			}
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
			return simpleDateFormat.parse(value.toString());
		} catch (ParseException e) {
			return defaultValue;
		}
	}
	
	public static java.sql.Date castToSqlDate(Object value) {
		return castToSqlDate(value, null);
	}
	
	public static java.sql.Date castToSqlDate(Object value, java.sql.Date defaultValue) {
        if (value == null) {
            return null;
        }

        if (value instanceof java.sql.Date) {
            return (java.sql.Date) value;
        }

        if (value instanceof java.util.Date) {
            return new java.sql.Date(((java.util.Date) value).getTime());
        }

        if (value instanceof Calendar) {
            return new java.sql.Date(((Calendar) value).getTimeInMillis());
        }

        long longValue = 0;

        if (value instanceof Number) {
            longValue = ((Number) value).longValue();
        }

        if (value instanceof String) {
            String strVal = (String) value;
            if (strVal.length() == 0
                || "null".equals(strVal)
                || "NULL".equals(strVal)) {
                return null;
            }

            longValue = getTime(value, strVal);
        }

        if (longValue <= 0) {
            return defaultValue;
        }

        return new java.sql.Date(longValue);
    }

	private static long getTime(Object value, String strVal) {
		return DateUtil.parse(value.toString(), DateUtil.NORM_DATETIME_MS_PATTERN).getTime();
	}

	public static java.sql.Timestamp castToTimestamp(Object value) {
		return castToTimestamp(value, null);
	}
    public static java.sql.Timestamp castToTimestamp(Object value, java.sql.Timestamp defaultTimestamp) {
        if (value == null) {
            return null;
        }

        if (value instanceof Calendar) {
            return new java.sql.Timestamp(((Calendar) value).getTimeInMillis());
        }

        if (value instanceof java.sql.Timestamp) {
            return (java.sql.Timestamp) value;
        }

        if (value instanceof java.util.Date) {
            return new java.sql.Timestamp(((java.util.Date) value).getTime());
        }

        long longValue = 0;

        if (value instanceof Number) {
            longValue = ((Number) value).longValue();
        }

        if (value instanceof String) {
            String strVal = (String) value;
            if (strVal.length() == 0 //
                || "null".equals(strVal) //
                || "NULL".equals(strVal)) {
                return null;
            }
            longValue = getTime(value, strVal);
        }

        if (longValue <= 0) {
            return defaultTimestamp;
        }

        return new java.sql.Timestamp(longValue);
    }

	public static String toSBC(String input) {
		return toSBC(input, null);
	}
	
	public static String toSBC(String input, Set<Character> notConvertSet) {
		char c[] = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if(null != notConvertSet && notConvertSet.contains(c[i])) {
				continue;
			}
			
			if (c[i] == ' ') {
				c[i] = '\u3000';
			} else if (c[i] < '\177') {
				c[i] = (char) (c[i] + 65248);

			}
		}
		return new String(c);
	}

	public static String toDBC(String input) {
		return toDBC(input, null);
	}
	
	public static String toDBC(String text, Set<Character> notConvertSet) {
		char c[] = text.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if(null != notConvertSet && notConvertSet.contains(c[i])) {
				continue;
			}
			
			if (c[i] == '\u3000') {
				c[i] = ' ';
			} else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
				c[i] = (char) (c[i] - 65248);
			}
		}
		String returnString = new String(c);
		
		return returnString;
	}
	
	public static String toHex(byte[] bytes) {
		final StringBuilder des = new StringBuilder();
		String tmp = null;
		for (int i = 0; i < bytes.length; i++) {
			tmp = (Integer.toHexString(bytes[i] & 0xFF));
			if (tmp.length() == 1) {
				des.append("0");
			}
			des.append(tmp);
		}
		return des.toString();
	}
	
	public static String convertCharset(String str, String sourceCharset, String destCharset) {
		if(StringUtil.hasBlank(str, sourceCharset, destCharset)) {
			return str;
		}
		
		try {
			return new String(str.getBytes(sourceCharset), destCharset);
		} catch (UnsupportedEncodingException e) {
			return str;
		}
	}
	
	public static <T> T castToJavaBean(Object obj, Class<T> clazz) {
		JSONObject jsonObject = JSONObject.parseObject(obj.toString());
		return JSONObject.toJavaObject(jsonObject, clazz);
    }
	
	public static String digitUppercase(double n) {
		String fraction[] = { "角", "分" };
		String digit[] = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
		String unit[][] = { { "元", "万", "亿" }, { "", "拾", "佰", "仟" } };

		String head = n < 0 ? "负" : "";
		n = Math.abs(n);

		String s = "";
		for (int i = 0; i < fraction.length; i++) {
			s += (digit[(int) (Math.floor(n * 10 * Math.pow(10, i)) % 10)] + fraction[i]).replaceAll("(零.)+", "");
		}
		if (s.length() < 1) {
			s = "整";
		}
		int integerPart = (int) Math.floor(n);

		for (int i = 0; i < unit[0].length && integerPart > 0; i++) {
			String p = "";
			for (int j = 0; j < unit[1].length && n > 0; j++) {
				p = digit[integerPart % 10] + unit[1][j] + p;
				integerPart = integerPart / 10;
			}
			s = p.replaceAll("(零.)*零$", "").replaceAll("^$", "零") + unit[0][i] + s;
		}
		return head + s.replaceAll("(零.)*零元", "元").replaceFirst("(零.)+", "").replaceAll("(零.)+", "零").replaceAll("^整$", "零元整");
	}
}
