package com.logistics.persist.dao;

import java.util.List;

import com.logistics.model.lg.user.User;

public interface UserDao {

	/**
	 * 添加用户
	 * @param user
	 */
	public void add(User user);
	
	/**
	 * 批量添加用户
	 * @param users
	 */
	public void addBatch(List<User> users);
	
	/**
	 * 获取a88用户最大id
	 */
	public Long getUserMaxIdA88();
	
	/**
	 * 获取用户最大id
	 * @return
	 */
	public Long getUserMaxId();
	
}
