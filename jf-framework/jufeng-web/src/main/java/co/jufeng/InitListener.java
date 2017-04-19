package co.jufeng;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.annotation.WebListener;

import co.jufeng.logger.LoggerUtil;
import co.jufeng.web.ConfigFactory;

/**
 * The internationalization of the listener
 * @author jufeng
 */
@WebListener
public class InitListener implements ServletRequestAttributeListener, ServletContextListener  {
	
	
	@Override
	public void attributeAdded(ServletRequestAttributeEvent srae) {
	}

	@Override
	public void attributeRemoved(ServletRequestAttributeEvent srae) {
	}

	@Override
	public void attributeReplaced(ServletRequestAttributeEvent srae) {
	}
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext application = sce.getServletContext(); 
		Map<String, String> properties = ConfigFactory.get("path");
		Iterator<String> it = properties.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			String value = properties.get(key);
			application.setAttribute(key, value);
		}
		LoggerUtil.info(InitListener.class, ConfigFactory.get("mvc"));
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}
	
	
}