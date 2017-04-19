package co.jufeng.util;

import java.util.LinkedHashMap;
import java.util.Map;

import co.jufeng.util.http.HttpUtil;


public class Test{
	
	public static void main(String[] args) {
		String url  = "http://sjbb.1008656.com:812/YiDa.asmx/Get_DataSet";
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Map<String, Object> parameters = new LinkedHashMap<String, Object>();
		map.put("Customerid", "8888");
		map.put("PROCEDURENAME", "QSP_LOGIN_APP");
		parameters.put("username", "1");
		parameters.put("password", "680913");
		map.put("Parameters", parameters);
		String xml = XmlUtil.map2xml(map, "Procedure");
		System.out.println(xml);
		//返回来的JSON字符串
		String jsonString = HttpUtil.doPost(url, xml);
		System.out.println(jsonString);
	}
	

}
