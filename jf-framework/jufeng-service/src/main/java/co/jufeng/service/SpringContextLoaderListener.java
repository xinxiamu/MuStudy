package co.jufeng.service;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import co.jufeng.core.factory.BeanFactory;
import co.jufeng.logger.LoggerUtil;
import co.jufeng.string.StringUtil;

@WebListener
public class SpringContextLoaderListener extends ContextLoader implements ServletContextListener{
	
	public SpringContextLoaderListener() {
	}
	
	public SpringContextLoaderListener(WebApplicationContext context) {
		super(context);
	}

	/**
	 * Initialize the root web application context. default classpath:applicationContext.xml
	 */
	@Override
    public void contextInitialized(ServletContextEvent event) {
		event.getServletContext().setInitParameter("contextConfigLocation", "classpath:applicationContext.xml");
		initWebApplicationContext(event.getServletContext());
		LoggerUtil.info(SpringContextLoaderListener.class, "Initialize the root web application context. classpath:applicationContext.xml");
		/*Map<String, Object> repositoryBean = getCurrentWebApplicationContext().getBeansWithAnnotation(Repository.class); 
		for (Object bean : repositoryBean.values()) {
			Class<?> clazz = bean.getClass();
			Repository repository = clazz.getAnnotation(Repository.class);
			String beanName = null;
			if(repository != null){
				beanName = repository.value();
			}
			if (StringUtil.isEmpty(beanName)) {
				beanName = StringUtil.toLowerCaseFirstOne(clazz.getSimpleName());
				if(beanName.indexOf("$") >= 0){
					beanName = beanName.split("\\$")[0];
				}
			}
			BeanFactory.setAccessorPool(beanName, bean);
		}

		Map<String, Object> serviceBean = getCurrentWebApplicationContext().getBeansWithAnnotation(Service.class); 
		for (Object bean : serviceBean.values()) {
			Class<?> clazz = bean.getClass();
			Service service = clazz.getAnnotation(Service.class);
			String beanName = null;
			if(service != null){
				beanName = service.value();
			}
			if(StringUtil.isEmpty(beanName)){
				beanName = StringUtil.toLowerCaseFirstOne(clazz.getSimpleName());
				if(beanName.indexOf("$") >= 0){
					beanName = beanName.split("\\$")[0];
				}
			}
			BeanFactory.setServicePool(beanName, bean);
		}*/
	}
	
	/**
	 * Close the root web application context.
	 */
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		closeWebApplicationContext(event.getServletContext());
		cleanupAttributes(event.getServletContext());
	}
	private void cleanupAttributes(ServletContext sc) {
		Enumeration<String> attrNames = sc.getAttributeNames();
		while (attrNames.hasMoreElements()) {
			String attrName = attrNames.nextElement();
			if (attrName.startsWith("org.springframework.")) {
				Object attrValue = sc.getAttribute(attrName);
				if (attrValue instanceof DisposableBean) {
					try {
						((DisposableBean) attrValue).destroy();
					}
					catch (Throwable ex) {
						LoggerUtil.error(SpringContextLoaderListener.class, "Couldn't invoke destroy method of attribute with name '" + attrName + "'", ex);
					}
				}
			}
		}
	}

}
