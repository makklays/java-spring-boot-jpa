package com.techmatrix18.config;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class jpaConfig {

    @Value("${spring.datasource.driver-class-name}")
    public String driver;

    @Value("${spring.datasource.url}")
    public String url;

    @Value("${spring.datasource.username}")
    public String username;

    @Value("${spring.datasource.password}")
    public String password;

    @Value("${spring.jpa.properties.hibernate.dialect}")
    public String dialect;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    public String ddl;

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

    /*@Bean(name = "mysqlDataSource")
    @Description("This is DataSource for MySQL")
    @Primary
    public DataSource mysqlDataSource()
    {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url("jdbc:mysql://localhost:3306/currency_rest");
        dataSourceBuilder.username("admin");
        dataSourceBuilder.password("admin");
        return dataSourceBuilder.build();
    }*/

    /*****
    @Bean(name = "dataSource")
    @Description("This is DataSource for MySQL")
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }

    @Bean(name = "sessionFactory")
    public LocalSessionFactoryBean hibernateSessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[] { "com.techmatrix18.model.*" });
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    Properties hibernateProperties() {
        return new Properties() {
            {
                setProperty("hibernate.hbm2ddl.auto", ddl);
                setProperty("hibernate.connection.useUnicode", "true");
                setProperty("spring.jpa.hibernate.ddl-auto", ddl);
                setProperty("hibernate.dialect", dialect);
                setProperty("spring.jpa.properties.hibernate.dialect", dialect);
                setProperty("hibernate.globally_quoted_identifiers", "true");
                setProperty("hibernate.connection.CharSet", "utf8mb4");
                setProperty("hibernate.connection.characterEncoding", "utf8");

            }
        };
    }********/

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

