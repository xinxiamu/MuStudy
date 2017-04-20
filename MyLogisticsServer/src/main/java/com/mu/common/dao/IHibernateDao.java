package com.mu.common.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.mu.common.exception.BaseException;

/**
 * 
 * @类描述：hibernate数据库增删改查通用方法。
 *
 * @创建人：mt
 */
public interface IHibernateDao {
	
	/**
	 * 保存对象
	 * @param sessionFactory
	 * @param class1
	 * @return	成功返回id，否则返回0
	 * @author mutian
	 */
	public <T> Long save(SessionFactory sessionFactory,Class<T> class1);
	
	/**
	 * 批量插入数据
	 * @param sessionFactory
	 * @param objects
	 * @return 	返回插入总条数
	 * @throws BaseException
	 */
	public <T> Long saveBatch(SessionFactory sessionFactory,List<T> objects) throws BaseException;
	
//	public <T> void getCri(SessionFactory sessionFactory,List<T> objects) throws BaseException;
}
