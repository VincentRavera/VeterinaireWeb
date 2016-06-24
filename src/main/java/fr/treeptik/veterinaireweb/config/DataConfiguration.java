package fr.treeptik.veterinaireweb.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "fr.treeptik.veterinaireweb.dao", entityManagerFactoryRef = "entityManagerFactory")
public class DataConfiguration {
	private Logger logger = LoggerFactory.getLogger(DataConfiguration.class);

	@Autowired
	private Environment environment;
	// autre façon de récupérer les properties mais on doit obligatoirement
	// déclarer un PropertySourcesPlaceholderConfigurer
	@Value("${db.driverclassname}")
	private String driverClassName;

	@Bean(name = "dataSource")
	@Profile("tomcat")
	public DataSource dataSourceTomcat() {
		logger.info("chargement datasource profile tomcat");
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(environment.getProperty("db.driverclassname"));
		dataSource.setUrl(environment.getProperty("db.url"));
		dataSource.setUsername(environment.getProperty("db.username"));
		dataSource.setPassword(environment.getProperty("db.password"));

		return dataSource;
	}

	@Bean(name = "dataSource")
	@Profile("test")
	public DataSource dataSourceTest() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(environment.getProperty("db.driverclassname"));
		dataSource.setUrl("jdbc:mysql://localhost:3306/springsampletest");
		dataSource.setUsername(environment.getProperty("db.username"));
		dataSource.setPassword(environment.getProperty("db.password"));
		return dataSource;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setGenerateDdl(true);
		hibernateJpaVendorAdapter.setShowSql(true);
		hibernateJpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
		hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);

		localContainerEntityManagerFactoryBean.setJpaVendorAdapter(hibernateJpaVendorAdapter);

		localContainerEntityManagerFactoryBean.setDataSource(dataSource);

		Properties properties = new Properties();
		properties.put("hibernate.generate_statistics", true);

		localContainerEntityManagerFactoryBean.setJpaProperties(properties);
		localContainerEntityManagerFactoryBean.setPackagesToScan("fr.treeptik.veterinaireweb.model");

		return localContainerEntityManagerFactoryBean;

	}

	@Bean
	public PlatformTransactionManager transactionManager(DataSource dataSource) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory(dataSource).getObject());
		return transactionManager;
	}

}
