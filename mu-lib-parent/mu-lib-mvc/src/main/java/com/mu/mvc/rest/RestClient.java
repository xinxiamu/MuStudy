package com.mu.mvc.rest;

import java.util.Map;

import org.springframework.web.client.RestTemplate;

/**
 * rest请求处理。
 * 
 * @author mt
 * 
 */
public class RestClient {

	public static void main(String[] args) {

	}

	// ************************ get *********************//

	/**
	 * 请求http接口并返回特定的类型。
	 * 
	 * @param httpUrl
	 *            接口url
	 * @param t
	 *            返回数据的类型
	 * @param praMap
	 *            接口参数
	 * @return
	 */
	public static <T> T getForObject(String httpUrl, Class<T> t,
			Map<String, Object> praMap) {
		RestTemplate restTemplate = new RestTemplate();
		if (httpUrl == null || httpUrl.equals("")) {
			return null;
		} 
		if (praMap == null) {
			return restTemplate.getForObject(httpUrl, t);
		}
		return restTemplate.getForObject(httpUrl, t, praMap);
	}

	/**
	 * 重载
	 * 
	 * @param httpUrl
	 * @param t
	 * @param urlVariables
	 * @return
	 */
	public static <T> T getForObject(String httpUrl, Class<T> t,
			Object... urlVariables) {
		RestTemplate restTemplate = new RestTemplate();
		if (httpUrl == null || httpUrl.equals("")) {
			return null;
		}
		return restTemplate.getForObject(httpUrl, t, urlVariables);
	}

	// ************************* post *********************//

	/**
	 * 请求http接口并返回特定的类型。
	 * 
	 * @param httpUrl
	 *            接口url
	 * @param t
	 *            返回数据的类型
	 * @param praMap
	 *            接口参数
	 * @return
	 */
	public static <T> T postForObject(String httpUrl, Class<T> t,
			Map<String, Object> praMap) {
		RestTemplate restTemplate = new RestTemplate();
		if (httpUrl == null || httpUrl.equals("")) {
			return null;
		}
		if (praMap == null) {
			return restTemplate.postForObject(httpUrl, null, t);
		}
		return restTemplate.postForObject(httpUrl, null, t, praMap);
	}

}
