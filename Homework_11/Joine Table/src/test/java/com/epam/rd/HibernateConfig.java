package com.epam.rd;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "com.epam.rd")
@EnableTransactionManagement
public class HibernateConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/bank_joined_table?serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("sqlpas");

        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() throws SQLException {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.epam.rd");
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }

    @Bean
    public PlatformTransactionManager hibernateTransactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);

        return transactionManager;
    }

    private Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update"); // update create
        hibernateProperties.setProperty("hibernate.show_sql", "true");
        hibernateProperties.setProperty("hibernate.format_sql", "true");
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        return hibernateProperties;
    }
}
