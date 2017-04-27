package com.ymu.dao.jdbc.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ymu.dao.datasource.DatabaseContextHolder;
import com.ymu.dao.datasource.DatabaseType;

public abstract class BaseDao {

    /*---------------------   spring jdbc 模板获取     ----------------------*/

    @Autowired
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate(DatabaseType databaseType) {
    	if (null == databaseType) {
			DatabaseContextHolder.setDatabaseType(DatabaseType.ymuSlave);
		} else {
			 DatabaseContextHolder.setDatabaseType(databaseType);
		}
        return jdbcTemplate;
    }

}
