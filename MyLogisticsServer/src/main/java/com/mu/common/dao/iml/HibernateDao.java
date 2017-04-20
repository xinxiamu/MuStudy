package com.mu.common.dao.iml;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.mu.common.dao.IHibernateDao;
import com.mu.common.exception.BaseException;
import com.mu.common.utils.Log;

@Repository
public class HibernateDao implements IHibernateDao {

	@Resource
	protected Accesser accesser;

	@Override
	public <T> Long save(SessionFactory sessionFactory, Class<T> class1) {
		Long identifier = 0L;

		Session session = sessionFactory.openSession();
		try {
			Serializable id = session.save(class1);
			identifier = Long.valueOf(id.toString());
		} catch (Exception e) {
			BaseException.throwsException(getClass(), e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return identifier;
	}

	@Override
	public <T> Long saveBatch(SessionFactory sessionFactory, List<T> objects) throws BaseException {
		Long k = 0L;
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		for (int i = 0; i < objects.size(); i++) {
			session.save(objects.get(i));
			k++;
			// 将session一级缓存的数据定期刷入数据库,并清空
			if (i % 20 == 0) {
				session.flush();
				session.clear();
			}
		}
		tx.commit();
		if (session != null) {
			session.close();
		}
		return k;
	}
}
