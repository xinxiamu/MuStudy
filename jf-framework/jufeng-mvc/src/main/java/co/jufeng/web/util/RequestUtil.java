package co.jufeng.web.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import co.jufeng.util.EncodingUtils;
import co.jufeng.util.Parameter;
import co.jufeng.util.ReflexUtil;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class RequestUtil {

	public synchronized static <T> T toObject(HttpServletRequest request, Class<T> javaBean) throws Exception {
		return (T) ReflexUtil.reflexObject(toObject(request), javaBean.newInstance(), javaBean);
	}

	public synchronized static Map<String, String> toObject(HttpServletRequest request) {
		Map<String, String> returnMap = null;
		try {
			Map<String, String[]> properties = request.getParameterMap();
			returnMap = new Parameter<String, String>();
			Iterator<?> entries = properties.entrySet().iterator();
			Map.Entry entry;
			String name = "";
			String value = "";
			while (entries.hasNext()) {
				entry = (Map.Entry) entries.next();
				name = (String) entry.getKey();
				Object valueObj = entry.getValue();
				if (null == valueObj) {
					value = "";
				} else if (valueObj instanceof String[]) {
					String[] values = (String[]) valueObj;
					for (int i = 0; i < values.length; i++) {
						value = values[i] + ",";
					}
					value = value.substring(0, value.length() - 1);
				} else {
					value = valueObj.toString();
				}
				if (EncodingUtils.isUTF8(value)) {
					returnMap.put(name, value);
					
				} else if (EncodingUtils.isGBK(value)) {
					returnMap.put(name, value);
					
				} else if (java.nio.charset.Charset.forName("ISO-8859-1").newEncoder().canEncode(value)) {
					if(isIE(request)){
						returnMap.put(name, new String(value.getBytes("ISO-8859-1"), "GBK"));
					}else{
						returnMap.put(name, new String(value.getBytes("ISO-8859-1"), "UTF-8"));
					}
					
				} else {
					returnMap.put(name, new String(value.getBytes("ISO-8859-1"), "UTF-8"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnMap;
	}
	
	public static boolean  isIE(HttpServletRequest request){  
	    return  request.getHeader( "USER-AGENT" ).toLowerCase().indexOf( "msie" ) >  0  ?  true  :  false ;  
	}  

	public static Object copy(Object object) throws Exception{
		Class classType = object.getClass();// 获得对象的类型
		// 通过默认的构造函数创建一个新的对象
		Object objectCopy = classType.getConstructor(new Class[] {}).newInstance(new Object[] {});
		// 获得对象的所有属性
		Field fields[] = classType.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			String fieldName = field.getName();
			copyPara(object, objectCopy, classType, null, field, fieldName);
		}
		return objectCopy;

	}

	public static void copy(Object sourceObject, Object targetObject,
			List<String> copyParas, List<String> noCopyParas)
			throws IllegalArgumentException, SecurityException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		if (sourceObject == null || targetObject == null)
			return;

		Class sourceclassType = sourceObject.getClass();// 获得对象的类型
		Class targetclassType = targetObject.getClass();// 获得对象的类型
		// 获得对象的所有属性
		Field fields[] = sourceclassType.getDeclaredFields();
		Field targetfields[] = targetclassType.getDeclaredFields();
		Map<String, Field> targetFieldMap = new HashMap<String, Field>();
		for (int i = 0; i < targetfields.length; i++) {
			String fieldName = targetfields[i].getName();
			targetFieldMap.put(fieldName, targetfields[i]);
		}
		Field.setAccessible(fields, true);
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			String fieldName = field.getName();
			if (targetFieldMap.containsKey(fieldName)) {
				if (noCopyParas != null && noCopyParas.contains(fieldName))
					continue;
				targetFieldMap.get(fieldName).setAccessible(true);
				if (copyParas == null) {
					targetFieldMap.get(fieldName).set(targetObject,
							fields[i].get(sourceObject));
				} else if (copyParas.contains(fieldName)) {
					targetFieldMap.get(fieldName).set(targetObject,
							fields[i].get(sourceObject));
				}
			}
		}

	}

	private static void copyPara(Object sourceObject, Object targetObject, Class sourceclassType, Class targetclassType, Field field, String fieldName) throws Exception {
		String firstLetter = fieldName.substring(0, 1).toUpperCase();
		// 获得和属性对应的getXXX（）方法的名字
		String getMethodName = "get" + firstLetter + fieldName.substring(1);
		// 获得和属性对应的setXXX()方法的名字
		String setMethodName = "set" + firstLetter + fieldName.substring(1);
		// 获得和属性对应的getXXX()方法
		Method getMethod = sourceclassType.getMethod(getMethodName, new Class[] {});
		// 获得和属性对应的setXXX()方法
		Method setMethod = null;
		if (targetclassType != null)
			setMethod = targetclassType.getMethod(setMethodName, new Class[] { field.getType() });
		else
			setMethod = sourceclassType.getMethod(setMethodName, new Class[] { field.getType() });
		if (getMethod != null && setMethod != null) {
			// 调用原对象的getXXX()方法
			Object value = getMethod.invoke(sourceObject, new Object[] {});
			// 调用复制对象的setXXX()方法
			setMethod.invoke(targetObject, new Object[] { value });
		}
	}

	public static List<Map<String, Object>> transformMap(List<?> dataList, Class<?> modelClass, List<String> addList, List<String> notAddList) throws Exception {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Field fields[] = modelClass.getDeclaredFields();
		Field.setAccessible(fields, true);
		for (Object object : dataList) {
			Map<String, Object> dataMap = new HashMap<String, Object>();
			for (int i = 0; i < fields.length; i++) {
				String name = fields[i].getName();
				if (notAddList != null && notAddList.contains(name))
					continue;
				Object value = fields[i].get(object);
				if (addList == null) {
					dataMap.put(name, value == null ? "" : value);
				} else if (addList.contains(name)) {
					dataMap.put(name, value == null ? "" : value);
				}
			}
			if (!dataMap.isEmpty())
				list.add(dataMap);
		}
		return list;
	}

	public static Map<String, Object> transformMap(Object obj,
			Class<?> modelClass, List<String> addList, List<String> notAddList)
			throws Exception {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		Field fields[] = modelClass.getDeclaredFields();
		Field.setAccessible(fields, true);
		if (obj != null) {
			for (int i = 0; i < fields.length; i++) {
				String name = fields[i].getName();
				if (notAddList != null && notAddList.contains(name))
					continue;
				Object value = fields[i].get(obj);

				if (addList == null) {
					dataMap.put(name, value == null ? "" : value);
				} else if (addList.contains(name)) {
					dataMap.put(name, value == null ? "" : value);
				}
			}
		}
		return dataMap;
	}
	
	
	public static Map<String, Object> getSystemMessage(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		InetAddress inet = null;
		try {
			map.put("userAgent", request.getHeader("User-Agent")); //就是取得客户端的系统版本 
			map.put("remoteAddr", request.getRemoteAddr());        //取得客户端的IP   
			map.put("remoteHost", request.getRemoteHost());		//取得客户端的主机名    
			map.put("remotePort", request.getRemotePort());		//取得客户端的端口   
			map.put("remoteUser", request.getRemoteUser());		//取得客户端的用户     
			map.put("localAddr", request.getLocalAddr());			//取得本地IP
			map.put("localPort", request.getLocalPort());			//取得本地端口
			inet = InetAddress.getLocalHost();
			map.put("ip",  inet.getHostAddress());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	public static void main(String[] args) {
        try {
            Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface nif = netInterfaces.nextElement();
                Enumeration<InetAddress> iparray = nif.getInetAddresses();
                while (iparray.hasMoreElements()) {
                    System.out.println("IP:"
                            + iparray.nextElement().getHostAddress());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
	

}
