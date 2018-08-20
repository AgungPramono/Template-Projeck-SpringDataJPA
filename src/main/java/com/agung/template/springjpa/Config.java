/*
 *  Config.java
 *  Template-Projeck-SpringDataJpa
 *
 *  Created by Agung Pramono on 19/08/2018
 *  Copyright (c) 2018 Java Development. All rights reserved.
 */
package com.agung.template.springjpa;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author agung
 */
@Configuration
@PropertySource("classpath:jdbc.properties")
@EnableJpaRepositories(basePackages = {"com.agung.template.springjpa.repository"})
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.agung.template.springjpa"})
public class Config {

    @Autowired
    private Environment env;

    @Value("${jdbc.driver}")
    private String driver;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;

    @Value("${hibernate.dialect}")
    private String dialect;
    @Value("${hibernate.hbm2ddl.auto}")
    private String hibernateHbm2Ddl;
    @Value("${hibernate.show_sql}")
    private String showSql;
    @Value("${hibernate.format_sql}")
    private String formatSql;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource());
        entityManagerFactory.setPackagesToScan(new String[]{"com.agung.template.springjpa.entity"});
        entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        entityManagerFactory.setJpaProperties(jpaProperties());
        return entityManagerFactory;
    }

    @Bean
    public DataSource dataSource() {
        System.out.println("Menginstankan datasource");
        HikariConfig datasourceConfig = new HikariConfig();
        datasourceConfig.setDriverClassName(env.getProperty("jdbc.driver"));
        datasourceConfig.setJdbcUrl(env.getProperty("jdbc.url"));
        datasourceConfig.setUsername(env.getProperty("jdbc.username"));
        datasourceConfig.setPassword(env.getProperty("jdbc.password"));

        return new HikariDataSource(datasourceConfig);
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    public Properties jpaProperties() {
        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        jpaProperties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        jpaProperties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        jpaProperties.put("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
        return jpaProperties;
    }

}
