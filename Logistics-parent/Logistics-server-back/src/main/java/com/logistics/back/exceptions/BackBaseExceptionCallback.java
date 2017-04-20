package com.logistics.back.exceptions;

import org.springframework.stereotype.Component;

import com.mu.utils.exceptions.BaseException.BaseExceptionCallback;
import com.mu.utils.logger.Log;

/**
 * 
 * @类描述：统一的异常持久化回调接口。BaseException异常的持久化回调
 * 
 * @创建人：mt
 */
@Component
public class BackBaseExceptionCallback implements BaseExceptionCallback {

	@Override
	public void saveExceptionInfo(String classStr,
			String addExceptionNoteMessage, String exceptionType,
			int exceptionLine) {
		Log.println("哎呀，抛异常鸟，去看看吧：" + classStr + ":" + addExceptionNoteMessage
				+ ":" + exceptionLine + ":" + exceptionLine);
	}

}
