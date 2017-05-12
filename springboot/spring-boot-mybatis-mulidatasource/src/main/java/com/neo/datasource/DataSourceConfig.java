package com.neo.datasource;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.neo.datasource.utils.DynamicDataSource;

/**
 * 配置数据源
 */
@Configuration
public class DataSourceConfig {

	@Autowired
	private Environment env;

	/**
	 * mu_master主库，使用阿里druid连接池
	 *
	 * @return
	 * @throws Exception
	 */
	@Bean(name = "muMasterDataSource")
	@Qualifier("muMasterDataSource")
	public DataSource muMasterDataSource() throws Exception {
		Properties props = new Properties();
		props.put("driverClassName", env.getProperty("datasource.mu_master.driverClassName"));
		props.put("url", env.getProperty("datasource.mu_master.url"));
		props.put("username", env.getProperty("datasource.mu_master.username"));
		props.put("password", env.getProperty("datasource.mu_master.password"));
		DataSource muMaster = DruidDataSourceFactory.createDataSource(props);
		return muMaster;
	}

	/**
	 * mu_slave从库数据源
	 *
	 * @return
	 * @throws Exception
	 */
	@Bean(name = "muSlaveDataSource")
	@Qualifier("muSlaveDataSource")
	public DataSource muSlaveDataSource() throws Exception {
		Properties props = new Properties();
		props.put("driverClassName", env.getProperty("datasource.mu_slave.driverClassName"));
		props.put("url", env.getProperty("datasource.mu_slave.url"));
		props.put("username", env.getProperty("datasource.mu_slave.username"));
		props.put("password", env.getProperty("datasource.mu_slave.password"));
		DataSource muSlave = DruidDataSourceFactory.createDataSource(props);
		return muSlave;
	}

	/**
	 * 动态数据源: 通过AOP在不同数据源之间动态切换
	 * 
	 * @return
	 */
	@Bean(name = "mu_dynamic_ds")
	@Scope("singleton")
	@Primary
	public DataSource muDynamicDataSource(@Qualifier("muMasterDataSource") DataSource muMasterDataSource,
			@Qualifier("muSlaveDataSource") DataSource muSlaveDataSource) {
		DynamicDataSource dynamicDataSource = new DynamicDataSource();
		// 默认数据源
		dynamicDataSource.setDefaultTargetDataSource(muMasterDataSource);

		// 配置多数据源
		Map<Object, Object> dsMap = new HashMap<>(5);
		dsMap.put(DSType.MU_MASTER, muMasterDataSource);
		dsMap.put(DSType.MU_SLAVE, muSlaveDataSource);

		dynamicDataSource.setTargetDataSources(dsMap);

		return dynamicDataSource;
	}

}
