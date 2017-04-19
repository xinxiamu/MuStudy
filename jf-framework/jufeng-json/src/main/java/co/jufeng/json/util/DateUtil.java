package co.jufeng.json.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Locale;

import co.jufeng.json.lang.DateTime;

public class DateUtil {
	public final static long MS = 1;
	public final static long SECOND_MS = MS * 1000;
	public final static long MINUTE_MS = SECOND_MS * 60;
	public final static long HOUR_MS = MINUTE_MS * 60;
	public final static long DAY_MS = HOUR_MS * 24;
	public final static String NORM_DATE_PATTERN = DateStyle.YYYY_MM_DD.getValue();
	public final static String NORM_TIME_PATTERN = DateStyle.HH_MM_SS.getValue();
	public final static String NORM_DATETIME_MINUTE_PATTERN = DateStyle.YYYY_MM_DD_HH_MM.getValue();
	public final static String NORM_DATETIME_PATTERN = DateStyle.YYYY_MM_DD_HH_MM_SS.getValue();
	public final static String NORM_DATETIME_MS_PATTERN = DateStyle.YYYY_MM_DD_HH_MM_SS_SSS.getValue();
	public final static String HTTP_DATETIME_PATTERN = "EEE, dd MMM yyyy HH:mm:ss z";

	private static ThreadLocal<SimpleDateFormat> NORM_DATE_FORMAT = new ThreadLocal<SimpleDateFormat>(){
		synchronized protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat(NORM_DATE_PATTERN);
		};
	};
	private static ThreadLocal<SimpleDateFormat> NORM_TIME_FORMAT = new ThreadLocal<SimpleDateFormat>(){
		synchronized protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat(NORM_TIME_PATTERN);
		};
	};
	private static ThreadLocal<SimpleDateFormat> NORM_DATETIME_FORMAT = new ThreadLocal<SimpleDateFormat>(){
		synchronized protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat(NORM_DATETIME_PATTERN);
		};
	};
	private static ThreadLocal<SimpleDateFormat> HTTP_DATETIME_FORMAT = new ThreadLocal<SimpleDateFormat>(){
		synchronized protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat(HTTP_DATETIME_PATTERN, Locale.US);
		};
	};

	public static String now() {
		return formatDateTime(new DateTime());
	}
	
	public static long current(boolean isNano){
		return isNano ? System.nanoTime() : System.currentTimeMillis();
	}

	public static String today() {
		return formatDate(new DateTime());
	}

	public static int thisMonth() {
		return month(date());
	}

	public static int thisYear() {
		return year(date());
	}

	public static DateTime date() {
		return new DateTime();
	}

	public static DateTime date(long date) {
		return new DateTime(date);
	}

	public static Calendar toCalendar(Date date) {
		final Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	public static int month(Date date) {
		return toCalendar(date).get(Calendar.MONTH) + 1;
	}

	public static int year(Date date) {
		return toCalendar(date).get(Calendar.YEAR);
	}

	public static int season(Date date) {
		return toCalendar(date).get(Calendar.MONTH) / 3 + 1;
	}

	public static String yearAndSeason(Date date) {
		return yearAndSeason(toCalendar(date));
	}

	public static LinkedHashSet<String> yearAndSeasons(Date startDate, Date endDate) {
		final LinkedHashSet<String> seasons = new LinkedHashSet<String>();
		if (startDate == null || endDate == null) {
			return seasons;
		}

		final Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		while (true) {
			if (startDate.after(endDate)) {
				startDate = endDate;
			}

			seasons.add(yearAndSeason(cal));

			if (startDate.equals(endDate)) {
				break;
			}

			cal.add(Calendar.MONTH, 3);
			startDate = cal.getTime();
		}

		return seasons;
	}

	public static String format(Date date, String format) {
		return new SimpleDateFormat(format).format(date);
	}

	public static String formatDateTime(Date date) {
		return NORM_DATETIME_FORMAT.get().format(date);
	}

	public static String formatDate(Date date) {
		return NORM_DATE_FORMAT.get().format(date);
	}

	public static String formatHttpDate(Date date) {
		return HTTP_DATETIME_FORMAT.get().format(date);
	}

	public static DateTime parse(String dateStr, SimpleDateFormat simpleDateFormat) {
		try {
			return new DateTime(simpleDateFormat.parse(dateStr));
		} catch (Exception e) {
			throw new RuntimeException(StringUtil.format("Parse [{}] with format [{}] error!", dateStr, simpleDateFormat.toPattern()), e);
		}
	}

	public static DateTime parse(String dateString, String format) {
		return parse(dateString, new SimpleDateFormat(format));
	}

	public static DateTime parseDateTime(String dateString) {
		return parse(dateString, NORM_DATETIME_FORMAT.get());
	}

	public static DateTime parseDate(String dateString) {
		return parse(dateString, NORM_DATE_FORMAT.get());
	}

	public static DateTime parseTime(String timeString) {
		return parse(timeString, NORM_TIME_FORMAT.get());
	}

	public static DateTime parse(String dateStr) {
		if (null == dateStr) {
			return null;
		}
		dateStr = dateStr.trim();
		int length = dateStr.length();
		try {
			if (length == NORM_DATETIME_PATTERN.length()) {
				return parseDateTime(dateStr);
			} else if (length == NORM_DATE_PATTERN.length()) {
				return parseDate(dateStr);
			} else if (length == NORM_TIME_PATTERN.length()) {
				return parseTime(dateStr);
			} else if (length == NORM_DATETIME_MINUTE_PATTERN.length()) {
				return parse(dateStr, NORM_DATETIME_MINUTE_PATTERN);
			} else if (length >= NORM_DATETIME_MS_PATTERN.length() - 2) {
				return parse(dateStr, NORM_DATETIME_MS_PATTERN);
			}
		} catch (Exception e) {
			throw new RuntimeException(StringUtil.format("Parse [{}] with format normal error!", dateStr));
		}

		throw new RuntimeException(StringUtil.format(" [{}] format is not fit for date pattern!", dateStr));
	}

	public static DateTime getBeginTimeOfDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return new DateTime(calendar.getTime());
	}

	public static DateTime getEndTimeOfDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return new DateTime(calendar.getTime());
	}

	public static DateTime yesterday() {
		return offsiteDay(new DateTime(), -1);
	}

	public static DateTime lastWeek() {
		return offsiteWeek(new DateTime(), -1);
	}

	public static DateTime lastMouth() {
		return offsiteMonth(new DateTime(), -1);
	}

	public static DateTime offsiteDay(Date date, int offsite) {
		return offsiteDate(date, Calendar.DAY_OF_YEAR, offsite);
	}

	public static DateTime offsiteWeek(Date date, int offsite) {
		return offsiteDate(date, Calendar.WEEK_OF_YEAR, offsite);
	}

	public static DateTime offsiteMonth(Date date, int offsite) {
		return offsiteDate(date, Calendar.MONTH, offsite);
	}

	public static DateTime offsiteDate(Date date, int calendarField, int offsite) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(calendarField, offsite);
		return new DateTime(cal.getTime());
	}

	public static long diff(Date subtrahend, Date minuend, long diffField) {
		long diff = minuend.getTime() - subtrahend.getTime();
		return diff / diffField;
	}

	public static long spendNt(long preTime) {
		return System.nanoTime() - preTime;
	}

	public static long spendMs(long preTime) {
		return System.currentTimeMillis() - preTime;
	}

	public static int toIntSecond(Date date) {
		return Integer.parseInt(DateUtil.format(date, "yyMMddHHmm"));
	}

	public static int weekCount(Date start, Date end) {
		final Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(start);
		final Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(end);

		final int startWeekofYear = startCalendar.get(Calendar.WEEK_OF_YEAR);
		final int endWeekofYear = endCalendar.get(Calendar.WEEK_OF_YEAR);

		int count = endWeekofYear - startWeekofYear + 1;

		if (Calendar.SUNDAY != startCalendar.get(Calendar.DAY_OF_WEEK)) {
			count--;
		}

		return count;
	}

	public static Timer timer() {
		return new Timer();

	}

	public static class Timer {
		private long time;
		private boolean isNano;
		
		public Timer() {
			this(false);
		}

		public Timer(boolean isNano) {
			this.isNano = isNano;
			start();
		}

		public long start() {
			time = current(isNano);
			return time;
		}

		public long durationRestart() {
			long now = current(isNano);
			long d = now - time;
			time = now;
			return d;
		}

		public long duration() {
			return current(isNano) - time;
		}
	}

	private static String yearAndSeason(Calendar cal) {
		return new StringBuilder().append(cal.get(Calendar.YEAR)).append(cal.get(Calendar.MONTH) / 3 + 1).toString();
	}
}
