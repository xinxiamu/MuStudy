package com.mu.common.dao;

import java.io.Serializable;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.mu.common.enums.ConnectionPool;
import com.mu.common.enums.DataSource;

/**
 * 数据访问器,可克隆且拥有序列化规范化接口
 */
public interface IAccesser extends Cloneable, Serializable { 
	
	/**
	 * 获取spring jdbc模板
	 * @param databaseName	数据库
	 * @param poolPattern	连接池，默认c3p0
	 * @return
	 */
	public JdbcTemplate getJdbcTemplate(DataSource databaseName, ConnectionPool poolPattern);
	
	/**
	 * 获取系统信息
	 * @return
	 */
	Map<Object, Object> getSystemInfo();
	
}
