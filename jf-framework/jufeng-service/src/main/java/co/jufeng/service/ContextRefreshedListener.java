//package co.jufeng.service;
//
//import java.util.Map;
//
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//import javax.servlet.annotation.WebListener;
//
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.stereotype.Repository;
//
//@WebListener
//public class ContextRefreshedListener implements ApplicationListener<ContextRefreshedEvent>, ServletContextListener{  
//  
//    @Override  
//    public void onApplicationEvent(ContextRefreshedEvent event) {  
//        // 根容器为Spring容器  
//        if(event.getApplicationContext().getParent()==null){  
//            Map<String,Object> beans = event.getApplicationContext().getBeansWithAnnotation(Repository.class);  
//            for(Object bean:beans.values()){  
//                System.err.println(bean==null?"null":bean.getClass().getName());  
//            }  
//            System.err.println("=====ContextRefreshedEvent====="+event.getSource().getClass().getName());  
//        }  
//    }
//
//	@Override
//	public void contextInitialized(ServletContextEvent sce) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void contextDestroyed(ServletContextEvent sce) {
//		// TODO Auto-generated method stub
//		
//	}  
//}