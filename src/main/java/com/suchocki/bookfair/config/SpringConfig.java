package com.suchocki.bookfair.config;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages= {"com.suchocki.bookfair"})
@EnableTransactionManagement
@PropertySource({ "classpath:hibernate-props.properties", "classpath:spring-security-props.properties" })
public class SpringConfig implements WebMvcConfigurer {

	@Autowired
	private Environment environment;

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Bean
	public DataSource dataSource() {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();

		try {
			dataSource.setDriverClass(environment.getProperty("jdbc.driver"));
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		dataSource.setJdbcUrl(environment.getProperty("jdbc.url"));
		dataSource.setUser(environment.getProperty("jdbc.user"));
		dataSource.setPassword(environment.getProperty("jdbc.password"));

		dataSource.setInitialPoolSize(getIntProperty("connectionPool.initialPoolSize"));
		dataSource.setMaxPoolSize(getIntProperty("connectionPool.maxPoolSize"));
		dataSource.setMinPoolSize(getIntProperty("connectionPool.minPoolSize"));
		dataSource.setMaxIdleTime(getIntProperty("connectionPool.maxIdleTime"));

		return dataSource;
	}

	@Bean
	public DataSource securityDataSource() {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();

		try {
			dataSource.setDriverClass(environment.getProperty("security.jdbc.driver"));
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		dataSource.setJdbcUrl(environment.getProperty("security.jdbc.url"));
		dataSource.setUser(environment.getProperty("security.jdbc.user"));
		dataSource.setPassword(environment.getProperty("security.jdbc.password"));

		dataSource.setInitialPoolSize(getIntProperty("security.connectionPool.initialPoolSize"));
		dataSource.setMaxPoolSize(getIntProperty("security.connectionPool.maxPoolSize"));
		dataSource.setMinPoolSize(getIntProperty("security.connectionPool.minPoolSize"));
		dataSource.setMaxIdleTime(getIntProperty("security.connectionPool.maxIdleTime"));

		return dataSource;
	}

	private Properties getHibernateProps() {
		Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.dialect", environment.getProperty("hibernate.dialect"));
		hibernateProperties.setProperty("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));

		return hibernateProperties;
	}

	private int getIntProperty(String propertyName) {
		return Integer.parseInt(environment.getProperty(propertyName));
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

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) { //poczytaæ jeszcze do czego to s³u¿y
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

}
