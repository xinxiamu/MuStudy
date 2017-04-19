package co.jufeng.hibernate;

import java.io.Serializable;

import org.hibernate.Criteria;

import co.jufeng.accessor.IAccessor;
import co.jufeng.accessor.criterion.ICriterion;

public interface IHibernateAccessor extends IAccessor, Cloneable, Serializable {
	
	public Criteria getCriteria(Class<?> object);
	public Criteria getCriteria(String object);
	public Criteria[] getCriteria(ICriterion... criterions);
	public Criteria createAGroupCriteria(Criteria criteria,  ICriterion... criterions);
	public org.hibernate.Query createAGroupCriteria(org.hibernate.Query query, ICriterion... criterions);
	public org.hibernate.SessionFactory getSessionFactory();
	public org.hibernate.Session openSession();
	public void closeSession();
	public void evict(Object entity);
	public void clear();
	public void contains(Object entity);
	public void flush();
}
