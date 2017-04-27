package sample.redundant.mgr;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.BeanUtilsBean2;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

import sample.redundant.dao.MockDAOService;

/**
 * @Create on Aug 4, 2013
 * @Since 
 * @author kevin.xiao
 *
 */
public class RedundantDataUpdateMgr {
	
	private static RedundantDataUpdateMgr instance =  new RedundantDataUpdateMgr();
	
	private  RedundantDataUpdateMgr()
	{
	}
	
	public static RedundantDataUpdateMgr getInstance()
	{
		return instance;
	}
	
	
	/**
	 * 初始化表单冗余数据
	 * @param descObject
	 * @return
	 */
	public Object initRedundantData(String formId,String actionId,Object descObject)
	{
		List<FormMapping> formMappingList = FormMappingMgr.getMapping(formId,actionId,FormMapping.MAPPING_TYPE_INIT);
		for(FormMapping formMapping : formMappingList)
		{
			List objectList = queryObjects(descObject,formMapping);
			for(Object srcObject : objectList)
			{
				porpulateFields(formMapping,srcObject,descObject);
			}
		}
		return descObject;
	}
	
	/**
	 * 同步更新表单冗余数据
	 * @param srcObject
	 */
	public void updateRedundantData(String formId, String actionId,Object srcObject)throws Exception
	{
		List<FormMapping> formMappingList = FormMappingMgr.getMapping(formId, actionId, FormMapping.MAPPING_TYPE_UPDATE);
		for(FormMapping formMapping : formMappingList)
		{
			List objectList = queryObjects(srcObject,formMapping);
			for(Object descObject : objectList)
			{
				porpulateFields(formMapping,srcObject,descObject);
				MockDAOService.update(descObject);
			}
		}
	}
	
	/**
	 * 对映射字段赋值
	 * @param formMapping
	 * @param srcObject
	 * @param destObject
	 */
	private void porpulateFields(FormMapping formMapping,Object srcObject,Object destObject)
	{
		Set<String> srcFields = formMapping.getSrcFields();
		for(String srcField : srcFields )
		{
			String destField = formMapping.getDestField(srcField);
			copyProperty(srcObject,srcField,destObject,destField);
		}
	}
	
	/**
	 * 对单个字段赋值
	 * @param srcObject
	 * @param srcField
	 * @param destObject
	 * @param destField
	 */
	public void copyProperty(Object srcObject,String srcField,Object destObject,String destField)
	{
		BeanUtilsBean.setInstance(new BeanUtilsBean2());
		DateConverter dateConverter = new DateConverter();
		dateConverter.setPattern("yyyy-MM-dd HH:mm:ss");
		ConvertUtils.register(dateConverter, Date.class);
		try {
			BeanUtils.setProperty(destObject, destField, BeanUtils.getProperty(srcObject, srcField));
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据当前的formObject和关联关系，查找对应的数据对象
	 * 1.init:查找数据来源对象
	 * 2.update:查找更新目标对象
	 * @param object
	 * @param formMapping
	 * @return
	 */
	private List queryObjects(Object object,FormMapping formMapping)
	{
		Object queryExampleObject = null;
		if(FormMapping.MAPPING_TYPE_INIT.equals(formMapping.getMappingType()))
		{
			queryExampleObject =  formMapping.getSrcForm().createFormObject();
		}
		else if(FormMapping.MAPPING_TYPE_UPDATE.equals(formMapping.getMappingType()))
		{
			queryExampleObject =  formMapping.getDestForm().createFormObject();
		}
		else{
			throw new RuntimeException("映射类型错误！");
		}
		Set<String> queryFields = formMapping.getQueryFields();
		for(String queryField : queryFields)
		{
			String dataField = formMapping.getQueryDataField(queryField);
			copyProperty(object,dataField,queryExampleObject,queryField);
		}
		return MockDAOService.findByExmaple(queryExampleObject);
	}
}
