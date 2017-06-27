package com.example.ymu.dao.base;

import javax.persistence.EntityManager;

import org.jooq.DSLContext;

@SuppressWarnings("rawtypes")
public interface BaseDao<T extends BaseRepository> {

	 T getMRepository();
	 
	 EntityManager getEntityManager();
	 
	 DSLContext getJooq();
}
