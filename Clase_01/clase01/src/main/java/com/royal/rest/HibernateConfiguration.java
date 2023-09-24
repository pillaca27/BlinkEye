package com.royal.rest;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@Configuration
public class HibernateConfiguration {

	private static Logger logger = LogManager.getLogger(HibernateConfiguration.class);

	@Value("${spring.datasource.driver-class-name}")
	private String DB_DRIVER;

	@Value("${spring.datasource.password}")
	private String DB_PASSWORD;

	@Value("${spring.datasource.url}")
	private String DB_URL;

	@Value("${spring.datasource.username}")
	private String DB_USERNAME;

	@Value("${spring.datasource.dialect}")
	private String HIBERNATE_DIALECT;

	@Value("${spring.datasource.show_sql}")
	private String HIBERNATE_SHOW_SQL;

	@Value("${spring.datasource.packagesToScan}")
	private String ENTITYMANAGER_PACKAGES_TO_SCAN;

	@Autowired
	private ResourceLoader rl;

	@Bean(value = "sessionFactory")
	public LocalSessionFactoryBean getSessionFactory() {

		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(ENTITYMANAGER_PACKAGES_TO_SCAN);
		sessionFactory.setMappingLocations(loadResources());
		Properties hibernateProperties = new Properties();
		hibernateProperties.put("hibernate.dialect", HIBERNATE_DIALECT);
		hibernateProperties.put("hibernate.show_sql", HIBERNATE_SHOW_SQL);
		hibernateProperties.put("hibernate.jdbc.lob.non_contextual_creation", "true");

		sessionFactory.setHibernateProperties(hibernateProperties);
		return sessionFactory;
	}

	public Resource[] loadResources() {
		Resource[] resources = null;
		try {
			resources = ResourcePatternUtils.getResourcePatternResolver(rl)
					.getResources("classpath*:**/hibernate/**/*.hbm.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resources;
	}

	@Bean
	public DataSource dataSource() {
		logger.debug("dataSource");
		logger.debug(DB_DRIVER);
		logger.debug(DB_URL);
		logger.debug(DB_USERNAME);
		logger.debug(DB_PASSWORD);

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(DB_DRIVER);
		dataSource.setUrl(DB_URL);
		dataSource.setUsername(DB_USERNAME);
		dataSource.setPassword(DB_PASSWORD);
		return dataSource;
	}

	@Bean
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(getSessionFactory().getObject());
		return txManager;
	}

	@Bean
	public HibernateTemplate hibernateTemplate(SessionFactory sessionFactory) {
		return new HibernateTemplate(sessionFactory);
	}

}