package co.jufeng.core.factory;

public interface IPropertiesFactory {

	public Object get(String key);

	public <T> T get(String key, Class<T> clazz);

	public String getString(String key);
}
