package co.jufeng.login.gene;

public class KeyCreator {
	private String domain = "";
	private String cookieName = "";
	private PPKeyCreater ppKey = null;

	public KeyCreator(String domain, String cookieName) {
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

	public void createKey(String keyFilePath) {
		ppKey = new PPKeyCreater();
		ppKey.getKeyMap().put("domain", domain);
		ppKey.getKeyMap().put("cookieName", cookieName);
		ppKey.save(keyFilePath);
	}

}
