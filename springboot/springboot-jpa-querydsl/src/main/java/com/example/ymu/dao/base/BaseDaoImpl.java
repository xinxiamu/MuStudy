package com.example.ymu.dao.base;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

@SuppressWarnings("rawtypes")
public abstract class BaseDaoImpl<T extends BaseRepository> implements BaseDao<T> {

	@Autowired
	@PersistenceContext
	protected EntityManager em;

	@Autowired
	protected T mRepository;

	@Autowired
	@Qualifier("jdbcTemplate")
	protected JdbcTemplate jdbcTemplate;

	@Override
	public T getMRepository() {
		return mRepository;
	}

	@Override
	public EntityManager getEntityManager() {
		return em;
	}

	@Override
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

}
