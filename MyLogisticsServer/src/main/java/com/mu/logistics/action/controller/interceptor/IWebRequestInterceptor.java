package com.mu.logistics.action.controller.interceptor;

import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

import com.mu.common.utils.Log;

public class IWebRequestInterceptor implements WebRequestInterceptor {

	@Override
	public void preHandle(WebRequest request) throws Exception {
		Log.println("==========IWebRequestInterceptor-preHandle");
	}

	@Override
	public void postHandle(WebRequest request, ModelMap model) throws Exception {
		Log.println("==========IWebRequestInterceptor-postHandle");
	}

	@Override
	public void afterCompletion(WebRequest request, Exception ex)
			throws Exception {
		Log.println("==========IWebRequestInterceptor-afterCompletion");
	}
}
