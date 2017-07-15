package com.example.ymu.config;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.logging.Log4j2Filter;
import com.alibaba.druid.pool.DruidDataSource;

/**
 * 数据源。
 */
@Configuration
@AutoConfigureAfter(Config.class)
public class DataSourceConfig {

	@Value("${spring.datasource.druid.testDb.url}")
	private String url;
	@Value("${spring.datasource.druid.testDb.username}")
	private String username;
	@Value("${spring.datasource.druid.testDb.password}")
	private String password;
	@Value("${spring.datasource.druid.testDb.driver-class-name}")
	private String driverClassName;
	
	@Value("${spring.datasource.druid.filters}")
	private String filters;

	@Bean(name = "log4j2Filter")
	public Log4j2Filter log4j2Filter() {
		Log4j2Filter log4j2Filter = new Log4j2Filter();
		log4j2Filter.setConnectionLogEnabled(false);
		log4j2Filter.setResultSetLogEnabled(true); //显示sql
		log4j2Filter.setDataSourceLogEnabled(false);
		log4j2Filter.setStatementExecutableSqlLogEnable(true);
		log4j2Filter.setStatementLogEnabled(false);
		return log4j2Filter;

	}

	@Bean(name = "testDbDataSource")
	@Qualifier("testDbDataSource")
	public DataSource primaryDataSource() throws SQLException {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setDriverClassName(driverClassName);
		dataSource.setInitialSize(1);
		dataSource.setMinIdle(1);
		dataSource.setMaxActive(50);
		dataSource.setMaxWait(60000);
		dataSource.setTimeBetweenEvictionRunsMillis(60000);
		dataSource.setMinEvictableIdleTimeMillis(300000);

		//加上这个，否则无法监控sql
		dataSource.setFilters(filters); 
		
		List<Filter> proxyFilters = new ArrayList<>();
		proxyFilters.add(log4j2Filter());
		dataSource.setProxyFilters(proxyFilters);

		return dataSource;
	}
}
