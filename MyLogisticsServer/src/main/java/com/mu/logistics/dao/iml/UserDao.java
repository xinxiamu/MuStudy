package com.mu.logistics.dao.iml;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.mu.common.dao.iml.CommonDao;
import com.mu.common.exception.BaseException;
import com.mu.common.utils.DateUtil;
import com.mu.logistics.dao.IUserDao;
import com.mu.logistics.model.iwl.User;

@Repository
public class UserDao extends CommonDao implements IUserDao {

	@Override
	public Long add(final User user) {
		final String sql = "INSERT INTO "
				+ "user(headPhoto,moblieAccount,password,userName,userAddress,userSex,userType_id,fromSystem,createtime,id,realName)"
				+ "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
		try {
			KeyHolder keyHolder = springJdbcDao
					.add(new PreparedStatementCreator() {

						@Override
						public PreparedStatement createPreparedStatement(
								Connection con) throws SQLException {
							PreparedStatement ps = con.prepareStatement(sql,
									new String[] { "id" });
							ps.setString(1, user.getHeadPhoto());
							ps.setString(2, user.getMoblieAccount());
							ps.setString(3, user.getPassword());
							ps.setString(4, user.getUserName());
							ps.setString(5, user.getUserAddress());
							ps.setString(6, user.getUserSex());
							ps.setInt(7, user.getUserType_id());
							ps.setString(8, user.getFromSystem());
							ps.setString(9,
									DateUtil.dateToString(user.getCreatetime()));
							ps.setLong(10, user.getId());
							ps.setString(11, user.getRealName());
							return ps;
						}
					});
			long id = (Long) keyHolder.getKey();
			return id > 0 ? id : 0;
		} catch (BaseException e) {
			BaseException.throwsException(getClass(), e);
		}
		return 0L;
	}

	@Override
	public List<Map<String, Object>> getA88AllUser() {
		String sql = "SELECT TOP(50000) wj_ID,wj_User,wj_UserPwd,wj_Type,wj_Sex,wj_Mobile,wj_Address,createtime,link "
				+ "FROM wj_User "
				+ "WHERE wj_Mobile IS NOT NULL AND wj_Mobile!='' AND wj_Mobile != 'null' AND wj_Mobile!='NULL'  "
				+ "ORDER BY wj_ID ASC  ";
		return getUsers(accesser.getJdbcTemplateFactory()
				.getJdbcTemplateC3p0A88(), sql);
	}

	@Override
	public Long saveBatch(List<User> users) {
		try {
			Long total = hibernateDao.saveBatch(
					accesser.getSessionFactoryIwl(), users);
			return total;
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0L;
	}

	@Override
	public Map<String, Object> getUserByType(int userTypeCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userTypeCode", userTypeCode);
		Map<String, Object> resultSet = null;
		try {
			resultSet = springJdbcDao.getObjectGt("user_type", map);
		} catch (BaseException e) {
			e.printStackTrace();
		}
		return resultSet;
	}

	@Override
	public int[] batchAdd(final List<User> users) throws BaseException {
		String sql = "INSERT INTO "
				+ "user(headPhoto,moblieAccount,password,userName,userAddress,userSex,userType_id,fromSystem,createtime,id,realName)"
				+ "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
		springJdbcDao.batchUpdate(users, sql,
				new BatchPreparedStatementSetter() {

					@Override
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						User user = users.get(i);
						ps.setString(1, user.getHeadPhoto());
						ps.setString(2, user.getMoblieAccount());
						ps.setString(3, user.getPassword());
						ps.setString(4, user.getUserName());
						ps.setString(5, user.getUserAddress());
						ps.setString(6, user.getUserSex());
						ps.setInt(7, user.getUserType_id());
						ps.setString(8, user.getFromSystem());
						ps.setString(9,
								DateUtil.dateToString(user.getCreatetime()));
						ps.setLong(10, user.getId());
						ps.setString(11, user.getRealName());
					}

					@Override
					public int getBatchSize() {
						return users.size();
					}
				});

		return null;
	}

	@Override
	public List<Map<String, Object>> getUsers(JdbcTemplate jdbcTemplate,
			String sql) {
		List<Map<String, Object>> resultSet = new ArrayList<>();
		try {
			resultSet = springJdbcDao.getList(jdbcTemplate, sql);
		} catch (BaseException e) {
			e.printStackTrace();
		}
		return resultSet;
	}

	@Override
	public List<Map<String, Object>> getA88UsersBigId(Long minId) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT wj_ID,wj_User,wj_UserPwd,wj_Type,wj_Sex,wj_Mobile,wj_Address,createtime,link  ");
		sb.append("FROM ");
		sb.append("wj_User ");
		sb.append("WHERE ");
		sb.append("wj_ID > " + minId + "  ");
		sb.append("AND wj_Mobile IS NOT NULL AND wj_Mobile!='' AND wj_Mobile != 'null' AND wj_Mobile!='NULL'  ");
		sb.append("ORDER BY wj_ID ASC ");
		return getUsers(accesser.getJdbcTemplateFactory()
				.getJdbcTemplateC3p0A88(), sb.toString());
	}

}
