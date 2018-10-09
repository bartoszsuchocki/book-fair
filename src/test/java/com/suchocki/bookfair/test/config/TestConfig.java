package com.suchocki.bookfair.test.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = { "com.suchocki.bookfair.dao", "com.suchocki.bookfair.entity" })
@PropertySource({ "classpath:testDB.properties" })
public class TestConfig {

	@Autowired
	private Environment environment;

	@Bean
	public DataSource dataSource() {

		DriverManagerDataSource ds = new DriverManagerDataSource();

		ds.setDriverClassName(environment.getProperty("jdbc.driver"));
		ds.setUrl(environment.getProperty("jdbc.url"));
		ds.setUsername(environment.getProperty("jdbc.user"));
		ds.setPassword(environment.getProperty("jdbc.password"));

		return ds;

	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(environment.getProperty("hibernate.packageToScan"));
		sessionFactory.setHibernateProperties(getHibernateProps());

		return sessionFactory;
	}

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		return transactionManager;
	}

	protected Properties getHibernateProps() {
		Properties hibernateProperties = new Properties();

		hibernateProperties.setProperty("hibernate.dialect", environment.getProperty("hibernate.dialect"));
		hibernateProperties.setProperty("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));

		return hibernateProperties;
	}

	protected int getIntProperty(String propertyName) {
		return Integer.parseInt(environment.getProperty(propertyName));
	}

}
