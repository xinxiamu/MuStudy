package com.didispace;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.didispace.service.ComputeService;

@Controller
public class DemoController {
	
	@Autowired
	private ComputeService computeService;
	
	@RequestMapping(value="/")
	@ResponseBody
	public String index() {	
		return String.valueOf(computeService.add(2, 8));  
	}

}
