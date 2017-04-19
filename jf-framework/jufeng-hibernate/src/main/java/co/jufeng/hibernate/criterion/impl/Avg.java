package co.jufeng.hibernate.criterion.impl;

import java.io.Serializable;

import co.jufeng.accessor.Immutable;
import co.jufeng.accessor.criterion.IProjection;


@Immutable
public class Avg extends Projection implements IProjection, Cloneable, Serializable {

	private static final long serialVersionUID = 7024264947682578807L;
	
	private String name;
	
	public static Avg add(String name){
		return new Avg(name);
	}

	Avg(String name) {
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
