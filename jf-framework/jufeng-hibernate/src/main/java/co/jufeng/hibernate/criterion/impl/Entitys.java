package co.jufeng.hibernate.criterion.impl;

import co.jufeng.accessor.IEntityFactory;

public class Entitys {

	final IEntityFactory entityFactory;

	public Entitys(Object entityClass){
		this.entityFactory = (IEntityFactory) entityClass;
	}


}
