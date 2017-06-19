package com.example.ymu;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 数据源。
 */
@Configuration
public class DataSourceConfig {

    @Bean(name = "testDbDataSource")
    @Qualifier("testDbDataSource")
    @ConfigurationProperties(prefix="spring.datasource.testDb")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

}
