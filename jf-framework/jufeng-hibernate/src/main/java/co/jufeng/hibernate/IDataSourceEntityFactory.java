package co.jufeng.hibernate;

import co.jufeng.accessor.IEntityFactory;

public interface IDataSourceEntityFactory {
	
	void setBean(Object object);
	
	void setEntity(IEntityFactory entityFactory);

}
