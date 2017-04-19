package co.jufeng.json.util;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class BeanUtil {

	public static PropertyDescriptor[] getPropertyDescriptors(Class<?> clazz) throws IntrospectionException {
		return Introspector.getBeanInfo(clazz).getPropertyDescriptors();
	}
	
	public static Map<String, PropertyDescriptor> getFieldNamePropertyDescriptorMap(Class<?> clazz) throws IntrospectionException{
		final PropertyDescriptor[] propertyDescriptors = getPropertyDescriptors(clazz);
		Map<String, PropertyDescriptor> map = new HashMap<>();
		for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
			map.put(propertyDescriptor.getName(), propertyDescriptor);
		}
		return map;
	}

	public static PropertyDescriptor getPropertyDescriptor(Class<?> clazz, final String fieldName) throws IntrospectionException {
		PropertyDescriptor[] propertyDescriptors = getPropertyDescriptors(clazz);
		for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
			if (ObjectUtil.equals(fieldName, propertyDescriptor.getName())) {
				return propertyDescriptor;
			}
		}
		return null;
	}
	
	public static <T> T mapToBean(Map<?, ?> map, Class<T> beanClass) {
		return fillBeanWithMap(map, ClassUtil.newInstance(beanClass));
	}

	public static <T> T mapToBeanIgnoreCase(Map<?, ?> map, Class<T> beanClass) {
		return fillBeanWithMapIgnoreCase(map, ClassUtil.newInstance(beanClass));
	}

	public static <T> T fillBeanWithMap(final Map<?, ?> map, T bean) {
		return fill(bean, new ValueProvider(){
			@Override
			public Object value(String name) {
				return map.get(name);
			}
		});
	}

	public static <T> T fillBeanWithMapIgnoreCase(Map<?, ?> map, T bean) {
		final Map<Object, Object> map2 = new HashMap<Object, Object>();
		for (Entry<?, ?> entry : map.entrySet()) {
			final Object key = entry.getKey();
			if (key instanceof String) {
				final String keyStr = (String) key;
				map2.put(keyStr.toLowerCase(), entry.getValue());
			} else {
				map2.put(key, entry.getValue());
			}
		}

		return fill(bean, new ValueProvider(){
			@Override
			public Object value(String name) {
				return map2.get(name.toLowerCase());
			}
		});
	}

	public static <T> T toBean(Class<T> beanClass, ValueProvider valueProvider) {
		return fill(ClassUtil.newInstance(beanClass), valueProvider);
	}

	public static <T> T fill(T bean, ValueProvider valueProvider) {
		if (null == valueProvider) {
			return bean;
		}

		Class<?> beanClass = bean.getClass();
		try {
			PropertyDescriptor[] propertyDescriptors = getPropertyDescriptors(beanClass);
			String propertyName;
			Object value;
			for (PropertyDescriptor property : propertyDescriptors) {
				propertyName = property.getName();
				value = valueProvider.value(propertyName);
				if (null == value) {
					continue;
				}

				try {
					property.getWriteMethod().invoke(bean, TypeUtil.parse(property.getPropertyType(), value));
				} catch (Exception e) {
					e.printStackTrace();
//					throw new RuntimeException(StringUtil.format("Inject [{}] error!", property.getName()), e);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return bean;
	}

	public static <T> Map<String, Object> beanToMap(T bean) {

		if (bean == null) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			final PropertyDescriptor[] propertyDescriptors = getPropertyDescriptors(bean.getClass());
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();
				if (false == key.equals("class")) {
					Method getter = property.getReadMethod();
					Object value = getter.invoke(bean);
					if (null != value) {
						map.put(key, value);
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return map;
	}

	public static void copyProperties(Object source, Object target) {
		copyProperties(source, target, null, (String[]) null);
	}
	
	public static void copyProperties(Object source, Object target, String... ignoreProperties) {
		copyProperties(source, target, null, ignoreProperties);
	}
	
	private static void copyProperties(Object source, Object target, Class<?> editable, String... ignoreProperties) {
		Class<?> actualEditable = target.getClass();
		if (editable != null) {
			if (!editable.isInstance(target)) {
				throw new IllegalArgumentException(StringUtil.format("Target class [{}] not assignable to Editable class [{}]", target.getClass().getName(), editable.getName()));
			}
			actualEditable = editable;
		}
		PropertyDescriptor[] targetPds = null;
		Map<String, PropertyDescriptor> sourcePdMap;
		try {
			sourcePdMap = getFieldNamePropertyDescriptorMap(source.getClass());
			targetPds = getPropertyDescriptors(actualEditable);
		} catch (IntrospectionException e) {
			throw new RuntimeException(e);
		}
		
		List<String> ignoreList = (ignoreProperties != null ? Arrays.asList(ignoreProperties) : null);

		for (PropertyDescriptor targetPd : targetPds) {
			Method writeMethod = targetPd.getWriteMethod();
			if (writeMethod != null && (ignoreList == null || false == ignoreList.contains(targetPd.getName()))) {
				PropertyDescriptor sourcePd = sourcePdMap.get(targetPd.getName());
				if (sourcePd != null) {
					Method readMethod = sourcePd.getReadMethod();
					if (readMethod != null && ClassUtil.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())) {
						try {
							Object value = ClassUtil.setAccessible(readMethod).invoke(source);
							ClassUtil.setAccessible(writeMethod).invoke(target, value);
						} catch (Throwable ex) {
							throw new RuntimeException("Copy property [{}] to [{}] error: {}" + sourcePd.getName() + targetPd.getName() + ex.getMessage());
						}
					}
				}
			}
		}
	}

	public static interface ValueProvider {
		public Object value(String name);
	}
}
