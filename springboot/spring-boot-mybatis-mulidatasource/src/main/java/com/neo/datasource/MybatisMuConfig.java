package com.neo.datasource;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
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
 * mu数据库配置
 */
@Configuration
@Import(DataSourceConfig.class)
@AutoConfigureAfter(DataSourceConfig.class)
// @MapperScan(basePackages = "${mybatis.mu.typeAliasesPackage}",
// sqlSessionTemplateRef = "muSqlSessionTemplate")
public class MybatisMuConfig {

	@Autowired
	private Environment env;

	@Autowired
	@Qualifier("mu_dynamic_ds")
	private DataSource muDynamicDataSource; // 注入数据源,动态切换

	@Bean(name = "muSqlSessionFactory")
	@Primary
	public SqlSessionFactory muSqlSessionFactory() throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(muDynamicDataSource);
		bean.setTypeAliasesPackage(env.getProperty("mybatis.mu.typeAliasesPackage"));//扫描包
		bean.setMapperLocations(
				new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mu.mapperLocations"))); // 映射xml文件
		return bean.getObject();
	}

	@Bean(name = "muSqlSessionTemplate")
	@Primary
	public SqlSessionTemplate muSqlSessionTemplate(
			@Qualifier("muSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

	@Bean(name = "muTransactionManager")
	@Primary
	public DataSourceTransactionManager testTransactionManager() {
		return new DataSourceTransactionManager(muDynamicDataSource);
	}

}
