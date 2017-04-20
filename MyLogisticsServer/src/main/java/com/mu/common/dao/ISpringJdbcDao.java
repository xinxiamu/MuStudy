package com.mu.common.dao;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.KeyHolder;

import com.mu.common.exception.BaseException;

/**
 * 
 * @类描述：spring jdbc数据库操作通用方法。
 *
 * @创建人：mt
 */
public interface ISpringJdbcDao {
	
	/**
	 * 查询，返回结果集列表
	 * @param dataSource
	 * @param sql
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String, Object>> getList(JdbcTemplate dataSource,String sql) throws BaseException  ;	

	/**
	 * 插入数据
	 * @param sql
	 * @param callBack
	 * @return
	 * @author mutian
	 */
	public KeyHolder add(PreparedStatementCreator callBack) throws BaseException;
	
	/**
	 * 插入数据并返回id
	 * @param sql	完整的插入sql语句
	 * @return	该条实体id
	 * @throws BaseException
	 * @author mutian
	 */
	public Long add(String sql) throws BaseException;
	
	/**
	 * '等'精确查询获取表的某条记录
	 * @param tableName
	 * @param gts	查询条件集
	 * @return
	 * @throws BaseException
	 */
	public Map<String, Object> getObjectGt(String tableName,Map<String, Object> gts) throws BaseException;
	
	/**
	 * 批量插入数据
	 * @param beans
	 * @param sql
	 * @param callback
	 * @return
	 * @throws BaseException
	 */
	public <T> int[] batchUpdate(List<T> beans,String sql,BatchPreparedStatementSetter callback) throws BaseException;
	
	/**
	 * 获取某个表的最大id
	 * @param jdbcTemplate	
	 * @param tableName	表名
	 * @return	成功返回该表最大id，否则返回0
	 * @throws BaseException
	 */
	public Long getMaxId(JdbcTemplate jdbcTemplate,String tableName) throws BaseException;

}
