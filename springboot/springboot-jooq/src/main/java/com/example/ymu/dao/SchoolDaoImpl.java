package com.example.ymu.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.ymu.dao.base.BaseDaoImpl;

@Repository
@Transactional(readOnly = true)
public class SchoolDaoImpl extends BaseDaoImpl<SchoolRepository> implements SchoolDao {

	@Override
	public void showSchoolName() {
		System.out.println("马槛小学");
		System.out.println("em:" + em);
	}

}
