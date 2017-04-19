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

public class LogoutPlugin implements Plugin {
	private HttpServletRequest request = null;
	private HttpServletResponse response = null;
	private String uid = "";
	private UserDao userDao = null;

	public LogoutPlugin(HttpServletRequest request, HttpServletResponse response) {
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
				String cookieValue = "status=logout&uid=&create="
						+ System.currentTimeMillis() + "&server=" + serverName;
				PPKeys keys = new PPKeys();
				RSAPrivateKey privateKey = (RSAPrivateKey) ppKey.getKeyMap()
						.get(PPKey.PRIVATE_KEY);
				String encCookieValue = keys.encrypt(privateKey, cookieValue);
				Cookie cookie = new Cookie(cookieName, encCookieValue);
				cookie.setDomain(domain);
				cookie.setMaxAge(-1);
				cookie.setPath("/");

				response.addCookie(cookie);

				if (userDao != null) {
					request.getSession().setAttribute(
							userDao.getUserSessionAttributeName(), null);
				}
				return;
			}
		}
	}

	public String getUid() {
		return this.uid;
	}

	public boolean isLogined() {
		return false;
	}

	public boolean hasToken() {
		return false;
	}

	public void setUserDao(UserDao dao) {
		this.userDao = dao;
	}

	public void setTimeout(int ms) {
	}
}
