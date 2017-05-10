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
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * test1数据库配置
 */
@Configuration
@Import(DataSourceConfig.class)
@AutoConfigureAfter(DataSourceConfig.class)
@MapperScan(basePackages = "${mybatis.test1.typeAliasesPackage}", sqlSessionTemplateRef = "test1SqlSessionTemplate")
public class MybatisTest1Config {

	@Autowired
	private Environment env;

	@Autowired
	@Qualifier("test1DataSource")
	private DataSource test1DataSource; // 注入数据源

	@Bean(name = "test1SqlSessionFactory")
	@Primary
	public SqlSessionFactory testSqlSessionFactory() throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(test1DataSource);
		bean.setMapperLocations(
				new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.test1.mapperLocations")));
		return bean.getObject();
	}

	@Bean(name = "test1SqlSessionTemplate")
	@Primary
	public SqlSessionTemplate testSqlSessionTemplate(
			@Qualifier("test1SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

	@Bean(name = "test1TransactionManager")
	@Primary
	public DataSourceTransactionManager testTransactionManager() {
		return new DataSourceTransactionManager(test1DataSource);
	}

}
