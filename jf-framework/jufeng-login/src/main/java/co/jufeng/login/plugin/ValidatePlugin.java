package co.jufeng.login.plugin;

import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.jufeng.login.key.KeyManager;
import co.jufeng.login.key.PPKey;
import co.jufeng.login.key.PPKeys;
import co.jufeng.login.user.UserDao;
import co.jufeng.login.user.UserEntity;

public class ValidatePlugin implements Plugin {
	private HttpServletRequest request = null;
	private HttpServletResponse response = null;
	private String uid = "";
	private boolean isLogined = false;
	private boolean hasToken = false;
	private UserDao userDao = null;

	public ValidatePlugin(HttpServletRequest request, HttpServletResponse response) {
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
				Cookie cookie = getCookie(request, cookieName);
				if (cookie != null) {
					hasToken = true;
					String encCookieValue = cookie.getValue();
					PPKeys keys = new PPKeys();
					RSAPublicKey publicKey = (RSAPublicKey) ppKey.getKeyMap().get(PPKey.PUBLIC_KEY);
					String decCookieValue = keys.decrypt(publicKey, encCookieValue);
					Map<String, String> map = toMap(decCookieValue);
					request.setAttribute(serverName, map);
					if ("logined".equals(map.get("status"))) {
						int timeout = Integer.parseInt(map.get("timeout") + "");
						uid = map.get("uid") + "";
						if (timeout > 0) {
							long create = Long.parseLong(map.get("create") + "");
							if ((System.currentTimeMillis() - create) > timeout) {
								if (userDao != null) {
									request.getSession().setAttribute(userDao.getUserSessionAttributeName(), null);
								}
								return;
							}
						}

						isLogined = true;
						if (userDao != null) {
							if (request.getSession().getAttribute(userDao.getUserSessionAttributeName()) != null) {
								UserEntity userEntity = (UserEntity) request.getSession().getAttribute(userDao.getUserSessionAttributeName());
								if (uid.equals(userEntity.getUid()) == false) {
									request.getSession().setAttribute(userDao.getUserSessionAttributeName(), userDao.getUserByUid(uid));
								}
							} else {
								request.getSession().setAttribute(userDao.getUserSessionAttributeName(), userDao.getUserByUid(uid));
							}
						}
					} else {
						if (userDao != null) {
							request.getSession().setAttribute(userDao.getUserSessionAttributeName(),null);
						}
					}
				}
				return;
			}
		}
	}

	private Cookie getCookie(HttpServletRequest request, String cookieName) {
		Cookie[] cookies = request.getCookies();
		if(null != cookies){
			for (int i = 0; i < cookies.length; i++) {
				if (cookieName.equals(cookies[i].getName())) {
					return cookies[i];
				}
			}
		}
		return null;
	}

	private Map<String, String> toMap(String strs) {
		Map<String, String> map = new HashMap<String, String>();
		String[] arr = strs.split("&");
		String temp = "";
		String[] tempArray = null;
		for (int i = 0; i < arr.length; i++) {
			temp = arr[i];
			tempArray = temp.split("=");
			if (tempArray.length == 2) {
				map.put(tempArray[0].trim(), tempArray[1]);
			} else {
				map.put(tempArray[0].trim(), "");
			}

		}
		return map;
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
	}

	public HttpServletResponse getResponse() {
		return response;
	}
	
}
