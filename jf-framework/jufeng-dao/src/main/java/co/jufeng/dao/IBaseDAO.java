package co.jufeng.dao;

import co.jufeng.accessor.IAccessor;

public interface IBaseDAO {
	
	public IAccessor getAccessorRead();

	public IAccessor getAccessorWrite();
	
}
