package co.jufeng.accessor.criterion;

import java.io.Serializable;

import javax.naming.Referenceable;

public abstract interface ICriterion extends Referenceable, Serializable {
	
	public Object getValue();
	
	public String getName();
	
	public boolean getCacheable();
}
