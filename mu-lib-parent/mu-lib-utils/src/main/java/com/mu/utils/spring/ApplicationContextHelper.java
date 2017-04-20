package com.mu.utils.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 
 * @类描述：获取基于spring构建系统的环境上下文
 *
 * @创建人：mt
 */
@Component
public class ApplicationContextHelper implements ApplicationContextAware  {

	private static ApplicationContext appCtx;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		appCtx = applicationContext;  
	}

	public static ApplicationContext getApplicationContext() {
		return appCtx;
	} 
}
