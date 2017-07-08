package com.example.ymu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ymu.service.TestService;

@RestController
@RequestMapping("/school")
public class SchoolController {
	
//	@Value("${jdbc.password}")
//	private String jdbcPWD;
//	
//	@Value("${ab}")
//	private String ab;
//	
//	@Value("${mumu}")
//	private String mutou;
	
//	@Value("${config}")
//	private String aaa;
	

	@Autowired
	TestService schoolService;

	@RequestMapping("/getSchoolNameById")
	public String getSchoolNameById(@RequestParam(required = true, defaultValue = "1") Long id) {
//		System.out.println("-------jdbc pwd:" + jdbcPWD);
//		System.out.println("-------aaa:" + aaa);
//		System.out.println("-------mutou:" + mutou);
		return schoolService.getSchoolNameById(id);
	}
}
