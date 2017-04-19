package co.jufeng;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.jufeng.util.Parameter;

@SuppressWarnings("unchecked")
public class BaseAction {
	
	private HttpServletRequest request;
	
	private HttpServletResponse response;
	
	private Parameter<String, String> parameter;
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}
	
	public HttpSession getSession() {
		return request.getSession();
	}

	public ServletContext getApplication() {
		return request.getServletContext();
	}
	
	public Parameter<String, String> getParameter() {
		return parameter;
	}
	
	public String getThemes() {
		return request.getSession().getAttribute("themes").toString();
	}
	
	public void setThemes(String value) {
		request.getSession().setAttribute("themes", value);
	}
	
	public Map<String, String> getI18n(){
		Map<String, String> i18nMap = (Map<String, String>) this.getSession().getAttribute("i18n");
		return i18nMap;
	}
	
	public Object getI18nObject(String key){
		return getI18n().get(key);
	}
	
	
	public String getI18nString(String key){
		String value = getI18nObject(key).toString();
		return value;
	}
	
	public Integer getI18nInteger(String key){
		Integer value = Integer.valueOf(getI18nString(key));
		return value;
	}
	
	public Long getI18nLong(String key){
		Long value = Long.valueOf(getI18nString(key));
		return value;
	}
	
	public Double getI18nDouble(String key){
		Double value = Double.valueOf(getI18nString(key));
		return value;
	}
	
}
