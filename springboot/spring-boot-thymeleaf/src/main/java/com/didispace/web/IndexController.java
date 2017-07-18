package com.didispace.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
	
	@RequestMapping("/gridReport")
	public String gridReport() {
		return "grid-report/index";
	}

	@RequestMapping("/report_view")
	public String report_view() {
		return "grid-report/report_view";
	}
}
