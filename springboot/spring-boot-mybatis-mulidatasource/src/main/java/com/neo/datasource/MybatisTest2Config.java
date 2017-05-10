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
 * test2数据库配置。从库
 */
@Configuration
@Import(DataSourceConfig.class)
@AutoConfigureAfter(DataSourceConfig.class)
@MapperScan(basePackages = "${mybatis.test2.typeAliasesPackage}", sqlSessionTemplateRef = "test2SqlSessionTemplate")
public class MybatisTest2Config {

	@Autowired
	private Environment env;

	@Autowired
	@Qualifier("test2DataSource")
	private DataSource test2DataSource; // 注入数据源

	@Bean(name = "test2SqlSessionFactory")
	public SqlSessionFactory testSqlSessionFactory() throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(test2DataSource);
		bean.setMapperLocations(new PathMatchingResourcePatternResolver()
				.getResources(env.getProperty("mybatis.test2.mapperLocations")));
		return bean.getObject();
	}

	@Bean(name = "test2SqlSessionTemplate")
	public SqlSessionTemplate testSqlSessionTemplate(
			@Qualifier("test2SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

	@Bean(name = "test2TransactionManager")
	public DataSourceTransactionManager testTransactionManager() {
		return new DataSourceTransactionManager(test2DataSource);
	}

}
