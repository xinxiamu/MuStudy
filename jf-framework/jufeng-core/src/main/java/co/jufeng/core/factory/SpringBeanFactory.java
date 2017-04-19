package co.jufeng.core.factory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

public class SpringBeanFactory implements BeanFactoryAware {

	public static BeanFactory beanFactory;

	public static Object getBean(String beanName) {
		try {
			return beanFactory.getBean(beanName);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static boolean isEmpty(String beanName) {
		Object object = getBean(beanName);
		if (object == null) {
			return false;
		} else {
			return true;
		}
	}

	public static boolean isEmpty(Class<?> clazz) {
		try {
			beanFactory.getBean(clazz);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static <T> T getBean(Class<T> clazz) {
		return beanFactory.getBean(clazz);
	}

	public static <T> T getBean(String beanName, Class<T> clazs) {
		return clazs.cast(getBean(beanName));
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		SpringBeanFactory.beanFactory = beanFactory;
	}

}
