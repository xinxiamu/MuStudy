package co.jufeng.controller;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;

import co.jufeng.BaseAction;
import co.jufeng.api.user.IUserService;
import co.jufeng.core.factory.BeanFactory;
import co.jufeng.json.JSONArray;
import co.jufeng.json.JSONObject;
import co.jufeng.web.ApiRspResultVO;
import co.jufeng.web.servlet.bind.annotation.RestController;

@RestController
public class IndexAction extends BaseAction{
	
	@Resource(name = "userService")
	private IUserService userService;

	public String index(){
		try {
			System.out.println(99);
			System.out.println(BeanFactory.getServicePool());
			System.out.println(BeanFactory.getAccessorPool());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index.html";
	}
	
	//http://www.jufeng.co:8080/jufeng-web/index!add.do?jsonString={"userName":"admin", "password":"a1234567", "mobile":"13822119203"}
	public ApiRspResultVO add(ApiRspResultVO apiRspResultVO, String jsonString){
		try {
			Long id = (Long) userService.add(jsonString);
			apiRspResultVO.setData(id);
			System.out.println(id + "===id");
		} catch (Exception e) {
			apiRspResultVO.setDescription(e.getMessage());
		}
		return apiRspResultVO;
	}
	
	/**
	 * http://www.jufeng.co:8080/jufeng-web/index!update.do?jsonString={"id":1,"userName":"98df", "password":"qwqwwqwq", "mobile":"13877778888"}
	 * @param jsonString
	 * @return
	 */
	public ApiRspResultVO update(ApiRspResultVO apiRspResultVO, String jsonString){
		try {
			boolean bol = userService.update(jsonString);
			apiRspResultVO.setData(bol);
			System.out.println(bol);
		} catch (Exception e) {
			apiRspResultVO.setDescription(e.getMessage());
		}
		return apiRspResultVO;
	}
	
	/**
	 * http://www.jufeng.co:8080/jufeng-web/index!getById.do?jsonString={"id":1}
	 * @param jsonString
	 * @return
	 */
//	public ApiRspResultVO getById(ApiRspResultVO apiRspResultVO, String jsonString){
//		try {
//			User user = userService.getById(jsonString);
////			System.out.println(user);
//			System.out.println("=============>>>>>> UserName " + user.getUserName());
//			apiRspResultVO.setData(user);
//		} catch (Exception e) {
//			apiRspResultVO.setDescription(e.getMessage());
//		}
//		return apiRspResultVO;
//	}
	


	public static void main(String[] args) throws Exception {
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		
		//读入JSON文件
		InputStream is = classloader.getResourceAsStream("j_area.json");
		
		//转成 string
		String text = IOUtils.toString(is);
		JSONObject jsonObject = JSONObject.parseJSONObject(text);
		JSONArray records = jsonObject.getJSONArray("RECORDS");
		
		List<LinkedHashMap<String, Object>> province = new ArrayList<LinkedHashMap<String, Object>>(); 
		for (int i = 0; i < records.length(); i++) {
			LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
			JSONObject json = records.getJSONObject(i);
			int id = json.getInteger("id");
			String name = json.getString("name");
			int parent = json.getInteger("parent");
			int level = json.getInteger("level");
			int cityCode = json.getInteger("city_code");
			int zipcode = json.getInteger("zipcode");
			String fullName = json.getString("full__name");
			if(id != parent && level == 1){
				map.put("id", id);
				map.put("name", name);
				map.put("parent", parent);
				map.put("level", level);
				map.put("cityCode", cityCode);
				map.put("zipcode", zipcode);
				map.put("fullName", fullName);
				
				province.add(map);
			}
		}
		List<LinkedHashMap<String, Object>> provinces = new ArrayList<LinkedHashMap<String, Object>>(); 
		List<LinkedHashMap<String, Object>> cityList = new ArrayList<LinkedHashMap<String, Object>>();
		
		for (int i = 0; i < province.size(); i++) {
			LinkedHashMap<String, Object> provinceMap = province.get(i);
			int id = (int) provinceMap.get("id");
			LinkedHashMap<String, Object> cityMap = null;
			for (int j = 0; j < records.length(); j++) {
				JSONObject parentJSONObject = records.getJSONObject(j);
				cityMap = new LinkedHashMap<String, Object>();
				int parent = parentJSONObject.getInteger("parent");
				if(id == parent){
					int cityId = parentJSONObject.getInteger("id");
					int parentId = parentJSONObject.getInteger("parent");
					String cityName = parentJSONObject.getString("name");
					String shortName = parentJSONObject.getString("short_name");
					int level = parentJSONObject.getInteger("level");
					int cityCode = parentJSONObject.getInteger("city_code");
					String fullName = parentJSONObject.getString("full__name");
					
					cityMap.put("id", cityId);
					cityMap.put("parent", parentId);
					cityMap.put("name", cityName);
					cityMap.put("shortName", shortName);
					cityMap.put("level", level);
					cityMap.put("cityCode", cityCode);
					cityMap.put("fullName", fullName);
					
					cityList.add(cityMap);
				}
			}
		}
		
		provinces = province;
		for (int i = 0; i < province.size(); i++) {
			List<LinkedHashMap<String, Object>> list = new ArrayList<LinkedHashMap<String, Object>>(); 
			LinkedHashMap<String, Object> provinceMap = province.get(i);
			int id = (int) provinceMap.get("id");
			for (int j = 0; j < cityList.size(); j++) {
				LinkedHashMap<String, Object> map = cityList.get(j);
				if(id == (int)map.get("parent")){
					list.add(map);
				}
			}
			provinces.get(i).put("city", list);
		}
		
		String  jsonString  = JSONObject.toJSONString(provinces);
		System.out.println(jsonString);
		/*JSONArray arrsy = JSONObject.parseArray(jsonString);
		for (int i = 0; i < arrsy.length(); i++) {
			JSONObject json = arrsy.getJSONObject(i);
			System.out.println(json);
		}*/
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
