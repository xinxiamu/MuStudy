package com.ymu.dao.mybatis;

import org.springframework.beans.factory.annotation.Autowired;

import com.ymu.dao.datasource.DatabaseContextHolder;
import com.ymu.dao.datasource.DatabaseType;

public abstract class BaseDao<M> {

    @Autowired
    private M mapper;

    /*-------------------------- mybatis  --------------------------*/

    public M getMapper(DatabaseType databaseType) {
        DatabaseContextHolder.setDatabaseType(databaseType);
        return mapper;
    }

}
