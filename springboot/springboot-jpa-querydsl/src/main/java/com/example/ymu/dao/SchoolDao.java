package com.example.ymu.dao;

import com.example.ymu.dao.base.BaseDao;
import com.example.ymu.dao.repository.SchoolRepository;

public interface SchoolDao extends BaseDao<SchoolRepository> {

	String getSchoolNameById();
}
