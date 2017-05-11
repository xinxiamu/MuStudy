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
    @Bean(name = "muMasterDataSource")
    @Qualifier("muMasterDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mu-master")
    @Primary
    public DataSource test1DataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * test2从库数据源
     *
     * @return
     */
    @Bean(name = "muSlaveDataSource")
    @Qualifier("muSlaveDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mu-slave")
    public DataSource test2DataSource() {
        return DataSourceBuilder.create().build();
    }
    
    /**
     * 动态数据源: 通过AOP在不同数据源之间动态切换
     * @return
     */
    @Bean(name = "dynamicDB")
    public DataSource dataSource(@Qualifier("muMasterDataSource") DataSource muMasterDataSource,
            @Qualifier("muSlaveDataSource") DataSource muSlaveDataSource) {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        // 默认数据源
        dynamicDataSource.setDefaultTargetDataSource(muMasterDataSource);

        // 配置多数据源
        Map<Object, Object> dsMap = new HashMap<>(5);
        dsMap.put("mu-master", muMasterDataSource);
        dsMap.put("mu-slave", muSlaveDataSource);

        dynamicDataSource.setTargetDataSources(dsMap);

        return dynamicDataSource;
    }

}
