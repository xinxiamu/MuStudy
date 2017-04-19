package co.jufeng.util;

import java.util.HashMap;
import java.util.Map;

public class XmlUtilTest {
	
	public static void main(String[] args) {
		Map<String, Object> vo1 = new HashMap<String, Object>();
		vo1.put("userName", "jufeng");
		vo1.put("password", "a123456");
		String xml = XmlUtil.map2xml(vo1, "body");
		System.out.println(xml);
	}
	

}
