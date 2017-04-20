package com.didispace;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ApiController {
	
	/**
	 * 转发请求api接口文档界面
	 * @return
	 */
	@RequestMapping(value="/")
	public String api() {
		return "redirect:/swagger-ui.html";
	}

}
