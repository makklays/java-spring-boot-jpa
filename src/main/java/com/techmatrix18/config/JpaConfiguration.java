package com.techmatrix18.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class JpaConfiguration {

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

    private final DataSource dataSource;

    public JpaConfiguration(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.techmatrix18.repository");
        factory.setDataSource(dataSource);
        factory.afterPropertiesSet();

        return factory.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory());
        return txManager;
    }

    @Bean(name = "sessionFactory")
    public LocalSessionFactoryBean hibernateSessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
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
    }

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
    }********/
}

