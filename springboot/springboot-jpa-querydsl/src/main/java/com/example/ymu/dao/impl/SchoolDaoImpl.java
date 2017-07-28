package com.example.ymu.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.ymu.dao.SchoolDao;
import com.example.ymu.dao.base.BaseDaoImpl;
import com.example.ymu.dao.repository.SchoolRepository;
import com.example.ymu.domain.QSchool;
import com.example.ymu.domain.School;
import com.querydsl.jpa.impl.JPAQuery;
import com.ymu.framework.dao.persist.jdbc.SpringJdbcAccessor;
import com.ymu.framework.utils.time.DateUtil;

@Repository
public class SchoolDaoImpl extends BaseDaoImpl<SchoolRepository> implements SchoolDao {

	@Override
	public String getSchoolNameById(Long id) {
		QSchool school = QSchool.school;
		JPAQuery<?> query = new JPAQuery<Void>(em);
		String schoolName = query.select(school.name).from(school).where(school.id.eq(id)).fetchOne();
		return schoolName;
	}

	@Override
	public String getSchoolNameUseJdbc(Long id) {
		String schName = jdbcTemplate.queryForObject("SELECT `name` FROM school WHERE id=?", new Object[] { id },
				String.class);
		return schName;
	}

	@Override
	public void batchInsert(List<School> schools) {
		long startTime = System.currentTimeMillis();

		// sql前缀
		String prefix = "INSERT INTO school(addr,found_time,name) VALUES ";

		Connection conn = null;
		PreparedStatement pst = null;
		try {
			conn = jdbcTemplate.getDataSource().getConnection();

			// 保存sql后缀
			StringBuffer suffix = new StringBuffer();
			// 设置事务为非自动提交
			conn.setAutoCommit(false);
			// Statement st = conn.createStatement();
			// 比起st，pst会更好些
			pst = conn.prepareStatement("");
			// 外层循环，总提交事务次数
			for (int i = 1; i <= 100; i++) {
				// 第次提交步长,一万条插一次
				for (int j = 1; j <= 10000; j++) {
					// 构建sql后缀
					suffix.append("(").append("'").append("电城镇").append(i).append(j).append("'").append(",").append("'")
							.append(DateUtil.dateToString(new Date())).append("'").append(",").append("'").append("小学")
							.append(i).append(j).append("'").append(")").append(",");
				}
				// 构建完整sql
				String sql = prefix + suffix.substring(0, suffix.length() - 1);
				// 添加执行sql
				pst.addBatch(sql);
				// 执行操作
				pst.executeBatch();
				// 提交事务
				conn.commit();
				// 清空上一次添加的数据
				suffix = new StringBuffer();
			}
			// 头等连接
			pst.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		System.out.println("====共用时间(秒)：" + (endTime - startTime) / 1000);
	}

	@Override
	public void batchInser2() {
		try {
			String[] fields = new String[] { "name", "addr", "foundTime" };
			List<Object[]> list = new ArrayList<>();
			for (int i = 1; i <= 10000 * 100; i++) { 
				Object[] row = new Object[] { "马槛小学" + i, "电城镇马槛村", new Date() };
				list.add(row);
			}
			SpringJdbcAccessor.addBatch(jdbcTemplate, School.class.getSimpleName(), fields, list);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
