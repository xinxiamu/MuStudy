package co.jufeng.dao;

import java.io.Serializable;

import javax.annotation.Resource;

import co.jufeng.accessor.IAccessor;

public class BaseDAO implements IBaseDAO, Cloneable, Serializable {
	
	private static final long serialVersionUID = 1051476264250270781L;
	
	@Resource(name = "accessorRead")
	private IAccessor accessorRead;

	@Resource(name = "accessorWrite")
	private IAccessor accessorWrite;

	@Override
	public IAccessor getAccessorRead() {
		return accessorRead;
	}

	@Override
	public IAccessor getAccessorWrite() {
		return accessorWrite;
	}

	
	
	

}
