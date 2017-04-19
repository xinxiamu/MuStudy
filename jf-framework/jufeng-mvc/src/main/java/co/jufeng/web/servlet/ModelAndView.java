package co.jufeng.web.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequestWrapper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import co.jufeng.web.enums.ScopeTypeEnum;

public class ModelAndView extends ServletRequestWrapper {
	private HttpServletRequest request;
	private HttpSession session;
	private ServletContext application;
	
	
	public ModelAndView(HttpServletRequest request) {
		super(request);
		this.request = request;
		this.session = request.getSession();
		this.application = this.session.getServletContext();
	}

	private Object view;

	public Object getView() {
		return view;
	}

	public ModelAndView addViewName(String viewName) {
		this.view = viewName;
		return this;
	}
	
	@Override
	public void setAttribute(String name, Object o) {
		this.addObject(name, o);
	}
	
	public ModelAndView addObject(String attributeName, Object attributeValue) {
		return this.addObject(attributeName, attributeValue, ScopeTypeEnum.REQUEST);
	}
	
	public ModelAndView addObject(String attributeName, Object attributeValue, ScopeTypeEnum scopeTypeEnum) {
		switch (scopeTypeEnum) {
		case REQUEST:
			request.setAttribute(attributeName, attributeValue);
			break;
		
		case SESSION:
			session.setAttribute(attributeName, attributeValue);
			break;

		case APPLICATION:
			application.setAttribute(attributeName, attributeValue);
			break;
			
		default:
			break;
		}
		return this;
	}
	
}
