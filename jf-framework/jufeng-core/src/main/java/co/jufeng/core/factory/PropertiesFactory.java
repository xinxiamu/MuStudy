package co.jufeng.core.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class PropertiesFactory extends PropertyPlaceholderConfigurer implements IPropertiesFactory {

	private static Map<String, Object> NAME_VALUE_PAIR_ALL;

	private static IPropertiesFactory propertiesFactory;

	private PropertiesFactory() {
	}

	public static IPropertiesFactory newInstance() {
		if (propertiesFactory == null)
			propertiesFactory = new PropertiesFactory();
		return propertiesFactory;
	}

	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
		super.processProperties(beanFactoryToProcess, props);
		NAME_VALUE_PAIR_ALL = new HashMap<String, Object>();
		for (Object key : props.keySet()) {
			String keyStr = key.toString();
			String value = props.getProperty(keyStr);
			NAME_VALUE_PAIR_ALL.put(keyStr, value);
		}
	}

	@Override
	public Object get(String key) {
		return NAME_VALUE_PAIR_ALL.get(key);
	}

	@Override
	public <T> T get(String key, Class<T> clazz) {
		return clazz.cast(get(key));
	}

	@Override
	public String getString(String key) {
		Object value = get(key);

		if (value == null) {
			return null;
		}

		return value.toString();
	}
	
}
