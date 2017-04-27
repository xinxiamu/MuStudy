package com.ymu.dao.base;

import java.io.Serializable;
import java.util.List;

public class CommonDao<S extends Serializable, ID extends Serializable>
		implements ICommonDao<S, ID> {

	@Override
	public S getById(ID id) throws Exception {
		return null;
	}

	@Override
	public List<S> getAll() throws Exception {
		return null;
	}

}
