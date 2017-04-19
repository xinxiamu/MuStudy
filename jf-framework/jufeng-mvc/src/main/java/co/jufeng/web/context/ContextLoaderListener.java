package co.jufeng.web.context;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import co.jufeng.core.factory.BeanFactory;
import co.jufeng.core.factory.SpringBeanFactory;
import co.jufeng.core.lang.ReflexException;
import co.jufeng.logger.LoggerUtil;
import co.jufeng.web.ConfigFactory;
import co.jufeng.web.servlet.bind.annotation.RestController;

@WebListener
public class ContextLoaderListener implements ServletContextListener{
	
    public static final String CONTROLLER_PACKAGE_PATH = "controllerPackage";
    public static final String CONTROLLER_VIEW_PATH = "viewPath";
    public static final String CONTROLLER_SUFFIXES_PATH = "controllerSuffixes";
    public static final String CONTROLLER_VIEW = "view";
    
    private static Map<String, String> mvc;
    
    private String controllerPackage;
    private String controllerSuffixes;
    
    private Object view;
    
	private ServletContext application;
	
	static {
		mvc = ConfigFactory.get("mvc");
	}
	
    @Override
    public void contextDestroyed(ServletContextEvent event) {
    }
    private static File controllerClass = null;

    public static  void getFileName(String path, String controllerPackage) {
        File f = new File(path);
        if (!f.exists()) {
            LoggerUtil.info(ContextLoaderListener.class, path + " not exists");
        }

        File fa[] = f.listFiles();
        for (int i = 0; i < fa.length; i++) {
            File fs = fa[i];
            if (fs.isDirectory()) {
            	if(fs.getName().equals(controllerPackage)){
            		controllerClass = fs;
            		return ;
            	}
                getFileName(fs.getPath(), controllerPackage);
            } 
        }
    }
    
    @Override
    public void contextInitialized(ServletContextEvent event) {
    	application = event.getServletContext();
        controllerPackage = mvc.get(CONTROLLER_PACKAGE_PATH);
        view = mvc.get(CONTROLLER_VIEW_PATH);
        controllerSuffixes = mvc.get(CONTROLLER_SUFFIXES_PATH);
        getFileName(application.getRealPath("/WEB-INF/classes/"), controllerPackage);
        controllerPackage = controllerClass.getPath().split("classes/")[1].replace(File.separatorChar, '.');
        LoggerUtil.info(ContextLoaderListener.class, controllerPackage);
        scanClassPath(controllerClass);
        application.setAttribute(CONTROLLER_VIEW, view);
    }
     
    private void scanClassPath(File file) {
        try {
            if (file.isFile()) {
                if (file.getName().endsWith(".class")) {
                    String path = file.getPath();
                    ControllerClassLoader myClassLoader = new ControllerClassLoader(this.getClass().getClassLoader());
                    Class<?> clazz = myClassLoader.load(path);
                    RestController controller = clazz.getAnnotation(RestController.class);
                    if (controller != null) {
                        String uri = controller.value();
                        Object action = clazz.newInstance();
                        
                        Field[] fs = action.getClass().getDeclaredFields();  
                        for(int i = 0 ; i < fs.length; i++){  
                            Field f = fs[i];  
                            f.setAccessible(true);
                            Resource resource = f.getAnnotation(Resource.class);
                            if(resource != null){
                            	String resourceKey = resource.name();
                            	Object object = SpringBeanFactory.getBean(resourceKey);
                            	f.set(action, object);
                            	BeanFactory.setServicePool(resourceKey, object);
                            }
                        }  
                        
                        if(action.toString().indexOf(controllerSuffixes) > 0){
                        	if(uri.equals("")){
                        		String className = action.getClass().getSimpleName();
                        		String classPath = clazz.getName();  
                        		String packageName = classPath.replace(controllerPackage, "").replace("." + className, "").replace(".", "/");
                        		char[] chars = new char[1];  
                        		chars[0] = className.charAt(0);  
                        		String temp = new String(chars);  
                        		if(chars[0] >= 'A'  &&  chars[0] <= 'Z') { 
                        			uri = className.replaceFirst(temp, temp.toLowerCase());
                        			uri = uri.replace(controllerSuffixes, "");
                        			uri = packageName + "/" + uri;
                        		}  
                        	}
                        	LoggerUtil.info(getClass(), uri);
                        	BeanFactory.setControllerPool(uri, action);
                        	application.setAttribute(action.getClass().getSimpleName(), action);
                        }
                    }
                }
            } else {
                File[] files = file.listFiles();
                for (File child : files) {
                    scanClassPath(child);
                }
            }
            
            
        } catch (Exception e) {
            throw new ReflexException(this.getClass(), e);
        }
    }
    
    
    
 
    class ControllerClassLoader extends ClassLoader {
        public ControllerClassLoader(ClassLoader parent) {
            super(parent);
        }
        public Class<?> load(String path) {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(path);
                byte[] buf = new byte[fis.available()];
                int len = 0;
                int total = 0;
                int fileLength = buf.length;
                while (total < fileLength) {
                    len = fis.read(buf, total, fileLength - total);
                    total = total + len;
                }
                return super.defineClass(null, buf, 0, fileLength);
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    fis = null;
                }
            }
        }
    }
}