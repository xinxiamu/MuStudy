package com.neo.datasource;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 配置数据源
 */
@Configuration
public class DataSourceConfig {

    /**
     * test1主库，默认的tomcat-jdbc连接池
     *
     * @return
     */
    @Bean(name = "test1DataSource")
    @Qualifier("test1DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.test1")
    @Primary
    public DataSource test1DataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * test2从库数据源
     *
     * @return
     */
    @Bean(name = "test2DataSource")
    @Qualifier("test2DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.test2")
    public DataSource test2DataSource() {
        return DataSourceBuilder.create().build();
    }
    
    /**
     * 动态数据源: 通过AOP在不同数据源之间动态切换
     * @return
     */
    @Bean(name = "dynamicDS")
    public DataSource dataSource(@Qualifier("test1DataSource") DataSource test1DataSource,
            @Qualifier("test2DataSource") DataSource test2DataSource) {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        // 默认数据源
        dynamicDataSource.setDefaultTargetDataSource(test1DataSource);

        // 配置多数据源
        Map<Object, Object> dsMap = new HashMap<>(5);
        dsMap.put("test1", test1DataSource);
        dsMap.put("test2", test2DataSource);

        dynamicDataSource.setTargetDataSources(dsMap);

        return dynamicDataSource;
    }

}
