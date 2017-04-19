package co.jufeng.web.enums;

/**
 * 存储对象作用域枚举
 * @author jufeng
 *
 */
public enum ScopeTypeEnum {
	
	/**
	 * 请求响应后自动消除
	 */
	REQUEST("httpServletRequest"), 
	
	/**
	 * 请求会话结束后清除
	 */
	SESSION("httpSession"),
	
	/**
	 * 应用服务器重启或停止后清除
	 */
	APPLICATION("servletContext");
	
	public final String value;

	public String getValue() {
		return value;
	}
	
	ScopeTypeEnum(String value) {
		this.value = value;
	}

}
