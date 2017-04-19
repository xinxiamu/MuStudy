package co.jufeng.web.servlet;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.jufeng.logger.LoggerUtil;
import co.jufeng.string.StringUtil;
import co.jufeng.web.util.RequestUtil;

public class MVCHttpServlet extends HttpServlet{

	private static final long serialVersionUID = 4518764411274378957L;
	

	protected List<String> ftlAction = null;

	protected boolean isPrefix;

	protected String viewSuffixes;
	
	@Override
	public void init() throws ServletException {
		super.init();
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.service(request, response);
	}
	
	protected void setSuperClassProperty(Object object, HttpServletRequest request, HttpServletResponse response) {
		try {
			Field field = null ;              
	        Class<?> clazz = object.getClass() ;    
	        clazz = clazz.getSuperclass();
        	Field[] fields = clazz.getDeclaredFields();  
        	for (int i = 0; i < fields.length; i++) {
        		field = fields[i];
        		field.setAccessible(true);
				String type = field.getType().toString();
				if (type.endsWith("HttpServletRequest")) {
					field.set(object, request);
					continue;
				}
				
				if (type.endsWith("HttpServletResponse")) {
					field.set(object, response);
					continue;
				}
				if (type.endsWith("Parameter")) {
					field.set(object, RequestUtil.toObject(request));
					continue;
				}
			}
		} catch (Exception e) {
		} 
	}
	
	protected boolean isRedirect(String view){
		if(view.indexOf(viewSuffixes) > 0){
			return false;
		}else{
			return true;
		}
		
	}
	
	protected CtMethod initClassPool(Class<?> clazz, String methodName) throws NotFoundException {
		ClassPool pool = ClassPool.getDefault();
		pool.insertClassPath(new ClassClassPath(clazz));
		CtClass cc = pool.get(clazz.getName());
		CtMethod cm = cc.getDeclaredMethod(methodName);
		return cm;
	}
	
	protected LocalVariableAttribute getMethodInfo(CtMethod cm) {
		MethodInfo methodInfo = cm.getMethodInfo();
		CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
		LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute .getAttribute(LocalVariableAttribute.tag);
		return attr;
	}
	
	protected String[] getParamName(Class<?> clazz, String methodName) {
		String[] paramNames = null;
		try {
			CtMethod cm = initClassPool(clazz, methodName);
			LocalVariableAttribute attr = getMethodInfo(cm);
			if (attr == null) {
			}
			paramNames = new String[cm.getParameterTypes().length];
			int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
			for (int i = 0; i < paramNames.length; i++){
				paramNames[i] = attr.variableName(i + pos);
			}
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		return paramNames;
	}

	protected String[] getParamType(Class<?> clazz, String methodName) {
		String[] paramTypes = null;
		try {
			CtMethod cm = initClassPool(clazz, methodName);
			CtClass[] cmTypeArray = cm.getParameterTypes();
			paramTypes = new String[cmTypeArray.length];
			for (int i = 0; i < cmTypeArray.length; i++) {
				CtClass cmType = cmTypeArray[i];
				paramTypes[i] = cmType.getName();
			}
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		return paramTypes;
	}


	protected String countView(HttpServletRequest request) {
		String view = this.getServletContext().getAttribute("view").toString();
		StringBuffer webPath = new StringBuffer(request.getServletContext().getRealPath(File.separator));
		File file = new File(webPath + view);
		getAllFiles(file);
		return view;
	}
	

	protected void forward(String view, HttpServletRequest request, HttpServletResponse response) {
		try {
			Object viewPath = this.getServletContext().getAttribute("view");
			String themes = getThemes(request);
			viewPath += themes;
			if (StringUtil.isEmpty(viewPath.toString())) {
				response.sendError(404);
			} else {
				viewPath += view;
			}
			request.getRequestDispatcher(viewPath.toString()).forward(request, response);
		} catch (Exception e) {
			LoggerUtil.debug(getClass(), e);
		}
	}

	protected String getThemes(HttpServletRequest request) {
		Object themes = request.getSession().getAttribute("themes");
		if(themes == null){
			themes = "";
		}else{
			themes = themes.toString().concat(File.separator);
		}
		return themes.toString();
	}
	
	protected void sendRedirect(String view, HttpServletRequest request, HttpServletResponse response) {
		try {
			if(view.indexOf("http") < 0){
				String path = request.getContextPath();
				String basePath = request.getScheme() + "://" + request.getServerName() + ":" +request.getServerPort() + path + "/";
				view = basePath + view;
			}
			response.sendRedirect(view);
		} catch (Exception e) {
			LoggerUtil.debug(getClass(), e);
		}
	}

	protected void getAllFiles(File dir) {
		ftlAction = new ArrayList<String>();
		List<File> myfile = new ArrayList<File>();
		listDirectory(dir, myfile);
		for (File file : myfile) {
			String fileName = file.getName();
			if (isPrefix == false) {
				viewSuffixes = fileName.substring(fileName.lastIndexOf("."));
				isPrefix = true;
			}
			ftlAction.add(file.getAbsolutePath().replace(dir + File.separator, File.separator));
		}
	}
	
	protected void listDirectory(File path, List<File> myfile) {
		if (path.isFile()) {
			myfile.add(path);
		} else {
			File[] files = path.listFiles();
			for (int i = 0; i < files.length; i++) {
				listDirectory(files[i], myfile);
			}
		}
	}
	//返回的String指明了客户端的提交方式
//	protected return_type name() {
//		
//		if (request.getMethod().equalsignore("Get"))
//		  String gets=request.getParameter("gets");
//		if (request.getMethod().equalsignore("Post"))
//		  String posts=request.getParameter("gets");
//		if (request.getMethod().equalsignore("Delete"))
//		
//	}
	

}
