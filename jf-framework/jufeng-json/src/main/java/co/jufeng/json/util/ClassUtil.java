package co.jufeng.json.util;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.net.URI;
import java.net.URL;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import co.jufeng.json.lang.BasicType;

/**
 * 类工具类 1、扫描指定包下的所有类<br>
 * 参考 http://www.oschina.net/code/snippet_234657_22722
 * 
 * @author seaside_hi, jufeng
 *
 */
public class ClassUtil {

	private ClassUtil() {
	}

	public static Method findMethod(Class<?> clazz, String methodName, Class<?>... paramTypes) {
		try {
			return clazz.getMethod(methodName, paramTypes);
		} catch (NoSuchMethodException ex) {
			return findDeclaredMethod(clazz, methodName, paramTypes);
		}
	}
	public static Method findDeclaredMethod(Class<?> clazz, String methodName, Class<?>... paramTypes) {
		try {
			return clazz.getDeclaredMethod(methodName, paramTypes);
		} catch (NoSuchMethodException ex) {
			if (clazz.getSuperclass() != null) {
				return findDeclaredMethod(clazz.getSuperclass(), methodName, paramTypes);
			}
			return null;
		}
	}

	public static Class<?>[] getClasses(Object... objects) {
		Class<?>[] classes = new Class<?>[objects.length];
		for (int i = 0; i < objects.length; i++) {
			classes[i] = objects[i].getClass();
		}
		return classes;
	}

	public final static Set<String> getMethods(Class<?> clazz) {
		HashSet<String> methodSet = new HashSet<String>();
		Method[] methodArray = clazz.getMethods();
		for (Method method : methodArray) {
			String methodName = method.getName();
			methodSet.add(methodName);
		}
		return methodSet;
	}

	/**
	 * 获得ClassPath
	 * 
	 * @return ClassPath集合
	 */
	public static Set<String> getClassPathResources() {
		return getClassPaths(StringUtil.EMPTY);
	}

	/**
	 * 获得ClassPath
	 * 
	 * @param packageName 包名称
	 * @return ClassPath路径字符串集合
	 */
	public static Set<String> getClassPaths(String packageName) {
		String packagePath = packageName.replace(StringUtil.DOT, StringUtil.SLASH);
		Enumeration<URL> resources;
		try {
			resources = getClassLoader().getResources(packagePath);
		} catch (IOException e) {
			throw new RuntimeException(StringUtil.format("Loading classPath [{}] error!", packagePath), e);
		}
		Set<String> paths = new HashSet<String>();
		while (resources.hasMoreElements()) {
			paths.add(resources.nextElement().getPath());
		}
		return paths;
	}

	public static String getClassPath() {
		return getClassPathURL().getPath();
	}

	public static URL getClassPathURL() {
		return getURL(StringUtil.EMPTY);
	}

	public static URL getURL(String resource) {
		return ClassUtil.getClassLoader().getResource(resource);
	}

	public static String[] getJavaClassPaths() {
		String[] classPaths = System.getProperty("java.class.path").split(System.getProperty("path.separator"));
		return classPaths;
	}

	public static Class<?> castToPrimitive(Class<?> clazz) {
		if (null == clazz || clazz.isPrimitive()) {
			return clazz;
		}

		BasicType basicType;
		try {
			basicType = BasicType.valueOf(clazz.getSimpleName().toUpperCase());
		} catch (Exception e) {
			return clazz;
		}

		switch (basicType) {
			case BYTE:
				return byte.class;
			case SHORT:
				return short.class;
			case INTEGER:
				return int.class;
			case LONG:
				return long.class;
			case DOUBLE:
				return double.class;
			case FLOAT:
				return float.class;
			case BOOLEAN:
				return boolean.class;
			case CHAR:
				return char.class;
			default:
				return clazz;
		}
	}

	public static ClassLoader getContextClassLoader() {
		return Thread.currentThread().getContextClassLoader();
	}

	public static ClassLoader getClassLoader() {
		ClassLoader classLoader = getContextClassLoader();
		if (classLoader == null) {
			classLoader = ClassUtil.class.getClassLoader();
		}
		return classLoader;
	}

	@SuppressWarnings("unchecked")
	public static <T> T newInstance(String clazz) {
		try {
			return (T) Class.forName(clazz).newInstance();
		} catch (Exception e) {
			throw new RuntimeException(StringUtil.format("Instance class [{}] error!", clazz), e);
		}
	}

	public static <T> T newInstance(Class<T> clazz) {
		try {
			return (T) clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(StringUtil.format("Instance class [{}] error!", clazz), e);
		}
	}

	public static <T> T newInstance(Class<T> clazz, Object... params) {
		if (CollectionUtil.isEmpty(params)) {
			return newInstance(clazz);
		}

		try {
			return clazz.getDeclaredConstructor(getClasses(params)).newInstance(params);
		} catch (Exception e) {
			throw new RuntimeException(StringUtil.format("Instance class [{}] error!", clazz), e);
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> Class<T> loadClass(String className, boolean isInitialized) {
		Class<T> clazz;
		try {
			clazz = (Class<T>) Class.forName(className, isInitialized, getClassLoader());
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		return clazz;
	}

	public static <T> Class<T> loadClass(String className) {
		return loadClass(className, true);
	}

	public static <T> T invoke(String classNameDotMethodName, Object... args) {
		return invoke(classNameDotMethodName, false, args);
	}

	public static <T> T invoke(String classNameDotMethodName, boolean isSingleton, Object... args) {
		if (StringUtil.isBlank(classNameDotMethodName)) {
			throw new RuntimeException("Blank classNameDotMethodName!");
		}
		final int dotIndex = classNameDotMethodName.lastIndexOf('.');
		if (dotIndex <= 0) {
			throw new RuntimeException("Invalid classNameDotMethodName [{}]!" + classNameDotMethodName);
		}

		final String className = classNameDotMethodName.substring(0, dotIndex);
		final String methodName = classNameDotMethodName.substring(dotIndex + 1);

		return invoke(className, methodName, isSingleton, args);
	}

	public static <T> T invoke(String className, String methodName, Object... args) {
		return invoke(className, methodName, false, args);
	}

	public static <T> T invoke(Object obj, String methodName, Object... args) {
		try {
			final Method method = getDeclaredMethod(obj, methodName, args);
			return invoke(obj, method, args);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T invoke(Object obj, Method method, Object... args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		if (false == method.isAccessible()) {
			method.setAccessible(true);
		}
		return (T) method.invoke(isStatic(method) ? null : obj, args);
	}
	
	public static Method getDeclaredMethod(Object obj, String methodName, Object... args) throws NoSuchMethodException, SecurityException {
		return getDeclaredMethod(obj.getClass(), methodName, getClasses(args));
	}

	public static Method getDeclaredMethod(Class<?> clazz, String methodName, Class<?>... parameterTypes) throws NoSuchMethodException, SecurityException {
		Method method = null;
		for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
			try {
				method = clazz.getDeclaredMethod(methodName, parameterTypes);
				return method;
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
		
		return Object.class.getDeclaredMethod(methodName, parameterTypes);
	}

	@SuppressWarnings("unchecked")
	public static <T> T newProxyInstance(Class<T> interfaceClass, InvocationHandler invocationHandler) {
		return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[] { interfaceClass }, invocationHandler);
	}

	public static boolean isPrimitiveWrapper(Class<?> clazz) {
		if (null == clazz) {
			return false;
		}
		return BasicType.wrapperPrimitiveMap.containsKey(clazz);
	}

	public static boolean isBasicType(Class<?> clazz) {
		if (null == clazz) {
			return false;
		}
		return (clazz.isPrimitive() || isPrimitiveWrapper(clazz));
	}

	public static boolean isSimpleTypeOrArray(Class<?> clazz) {
		if (null == clazz) {
			return false;
		}
		return isSimpleValueType(clazz) || (clazz.isArray() && isSimpleValueType(clazz.getComponentType()));
	}

	public static boolean isSimpleValueType(Class<?> clazz) {
		return isBasicType(clazz) || clazz.isEnum() || CharSequence.class.isAssignableFrom(clazz) || Number.class.isAssignableFrom(clazz) || Date.class.isAssignableFrom(clazz) || clazz
				.equals(URI.class) || clazz.equals(URL.class) || clazz.equals(Locale.class) || clazz.equals(Class.class);
	}

	public static boolean isAssignable(Class<?> targetType, Class<?> sourceType) {
		if (null == targetType || null == sourceType) {
			return false;
		}

		if (targetType.isAssignableFrom(sourceType)) {
			return true;
		}

		if (targetType.isPrimitive()) {
			Class<?> resolvedPrimitive = BasicType.wrapperPrimitiveMap.get(sourceType);
			if (resolvedPrimitive != null && targetType.equals(resolvedPrimitive)) {
				return true;
			}
		} else {
			Class<?> resolvedWrapper = BasicType.primitiveWrapperMap.get(sourceType);
			if (resolvedWrapper != null && targetType.isAssignableFrom(resolvedWrapper)) {
				return true;
			}
		}
		return false;
	}

	public static boolean isPublic(Class<?> clazz) {
		if (null == clazz) {
			throw new NullPointerException("Class to provided is null.");
		}
		return Modifier.isPublic(clazz.getModifiers());
	}

	public static boolean isPublic(Method method) {
		if (null == method) {
			throw new NullPointerException("Method to provided is null.");
		}
		return isPublic(method.getDeclaringClass());
	}

	public static boolean isNotPublic(Class<?> clazz) {
		return false == isPublic(clazz);
	}

	public static boolean isNotPublic(Method method) {
		return false == isPublic(method);
	}
	
	public static boolean isStatic(Method method){
		return Modifier.isStatic(method.getModifiers());
	}

	public static Method setAccessible(Method method) {
		if (null != method && ClassUtil.isNotPublic(method)) {
			method.setAccessible(true);
		}
		return method;
	}

	public interface ClassFilter {
		boolean accept(Class<?> clazz);
	}
}