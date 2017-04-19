package co.jufeng.controller.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.jufeng.BaseAction;
import co.jufeng.UserVO;
import co.jufeng.json.JSONObject;
import co.jufeng.logger.LoggerUtil;
import co.jufeng.web.servlet.ModelAndView;
import co.jufeng.web.servlet.bind.annotation.DefaultAction;
import co.jufeng.web.servlet.bind.annotation.RestController;
import co.jufeng.web.util.RequestUtil;

@RestController
public class IndexAction extends BaseAction{

	/**
	 * http://127.0.0.1:8080/jufeng-web/index.do
	 * Default execution index method
	 * @return String 
	 */
	@DefaultAction
	public String index(){
		LoggerUtil.info(getClass(), this.getParameter());
		return "test/index.html";
	}

	/**
	 * http://127.0.0.1:8080/jufeng-web/index!main.do
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView(request);
		modelAndView.addObject("key", "main");
		modelAndView.addViewName("test/main.html");
		return modelAndView;
	}
	
	/**
	 * http://127.0.0.1:8080/jufeng-web/index!sendRedirect.do
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	public String sendRedirect(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> parameter = RequestUtil.toObject(request);
		LoggerUtil.info(getClass(), parameter);
		ModelAndView modelAndView = new ModelAndView(request);
		modelAndView.addObject("action", "index");
		return "test/index!userList.do?userName=%E6%88%91ddssdfdsf&password=a123456";
	}

	/**
	 * http://127.0.0.1:8080/jufeng-web/index!sendRedirect2.do
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	public ModelAndView sendRedirect2(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView(request);
		modelAndView.addObject("key", "main");
		modelAndView.addViewName("http://www.baidu.com");
		return modelAndView;
	}

	/**
	 * http://127.0.0.1:8080/jufeng-web/index!ajax.do?jsonString={"name":"jufeng","password":"a123456","realName":"郑坚焱","age":18}
	 * @return
	 * @throws Exception
	 */
	public JSONObject ajax() throws Exception {
		LoggerUtil.info(getClass(), this.getParameter());
		JSONObject jsonObject = this.getParameter().getJSONObject("jsonString");
		System.out.println(jsonObject);
		jsonObject.put("name", "微信商城");
		jsonObject.put("list", Arrays.asList("www", "jufeng", "co"));
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < 10; i++) {
			map.put("key" + i, "value" + i);
		}
		jsonObject.put("map", map);
		System.out.println(jsonObject);
		JSONObject obj = jsonObject.getJSONObject("map");
		System.out.println(obj);
		return jsonObject;
	}
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("kkk", "ddd");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", "微信商城");
		jsonObject.put("list", Arrays.asList("www", "jufeng", "co"));
		jsonObject.put("map", map);
		System.out.println(jsonObject);
		Object obj = jsonObject.get("map");
		System.out.println(obj);
		System.out.println(jsonObject.get("mapss", "dddddddddddd"));
	}
	/**
	 * http://127.0.0.1:8080/jufeng-web/index!voids.do
	 * @return default voids.html
	 * @throws Exception
	 */
	public void voids() throws Exception {
		ModelAndView modelAndView = new ModelAndView(this.getRequest());
		modelAndView.addObject("key", "voisds");
	}

	
	/**
	 * http://127.0.0.1:8080/jufeng-web/index!userList.do?userName=%E6%88%91ddssdfdsf&password=a123456
	 * @param request
	 * @param response
	 * @return default user-list.html
	 * @throws Exception
	 */
	public void userList(HttpServletRequest request, HttpServletResponse response, String userName, UserVO userVO) throws Exception {
		System.out.println(userName);
		System.out.println(userVO);
		ModelAndView modelAndView = new ModelAndView(request);
		modelAndView.addObject("key", "userList");
		modelAndView.addObject("userName", userName);
		modelAndView.addObject("userVO", userVO);
	}
	
	/**
	 * http://127.0.0.1:8080/jufeng-web/index!user.do
	 * @return default user.html
	 */
	public void user() throws Exception {
		LoggerUtil.info(getClass());
		LoggerUtil.error(getClass());
	}
	
	/**
	 * http://127.0.0.1:8080/jufeng-web/index!modelAndView.do
	 */
	public String modelAndView(ModelAndView modelAndView) throws Exception {
		System.out.println(modelAndView); 
		return "index.html"; 
	}
	
	/**
	 * http://127.0.0.1:8080/jufeng-web/index!modelAndView2.do
	 */
	public ModelAndView modelAndView2(HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView(request);
		System.out.println(modelAndView);
		modelAndView.addViewName("index.html");
		return modelAndView;
	}

}