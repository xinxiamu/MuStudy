package com.logistics.persist.accesser;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 数据库连接工厂, 实现连接池有 Druid, Spring, c3p0, dbcp
 */
public interface SpringJdbcTemplateFactory {
	
	//====================== lg数据库 ================//
	
	/**
	 * 以Druid连接池实现Lg数据源
	 * @return
	 */
	public JdbcTemplate getJdbcTemplateDruidLg();
	
	/**
	 * 以Spring连接池实现Lg数据源
	 * @return
	 */
	public JdbcTemplate getJdbcTemplateSpringLg();
	
	/**
	 * 以C3p0连接池实现Lg数据源
	 * @return
	 */
	public JdbcTemplate getJdbcTemplateC3p0Lg();
	
	//====================== lg_dictionary =================//
	/**
	 * 以Druid连接池实现
	 * @return
	 */
	public JdbcTemplate getJdbcTemplateDruidLg_dictionary();
	
	/**
	 * 以Spring连接池实现
	 * @return
	 */
	public JdbcTemplate getJdbcTemplateSpringLg_dictionary();
	
	/**
	 * 以C3p0连接池实现
	 * @return
	 */
	public JdbcTemplate getJdbcTemplateC3p0Lg_dictionary();
	
	//====================== lg_log =================//
		/**
		 * 以Druid连接池实现
		 * @return
		 */
		public JdbcTemplate getJdbcTemplateDruidLg_log();
		
		/**
		 * 以Spring连接池实现
		 * @return
		 */
		public JdbcTemplate getJdbcTemplateSpringLg_log();
		
		/**
		 * 以C3p0连接池实现
		 * @return
		 */
		public JdbcTemplate getJdbcTemplateC3p0Lg_log();
	
	//=================== a88 ==================//
	
	/**
	 * a88库
	 * @return
	 */
	public JdbcTemplate getJdbcTemplateC3p0A88();
	
}
