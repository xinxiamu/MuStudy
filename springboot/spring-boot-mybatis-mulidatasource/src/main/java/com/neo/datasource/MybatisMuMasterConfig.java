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
@MapperScan(basePackages = "${mybatis.mu-master.typeAliasesPackage}", sqlSessionTemplateRef = "muMasterSqlSessionTemplate")
public class MybatisMuMasterConfig {

	@Autowired
	private Environment env;

	@Autowired
	@Qualifier("muMasterDataSource")
	private DataSource muMasterDataSource; // 注入数据源

	@Bean(name = "test1SqlSessionFactory")
	@Primary
	public SqlSessionFactory testSqlSessionFactory() throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(muMasterDataSource);
		bean.setMapperLocations(
				new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mu-master.mapperLocations")));
		return bean.getObject();
	}

	@Bean(name = "muMasterSqlSessionTemplate")
	@Primary
	public SqlSessionTemplate testSqlSessionTemplate(
			@Qualifier("muMasterSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

	@Bean(name = "muMasterTransactionManager")
	@Primary
	public DataSourceTransactionManager testTransactionManager() {
		return new DataSourceTransactionManager(muMasterDataSource);
	}

}
