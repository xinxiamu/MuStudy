package co.jufeng.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import co.jufeng.web.ConfigFactory;

@WebFilter(filterName = "i18n", urlPatterns="/*")
public class I18nFilter implements Filter {
	
	private String language;

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		language = request.getLocale().getLanguage();
		HttpSession session = request.getSession();
		if(language.equals("en") || language.equals("en_US")){
			session.setAttribute("i18n", ConfigFactory.get("i18n_en_US"));
		}else{
			session.setAttribute("i18n", ConfigFactory.get("i18n_zh_CN"));
		}
		chain.doFilter(req, resp);
	}
	

	@Override
	public void init(FilterConfig config) throws ServletException {
	}
}