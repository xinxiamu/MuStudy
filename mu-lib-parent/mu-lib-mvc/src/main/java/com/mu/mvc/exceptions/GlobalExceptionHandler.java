package com.mu.mvc.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.mu.mvc.ReturnJsonUtil;
import com.mu.utils.exceptions.BaseException;

/**
 * 
 * @类描述：统一的异常处理。
 * 
 * @创建人：mt
 * @创建时间：2014年9月10日下午9:08:03
 * @修改人：Administrator
 * @修改时间：2014年9月10日下午9:08:03
 * @修改备注：
 * @version v1.0
 * @Copyright
 * @mail 932852117@qq.com
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * 在抛出HttpProtocolException异常时执行.
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(HttpProtocolException.class)
	public @ResponseBody
	String handleHttpProtocolException(HttpProtocolException ex) {
		return ReturnJsonUtil.outSystemErrorProtocol();
	}

	@ExceptionHandler(AppBaseException.class)
	public @ResponseBody
	String handleAppBaseException(AppBaseException ex) {
		return ReturnJsonUtil.outSystemError();
	}

	@ExceptionHandler(Exception.class)
	public @ResponseBody
	String handleException(Exception ex) {
		ex.printStackTrace();
		return ReturnJsonUtil.outSystemError();
	}

	/**
	 * 处理BaseException异常。
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(BaseException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public String index(BaseException e) {
		BaseException.throwsException(getClass(), e);
		return "index";
	}

}
