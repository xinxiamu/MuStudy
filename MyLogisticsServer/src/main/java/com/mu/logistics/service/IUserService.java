package com.mu.logistics.service;

import com.mu.common.exception.BaseException;


public interface IUserService {

	/**
	 * 会员数据迁移。a88迁移过来
	 * @return
	 * @author mutian
	 * @throws BaseException 
	 */
	public int[] a88UserMoveToIwl() throws BaseException ;
	
	/**
	 * 同步每一天的数据
	 * @throws BaseException
	 * @author mutian
	 */
	public void a88UserMoveToIwlEveryday() throws BaseException;
}
