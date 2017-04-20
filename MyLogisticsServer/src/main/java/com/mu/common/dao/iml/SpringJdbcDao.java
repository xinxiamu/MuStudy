package com.mu.common.dao.iml;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.mu.common.dao.ISpringJdbcDao;
import com.mu.common.exception.BaseException;

@Repository
public class SpringJdbcDao implements ISpringJdbcDao {

	@Resource
	protected Accesser accesser;

	@Override
	public KeyHolder add(PreparedStatementCreator callBack)
			throws BaseException {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		JdbcTemplate jdbcTemplate = accesser.getJdbcTemplateFactory()
				.getJdbcTemplateC3p0Iwl();
		jdbcTemplate.update(callBack, keyHolder);
		return keyHolder;
	}

	@Override
	public List<Map<String, Object>> getList(JdbcTemplate jdbcTemplate,
			String sql) throws BaseException {
		List<Map<String, Object>> resultSet = jdbcTemplate.queryForList(sql);
		return resultSet;
	}

	@Override
	public Long add(String sql) throws BaseException {
		final String sql_insert = sql;

		KeyHolder keyHolder = new GeneratedKeyHolder();
		JdbcTemplate jdbcTemplate = accesser.getJdbcTemplateFactory()
				.getJdbcTemplateC3p0Iwl();
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql_insert,
						new String[] { "id" });
				return ps;
			}
		}, keyHolder);

		long id = (Long) keyHolder.getKey();

		if (id > 0) {
			return id;
		}
		return 0L;
	}

	@Override
	public Map<String, Object> getObjectGt(String tableName,
			Map<String, Object> gts) throws BaseException {
		StringBuffer sb = new StringBuffer("select * from " + tableName
				+ " where 1=1 ");
		if (gts == null || gts.isEmpty()) {
			return null;
		}
		Set<String> keys = gts.keySet();
		for (String key : keys) {
			Object fieldV = gts.get(key);
			if (fieldV == null) {
				continue;
			}
			if (fieldV == String.class) {
				sb.append(" and " + key + "='" + fieldV + "' ");
			} else {
				sb.append(" and " + key + "=" + fieldV + " ");
			}
		}
		JdbcTemplate jdbcTemplate = accesser.getJdbcTemplateFactory()
				.getJdbcTemplateC3p0Iwl();
		Map<String, Object> resultSet = jdbcTemplate.queryForMap(sb.toString());
		return resultSet;
	}

	@Override
	public <T> int[] batchUpdate(List<T> beans, String sql,
			BatchPreparedStatementSetter callback) throws BaseException {
		JdbcTemplate jdbcTemplate = accesser.getJdbcTemplateFactory().getJdbcTemplateC3p0Iwl();
		 int[] updateCounts = jdbcTemplate.batchUpdate(sql, callback);
		return updateCounts;
	}

	@Override
	public Long getMaxId(JdbcTemplate jdbcTemplate,String tableName)  throws BaseException {
		String sql = "SELECT MAX(id) AS maxId FROM "+tableName+"";
		Long maxId = 0L;
		try {
			maxId = jdbcTemplate.queryForObject(sql, Long.class);
		} catch (Exception e) {
			BaseException.throwsException(SpringJdbcDao.class, e);
		}
		return maxId;
	}
	
}
