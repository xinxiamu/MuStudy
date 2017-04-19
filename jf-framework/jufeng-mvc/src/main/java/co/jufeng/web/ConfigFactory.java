package co.jufeng.web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream; 
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties; 
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;

import co.jufeng.json.JSONObject;
import co.jufeng.util.FileUtil;
import co.jufeng.util.XmlUtil;
import co.jufeng.web.enums.ConfigTypeEnum;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class ConfigFactory {
	
	private final static Map<Object, Object> RESOURCES = new HashMap<Object, Object>();
	
	ConfigFactory(){
	}
	
	static{
		String dir = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    	File file = new File(dir);
		File[] files = file.listFiles();
		for (int i = 0; i < files.length; i++) {
			String fileName = files[i].getName();
			String extensionName = FileUtil.getExtensionName(fileName);
			if(extensionName.equals(ConfigTypeEnum.PROPERTIES.value)){
				load(files[i]);
			}else if (extensionName.equals(ConfigTypeEnum.JSON.value)){
				load(files[i]);
			}else if (extensionName.equals(ConfigTypeEnum.XML.value)){
				load(files[i]);
			}
		}
		
		if(!RESOURCES.containsKey("mvc")){
    		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    		InputStream is = classloader.getResourceAsStream("mvc.json");
    		try {
				String jsonString = IOUtils.toString(is);
				JSONObject jsonObject = JSONObject.parseObject(jsonString);
				Map<Object, Object> mvc = (Map)jsonObject;
				RESOURCES.put("mvc", mvc);
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
	}

	public static void load(File file) {
		try {
			String fileName = file.getName();
			String extensionName = FileUtil.getExtensionName(fileName);
			if(extensionName.equals(ConfigTypeEnum.PROPERTIES.value)){
				Map<String, String> properties = new HashMap<String, String>();
	        	Properties props = new Properties();
	        	props.load(getInputStream(file));
				Map<String, String> map = new HashMap<String, String>((Map) props);  
				Set<Map.Entry<String, String>> propertySet = map.entrySet();
				Iterator<Map.Entry<String, String>> it = propertySet.iterator();
				while (it.hasNext()) {
					Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
					properties.put(entry.getKey(), entry.getValue());
				}
	            RESOURCES.put(FileUtil.getFileNameNoEx(file.getName()), properties);
	            return ;
			}
			
			if (extensionName.equals(ConfigTypeEnum.JSON.value)){
				Map<String, String> json = new HashMap<String, String>();
				StringBuffer sb = inputStreamToString(getInputStream(file));
		        JSONObject jsonObject = JSONObject.parseObject(sb.toString());  
		        Iterator<Object> jsonIt = jsonObject.keySet().iterator();
		        while (jsonIt.hasNext()) {  
		           String key = String.valueOf(jsonIt.next());  
		           Object value = jsonObject.get(key);  
		           json.put(key, value.toString());  
		        }  
		        RESOURCES.put(FileUtil.getFileNameNoEx(file.getName()), json);
		        return;
			}
			
			if (extensionName.equals(ConfigTypeEnum.XML.value)){
				StringBuffer xml = inputStreamToString(getInputStream(file));
				Document doc = DocumentHelper.parseText(xml.toString());
				RESOURCES.put(FileUtil.getFileNameNoEx(file.getName()), XmlUtil.xml2map(doc.getRootElement()));
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static StringBuffer inputStreamToString(InputStream inputStream) {
		StringBuffer sb = new StringBuffer();
		byte[] b = new byte[1024];
		int len = 0;
		try {
			while ((len = inputStream.read(b)) != -1) {
				sb.append(new String(b, 0, len));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb;
	}


	private static InputStream getInputStream(File file) {
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(file.getName());
		return inputStream;
	}
	
	public static Map<String, String> get(String fileName) {
		return (Map<String, String>) RESOURCES.get(fileName);
	}
	
	
	public static String getString(String fileName, String key) {
		Map<String, String> map = get(fileName);
		return String.valueOf(map.get(key));
	}
	
	public static Map<Object, Object> getAll() {
		return RESOURCES;
	}
	
}