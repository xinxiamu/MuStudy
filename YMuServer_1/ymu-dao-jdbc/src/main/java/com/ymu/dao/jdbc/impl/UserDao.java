package com.ymu.dao.jdbc.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.ymu.dao.datasource.DatabaseType;
import com.ymu.dao.jdbc.IUserDao;
import com.ymu.infrastructure.core.sql.SqlBuilder;
import com.ymu.model.User;
import com.ymu.vo.UserVo;

/**
 * 用户信息相关。 查询全部使用spring jdbc技术
 */
@Repository
public class UserDao extends BaseDao implements IUserDao {

	@Override
	public User getByUserName(String userName) throws Exception {
		String sql = new SqlBuilder() {
			{
				SELECT("*").FROM("user").WHERE("user_name=?");
			}
		}.toString();

		User user = getJdbcTemplate(DatabaseType.ymuSlave).queryForObject(sql,
				new BeanPropertyRowMapper<User>(User.class), userName);
		return user;
	}

	@Override
	public UserVo getUserAllInfoByUserId(long userId) throws Exception {
		String sql = new SqlBuilder() {
			{
				SELECT("u.*,ud.mobile,ud.real_name");
				FROM("user AS u");
				LEFT_OUTER_JOIN("user_details AS ud ON u.id=ud.user_id");
				WHERE("u.id=?");
			}
		}.toString();
		UserVo userVo = getJdbcTemplate(DatabaseType.ymuSlave).queryForObject(
				sql, new BeanPropertyRowMapper<UserVo>(UserVo.class), userId);
		return userVo;
	}

	public User getById(Long id) throws Exception {
		String sql = new SqlBuilder() {
			{
				SELECT("*").FROM("user").WHERE("id=?");
			}
		}.toString();

		User user = null;
		try {
			user = getJdbcTemplate(DatabaseType.ymuSlave).queryForObject(sql,
					new BeanPropertyRowMapper<User>(User.class), id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	public List<User> getAll() throws Exception {
		return null;
	}
}
