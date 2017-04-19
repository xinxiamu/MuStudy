package co.jufeng.json.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class StringUtil {
	public static final String DOT = ".";
	public static final String SLASH = "/";
	public static final String EMPTY = "";
	public static boolean isBlank(String str) {
		int length;
		if ((str == null) || ((length = str.length()) == 0)) {
			return true;
		}

		for (int i = 0; i < length; i++) {
			if (false == Character.isWhitespace(str.charAt(i))) {
				return false;
			}
		}

		return true;
	}

	public static boolean hasBlank(String... strs) {
		if (CollectionUtil.isEmpty(strs)) {
			return true;
		}

		for (String str : strs) {
			if (isBlank(str)) {
				return true;
			}
		}
		return false;
	}
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}

	public static String removeSuffix(String str, String suffix) {
		if(isEmpty(str) || isEmpty(suffix)){
			return str;
		}
		
		if (str.endsWith(suffix)) {
			return str.substring(0, str.length() - suffix.length());
		}
		return str;
	}

	public static String removeSuffixIgnoreCase(String str, String suffix) {
		if(isEmpty(str) || isEmpty(suffix)){
			return str;
		}
		
		if (str.toLowerCase().endsWith(suffix.toLowerCase())) {
			return str.substring(0, str.length() - suffix.length());
		}
		return str;
	}

	public static String cleanBlank(String str) {
		if (str == null) {
			return null;
		}

		return str.replaceAll("\\s*", EMPTY);
	}

	public static List<String> split(String str, char separator) {
		return split(str, separator, 0);
	}

	public static List<String> split(String str, char separator, int limit) {
		if (str == null) {
			return null;
		}
		List<String> list = new ArrayList<String>(limit == 0 ? 16 : limit);
		if (limit == 1) {
			list.add(str);
			return list;
		}

		boolean isNotEnd = true; 
		int strLen = str.length();
		StringBuilder sb = new StringBuilder(strLen);
		for (int i = 0; i < strLen; i++) {
			char c = str.charAt(i);
			if (isNotEnd && c == separator) {
				list.add(sb.toString());
				sb.delete(0, sb.length());

				if (limit != 0 && list.size() == limit - 1) {
					isNotEnd = false;
				}
			} else {
				sb.append(c);
			}
		}
		list.add(sb.toString());
		return list;
	}

	public static String[] split(String str, String delimiter) {
		if (str == null) {
			return null;
		}
		if (str.trim().length() == 0) {
			return new String[] { str };
		}

		int dellen = delimiter.length();
		int maxparts = (str.length() / dellen) + 2;
		int[] positions = new int[maxparts];

		int i, j = 0;
		int count = 0;
		positions[0] = -dellen;
		while ((i = str.indexOf(delimiter, j)) != -1) {
			count++;
			positions[count] = i;
			j = i + dellen;
		}
		count++;
		positions[count] = str.length();

		String[] result = new String[count];

		for (i = 0; i < count; i++) {
			result[i] = str.substring(positions[i] + dellen, positions[i + 1]);
		}
		return result;
	}

	public static String sub(String string, int fromIndex, int toIndex) {
		int len = string.length();

		if (fromIndex < 0) {
			fromIndex = len + fromIndex;

			if (toIndex == 0) {
				toIndex = len;
			}
		}

		if (toIndex < 0) {
			toIndex = len + toIndex;
		}

		if (toIndex < fromIndex) {
			int tmp = fromIndex;
			fromIndex = toIndex;
			toIndex = tmp;
		}

		if (fromIndex == toIndex) {
			return EMPTY;
		}

		char[] strArray = string.toCharArray();
		char[] newStrArray = Arrays.copyOfRange(strArray, fromIndex, toIndex);
		return new String(newStrArray);
	}

	public static String subPre(String string, int toIndex) {
		return sub(string, 0, toIndex);
	}
	
	public static String subSuf(String string, int fromIndex) {
		if (isEmpty(string)) {
			return null;
		}
		return sub(string, fromIndex, string.length());
	}
	public static String format(String template, Object... values) {
		if (CollectionUtil.isEmpty(values) || isBlank(template)) {
			return template;
		}

		final StringBuilder sb = new StringBuilder();
		final int length = template.length();

		int valueIndex = 0;
		char currentChar;
		for (int i = 0; i < length; i++) {
			if (valueIndex >= values.length) {
				sb.append(sub(template, i, length));
				break;
			}

			currentChar = template.charAt(i);
			if (currentChar == '{') {
				final char nextChar = template.charAt(++i);
				if (nextChar == '}') {
					sb.append(values[valueIndex++]);
				} else {
					sb.append('{').append(nextChar);
				}
			} else {
				sb.append(currentChar);
			}

		}

		return sb.toString();
	}

	public static String format(String template, Map<?, ?> map) {
		if (null == map || map.isEmpty()) {
			return template;
		}

		for (Entry<?, ?> entry : map.entrySet()) {
			template = template.replace("{" + entry.getKey() + "}", entry.getValue().toString());
		}
		return template;
	}
}
