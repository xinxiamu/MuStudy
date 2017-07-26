package com.example.ymu.dao;

import java.util.List;

import com.example.ymu.dao.base.BaseDao;
import com.example.ymu.dao.repository.SchoolRepository;
import com.example.ymu.domain.School;

public interface SchoolDao extends BaseDao<SchoolRepository> {

	String getSchoolNameById(Long id);
	
	String getSchoolNameUseJdbc(Long id);
	
	/**
	 * 大批量插入。百万级别。10秒时间。
	 * 
	 * @param schools
	 */
	void batchInsert(List<School> schools);
}
