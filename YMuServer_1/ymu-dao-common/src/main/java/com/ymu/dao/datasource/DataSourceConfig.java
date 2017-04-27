package com.ymu.dao.datasource;

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
     * ymu_master主库数据源，默认的tomcat-jdbc连接池
     *
     * @return
     */
    @Bean(name = "ymuMasterDataSource")
    @Qualifier("ymuMasterDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.ymuMaster")
    public DataSource ymuMasterDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * ymu_slave从库数据源
     *
     * @return
     */
    @Bean(name = "ymuSlaveDataSource")
    @Qualifier("ymuSlaveDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.ymuSlave")
    public DataSource ymuSlaveDataSource() {
        return DataSourceBuilder.create().build();
    }


    /**
     * @Primary 该注解表示在同一个接口有多个实现类可以注入的时候，默认选择哪一个，而不是让@autowire注解报错
     * @Qualifier 根据名称进行注入，通常是在具有相同的多个类型的实例的一个注入（例如有多个DataSource类型的实例）
     */
    @Bean
    @Primary
    public DynamicDataSource dataSource(@Qualifier("ymuMasterDataSource") DataSource ymuMasterDataSource,
                                        @Qualifier("ymuSlaveDataSource") DataSource ymuSlaveDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DatabaseType.ymuMaster, ymuMasterDataSource);
        targetDataSources.put(DatabaseType.ymuSlave, ymuSlaveDataSource);

        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSources);// 该方法是AbstractRoutingDataSource的方法
        dataSource.setDefaultTargetDataSource(ymuMasterDataSource);// 默认的datasource设置为ymuMasterDataSource
        return dataSource;
    }

}
