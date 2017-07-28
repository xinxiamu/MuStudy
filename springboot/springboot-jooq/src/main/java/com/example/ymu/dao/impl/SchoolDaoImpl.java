package com.example.ymu.dao.impl;

import org.jooq.Record;
import org.jooq.Result;
import org.springframework.stereotype.Repository;

import com.example.ymu.dao.SchoolDao;
import com.example.ymu.dao.base.BaseDaoImpl;
import com.example.ymu.dao.repository.SchoolRepository;
import static com.example.demo.jooq.tables.School.*;

@Repository
public class SchoolDaoImpl extends BaseDaoImpl<SchoolRepository> implements SchoolDao {

	@Override
	public void findSchoolName() {
		
		Result<Record> a = jooq.select().from(SCHOOL).fetch();
		System.out.println("---sql:" + a.size()); 
	}

}
