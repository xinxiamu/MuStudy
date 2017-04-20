package com.mu.logistics.dao;

import java.util.List;
import java.util.Map;

public interface ICarDao {

	/**
	 * 获取所有比某个最大id大的车源。
	 * @param maxId
	 * @return
	 */
	public List<Map<String, Object>> getCarsGtMaxIdFromLinanA88(Long maxId);
}
