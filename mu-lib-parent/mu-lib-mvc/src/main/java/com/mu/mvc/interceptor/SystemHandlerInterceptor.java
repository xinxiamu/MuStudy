package com.mu.mvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mu.mvc.RequestUtil;
import com.mu.utils.logger.Log;

/**
 * 
 * @类描述：系统拦截器。
 * 
 * @创建人：mt
 * @创建时间：2014年9月10日下午9:41:04
 * @修改人：Administrator
 * @修改时间：2014年9月10日下午9:41:04
 * @修改备注：
 * @version v1.0
 * @Copyright
 * @mail 932852117@qq.com
 */
public class SystemHandlerInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String site = handler != null ? ("——访问具体位置：" + handler.toString()) : "";
		Log.println("--------拦截器SystemHandlerInterceptor——preHandle" + site);

		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		Log.println("-------------postHandle");

		// web项目用
		if (modelAndView != null) {
			// 设置项目访问路径。所有页面可以访问，${basePath}
			String basePath = RequestUtil.getBasePath(request);
			modelAndView.getModelMap().addAttribute("basePath", basePath);
		}

		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		Log.println("------------------afterCompletion");
		super.afterCompletion(request, response, handler, ex);
	}
}
