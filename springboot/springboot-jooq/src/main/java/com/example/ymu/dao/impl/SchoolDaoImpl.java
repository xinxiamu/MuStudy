package com.example.ymu.dao.impl;

import org.springframework.stereotype.Repository;

import com.example.ymu.dao.SchoolDao;
import com.example.ymu.dao.base.BaseDaoImpl;
import com.example.ymu.dao.repository.SchoolRepository;

@Repository
public class SchoolDaoImpl extends BaseDaoImpl<SchoolRepository> implements SchoolDao {
	
	@Override
	public void showSchoolName() {
		System.out.println("马槛小学");
		System.out.println("em:" + em);
	}

}
