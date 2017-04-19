package co.jufeng.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;

public class PropertiesUtil {

	static PropertiesUtil uniqueInstance = null;

	static Properties PROPERTIES;
	
	PropertiesUtil() {
	}
	
	public static Map<String, String> loadProperties(String name) {
		return loadProperties(name, Locale.getDefault());
	}
	
	public static Map<String, String> loadProperties(String name, Locale currentLocale) {
		Map<String, String> map = new HashMap<String, String>();
		ResourceBundle config = ResourceBundle.getBundle(name, currentLocale);
		Iterator<String> it = config.keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			map.put(key, config.getString(key));
		}
		return map;
	}

	public static PropertiesUtil getInstance(String propertiesPath) {
		try {
			if (uniqueInstance == null) {
				uniqueInstance = new PropertiesUtil();
				FileInputStream fileInputStream = new FileInputStream(propertiesPath);
				InputStream in = new BufferedInputStream(fileInputStream);
				PROPERTIES = new Properties();
				PROPERTIES.load(in);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return uniqueInstance;
	}

	public String getString(String key) {
		return PROPERTIES.getProperty(key);
	}
	
	public String[] getStringArray(String key, String split) {
		return PROPERTIES.getProperty(key).split(split);
	}
	
	public Object getObject(String key) {
		return PROPERTIES.get(key);
	}
	
	public Enumeration<?> getKeys() {
		return PROPERTIES.keys();
	}

	public int getInt(String key) {
		try {
			String value = PROPERTIES.getProperty(key);
			if (null != value) {
				return Integer.valueOf(value);
			} else {
				return -1;
			}
		} catch (Exception e) {
			return -1;
		}
	}

	public boolean update(String propertiesFilePath, String key, int value) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put(key, value);

			File file = new File(propertiesFilePath);
			Properties pro = new Properties();
			FileInputStream fis = null;
			BufferedInputStream bis = null;

			if (!map.isEmpty()) {
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				pro.load(bis);
				FileOutputStream fos = new FileOutputStream(file);
				Set<Map.Entry<String, Object>> set = map.entrySet();
				Iterator<Map.Entry<String, Object>> it = set.iterator();
				while (it.hasNext()) {
					Map.Entry<java.lang.String, java.lang.Object> entry = (Map.Entry<java.lang.String, java.lang.Object>) it
							.next();
					pro.setProperty(entry.getKey(),
							String.valueOf(entry.getValue()));
				}
				pro.store(fos, null);
				fos.close();
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
