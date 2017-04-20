package com.mu.mvc.base;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;

import com.mu.mvc.RequestUtil;

@Controller
public abstract class BaseController {

	/**
	 * 是否开启检测自定义头协议。
	 * 
	 * @return 返回true开启。
	 */
	public abstract boolean isCheckProtocol();

	public Map<String, String> getParSimpleMap(HttpServletRequest request) {
		return RequestUtil.setPar2Map(request);
	}
}
