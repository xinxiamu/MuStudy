package com.example.ymu.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 数据源。
 */
@Configuration
@AutoConfigureAfter(Config.class)
public class DataSourceConfig {
	
    @Bean(name = "testDbDataSource")
    @Qualifier("testDbDataSource")
    @ConfigurationProperties(prefix="spring.datasource.druid.testDb")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }
    
    /**
     * spring jdbc
     * @param dataSource
     * @return
     */
    @Bean(name = "jdbcTemplate")
    public JdbcTemplate primaryJdbcTemplate(
            @Qualifier("testDbDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}
