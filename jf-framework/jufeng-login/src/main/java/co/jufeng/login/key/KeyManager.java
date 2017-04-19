package co.jufeng.login.key;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import co.jufeng.login.load.KeyLoader;

public class KeyManager {
	private static Map<String, PPKey> map = new HashMap<String, PPKey>();
	private static List<String> domains = new LinkedList<String>();

	public static synchronized void loadKey(String keyFilePath) {
		KeyLoader loadGenerator = new KeyLoader();
		loadGenerator.readKey(keyFilePath);
		map.put(loadGenerator.getDomain(), loadGenerator.getKey());
		if (!domains.contains(loadGenerator.getDomain())) {
			domains.add(loadGenerator.getDomain());
		}
		System.out.println(map);
	}

	public static List<String> getDomains() {
		return domains;
	}

	public static Map<String, PPKey> getMap() {
		return map;
	}

}
