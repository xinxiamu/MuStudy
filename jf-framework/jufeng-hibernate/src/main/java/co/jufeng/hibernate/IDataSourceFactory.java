package co.jufeng.hibernate;

import java.io.Serializable;
import javax.persistence.Table;
public interface IDataSourceFactory {
	
	<T> T getDateSource();
	
	<T> T setDataSource(Object object, Class<T> t);

	Serializable save(Object object);
	

}
