package co.jufeng.controller.test.user;

import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;

import co.jufeng.BaseAction;
import co.jufeng.util.XmlUtil;
import co.jufeng.web.ApiRspResultVO;
import co.jufeng.web.servlet.bind.annotation.DefaultAction;
import co.jufeng.web.servlet.bind.annotation.RestController;

@RestController
public class LoginAction extends BaseAction{

	@DefaultAction
	public ApiRspResultVO index(){
		ApiRspResultVO jsonObject = new ApiRspResultVO();
		try {
			String xml = this.getParameter().toString().replace("{", "").replace("}", "");
			System.out.println(xml);
			Document doc = DocumentHelper.parseText(xml);
			@SuppressWarnings("unchecked")
			Map<String, Object> map  = (Map<String, Object>) XmlUtil.xml2map(doc.getRootElement());
			String userName = map.get("userName").toString();
			String password = map.get("password").toString();
			if(userName.equals("jufeng") && password.equals("a123456")){
				jsonObject.setDescription("登录成功");
				Map<String, Object> user = new HashMap<String, Object>();
				user.put("id", 1);
				user.put("userName", "jufeng");
				user.put("realName", "林小姐");
				user.put("mobile", "13822228888");
				jsonObject.setData(user);
			}else{
				jsonObject.setDescription("用户名或密码错误");
			}
		} catch (DocumentException e) {
			jsonObject.setDescription("参数错误" + e.getMessage());
		}
		return jsonObject;
	}
	
	
	
	
	
	
	
	
	
	

}