package com.logistics.persist.accesser;

import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.logistics.persist.accesser.enums.ConnectionPool;
import com.logistics.persist.accesser.enums.DataSource;
import com.logistics.persist.accesser.iml.HibernateSessionFactoryImpl;
import com.logistics.persist.accesser.iml.SpringJdbcTemplateFactoryImpl;

/**
 * 数据访问器,可克隆且拥有序列化规范化接口
 */
public interface Accesser { 
	
	/**
	 * 获取不同库的sessionFactory
	 * @return
	 */
	public HibernateSessionFactory getHibernateSessionFactorys();
	
	/**
	 * 获取不同库的jdbc模板
	 * @return
	 */
	public SpringJdbcTemplateFactory getSpringJdbcTemplateFactory();
	
	/**
	 * 获取spring jdbc模板。默认使用的c3p0连接池
	 * @param dataSource 数据源枚举
	 * @return
	 */
	public JdbcTemplate getJdbcTemplate(DataSource dataSource);
	
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
