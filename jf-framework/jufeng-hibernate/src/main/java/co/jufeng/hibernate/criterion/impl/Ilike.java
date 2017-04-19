package co.jufeng.hibernate.criterion.impl;

import java.io.Serializable;

import co.jufeng.accessor.Immutable;
import co.jufeng.accessor.MatchMode;
import co.jufeng.accessor.criterion.Iilike;


@Immutable
public class Ilike extends Restrictions implements Iilike, Cloneable, Serializable {

	private static final long serialVersionUID = -1674580836018470900L;

	private String name;
	
	private Object value;
	
	private MatchMode matchMode;
	
	public static Ilike add(MatchMode matchMode, String name, String...value) {
		return new Ilike(matchMode, name, value);
	}
	
	/**
	 * cannot be instantiated
	 * @param matchMode
	 * @param name
	 * @param value
	 */
	Ilike(MatchMode matchMode, String name, String...value) {
		if (name == null) {
		      throw new IllegalArgumentException("Name may not be null");
	    }
		this.matchMode = matchMode;
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

	@Override
	public MatchMode getMatchMode() {
		return this.matchMode;
	}
	
}
