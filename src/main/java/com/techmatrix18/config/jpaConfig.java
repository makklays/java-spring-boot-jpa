package com.techmatrix18.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class jpaConfig {

    /*@Bean(name = "h2DataSource")
    public DataSource h2DataSource()
    {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.h2.Driver");
        dataSourceBuilder.url("jdbc:h2:file:home/alexander/h2-test");
        dataSourceBuilder.username("");
        dataSourceBuilder.password("");
        return dataSourceBuilder.build();
    }*/

    @Bean(name = "mysqlDataSource")
    @Primary
    public DataSource mysqlDataSource()
    {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url("jdbc:mysql://localhost:3306/currency_rest");
        dataSourceBuilder.username("admin");
        dataSourceBuilder.password("admin");
        return dataSourceBuilder.build();
    }

    /*@Bean
    public DataSource todosDataSource() {
        return mysqlDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

    @Bean
    public DataSource topicsDataSource() {
        return h2DataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }*/
}

