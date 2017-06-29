package com.example.ymu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ymu.dao.SchoolDao;
import com.example.ymu.service.TestService;

@Service
public class TestServiceImpl implements TestService {
	
	@Autowired
	SchoolDao schoolDao;

	@Override
	public String getSchoolNameById() {
		return schoolDao.getSchoolNameById();
	}

	@Override
	public String getSchoolNameUseJdbc() {
		return schoolDao.getSchoolNameUseJdbc();
	}
}
