package co.jufeng.login.gene;

public class KeyGenerator {
	private String domain = "";
	private String cookieName = "";
	private PPKeyCreater ppKey = null;

	public KeyGenerator() {
	}

	public KeyGenerator(String domain, String cookieName) {
		this.domain = domain;
		this.cookieName = cookieName;
	}

	public String getDomain() {
		return domain;
	}

	public String getCookieName() {
		return cookieName;
	}

	public PPKeyCreater getKey() {
		return ppKey;
	}

	public void generateKey(String keyFilePath) {
		ppKey = new PPKeyCreater();
		ppKey.getKeyMap().put("domain", domain);
		ppKey.getKeyMap().put("cookieName", cookieName);
		ppKey.save(keyFilePath);
	}

	public void readKey(String keyFilePath) {
		ppKey = new PPKeyCreater();
		ppKey.read(keyFilePath);
		domain = ppKey.getKeyMap().get("domain") + "";
		cookieName = ppKey.getKeyMap().get("cookieName") + "";
	}
}
