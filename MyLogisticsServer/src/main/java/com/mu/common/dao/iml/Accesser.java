package com.mu.common.dao.iml;

import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.mu.common.dao.IAccesser;
import com.mu.common.enums.ConnectionPool;
import com.mu.common.enums.DataSource;
import com.mu.common.factory.iml.JdbcTemplateFactory;

@Component
public class Accesser implements IAccesser {

	private static final long serialVersionUID = 694650536100293397L;

	public static Logger logger = LoggerFactory.getLogger(Accesser.class);

	//hibernate
	@Resource
	private SessionFactory sessionFactoryIwl;

	//spring jdbc
	@Resource
	JdbcTemplateFactory jdbcTemplateFactory;

	JdbcTemplate jdbcTemplate;

	public JdbcTemplateFactory getJdbcTemplateFactory() {
		return jdbcTemplateFactory;
	}

	public void setJdbcTemplateFactory(JdbcTemplateFactory jdbcTemplateFactory) {
		this.jdbcTemplateFactory = jdbcTemplateFactory;
	}

	public SessionFactory getSessionFactoryIwl() {
		return sessionFactoryIwl;
	}

	public void setSessionFactoryIwl(SessionFactory sessionFactoryIwl) {
		this.sessionFactoryIwl = sessionFactoryIwl;
	}

	@Override
	public Map<Object, Object> getSystemInfo() {
		return System.getProperties();
	}

	@Override
	public JdbcTemplate getJdbcTemplate(DataSource databaseName,
			ConnectionPool poolPattern) {
		setDatabaseConnectionPool(databaseName, poolPattern);
		return jdbcTemplate;
	}

	protected void setDatabaseConnectionPool(DataSource databaseName,
			ConnectionPool poolPattern) {
		switch (databaseName) {
		case IWL:
			switch (poolPattern) {
			case DRUID:
				jdbcTemplate = jdbcTemplateFactory.getJdbcTemplateDruidIwl();
				break;

			case C3P0:
				jdbcTemplate = jdbcTemplateFactory.getJdbcTemplateC3p0Iwl();
				break;

			case SPRING:
				jdbcTemplate = jdbcTemplateFactory.getJdbcTemplateSpringIwl();
				break;
			default:
				jdbcTemplate = jdbcTemplateFactory.getJdbcTemplateC3p0Iwl();
				break;
			}

			break;

		case LINAN:
			switch (poolPattern) {

			default:
				break;
			}
			break;

		default:
			break;
		}

	}

}
