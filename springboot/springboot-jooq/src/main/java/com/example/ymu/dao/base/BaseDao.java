package com.example.ymu.dao.base;

import javax.persistence.EntityManager;

@SuppressWarnings("rawtypes")
public interface BaseDao<T extends BaseRepository> {

	 T getMRepository();
	 
	 EntityManager getEntityManager();
}
