package com.example.ymu.dao.impl;

import org.springframework.stereotype.Repository;

import com.example.ymu.dao.SchoolDao;
import com.example.ymu.dao.base.BaseDaoImpl;
import com.example.ymu.dao.repository.SchoolRepository;
import com.example.ymu.domain.QSchool;
import com.querydsl.jpa.impl.JPAQuery;

@Repository
public class SchoolDaoImpl extends BaseDaoImpl<SchoolRepository> implements SchoolDao {

	@Override
	public String getSchoolNameById(Long id) {
		QSchool school = QSchool.school;
		JPAQuery<?> query = new JPAQuery<Void>(em);
		String schoolName = query.select(school.name).from(school).where(school.id.eq(id)).fetchOne();
		return schoolName;
	}

	@Override
	public String getSchoolNameUseJdbc() {
		String schName = jdbcTemplate.queryForObject("SELECT `name` FROM school WHERE id=1",  String.class);
		return schName;
	}

}
