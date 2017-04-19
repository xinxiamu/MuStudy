package co.jufeng.hibernate.criterion.impl;

import java.io.Serializable;

import co.jufeng.accessor.Immutable;
import co.jufeng.accessor.criterion.IQuery;


@Immutable
public class Sql extends Query implements IQuery, Cloneable, Serializable {

	private static final long serialVersionUID = -5053331411221537454L;

	String sql;
	
	Object value;
	
	boolean cacheable;
	
	public static Sql add(String sql, Object...entitys){
		return new Sql(false, sql, entitys);
	}
	
	public static Sql add(boolean cacheable, String sql, Object...entitys){
		return new Sql(cacheable, sql, entitys);
	}

	Sql(boolean cacheable, String sql, Object...entitys) {
		if (sql == null) {
		      throw new IllegalArgumentException("Sql may not be null");
	    }
	    this.sql = sql;
	    this.value = entitys;
	    this.setCacheable(cacheable);
	    
	}

	@Override
	public Object getValue() {
		return this.value;
	}

	@Override
	public String getQuery() {
		return this.sql;
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
