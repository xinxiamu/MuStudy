package co.jufeng.hibernate.criterion.impl;

import java.io.Serializable;

import co.jufeng.accessor.Immutable;
import co.jufeng.accessor.criterion.IProjection;


@Immutable
public class Max extends Projection implements IProjection, Cloneable, Serializable {

	private static final long serialVersionUID = 6918630929825963900L;
	
	private String name;
	
	public static Max add(String name){
		return new Max(name);
	}

	Max(String name) {
		if (name == null) {
		      throw new IllegalArgumentException("Name may not be null");
	    }
	    this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}
}
