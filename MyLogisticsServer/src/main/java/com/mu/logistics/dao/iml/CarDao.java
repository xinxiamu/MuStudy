package com.mu.logistics.dao.iml;

import java.util.List;
import java.util.Map;

import com.mu.common.dao.iml.CommonDao;
import com.mu.common.utils.sql.SqlSelect;
import com.mu.logistics.dao.ICarDao;

public class CarDao extends CommonDao implements ICarDao {

	@Override
	public List<Map<String, Object>> getCarsGtMaxIdFromLinanA88(Long maxId) {
		SqlSelect sqlSelect = new SqlSelect();
		sqlSelect.from("");
		return null;
	}

	
}
