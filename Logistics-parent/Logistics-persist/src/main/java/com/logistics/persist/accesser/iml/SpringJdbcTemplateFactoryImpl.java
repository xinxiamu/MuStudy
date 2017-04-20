package com.logistics.persist.accesser.iml;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.logistics.persist.accesser.SpringJdbcTemplateFactory;

/**
 * 
 * spring jdbcTemplate 管理类
 * 
 * @author
 * 
 */
@Component
public class SpringJdbcTemplateFactoryImpl implements SpringJdbcTemplateFactory {

	// lg库
	@Resource(name = "jdbcTemplateDruidLg")
	private JdbcTemplate jdbcTemplateDruidLg;
	@Resource(name = "jdbcTemplateC3p0Lg")
	private JdbcTemplate jdbcTemplateC3p0Lg;
	@Resource(name = "jdbcTemplateSpringLg")
	private JdbcTemplate jdbcTemplateSpringLg;

	// lg_log库
	@Resource(name = "jdbcTemplateDruidLg_log")
	private JdbcTemplate jdbcTemplateDruidLg_log;
	@Resource(name = "jdbcTemplateC3p0Lg_log")
	private JdbcTemplate jdbcTemplateC3p0Lg_log;
	@Resource(name = "jdbcTemplateSpringLg_log")
	private JdbcTemplate jdbcTemplateSpringLg_log;

	// lg_dictionary库
	@Resource(name = "jdbcTemplateDruidLg_dictionary")
	private JdbcTemplate jdbcTemplateDruidLg_dictionary;
	@Resource(name = "jdbcTemplateC3p0Lg_dictionary")
	private JdbcTemplate jdbcTemplateC3p0Lg_dictionary;
	@Resource(name = "jdbcTemplateSpringLg_dictionary")
	private JdbcTemplate jdbcTemplateSpringLg_dictionary;

	// a88库
	@Resource(name = "jdbcTemplateC3p0A88")
	private JdbcTemplate jdbcTemplateC3p0A88;

	@Override
	public JdbcTemplate getJdbcTemplateDruidLg() {
		return jdbcTemplateDruidLg;
	}

	@Override
	public JdbcTemplate getJdbcTemplateSpringLg() {
		return jdbcTemplateSpringLg;
	}

	@Override
	public JdbcTemplate getJdbcTemplateC3p0Lg() {
		return jdbcTemplateC3p0Lg;
	}

	@Override
	public JdbcTemplate getJdbcTemplateC3p0A88() {
		return jdbcTemplateC3p0A88;
	}

	@Override
	public JdbcTemplate getJdbcTemplateDruidLg_dictionary() {
		return jdbcTemplateDruidLg_dictionary;
	}

	@Override
	public JdbcTemplate getJdbcTemplateSpringLg_dictionary() {
		return jdbcTemplateSpringLg_dictionary;
	}

	@Override
	public JdbcTemplate getJdbcTemplateC3p0Lg_dictionary() {
		return jdbcTemplateC3p0Lg_dictionary;
	}

	@Override
	public JdbcTemplate getJdbcTemplateDruidLg_log() {
		return jdbcTemplateDruidLg_log;
	}

	@Override
	public JdbcTemplate getJdbcTemplateSpringLg_log() {
		return jdbcTemplateSpringLg_log;
	}

	@Override
	public JdbcTemplate getJdbcTemplateC3p0Lg_log() {
		return jdbcTemplateC3p0Lg_log;
	}

}