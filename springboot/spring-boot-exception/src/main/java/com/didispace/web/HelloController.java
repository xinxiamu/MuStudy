package com.didispace.web;

import com.didispace.exception.MyException;
import com.didispace.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author 程序猿DD
 * @version 1.0.0
 * @blog http://blog.didispace.com
 *
 */
@Controller
public class HelloController {

	@Autowired
	private UserService userService;

	@RequestMapping("/hello")
	public String hello() throws Exception {
		throw new Exception("发生错误");
	}

	@RequestMapping("/json")
	@ResponseBody
	public String json() throws Exception {
		return userService.getUserName(1);
		// throw new MyException("发生错误2");
	}

	/**
	 * 测试缺失参数异常
	 * @param name
	 * @param age
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/par")
	@ResponseBody
	public String testRequestParamter(@RequestParam String name, Integer age) throws Exception {  
		return name;
	}

	@RequestMapping("/")
	public String index(ModelMap map) {
		map.addAttribute("host", "http://blog.didispace.com");
		return "index";
	}

}