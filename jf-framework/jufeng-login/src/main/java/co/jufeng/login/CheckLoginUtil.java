package co.jufeng.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.jufeng.login.plugin.Plugin;
import co.jufeng.login.plugin.ValidatePlugin;

public class CheckLoginUtil {
	
	public static String action(HttpServletRequest request, HttpServletResponse response){
		String userId = null;
		try {
			Plugin plugin = new ValidatePlugin(request, response);
			plugin.doPlugin();
			if (plugin.hasToken()) {
				if (plugin.isLogined()) {
					userId = plugin.getUid();
					System.out.println("令牌验证通过，并且登录的ID是：" + userId);
				} else {
					System.out.println("令牌验证通过，但是没有登录或者已经注销");
				}
			} else {
				System.out.println("没有发现令牌");
			}

		} catch (Exception exception) {
			System.out.println("令牌验证出错:" + exception.toString());
			exception.printStackTrace();
		}
		return userId;
	}
	
	
}
