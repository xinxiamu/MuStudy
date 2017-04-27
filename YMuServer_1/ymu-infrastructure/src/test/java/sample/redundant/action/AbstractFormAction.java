package sample.redundant.action;

import java.util.Map;

import sample.redundant.mgr.RedundantDataUpdateMgr;

/**
 * @Create on Jul 6, 2013
 * @Since 
 * @author kevin.xiao
 *
 */
public abstract class AbstractFormAction{
	
	public static final String KEY_WORKFLOW_INSTANCE_ID = "workflowInstanceId";

	/**
	 * 当前操作表单Id
	 */
	private String formId;
	
	/**
	 * 当前操作环节Id
	 */
	private String actionId;
	
	/**
	 * 冗余字段操作管理类实例
	 */
	private RedundantDataUpdateMgr redundantDataUpdateMgr = RedundantDataUpdateMgr.getInstance();
	
	/**
	 * 表单创建操作，包含自动初始化表单数据操作
	 * @param paramMap
	 * @return
	 */
	public Object create(Map paramMap)
	{
		Object object = createObject(paramMap);
		return redundantDataUpdateMgr.initRedundantData(formId,actionId,object);
	}
	
	/**
	 * 表单保存操作，包含自动同步表单数据操作
	 * @param object
	 */
	public void save(Object object)throws Exception
	{
		object = this.saveObject(object);
		redundantDataUpdateMgr.updateRedundantData(formId,actionId,object);
	}

	/**
	 * 抽象方法，由子类具体实现
	 * @param paramMap
	 * @return
	 */
	protected abstract Object createObject(Map paramMap);

	/**
	 * 抽象方法，由子类具体实现
	 * @param object
	 * @return
	 */
	protected abstract Object saveObject(Object object)throws Exception;

	/**
	 * @return the formId
	 */
	public String getFormId() {
		return formId;
	}

	/**
	 * @param formId the formId to set
	 */
	public void setFormId(String formId) {
		this.formId = formId;
	}

	public String getActionId()
	{
		return actionId;
	}
	
	/**
	 * @param actionId the actionId to set
	 */
	public void setActionId(String actionId) {
		this.actionId = actionId;
	}
}