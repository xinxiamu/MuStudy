package sample.redundant.mgr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import sample.redundant.action.ActionConst;


/**
 * @Create on Jul 6, 2013
 * @Since 
 * @author kevin.xiao
 *
 */
public class FormMappingMgr {
	
	private static List<FormMapping> formMappingList = new ArrayList<FormMapping>();
	
	private static void addFormMapping(FormMapping formMapping)
	{
		formMappingList.add(formMapping);
	}
	
	/**
	 * 获取当前环节，当前表单，操作类型下的表单字段映射清单
	 * @param formId 表单标识
	 * @param actionId 环节标识
	 * @param type 操作类型：初始化/更新
	 * @return
	 */
	public static List<FormMapping> getMapping(String formId,String actionId,String type)
	{
		
		List<FormMapping> resultMappingList = new ArrayList<FormMapping>();
		
		for(FormMapping formMapping : formMappingList)
		{
			if(actionId.equals(formMapping.getActionId()) && type.equals(formMapping.getMappingType()))
			{
				if(FormMapping.MAPPING_TYPE_INIT.equals(type) && formMapping.getDestForm().getFormId().equals(formId))
				{
					resultMappingList.add(formMapping);
				}
				else if(FormMapping.MAPPING_TYPE_UPDATE.equals(type) && formMapping.getSrcForm().getFormId().equals(formId))
				{
					resultMappingList.add(formMapping);
				}
			}
		}
		return resultMappingList;
	}
	
	/**
	 * 注册冗余数据映射关系
	 * 实际应用中，此部分的数据可保存在数据库或者配置文件中，并提供相应的UI操作界面，供管理员配置
	 */
	public static void registerFormMapping()
	{
		FormInfo formA = new FormInfo("A","FormObjectA","sample.redundant.form.FormObjectA");
		FormInfo formB = new FormInfo("B","FormObjectB","sample.redundant.form.FormObjectB");
		FormInfo formC = new FormInfo("C","FormObjectC","sample.redundant.form.FormObjectC");
		
		//在环节3，创建formC的时候，其phone字段根据formA的phone字段初始化
		FormMapping initFormCFromFormAMapping = new FormMapping("3",formA,formC,ActionConst.ACTION_THREE,FormMapping.MAPPING_TYPE_INIT);
		initFormCFromFormAMapping.addQueryField("workflowInstanceId", "workflowInstanceId");
		initFormCFromFormAMapping.addMappingField("phone", "phone");
		FormMappingMgr.addFormMapping(initFormCFromFormAMapping);
		
		//在环节3，创建formC的时候，其address字段根据formB的address字段初始化
		FormMapping initFormCFromFormBMapping = new FormMapping("4",formB,formC,ActionConst.ACTION_THREE,FormMapping.MAPPING_TYPE_INIT);
		initFormCFromFormBMapping.addQueryField("workflowInstanceId", "workflowInstanceId");
		initFormCFromFormBMapping.addMappingField("address", "address");
		FormMappingMgr.addFormMapping(initFormCFromFormBMapping);
		
		//在环节3，当保存formC的时候，同步更新formA的address字段
		FormMapping updateFormCToFormAMapping = new FormMapping("5",formC,formA,ActionConst.ACTION_THREE,FormMapping.MAPPING_TYPE_UPDATE);
		updateFormCToFormAMapping.addQueryField("workflowInstanceId", "workflowInstanceId");
		updateFormCToFormAMapping.addMappingField("address", "address");
		FormMappingMgr.addFormMapping(updateFormCToFormAMapping);
		
		//在环节3，当保存formC的时候，同步更新formB的phone字段
		FormMapping updateFormCToFormBMapping = new FormMapping("6",formC,formB,ActionConst.ACTION_THREE,FormMapping.MAPPING_TYPE_UPDATE);
		updateFormCToFormBMapping.addQueryField("workflowInstanceId", "workflowInstanceId");
		updateFormCToFormBMapping.addMappingField("phone", "phone");
		FormMappingMgr.addFormMapping(updateFormCToFormBMapping);
	}
}
