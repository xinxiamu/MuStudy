package com.didispace.aspect;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Web层日志切面
 *
 * @author 程序猿DD
 * @version 1.0.0
 * @date 16/5/17 上午10:42.
 * @blog http://blog.didispace.com
 */
@Aspect
// 定义切面执行顺序。
// 在切入点前的操作，按order的值由小到大执行
// 在切入点后的操作，按order的值由大到小执行
//@Order(5)
@Component
public class WebLogAspect {

	private Logger logger = Logger.getLogger(getClass());

	// 线程局部变量。每个线程拥有自己的一个副本，线程各自调用自己的副本，互相不影响。
	ThreadLocal<Long> startTime = new ThreadLocal<>();

	@Pointcut(value = "execution(public * com.didispace.web..*.*(..))")
	public void webLog() {
	}

	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
		startTime.set(System.currentTimeMillis());

		// 接收到请求，记录请求内容
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		
		// 记录下请求内容
		logger.info("URL : " + request.getRequestURL().toString());
		logger.info("HTTP_METHOD : " + request.getMethod());
		logger.info("IP : " + request.getRemoteAddr());
		logger.info("CLASS_METHOD : "
				+ joinPoint.getSignature().getDeclaringTypeName() + "."
				+ joinPoint.getSignature().getName());
		logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
//		throw new NullPointerException("dddddddddd");
	}

//	@Around("webLog()")
//	public void around(ProceedingJoinPoint pjp) throws Throwable {
//		logger.info("已经记录下操作日志@Around 方法执行前");
//		pjp.proceed();
//		logger.info("已经记录下操作日志@Around 方法执行后");
//	}

	@After(value = "webLog()")
	public void doAfter(JoinPoint joinPoint) {
		logger.info("---执行切入点但还没返回: " + joinPoint.getSignature().getDeclaringType());	
	}

	//在切入点return内容之后切入内容（可以用来对处理返回值做一些加工处理）
	@AfterReturning(returning = "ret", pointcut = "webLog()")
	public void doAfterReturning(Object ret) throws Throwable {
//		ret = "sdfsafdsaf";
		// 处理完请求，返回内容
		logger.info("RESPONSE : " + ret);
		logger.info("SPEND TIME : "
				+ (System.currentTimeMillis() - startTime.get()));	
	}

	@AfterThrowing(value = "webLog()")  
	public void doAfterError() {
		logger.info("切入部分抛异常");
	}
}
