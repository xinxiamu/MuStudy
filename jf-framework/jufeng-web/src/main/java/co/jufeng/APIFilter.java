package co.jufeng;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.jufeng.core.factory.BeanFactory;
import co.jufeng.string.StringUtil;
import co.jufeng.web.ApiRspResultVO;
import co.jufeng.web.enums.OutTypeEnum;
import co.jufeng.web.util.OutUtil;
import co.jufeng.web.util.RequestUtil;

@WebFilter
public class APIFilter implements Filter{
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;
	private Map<String, String> mapI18n;
	static final Map<String, String> MAP = new HashMap<String, String>();
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		Map<String, Object> controllerMap = BeanFactory.getControllerPool();
		Map<String, Object> serviceMap = BeanFactory.getServicePool();
		Map<String, Object> repositoryMap = BeanFactory.getAccessorPool();
		System.out.println("controllerMap >>>>" + controllerMap);
		System.out.println("serviceMap >>>>" + serviceMap);
		System.out.println("repositoryMap >>>>" + repositoryMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doFilter(ServletRequest srt, ServletResponse sre, FilterChain chain) throws IOException, ServletException {
		request = (HttpServletRequest) srt;
		response = (HttpServletResponse) sre;
		session = request.getSession();
		mapI18n = (Map<String, String>) session.getAttribute("i18n");
		ApiRspResultVO apiResultVO = new ApiRspResultVO();
		Map<String, String> map = RequestUtil.toObject(request);
		String signMethod = map.get("signMethod");
    	if(StringUtil.isEmpty(signMethod) || !signMethod.equals(MAP.get("signMethod"))){
    		apiResultVO.setDescription(mapI18n.get("signMethod"));
    		apiResultVO.setIsSuccess(false);
    		OutUtil.out(response, apiResultVO, OutTypeEnum.JSON);
			return;
    	}
		
    	chain.doFilter(request, response);   
	}

	@Override
	public void destroy() {
	}

}
