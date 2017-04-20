package com.logistics.persist.accesser.iml;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.logistics.persist.accesser.Accesser;
import com.logistics.persist.accesser.HibernateSessionFactory;
import com.logistics.persist.accesser.SpringJdbcTemplateFactory;
import com.logistics.persist.accesser.enums.ConnectionPool;
import com.logistics.persist.accesser.enums.DataSource;

/**
 * 数据库访问器
 * 
 * @author mutou
 * 
 */
public abstract class AccesserImpl implements Accesser {

	@Resource(name = "hibernateSessionFactory")
	private HibernateSessionFactory hibernateSessionFactory;

	@Resource
	private SpringJdbcTemplateFactory springJdbcTemplateFactoryImpl;

	@Override
	public HibernateSessionFactory getHibernateSessionFactorys() {
		return hibernateSessionFactory;
	}

	@Override
	public SpringJdbcTemplateFactory getSpringJdbcTemplateFactory() {
		return springJdbcTemplateFactoryImpl;
	}
	
	@Override
	public JdbcTemplate getJdbcTemplate(DataSource dataSource) {
		return getJdbcTemplate(dataSource, ConnectionPool.C3P0);
	}

	@Override
	public JdbcTemplate getJdbcTemplate(DataSource databaseName,
			ConnectionPool poolPattern) {
		JdbcTemplate jdbcTemplate = null;
		switch (databaseName) {
		case LG:
			switch (poolPattern) {
			case DRUID:
				jdbcTemplate = springJdbcTemplateFactoryImpl
						.getJdbcTemplateDruidLg();
				break;
			case C3P0:
				jdbcTemplate = springJdbcTemplateFactoryImpl
						.getJdbcTemplateC3p0Lg();
				break;

			case SPRING:
				jdbcTemplate = springJdbcTemplateFactoryImpl
						.getJdbcTemplateSpringLg();
				break;
			default:
				jdbcTemplate = springJdbcTemplateFactoryImpl
						.getJdbcTemplateC3p0Lg();
				break;
			}

			break;

		case LG_LOG:
			switch (poolPattern) {
			case C3P0:
				jdbcTemplate = springJdbcTemplateFactoryImpl
						.getJdbcTemplateC3p0Lg_log();
				break;
			case DRUID:
				jdbcTemplate = springJdbcTemplateFactoryImpl
						.getJdbcTemplateDruidLg_log();
				break;
			case SPRING:
				jdbcTemplate = springJdbcTemplateFactoryImpl
						.getJdbcTemplateSpringLg_log();
				break;
			default:
				jdbcTemplate = springJdbcTemplateFactoryImpl
						.getJdbcTemplateC3p0Lg_log();
				break;
			}
			break;

		case LG_DICTIONARY:
			switch (poolPattern) {
			case C3P0:
				jdbcTemplate = springJdbcTemplateFactoryImpl
						.getJdbcTemplateC3p0Lg_dictionary();
				break;
			case DRUID:
				jdbcTemplate = springJdbcTemplateFactoryImpl
						.getJdbcTemplateDruidLg_dictionary();
				break;
			case SPRING:
				jdbcTemplate = springJdbcTemplateFactoryImpl
						.getJdbcTemplateSpringLg_dictionary();
				break;
			default:
				jdbcTemplate = springJdbcTemplateFactoryImpl
						.getJdbcTemplateC3p0Lg_dictionary();
				break;
			}
			break;

		case A88:
			switch (poolPattern) {

			default:
				jdbcTemplate = springJdbcTemplateFactoryImpl
						.getJdbcTemplateC3p0A88();
				break;
			}
			break;

		default:
			break;
		}
		return jdbcTemplate;
	}

	@Override
	public Map<Object, Object> getSystemInfo() {
		return System.getProperties();
	}

}
