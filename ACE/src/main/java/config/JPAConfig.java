package config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@PropertySource(value = "classpath:datasource.properties")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = { "repository" })
@Configuration
public class JPAConfig {
	private final Logger log = Logger.getLogger(this.getClass());

	private static final String PROPERTY_NAME_DATABASE_DRIVER = "db.driver";
	private static final String PROPERTY_NAME_DATABASE_PASSWORD = "db.password";
	private static final String PROPERTY_NAME_SERVERNAME = "db.servername";
	private static final String PROPERTY_NAME_DBPORT = "db.port";
	private static final String PROPERTY_NAME_DATABASENAME = "db.databasename";
	private static final String PROPERTY_NAME_DATABASE_USERNAME = "db.username";
	private static final String PROPERTY_NAME_MAXPOOLSIZE = "cp.maximumPoolSize";
	private static final String PROPERTY_NAME_MINIDLESIZE = "cp.minimumIdle";

	private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
	private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
	private static final String HIBERNATE_CONNECTION_RELEASE_MODE = "hibernate.connection.release_mode";
	private static final String HIBERNATE_FORMAT_SQL = "hibernate.format_sql";

	@Autowired
	private Environment env;

	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		HikariConfig config = new HikariConfig();
		config.setDataSourceClassName(env.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
		config.setUsername(env.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));
		config.setPassword(env.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));
		config.setMaximumPoolSize(Integer.valueOf(env.getRequiredProperty(PROPERTY_NAME_MAXPOOLSIZE)));
		config.setMinimumIdle(Integer.valueOf(env.getRequiredProperty(PROPERTY_NAME_MINIDLESIZE)));
		config.addDataSourceProperty("serverName", env.getRequiredProperty(PROPERTY_NAME_SERVERNAME));
		config.addDataSourceProperty("port", env.getRequiredProperty(PROPERTY_NAME_DBPORT));
		config.addDataSourceProperty("databaseName", env.getRequiredProperty(PROPERTY_NAME_DATABASENAME));
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
		HikariDataSource ds = new HikariDataSource(config);
		log.debug("HikariCP Datasource Executed");
		return ds;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource());
		entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		entityManagerFactoryBean.setPackagesToScan("models");
		entityManagerFactoryBean.setJpaProperties(hibernateProperties());
		log.debug("Entity Manager Factory Executed");
		return entityManagerFactoryBean;
	}

	@Bean
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		log.debug("JPA Transaction Manager Executed");
		return transactionManager;
	}

	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put(HIBERNATE_CONNECTION_RELEASE_MODE, env.getRequiredProperty(HIBERNATE_CONNECTION_RELEASE_MODE));
		properties.put(PROPERTY_NAME_HIBERNATE_DIALECT, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
		properties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
		properties.put(HIBERNATE_FORMAT_SQL, env.getRequiredProperty(HIBERNATE_FORMAT_SQL));
		log.debug("Hibernate Properties Created");
		return properties;
	}

}
