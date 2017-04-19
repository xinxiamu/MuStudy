package co.jufeng.hibernate.criterion.impl;

import java.io.Serializable;

import co.jufeng.accessor.Immutable;
import co.jufeng.accessor.criterion.IProjection;


@Immutable
public class Sum extends Projection implements IProjection, Cloneable, Serializable {

	private static final long serialVersionUID = 7698109701692277657L;
	
	private String name;
	
	public static Sum add(String name){
		return new Sum(name);
	}

	Sum(String name) {
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
