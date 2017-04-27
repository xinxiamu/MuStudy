package sample.redundant.app;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import sample.redundant.action.AbstractFormAction;
import sample.redundant.action.ActionConst;
import sample.redundant.action.FormAAction;
import sample.redundant.action.FormBAction;
import sample.redundant.action.FormCAction;
import sample.redundant.form.FormObjectA;
import sample.redundant.form.FormObjectB;
import sample.redundant.form.FormObjectC;
import sample.redundant.mgr.FormMappingMgr;

/**
 * @Create on Jul 8, 2013
 * @Since 
 * @author kevin.xiao
 *
 */
public class SampleApp {
	
	public FormAAction getFormAAction()
	{
		return new FormAAction();
	}
	
	public FormBAction getFormBAction()
	{
		return new FormBAction();
	}
	
	public FormCAction getFormCAction()
	{
		return new FormCAction();
	}
	
	public static void main(String[] agrs) throws Exception
	{
		//第1步：注册表单字段映射信息
		FormMappingMgr.registerFormMapping();
		
		String workflowInstanceId = "001";
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put(AbstractFormAction.KEY_WORKFLOW_INSTANCE_ID, workflowInstanceId);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		SampleApp sampleApp = new  SampleApp();
		
		FormAAction formAAction = sampleApp.getFormAAction();
		formAAction.setActionId(ActionConst.ACTION_ONE);
		formAAction.setFormId("A");
		
		//第2步：准备数据
		//准备数据：环节001 --> 创建并保存formA
		FormObjectA formObjectA = (FormObjectA)formAAction.create(paramMap);
		formObjectA.setName("张三");
		formObjectA.setAddress("上海市南京东路  (formA)");
		formObjectA.setPhone("021-58888888  (formA)");
		formObjectA.setBirthday(sdf.parse("1990-01-21"));
		formAAction.save(formObjectA);
		
		
		//准备数据：环节002 --> 创建并保存formB
		FormBAction formBAction = sampleApp.getFormBAction();
		formBAction.setActionId(ActionConst.ACTION_TWO);
		formBAction.setFormId("B");
		FormObjectB formObjectB = (FormObjectB)formBAction.create(paramMap);
		formObjectB.setAddress("上海市淮海路  (formB)");
		formObjectB.setPhone("021-566666666  (formB)");
		formObjectB.setBirthday(sdf.parse("1990-06-21"));
		formBAction.save(formObjectB);
		
		//第3步：formC的创建与自动初始化
		//环节003 --> 创建formC的数据，查看formC的相应字段是否正确初始化
		FormCAction formCAction = sampleApp.getFormCAction();
		formCAction.setActionId(ActionConst.ACTION_THREE);
		formCAction.setFormId("C");
		
		FormObjectC formObjectC = (FormObjectC)formCAction.create(paramMap);
		System.out.println("-----CASE1：环节003 --> formC创建并初始化之后的数据如下：-----");
		System.out.println("formObjectC's address = " + formObjectC.getAddress());
		System.out.println("formObjectC's phone   = " + formObjectC.getPhone());
		System.out.println("formObjectC's birthday   = " + (formObjectC.getBirthday()!=null?sdf.format(formObjectC.getBirthday()):null));
		
		//第4步：formC的保存，及对应表单字段的同步更新
		//环节003 --> 改变formC的数据并保存，查看其它form的字段是否同步更新
		System.out.println("-----CASE2: 环节003 --> 改变formC的数据并保存，查看其它form的字段是否同步更新-----");
		formObjectC.setAddress("上海市人民路  (formC)");
		formObjectC.setPhone("021-59999999  (formC)");
		formObjectC.setBirthday(sdf.parse("1990-12-21"));
		formCAction.save(formObjectC);
		
		System.out.println("-----保存formObjectC后的formA的数据如下：-----");
		System.out.println("formObjectA's address = " + formObjectA.getAddress());
		System.out.println("formObjectA's phone   = " + formObjectA.getPhone());
		System.out.println("formObjectA's birthday   = " + (formObjectA.getBirthday()!=null?sdf.format(formObjectA.getBirthday()):null));
		System.out.println("-----保存formObjectC后的formB的数据如下：-----");
		System.out.println("formObjectB's address = " + formObjectB.getAddress());
		System.out.println("formObjectB's phone   = " + formObjectB.getPhone());
		System.out.println("formObjectB's birthday   = " + (formObjectB.getBirthday()!=null?sdf.format(formObjectB.getBirthday()):null));
	}
}
