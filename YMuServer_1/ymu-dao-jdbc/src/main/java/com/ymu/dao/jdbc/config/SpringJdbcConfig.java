package com.ymu.dao.jdbc.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ymu.dao.datasource.DataSourceConfig;
import com.ymu.dao.datasource.DynamicDataSource;

/**
 * spring jdbc模板配置
 */
@Configuration
@AutoConfigureAfter(DataSourceConfig.class)
@Import(DataSourceConfig.class)
public class SpringJdbcConfig {

    /*---------------------------- spring jdbcTemplate  ------------------------------*/

    @Bean(name = "jdbcTemplate")
    @Qualifier("jdbcTemplate")
    public JdbcTemplate ymuMasterDataSource(DynamicDataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

//    @Bean(name = "ymuSlaveJdbcTemplate")
//    public JdbcTemplate ymuSlaveJdbcTemplate(
//            @Qualifier("ymuSlaveDataSource") DataSource dataSource) {
//        return new JdbcTemplate(dataSource);
//    }
}
