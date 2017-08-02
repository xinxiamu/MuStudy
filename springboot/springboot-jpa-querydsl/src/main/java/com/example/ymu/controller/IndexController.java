package com.example.ymu.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class IndexController {

	@RequestMapping("/")
	public String getSchoolNameById(@RequestParam(required = true, defaultValue = "1") Long id) {
		return "index";
	}
	
	
	
}
