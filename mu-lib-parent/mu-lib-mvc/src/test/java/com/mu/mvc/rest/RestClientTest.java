package com.mu.mvc.rest;


import java.util.HashMap;
import java.util.Map;

public class RestClientTest {

	public static void main(String[] args) {
		//该url要翻墙
//		String a = RestClient.getForObject(
//				"http://graph.facebook.com/pivotalsoftware", String.class);
//		Page page = RestClient.getForObject(
//				"http://graph.facebook.com/pivotalsoftware", Page.class);
//		System.out.println("Name:    " + page.getName());
//		System.out.println("About:   " + page.getAbout());
//		System.out.println("Phone:   " + page.getPhone());
//		System.out.println("Website: " + page.getWebsite());
//		System.out.println("awards  " + page.getAwards());
//		System.out.println("map---" + page.getCover().get("source"));
//		System.out.println("can_post---" + page.isCan_post());
//		System.out.println("========" + a);
		
		String url2 = "http://www.xcsqjr.com:8080/clientApi?methodIdentifier=userService.getUerAllInfo&userId=1";
		String b = RestClient.postForObject(url2, String.class, null);
		System.out.println(b);
		
//		String url3 = "http://www.xcsqjr.com:8080/clientApi";
//		Map<String, Object> praMap = new HashMap<String, Object>();
//		praMap.put("methodIdentifier", "userService.getUerAllInfo");
//		praMap.put("userId", 1);
//		String c = RestClient.postForObject(url3, String.class, praMap);
//		System.out.println(c);
		
	}

}
