package com.neo.datasource;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * test数据库配置
 */
//@Configuration
//@Import(DataSourceConfig.class)
//@AutoConfigureAfter(DataSourceConfig.class)
//@MapperScan(basePackages = "${mybatis.mu-slave.typeAliasesPackage}", sqlSessionTemplateRef = "muSlaveSqlSessionTemplate")
public class MybatisTest1SlaveConfig {

	@Autowired
	private Environment env;

	@Autowired
	@Qualifier("muSlaveDataSource")
	private DataSource muSlaveDataSource; // 注入数据源

	@Bean(name = "muSlaveSqlSessionFactory")
	public SqlSessionFactory testSqlSessionFactory() throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(muSlaveDataSource);
		bean.setMapperLocations(new PathMatchingResourcePatternResolver()
				.getResources(env.getProperty("mybatis.mu-slave.mapperLocations")));
		return bean.getObject();
	}

	@Bean(name = "muSlaveSqlSessionTemplate")
	public SqlSessionTemplate testSqlSessionTemplate(
			@Qualifier("muSlaveSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

	@Bean(name = "muSlaveTransactionManager")
	public DataSourceTransactionManager testTransactionManager() {
		return new DataSourceTransactionManager(muSlaveDataSource);
	}

}
