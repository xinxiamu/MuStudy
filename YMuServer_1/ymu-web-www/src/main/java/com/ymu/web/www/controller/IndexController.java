package com.ymu.web.www.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ymu.web.www.mqreceiver.MainQueueReceiver;

@Controller
public class IndexController extends BaseController {
	
	@RequestMapping(value = {"/","/index.do"}, method = { RequestMethod.GET,
			RequestMethod.POST })
	public String index(String bg,String abcd) {
		System.out.println("重定向参数bg,出现在地址栏：" + bg);
		System.out.println("重定向参数，不出现在地址栏：" + abcd);
		model.addAttribute("bg",bg);
		model.addAttribute("abcd",abcd);

		model.addAttribute("host", "你好");
		model.addAttribute("name", MainQueueReceiver.mainQMsg);
		return "index";
	}
	
	@RequestMapping(value = "/demo.do")
	public String demo() {
		model.addAttribute("name", "aaaa");
		model.addAttribute("host", "你好");	
		return "login/index";
	}

	@RequestMapping(value = "redirect.do")
	public String redirect(RedirectAttributes attr) {
		attr.addAttribute("bg","重定向传参数");
		attr.addFlashAttribute("abcd","参数不会出现在地址栏中");
		return "redirect:/index.do";
	}
	
	@RequestMapping(value = "ajax-kuayu")
	public String ajaxKuayuDemo() {
		return "demo/ajax";
	}

}
