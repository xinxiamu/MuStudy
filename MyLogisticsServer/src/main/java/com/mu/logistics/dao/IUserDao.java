package com.mu.logistics.dao;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.mu.common.exception.BaseException;
import com.mu.logistics.model.iwl.User;
import com.mu.logistics.model.iwl.UserType;

public interface IUserDao {
	
	/**
	 * 获取会员列表
	 * @param sql
	 * @return
	 */
	public List<Map<String, Object>> getUsers(JdbcTemplate jdbcTemplate,String sql);
	
	/**
	 * 获取a88会员表id大于minId的会员
	 * @param minId 最小id
	 * @return
	 */
	public List<Map<String, Object>> getA88UsersBigId(Long minId);

	/**
	 * 获取全部会员信息
	 * @param par
	 * @return 
	 */
	public List<Map<String, Object>> getA88AllUser();
	
	/**
	 * 更具会员类型获取会员信息
	 * @param userType
	 * @return
	 */
	public Map<String, Object> getUserByType(int userType) ;
	
	/**
	 * 保存会员信息
	 * @param par 参数
	 * @return	
	 */
	public Long add(final User user);
	
	/**
	 * 批量插入会员信息
	 * @param par 参数
	 * @return	
	 */
	public Long saveBatch(List<User> users);

	/**
	 * 批量插入会员
	 * @param users
	 * @return
	 * @throws BaseException
	 */
	int[] batchAdd(List<User> users) throws BaseException;
}
