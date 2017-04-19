package co.jufeng.login.plugin;

import co.jufeng.login.user.UserDao;

public interface Plugin {
	public boolean hasToken();

	public boolean isLogined();

	public String getUid();

	public void doPlugin() throws Exception;

	public void setUserDao(UserDao dao);

	public void setTimeout(int ms);
}
