package co.jufeng.hibernate.criterion.impl;

import java.io.Serializable;

import co.jufeng.accessor.Immutable;
import co.jufeng.accessor.criterion.IRestrictions;


@Immutable
public class Ne extends Restrictions implements IRestrictions, Cloneable, Serializable {

	private static final long serialVersionUID = 5682767492564083173L;

	private String name;
	
	private Object value;
	
	public static Ne add(String name, Object...value){
		return new Ne(name, value);
	}
	
	Ne(String name, Object...value) {
		if (name == null) {
		      throw new IllegalArgumentException("Name may not be null");
	    }
	    this.name = name;
	    this.value = value;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public Object getValue() {
		return this.value;
	}

}
