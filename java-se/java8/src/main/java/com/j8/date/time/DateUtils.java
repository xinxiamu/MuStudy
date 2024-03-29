package com.j8.date.time;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * java8 date，time
 * @author zmt
 *
 */
public final class DateUtils {

	final static class FormatterStyle {
		/*
		 * yyyy-MM-dd
		 */
		public static final String FORMAT1 = "yyyy-MM-dd";

		/*
		 * yyyy.MM.dd
		 */
		public static final String FORMAT2 = "yyyy.MM.dd";

		/*
		 * yyyy/MM/dd
		 */
		public static final String FORMAT3 = "yyyy/MM/dd";

		/*
		 * yyyy-MM-dd HH:mm
		 */
		public static final String FORMAT4 = "yyyy-MM-dd HH:mm";

		/*
		 * yyyy.MM.dd HH:mm
		 */
		public static final String FORMAT5 = "yyyy.MM.dd HH:mm";

		/*
		 * yyyy/MM/dd HH:mm
		 */
		public static final String FORMAT6 = "yyyy/MM/dd HH:mm";

		/*
		 * yyyy-MM-dd HH:mm:ss
		 */
		public static final String FORMAT7 = "yyyy-MM-dd HH:mm:ss";

		/*
		 * YYYY-MM-dd HH-mm-ss
		 */
		public static final String FORMAT15 = "YYYY-MM-dd HH-mm-ss";

		/*
		 * yyyy.MM.dd HH:mm:ss
		 */
		public static final String FORMAT8 = "yyyy.MM.dd HH:mm:ss";

		/*
		 * yyyy/MM/dd HH:mm:ss
		 */
		public static final String FORMAT9 = "yyyy/MM/dd HH:mm:ss";

		/*
		 * yyyy_MM_dd_HH_mm_ss
		 */
		public static final String FORMAT10 = "yyyy_MM_dd_HH_mm_ss";

		/*
		 * yy-MM-dd
		 */
		public static final String FORMAT11 = "yy-MM-dd";

		/*
		 * yyyyMMdd
		 */
		public static final String FORMAT12 = "yyyyMMdd";

		/*
		 * yyyyMMddHHmmss
		 */
		public static final String FORMAT13 = "yyyyMMddHHmmss";

		/*
		 * yyyyMM
		 */
		public static final String FORMAT14 = "yyyyMM";
	}

	private DateUtils() {
	}

	/**
	 * 格式化获取系统当前时间。
	 * 
	 * @param formatterStyle
	 *            时间格式{@link DateUtils.FormatterStyle}
	 * @return 格式化的系统当前时间
	 */
	public static String getCurrentDateTime(String formatterStyle) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatterStyle);
		String fromatterDateTime = LocalDateTime.now().format(formatter);
		return fromatterDateTime;
	}
	
	/**
	 * 格式化日期。
	 * @param strDateTimeOri	原始字符串格式日期
	 * @param formatterStyle	目标日期格式
	 * @return	新的格式化日期，失败返回null
	 */
	public static String strDateTimeFormatter(String strDateTimeOri, String formatterStyle) {
		String str = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatterStyle);
		try {
			LocalDateTime localDateTime = LocalDateTime.parse(strDateTimeOri, formatter);
			str = localDateTime.toString();
		} catch (DateTimeParseException  e) {
			
		}
	
		return str;
	}

	public static void main(String[] args) {
		System.out.println("----getCurrentDateTime:" + getCurrentDateTime(DateUtils.FormatterStyle.FORMAT4));
		System.out.println("-----strDateTimeFormatter:" + strDateTimeFormatter("2017/04/28 10:17", DateUtils.FormatterStyle.FORMAT5));
	}
}
