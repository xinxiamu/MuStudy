package sample.redundant.mgr;


/**
 * @Create on Jul 6, 2013
 * @Since 
 * @author kevin.xiao
 *
 */
public class FormInfo {

	/** 表单Id */
	private String formId;
	
	/**表单名称*/
	private String formName;
	/**表单java类，格式:sample.redundant.form.FormObjectA*/
	private String formClass;
	
	public FormInfo(String formId,String formName,String formClass)
	{
		this.formId = formId;
		this.formName = formName;
		this.formClass = formClass;
	}
	
	/**
	 * 创建form对应的对象实例
	 * @return
	 */
	public Object createFormObject()
	{
		try {
			Class clazz = Class.forName(formClass);
			return clazz.newInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
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
	/**
	 * @return the formName
	 */
	public String getFormName() {
		return formName;
	}
	/**
	 * @param formName the formName to set
	 */
	public void setFormName(String formName) {
		this.formName = formName;
	}
	/**
	 * @return the formClass
	 */
	public String getFormClass() {
		return formClass;
	}
	/**
	 * @param formClass the formClass to set
	 */
	public void setFormClass(String formClass) {
		this.formClass = formClass;
	}
	
	
}
