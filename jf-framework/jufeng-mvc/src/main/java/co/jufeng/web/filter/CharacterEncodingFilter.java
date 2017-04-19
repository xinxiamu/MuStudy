package co.jufeng.web.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

@WebFilter(filterName = "encodingFilter", urlPatterns="/*", initParams = {@WebInitParam(name="encoding", value="UTF-8")})
public class CharacterEncodingFilter implements Filter {
	
	private String encoding = "UTF-8";
	
	private Map<String, String> params = new HashMap<String, String>();

	@Override
	public void destroy() {
		params = null;
		encoding = null;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		req.setCharacterEncoding(encoding);
		resp.setCharacterEncoding(encoding);
		resp.setContentType("text/html;charset=" + encoding);
		chain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		encoding = config.getInitParameter("encoding");
		for (Enumeration<String> e = config.getInitParameterNames(); e.hasMoreElements();) {
			String name = e.nextElement();
			String value = config.getInitParameter(name);
			params.put(name, value);
		}
	}
}