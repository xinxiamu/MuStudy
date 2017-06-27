package com.example.ymu.dao.impl;

import static com.example.demo.jooq.Tables.SCHOOL;

import org.jooq.Result;
import org.springframework.stereotype.Repository;

import com.example.demo.jooq.tables.records.SchoolRecord;
import com.example.ymu.dao.SchoolDao;
import com.example.ymu.dao.base.BaseDaoImpl;
import com.example.ymu.dao.repository.SchoolRepository;

@Repository
public class SchoolDaoImpl extends BaseDaoImpl<SchoolRepository> implements SchoolDao {

	@Override
	public void findSchoolName() {
		Result<SchoolRecord> result = jooq.selectFrom(SCHOOL).where(SCHOOL.ID.eq(1L)).fetch();
		System.out.println("---sql:" + result.getValue(0, SCHOOL.NAME)); 
	}

}
