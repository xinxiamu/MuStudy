package com.neo.datasource;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//@Aspect
//@Order(5)
//@Component
public class WebLogAspect {

	private Logger logger = Logger.getLogger(getClass());

	@Pointcut(value = "execution(public * com.neo.mapper.mu..*.*(..))") 
	public void webLog() {
	}

	@Before("DSCheck()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
		System.out.println("----doBefore:DSCheck");
	}


	@After(value = "DSCheck()")
	public void doAfter(JoinPoint joinPoint) {
		System.out.println("----doBefore:doAfter");  
	}

	//在切入点return内容之后切入内容（可以用来对处理返回值做一些加工处理）
	@AfterReturning(returning = "ret", pointcut = "DSCheck()")
	public void doAfterReturning(Object ret) throws Throwable {
	}

	@AfterThrowing(value = "DSCheck()")  
	public void doAfterError() {
		logger.info("切入部分抛异常");
	}
}
