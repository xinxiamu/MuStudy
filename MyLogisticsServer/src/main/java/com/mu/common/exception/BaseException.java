package com.mu.common.exception;

import com.mu.common.utils.Log;

public class BaseException extends Exception {

	private static final long serialVersionUID = -4726524771770627300L;

	public static void throwsException(Class<?> clazz, Throwable throwable) {
		throwsException(clazz, throwable.getMessage(), throwable);
	}

	private static void throwsException(Class<?> clazz,
			String addExceptionNoteMessage, Throwable throwable) {
		int exceptionLine = TraceInfoUtil.getExceptionLine(clazz, throwable);
		String exceptionType = TraceInfoUtil.getExceptionMessage(throwable);
		Log.e(clazz, addExceptionNoteMessage + "\n异常信息:" + throwable.getMessage()
				+ "\n异常类型:" + exceptionType + "\n异常行:" + exceptionLine);
	}

}
