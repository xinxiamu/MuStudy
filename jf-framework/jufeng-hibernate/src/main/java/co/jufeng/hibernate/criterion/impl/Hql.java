package co.jufeng.hibernate.criterion.impl;

import java.io.Serializable;

import co.jufeng.accessor.Immutable;
import co.jufeng.accessor.criterion.IQuery;


@Immutable
public class Hql extends Query implements IQuery, Cloneable, Serializable {

	private static final long serialVersionUID = 6604869382434279808L;

	String hql;
	
	Object value;
	
	boolean cacheable;
	
	public static Hql add(String hql, Object...entitys){
		return new Hql(false, hql, entitys);
	}
	
	public static Hql add(boolean cacheable, String sql, Object...entitys){
		return new Hql(cacheable, sql, entitys);
	}

	Hql(boolean cacheable, String hql, Object...entitys) {
		if (hql == null) {
		      throw new IllegalArgumentException("Sql may not be null");
	    }
	    this.hql = hql;
	    this.value = entitys;
	    this.setCacheable(cacheable);
	    
	}

	@Override
	public Object getValue() {
		return this.value;
	}

	@Override
	public String getQuery() {
		return this.hql;
	}
	
	@Override
	public boolean getCacheable() {
		return cacheable;
	}
	
	@Override
	public void setCacheable(boolean cacheable) {
		this.cacheable = cacheable;
	}
	
}
