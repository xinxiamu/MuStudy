package co.jufeng.hibernate.criterion.impl;

import java.io.Serializable;

import co.jufeng.accessor.Immutable;
import co.jufeng.accessor.criterion.IRestrictions;


@Immutable
public class IsNotNull extends Restrictions implements IRestrictions, Cloneable, Serializable {

	private static final long serialVersionUID = -2003332720701737264L;
	
	private Object name;
	
	public static IsNotNull add(String...name){
		return new IsNotNull(name);
	}

	/**
	 * cannot be instantiated
	 * @param name
	 * @param value
	 */
	IsNotNull(String...name) {
		if (name == null) {
		      throw new IllegalArgumentException("name may not be null");
	    }
	    this.name = name;
	}

	@Override
	public Object getValue() {
		return this.name;
	}

}
