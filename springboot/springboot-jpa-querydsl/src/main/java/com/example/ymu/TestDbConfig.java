package com.example.ymu;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.example.ymu.dao.base.BaseRepositoryFactoryBean;

import net.sf.log4jdbc.sql.jdbcapi.DataSourceSpy;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactoryTestDb", transactionManagerRef = "transactionManagerTestDb", basePackages = {
		Constants.TEST_DB_PACKAGE_PATH, Constants.TEST_DB_PACKAGE_PATH_1,
		Constants.TEST_DB_PACKAGE_DEMO }, repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class) // 设置Repository所在位置
public class TestDbConfig {

	@Autowired
	Environment environment;

	@Autowired
	@Qualifier("testDbDataSource")
	private DataSource testDbDataSource; // 数据源

	public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
		return entityManagerFactoryTestDb(builder).getObject().createEntityManager();
	}

	@Bean(name = "entityManagerFactoryTestDb")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryTestDb(EntityManagerFactoryBuilder builder) {
		if (environment.acceptsProfiles("dev") || environment.acceptsProfiles("test")
				|| environment.acceptsProfiles("update")) {// log4jdbc打印sql日志
			testDbDataSource = new DataSourceSpy(testDbDataSource);
		}
		return builder.dataSource(testDbDataSource).properties(getVendorProperties(testDbDataSource))
				.packages(Constants.TEST_DB_PACKAGE_PATH, Constants.TEST_DB_PACKAGE_DEMO) // 设置实体类所在位置
				.persistenceUnit("testDbPersistenceUnit").build();
	}

	@Autowired
	private JpaProperties jpaProperties;

	private Map<String, String> getVendorProperties(DataSource dataSource) {
		return jpaProperties.getHibernateProperties(dataSource);
	}

	@Primary
	@Bean(name = "transactionManagerTestDb")
	public PlatformTransactionManager transactionManagerTestDb(EntityManagerFactoryBuilder builder) {
		return new JpaTransactionManager(entityManagerFactoryTestDb(builder).getObject());
	}

}