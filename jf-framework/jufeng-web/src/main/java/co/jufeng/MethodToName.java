package co.jufeng;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

import co.jufeng.api.annotation.Api;
import co.jufeng.api.annotation.Param;
import co.jufeng.json.JSONObject;

public class MethodToName {
	public void init(String name, Integer age) {

	}

	public static void main(String[] args) throws Exception {
		Class<?> clazz = Class.forName("co.jufeng.api.user.IUserService");
		Map<String, Map<String, Map<String, String>>> map = get(clazz);
		JSONObject json = (JSONObject) JSONObject.toJSON(map);
		System.out.println(json);
	}

	private static Map<String, Map<String, Map<String, String>>> get(Class<?> clazz) throws ClassNotFoundException {
		Api api = (Api) clazz.getAnnotation(Api.class);
		String apiName = api.value();
		Map<String, Map<String, Map<String, String>>> map = new HashMap<String, Map<String, Map<String, String>>>();
		Map<String, Map<String, String>> mapMethod = new HashMap<String, Map<String, String>>();
		Method[] methods = clazz.getMethods();
		for (Method method : methods) {
			String methodName = method.getName();
			Class<?>[] classTypes = method.getParameterTypes();
			Parameter[] parameters = method.getParameters();
			Map<String, String> mapClassTypeAndName = new HashMap<String, String>();
			for (int i = 0; i < classTypes.length; i++) {
				Param param = parameters[i].getAnnotation(Param.class);
				if (param != null) {
					String classTypeName = classTypes[i].getSimpleName();
					mapClassTypeAndName.put(classTypeName, param.value());
				}
			}
			mapMethod.put(methodName, mapClassTypeAndName);
		}
		map.put(apiName, mapMethod);
		return map;
	}
}
