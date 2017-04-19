package co.jufeng.login.load;

import co.jufeng.login.key.PPKey;

public class KeyLoader {
	private String domain = "";
	private String cookieName = "";
	private PPKey ppKey = null;

	public KeyLoader() {
	}

	public String getDomain() {
		return domain;
	}

	public String getCookieName() {
		return cookieName;
	}

	public PPKey getKey() {
		return ppKey;
	}

	public void readKey(String keyFilePath) {
		ppKey = new PPKey();
		ppKey.read(keyFilePath);
		domain = ppKey.getKeyMap().get("domain") + "";
		cookieName = ppKey.getKeyMap().get("cookieName") + "";
	}
}
