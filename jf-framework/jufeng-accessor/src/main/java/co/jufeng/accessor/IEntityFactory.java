package co.jufeng.accessor;

import java.io.Serializable;

import co.jufeng.accessor.criterion.ICriterion;


public abstract interface IEntityFactory extends ICriterion, Serializable{
	
	public boolean isEmpty();
	
	public void setCacheable(boolean cacheable);
	
	public int size();
	
	public IEntityFactory getEntityFactory();
	
	public void setValue(Object... value);
	
	public void setName(String name);
	
	
	
	
	
	
	
	
}
