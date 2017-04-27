package sample.redundant.mgr;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * @Create on Jul 6, 2013
 * @Since 
 * @author kevin.xiao
 *
 */
public class FormMapping {
	
	/**初始化操作*/
	public static final String MAPPING_TYPE_INIT = "0";
	/**同步更新操作*/
	public static final String MAPPING_TYPE_UPDATE = "1";
	/**映射标识*/
	private String mappingId;
	/**源表单对象*/
	private FormInfo srcForm;
	/**目标表单对象*/
	private FormInfo destForm;
	/**环节*/
	private String actionId;
	/**操作类型 ： 初始化/同步更新*/
	private String mappingType;
	
	/**
	 * 保存表单查询条件:queryField - valueField值对
	 */
	private Map<String,String> queryFieldsMap = new HashMap<String,String>();
	
	/**
	 * 字段映射关系 srcField <----> destField
	 */
	private Map<String,String> mappingFieldsMap = new HashMap<String,String>();

	/**
	 * 添加字段映射
	 * @param srcFieldName  源字段
	 * @param destFieldName 目标字段
	 */
	public void addMappingField(String srcFieldName,String destFieldName)
	{
		mappingFieldsMap.put(srcFieldName,destFieldName);
	}
	/**
	 * 获取源字段集合
	 * @return
	 */
	public Set<String> getSrcFields()
	{
		return mappingFieldsMap.keySet();
	}
	/**
	 * 根据源字段获取获取目标字段
	 * @param srcField  源字段
	 * @return
	 */
	public String getDestField(String srcField)
	{
		return mappingFieldsMap.get(srcField);
	}
	
	/**
	 * 添加表单查询字段
	 * @param queryFieldName  目标表单的查询字段
	 * @param valueFieldName  源表单的查询值来源字段
	 */
	public void addQueryField(String queryFieldName,String valueFieldName)
	{
		queryFieldsMap.put(queryFieldName,valueFieldName);
	}
	/**
	 * 获取查询字段集合
	 * @return
	 */
	public Set<String> getQueryFields()
	{
		return queryFieldsMap.keySet();
	}
	/**
	 * 根据查询字段获取对应的数值字段
	 * @param queryField 目标表单的查询字段
	 * @return
	 */
	public String getQueryDataField(String queryField)
	{
		return queryFieldsMap.get(queryField);
	}
	
	public FormMapping(String mappingId,FormInfo srcForm,FormInfo destForm,String actionId,String mappingType)
	{
		this.mappingId = mappingId;
		this.srcForm = srcForm;
		this.destForm = destForm;
		this.actionId = actionId;
		this.mappingType = mappingType;
	}
	
	/**
	 * @return the mappingId
	 */
	public String getMappingId() {
		return mappingId;
	}


	/**
	 * @param mappingId the mappingId to set
	 */
	public void setMappingId(String mappingId) {
		this.mappingId = mappingId;
	}


	/**
	 * @return the srcForm
	 */
	public FormInfo getSrcForm() {
		return srcForm;
	}


	/**
	 * @param srcForm the srcForm to set
	 */
	public void setSrcForm(FormInfo srcForm) {
		this.srcForm = srcForm;
	}


	/**
	 * @return the destForm
	 */
	public FormInfo getDestForm() {
		return destForm;
	}


	/**
	 * @param destForm the destForm to set
	 */
	public void setDestForm(FormInfo destForm) {
		this.destForm = destForm;
	}


	/**
	 * @return the actionId
	 */
	public String getActionId() {
		return actionId;
	}


	/**
	 * @param actionId the actionId to set
	 */
	public void setActionId(String actionId) {
		this.actionId = actionId;
	}


	/**
	 * @return the fieldsMap
	 */
	public Map<String, String> getFieldsMap() {
		return mappingFieldsMap;
	}


	/**
	 * @param fieldsMap the fieldsMap to set
	 */
	public void setFieldsMap(Map<String, String> fieldsMap) {
		this.mappingFieldsMap = fieldsMap;
	}

	/**
	 * @return the mappingType
	 */
	public String getMappingType() {
		return mappingType;
	}

	/**
	 * @param mappingType the mappingType to set
	 */
	public void setMappingType(String mappingType) {
		this.mappingType = mappingType;
	}

	/**
	 * @return the queryFieldsMap
	 */
	public Map<String, String> getQueryFieldsMap() {
		return queryFieldsMap;
	}

	/**
	 * @param queryFieldsMap the queryFieldsMap to set
	 */
	public void setQueryFieldsMap(Map<String, String> queryFieldsMap) {
		this.queryFieldsMap = queryFieldsMap;
	}
}
