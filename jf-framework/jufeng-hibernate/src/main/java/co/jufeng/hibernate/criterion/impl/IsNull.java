package co.jufeng.hibernate.criterion.impl;

import java.io.Serializable;

import co.jufeng.accessor.Immutable;
import co.jufeng.accessor.criterion.IRestrictions;


@Immutable
public class IsNull extends Restrictions implements IRestrictions, Cloneable, Serializable {

	private static final long serialVersionUID = -3214735620513544468L;

	private Object name;
	
	public static IsNull add(String...name){
		return new IsNull(name);
	}

	/**
	 * cannot be instantiated
	 * @param name
	 * @param value
	 */
	IsNull(String...name) {
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
