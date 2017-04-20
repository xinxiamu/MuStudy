package com.mu.common.factory;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 数据库连接工厂, 实现连接池有 Druid, Spring, c3p0, dbcp
 * @author ZhengJianYan
 * @see CreateDate: 2013-8-12
 * @see UpdateName:
 * @see UpdateDate:
 * @see Copyright:Linan
 * @see QQ:375273058
 * @see <a href="mailto:13822119203@139.com">Email</a>
 * @since 1.0
 * @version	V1.0
 */
public interface IJdbcTemplateFactory {
	
	/**
	 * 以Druid连接池实现Iwl数据源
	 * @return
	 */
	public JdbcTemplate getJdbcTemplateDruidIwl();
	
	/**
	 * 以Spring连接池实现Iwl数据源
	 * @return
	 */
	public JdbcTemplate getJdbcTemplateSpringIwl();
	
	/**
	 * 以C3p0连接池实现Iwl数据源
	 * @return
	 */
	public JdbcTemplate getJdbcTemplateC3p0Iwl();
	
}
