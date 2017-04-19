package co.jufeng.login.plugin;

import java.security.interfaces.RSAPrivateKey;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.jufeng.login.key.KeyManager;
import co.jufeng.login.key.PPKey;
import co.jufeng.login.key.PPKeys;
import co.jufeng.login.user.UserDao;

public class CreatePlugin implements Plugin {
	private String uid = "";
	private HttpServletRequest request = null;
	private HttpServletResponse response = null;
	private boolean isLogined = false;
	private boolean hasToken = false;
	private UserDao userDao = null;
	private int ms = -1;

	public CreatePlugin(HttpServletRequest request,
			HttpServletResponse response, String uid) {
		this.uid = uid;
		this.request = request;
		this.response = response;
	}

	public void doPlugin() throws Exception {
		String serverName = request.getServerName();
		List<String> domains = KeyManager.getDomains();
		for (int i = 0; i < domains.size(); i++) {
			String domain = domains.get(i);
			if (serverName.endsWith("." + domain)) {
				PPKey ppKey = KeyManager.getMap().get(domain);
				String cookieName = ppKey.getKeyMap().get("cookieName") + "";
				String cookieValue = "status=logined&uid=" + uid + "&create="
						+ System.currentTimeMillis() + "&server=" + serverName
						+ "&timeout=" + ms;
				PPKeys keys = new PPKeys();
				RSAPrivateKey privateKey = (RSAPrivateKey) ppKey.getKeyMap()
						.get(PPKey.PRIVATE_KEY);
				String encCookieValue = keys.encrypt(privateKey, cookieValue);
				Cookie cookie = new Cookie(cookieName, encCookieValue);
				cookie.setDomain(domain);
				cookie.setMaxAge(-1);
				cookie.setPath("/");

				response.addCookie(cookie);
				isLogined = true;
				hasToken = true;

				if (userDao != null) {
					if (request.getSession().getAttribute(
							userDao.getUserSessionAttributeName()) == null) {
						request.getSession().setAttribute(
								userDao.getUserSessionAttributeName(),
								userDao.getUserByUid(uid));
					}
				}
				return;
			}
		}
	}

	public String getUid() {
		return this.uid;
	}

	public boolean isLogined() {
		return isLogined;
	}

	public boolean hasToken() {
		return hasToken;
	}

	public void setUserDao(UserDao dao) {
		this.userDao = dao;
	}

	public void setTimeout(int ms) {
		this.ms = ms;
	}
}
