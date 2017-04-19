package co.jufeng.json;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import co.jufeng.json.JSONArray;
import co.jufeng.json.JSONObject;



public class MainTest {
	
	@Test
	public void testJSONObject(){
		System.out.println("testJSONObjectHuLoot");
		Map<String, Object> maps = initMap();
		long start = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++){
			JSONObject.toJSONString(maps);
		}
		String jsonString = JSONObject.toJSONString(maps).toString();
		System.out.println(jsonString);
		JSONObject jsonObject = new JSONObject(jsonString);
		System.out.println(jsonObject);
		Timestamp timestamp = jsonObject.getTimestamp("timestamp");
		System.out.println(timestamp);
		System.out.println(jsonObject.getString("www"));
		JSONArray jsonArray = jsonObject.getJSONArray("list");
		System.out.println(jsonArray);
		System.out.println(jsonArray.getJSONObject(3));
		
		System.out.println(JSONObject.toJavaObject(jsonObject.getJSONObject("user"), UserInfo.class));
		long time = System.currentTimeMillis() - start;
		System.out.println(time);
	}

	private Map<String, Object> initMap() {
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("www1", "www.baidu.com");
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("map1", map1);
		maps.put("list", Arrays.asList(12, "sdfsdf", 1.012, map1));
		
		maps.put("www", "www.baidu.co");
		maps.put("integer", 1);
		maps.put("double", 1.01);
		maps.put("timestamp", "2016-08-03 15:40:57.23");
		maps.put("user", new UserInfo());
		return maps;
	}
	public static void main(String[] args) {
		String jsonString = JSONObject.toJSONString(new UserInfo());
		System.out.println(jsonString);
		UserInfo userInfo = JSONObject.toJavaObject(jsonString, UserInfo.class);
		System.out.println(userInfo);
		System.out.println(userInfo.getDate());
	}
	
}
