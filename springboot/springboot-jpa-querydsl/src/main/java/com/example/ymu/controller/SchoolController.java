package com.example.ymu.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ymu.dao.SchoolDao;
import com.example.ymu.service.TestService;

@RestController
@RequestMapping("/school")
public class SchoolController {
	
	@Autowired
	TestService schoolService;
	
	@Autowired
	SchoolDao schoolDao;
	

	@RequestMapping("/getSchoolNameById")
	public String getSchoolNameById(@RequestParam(required = true, defaultValue = "1") Long id) {
		return schoolService.getSchoolNameById(id);
	}
	
	@RequestMapping("/getSchoolNameUseJdbc")
	public String getSchoolNameUseJdbc(@RequestParam(required = true, defaultValue = "1") Long id) {
		return schoolService.getSchoolNameUseJdbc(id);
	}
	
	@RequestMapping("/addBatchByJdbc")
	public void insert() {
		schoolDao.batchInsert(null);    
	}
	
	@RequestMapping("/addBatch2")
	public void addBatch2() {
		schoolDao.batchInser2();   
	}
	
	@RequestMapping("/selectData")
	public void selectData() throws SQLException {
		long startTime = System.currentTimeMillis();
		String sqlCmd = "SELECT * FROM school";
//		String sqlCmd = "SELECT * FROM school LIMIT 0,1000";
		schoolDao.selectData(sqlCmd); 
		System.out.println("====用时（s）：" + (System.currentTimeMillis() - startTime)/1000f);
	}
	
	
}
