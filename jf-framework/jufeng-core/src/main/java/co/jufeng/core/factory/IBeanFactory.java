package co.jufeng.core.factory;

public interface IBeanFactory extends IAware{
	
	public Object getBean(String name) throws Exception;
	
	public <T> T getBean(String name, Class<T> requiredType) throws Exception;
	

}
