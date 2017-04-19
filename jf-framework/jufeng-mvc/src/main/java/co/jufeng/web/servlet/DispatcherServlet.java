package co.jufeng.web.servlet;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import co.jufeng.core.factory.BeanFactory;
import co.jufeng.json.JSONObject;
import co.jufeng.string.StringUtil;
import co.jufeng.util.PathUtil;
import co.jufeng.web.ApiRspResultVO;
import co.jufeng.web.ConfigFactory;
import co.jufeng.web.enums.OutTypeEnum;
import co.jufeng.web.multipart.commons.CommonsMultipartFile;
import co.jufeng.web.util.OutUtil;
import co.jufeng.web.util.RequestUtil;

@WebServlet(name = "jufeng-mvc", urlPatterns="*.do")
public class DispatcherServlet extends MVCHttpServlet {
	
	private static final long serialVersionUID = -1044664027920380408L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String themes = ConfigFactory.getString("mvc", "themes");
		request.getSession().setAttribute("themes", themes);
		Map<String, String> parameter = RequestUtil.toObject(request);
		String uri = request.getRequestURI();
		uri = uri.substring(request.getContextPath().length(), uri.length() - 3);
		String methodName = null;
		if (uri.indexOf("!") > 0) {
			String[] uriAndMethodName = uri.split("!");
			uri = uriAndMethodName[0];
			methodName = uriAndMethodName[1];
		}
		
		Map<String, Object> actions = BeanFactory.getControllerPool();
		if (actions.containsKey(uri)) {
			Object obj = actions.get(uri);
 			setSuperClassProperty(obj, request, response);
			
			if (methodName == null) {
				methodName = "index";
			}
			Method method = null;
			Object[] args = null;
			try {
				Class<?> clazz = obj.getClass();
				Method[] methods = clazz.getMethods();
				for (int i = 0; i < methods.length; i++) {
					String methodString = methods[i].toString();
					if(methodString.indexOf(methodName + "(") > 0){
						String[] paramTypes = getParamType(clazz, methodName);
						String[] paramArrsy = getParamName(clazz, methodName);  
						args = new Object[paramTypes.length];
						for (int j = 0; j < paramTypes.length; j++) {
							if(paramTypes[j].indexOf("HttpServletRequest") > 0){
								args[j] = request;
								continue;
							}
							
							if(paramTypes[j].indexOf("HttpServletResponse") > 0){
								args[j] = response;
								continue;
								
							}
							
							if(paramTypes[j].indexOf("ModelAndView") > 0){
								args[j] = new ModelAndView(request);
								continue;
								
							}
							if(paramTypes[j].indexOf("CommonsMultipartFile") > 0){
								DiskFileItemFactory factory = new DiskFileItemFactory();
								String path = PathUtil.getWebRootPath();
								factory.setRepository(new File(path));
								ServletFileUpload upload = new ServletFileUpload(factory);
								CommonsMultipartFile commonsMultipartFile = new CommonsMultipartFile(upload.parseRequest(request));
								args[j] = commonsMultipartFile;
								continue;
							}
							
							if(paramTypes[j].indexOf("String") > 0){
								String key = paramArrsy[j];
								args[j] = parameter.get(key);
							}else{
								Class<?> clz = Class.forName(paramTypes[j]);
								Object javaBean = clz.newInstance();
								javaBean = RequestUtil.toObject(request, javaBean.getClass());
								args[j] = javaBean;
							}
						}
						method = methods[i];
						break;
					}
				}
				if(args == null){
					response.sendError(404);
					return;
				}
			} catch (Exception e) {
				e.printStackTrace();
				response.sendError(500);
			}
			
			try {
				if(viewSuffixes == null){
					countView(request);
				}
				Object object = method.invoke(obj, args);
				if (object instanceof ModelAndView) {
					ModelAndView modelAndView = (ModelAndView) object;
					String returnView = modelAndView.getView().toString();
					if(isRedirect(returnView)){
						sendRedirect(returnView, request, response);
						
					}else{
						forward(returnView, request, response);
						
					}

				} else if (object instanceof String) {
					String returnView = object.toString();
					if(isRedirect(returnView)){
						sendRedirect(returnView, request, response);
						
					}else{
						ModelAndView modelAndView = new ModelAndView(request);
						modelAndView.addViewName(object.toString());
						forward(modelAndView.getView().toString(), request, response);
						
					}

				}else if (object instanceof JSONObject || object instanceof ApiRspResultVO) {
					OutUtil.out(response, object, OutTypeEnum.JSON);

				}else{
					methodName = StringUtil.propertyToField(methodName, "-");
					forward(methodName + viewSuffixes, request, response);
				}
			} catch (Exception e) {
				e.printStackTrace();
				response.sendError(500);
			}
			
		} else {
			uri = uri.replaceFirst(File.separator, "");
			String view = countView(request);
			uri = getThemes(request) + uri;
			uri = File.separator + uri;
			if(ftlAction.lastIndexOf(uri + viewSuffixes) < 0){
				response.sendError(404);
				return;
			}
			int length = request.getRequestURI().length();
			String actionSuffixes = request.getRequestURI().substring(length - 3, length);
			uri = uri.concat(actionSuffixes);
			uri = uri.replace(request.getContextPath(), "").replace(actionSuffixes, viewSuffixes);
			for (int i = 0; i < ftlAction.size(); i++) {
				String ftl = view + ftlAction.get(i).replaceFirst(File.separator, "");
				if (ftl.indexOf(uri) > 0) {
					request.getRequestDispatcher(ftl).forward(request, response);
					break;
				}
			}
		}
	}

}