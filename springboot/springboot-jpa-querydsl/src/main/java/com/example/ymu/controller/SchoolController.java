package com.example.ymu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ymu.service.TestService;

@RestController
@RequestMapping("/school")
public class SchoolController {

	@Autowired
	TestService schoolService;

	@RequestMapping("/getSchoolNameById")
	public String getSchoolNameById() {
		return schoolService.getSchoolNameById();
	}
}
