package co.jufeng.hibernate.criterion.impl;

import java.io.Serializable;

import co.jufeng.accessor.Immutable;
import co.jufeng.accessor.criterion.IRestrictions;


@Immutable
public class Eq extends Restrictions implements IRestrictions, Cloneable, Serializable {

	private static final long serialVersionUID = -1674580836018470900L;

	private String name;
	
	private Object value;
	
	public static Eq add(String name, Object...value){
		return new Eq(name, value);
	}

	Eq(String name, Object...value) {
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
