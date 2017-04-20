package com.logistics.app.exception;

import com.mu.utils.exceptions.BaseException.BaseExceptionCallback;
import com.mu.utils.logger.Log;

public class HttpProtocolExceptionCallback implements BaseExceptionCallback {

	@Override
	public void saveExceptionInfo(String classStr,
			String addExceptionNoteMessage, String exceptionType,
			int exceptionLine) {
		Log.println("哎呀，抛异常鸟，去看看吧：" + classStr + ":" + addExceptionNoteMessage
				+ ":" + exceptionLine + ":" + exceptionLine);
	}

}
