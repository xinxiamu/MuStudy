package sample.redundant.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

/**
 * @Create on Jul 7, 2013
 * @Since 
 * @author kevin.xiao
 *
 */
public class MockDAOService {
	public static Map<String, Object> objectList = new HashMap<String, Object>();
	
	
	public static void add(Object object)throws Exception
	{
		objectList.put(BeanUtils.getProperty(object, "id"), object);
	}
	
	public static void update(Object object) throws Exception
	{
		objectList.put(BeanUtils.getProperty(object, "id"), object);
	}
	
	public static Object get(String id)
	{
		return objectList.get(id);
	}
	
	public static List findByExmaple(Object exampleObject)
	{
		List<Object> resultList = new ArrayList<Object>();
		for(Object object:objectList.values())
		{
			if(object.getClass() == exampleObject.getClass())
				resultList.add(object);
		}
		return resultList;
	}
	
	
	
}
