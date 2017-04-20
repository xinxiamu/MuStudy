package com.mu.common.dao.iml;

import javax.annotation.Resource;

import com.mu.common.dao.ICommonDao;
import com.mu.common.dao.IHibernateDao;
import com.mu.common.dao.ISpringJdbcDao;

public class CommonDao implements ICommonDao {

	@Resource
	public Accesser accesser;
	
	@Resource
	public ISpringJdbcDao springJdbcDao;
	
	@Resource
	public IHibernateDao hibernateDao;

}
