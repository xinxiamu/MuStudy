package com.mu.common.factory.iml;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.mu.common.factory.IJdbcTemplateFactory;

/**
 * 
 * spring jdbcTemplate 管理类
 * 
 * @author
 * 
 */
@Component
public class JdbcTemplateFactory implements IJdbcTemplateFactory {

	// iwl库
	@Resource(name = "jdbcTemplateDruidIwl")
	private JdbcTemplate jdbcTemplateDruidIwl;
	@Resource(name = "jdbcTemplateC3p0Iwl")
	private JdbcTemplate jdbcTemplateC3p0Iwl;
	@Resource(name = "jdbcTemplateSpringIwl")
	private JdbcTemplate jdbcTemplateSpringIwl;

	// a88库
	@Resource(name = "jdbcTemplateC3p0A88")
	private JdbcTemplate jdbcTemplateC3p0A88;

	public JdbcTemplate getJdbcTemplateDruidIwl() {
		return jdbcTemplateDruidIwl;
	}

	public JdbcTemplate getJdbcTemplateC3p0Iwl() {
		return jdbcTemplateC3p0Iwl;
	}

	public JdbcTemplate getJdbcTemplateSpringIwl() {
		return jdbcTemplateSpringIwl;
	}

	public JdbcTemplate getJdbcTemplateC3p0A88() {
		return jdbcTemplateC3p0A88;
	}


}