package com.ymu.dao.jpa.config;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.ymu.dao.datasource.DataSourceConfig;

/**
 * master库jpa配置
 */
@Configuration
@AutoConfigureAfter(DataSourceConfig.class)
@Import(DataSourceConfig.class)
// 导入DataSourceConfig的配置
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactoryYmuMaster", transactionManagerRef = "transactionManagerYmuMaster", basePackages = { "com.ymu.dao.jpa.repository" })
// 设置Repository所在位置
public class YmuMasterJpaConfig {

	@Autowired
	@Qualifier("ymuMasterDataSource")
	private DataSource ymuMasterDataSource;

	@Primary
	@Bean(name = "entityManagerYmuMaster")
	public EntityManager entityManagerYmuMaster(
			EntityManagerFactoryBuilder builder) {
		return entityManagerFactoryYmuMaster(builder).getObject()
				.createEntityManager();
	}

	@Primary
	@Bean(name = "entityManagerFactoryYmuMaster")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryYmuMaster(
			EntityManagerFactoryBuilder builder) {
		return builder.dataSource(ymuMasterDataSource)
				.properties(getVendorProperties(ymuMasterDataSource))
				.packages("com.ymu.model") // 设置实体类所在位置
				.persistenceUnit("ymuMasterPersistenceUnit").build();
	}

	@Autowired
	private JpaProperties jpaProperties;

	private Map<String, String> getVendorProperties(DataSource dataSource) {
		return jpaProperties.getHibernateProperties(dataSource);
	}

	/**
	 * 开启事务
	 *
	 * @param builder
	 * @return
	 */
	@Primary
	@Bean(name = "transactionManagerYmuMaster")
	public PlatformTransactionManager transactionManagerYmuMaster(
			EntityManagerFactoryBuilder builder) {
		return new JpaTransactionManager(entityManagerFactoryYmuMaster(builder)
				.getObject());
	}

}