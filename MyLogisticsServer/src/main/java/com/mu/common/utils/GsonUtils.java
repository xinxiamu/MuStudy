package com.mu.common.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class GsonUtils {


	/**
	 * java bean转化为对应的jsonStr
	 * @param bean 实例化的java bean对象。
	 * @return
	 */
	public static String bean2JsonStr(Class<?> bean) {
		if (bean == null) {
			return null;
		}
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		builder.setPrettyPrinting().serializeNulls();
		String jsonStr = gson.toJson(bean);
		return (jsonStr == null || jsonStr.equals("")) ? null : jsonStr;
	}
	
	public static void main(String[] args) {
		
	}
}
