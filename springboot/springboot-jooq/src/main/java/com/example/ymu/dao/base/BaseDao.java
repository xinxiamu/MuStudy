package com.example.ymu.dao.base;

@SuppressWarnings("rawtypes")
public interface BaseDao<T extends BaseRepository> {

	 T getMRepository();
}
