package com.j8.number;

import java.text.DecimalFormat;

public class NumberUtils {

	private NumberUtils() {
	}

	/**
	 * 格式化数字格式。保留小数会精确四舍五入。
	 * @param pattern	格式模式，如：###,###.###或$###,###.###或000000.000
	 * @param value	
	 * @return
	 */
	static public String customFormat(String pattern, double value) {
		DecimalFormat myFormatter = new DecimalFormat(pattern);
		String output = myFormatter.format(value);
		return output;
	}
	
	public static void main(String[] args) {
		float a = 0.242356f;
		String str = customFormat("###.##", a);
		System.out.println(str);

	}
}
