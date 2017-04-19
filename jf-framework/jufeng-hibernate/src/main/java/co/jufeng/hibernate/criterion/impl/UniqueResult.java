package co.jufeng.hibernate.criterion.impl;

import java.io.Serializable;

import co.jufeng.accessor.Immutable;
import co.jufeng.accessor.criterion.IRestrictions;

@Immutable
public class UniqueResult extends Restrictions implements IRestrictions, Cloneable, Serializable {

	private static final long serialVersionUID = 6585664383683308921L;
	
	private Object value;
	
	public static UniqueResult add(boolean value){
		return new UniqueResult(value);
	}

	/**
	 * cannot be instantiated
	 * @param name
	 * @param value
	 */
	UniqueResult(boolean bool) {
	    this.value = bool;
	}

	public Object getValue() {
		return value;
	}
	
	


}
