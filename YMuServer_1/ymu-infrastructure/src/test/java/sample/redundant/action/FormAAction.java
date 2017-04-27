package sample.redundant.action;

import java.util.Map;

import sample.redundant.dao.MockDAOService;
import sample.redundant.form.FormObjectA;

/**
 * @Create on Jul 6, 2013
 * @Since 
 * @author kevin.xiao
 *
 */
public class FormAAction extends AbstractFormAction{
	
	@Override
	protected Object createObject(Map paramMap) {
		// TODO Auto-generated method stub
		FormObjectA formObject = new FormObjectA();
		formObject.setId("A");
		String workflowInstanceId = (String)paramMap.get(AbstractFormAction.KEY_WORKFLOW_INSTANCE_ID);
		formObject.setWorkflowInstanceId(workflowInstanceId);
		return formObject;
	}

	@Override
	protected Object saveObject(Object formObject) throws Exception{
		// TODO Auto-generated method stub
		MockDAOService.add(formObject);
		return formObject;
	}
}
