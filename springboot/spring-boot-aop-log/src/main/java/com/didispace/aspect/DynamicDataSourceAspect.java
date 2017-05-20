package com.didispace.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.didispace.DSInject;

@Aspect
@Component
public class DynamicDataSourceAspect {

	@Before("@annotation(com.didispace.DSInject)")
	public void beforeSwitchDS(JoinPoint point) {
		System.out.println("----检测到注解：DSInject");

		// 获得当前访问的class
		Class<?> className = point.getTarget().getClass();

		// 获得访问的方法名
		String methodName = point.getSignature().getName();
		// 得到方法的参数的类型
		Class[] argClass = ((MethodSignature) point.getSignature()).getParameterTypes();
		String dataSource = "mu";
		try {
			// 得到访问的方法对象
			Method method = className.getMethod(methodName, argClass);

			// 判断是否存在@DBInject注解
			if (method.isAnnotationPresent(DSInject.class)) {
				DSInject annotation = method.getAnnotation(DSInject.class);
				// 取出注解中的数据源名
				dataSource = annotation.value();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("-----注入的数据源：" + dataSource);    
//
//		// 切换数据源
//		DataSourceContextHolder.setDB(dataSource);

	}

	@After("@annotation(com.didispace.DSInject)")
	public void afterSwitchDS(JoinPoint point) {
		System.out.println("----注解后");

//		DataSourceContextHolder.clearDB();

	}
}
