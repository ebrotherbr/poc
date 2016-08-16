package br.com.ebrother.poc.config;

import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@ConfigurationProperties(prefix = "jpa")
public class JPAConfiguration {

	private String[] packagesToScan;
	private String driveClassName;
	private String url;
	private String username;
	private String password;
	private String hibernateHbm2ddlAuto;
	private String hibernateDialect;
	private String hibernateShowSql;
	private String hibernateFormatSql;

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(this.dataSource());
		em.setPackagesToScan(this.packagesToScan);
		final JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(this.additionalProperties());
		return em;
	}

	@Bean
	public DataSource dataSource() {
		final DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(this.driveClassName);
		dataSource.setUrl(this.url);
		dataSource.setUsername(this.username);
		dataSource.setPassword(this.password);
		return dataSource;
	}

	@Bean
	public PlatformTransactionManager transactionManager(final EntityManagerFactory emf) {
		final JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);
		return transactionManager;
	}

	@Bean
	public EntityManager entityManager(final EntityManagerFactory entityManagerFactory) {
		return entityManagerFactory.createEntityManager();
	}

	private Properties additionalProperties() {
		final Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", this.hibernateHbm2ddlAuto);
		properties.setProperty("hibernate.dialect", this.hibernateDialect);
		properties.setProperty("hibernate.show_sql", this.hibernateShowSql);
		properties.setProperty("hibernate.format_sql", this.hibernateFormatSql);
		return properties;
	}

	public String getDriveClassName() {
		return this.driveClassName;
	}

	public void setDriveClassName(final String driveClassName) {
		this.driveClassName = driveClassName;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(final String url) {
		this.url = url;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public String getHibernateHbm2ddlAuto() {
		return this.hibernateHbm2ddlAuto;
	}

	public void setHibernateHbm2ddlAuto(final String hibernateHbm2ddlAuto) {
		this.hibernateHbm2ddlAuto = hibernateHbm2ddlAuto;
	}

	public String getHibernateDialect() {
		return this.hibernateDialect;
	}

	public void setHibernateDialect(final String hibernateDialect) {
		this.hibernateDialect = hibernateDialect;
	}

	public String getHibernateShowSql() {
		return this.hibernateShowSql;
	}

	public void setHibernateShowSql(final String hibernateShowSql) {
		this.hibernateShowSql = hibernateShowSql;
	}

	public String getHibernateFormatSql() {
		return this.hibernateFormatSql;
	}

	public void setHibernateFormatSql(final String hibernateFormatSql) {
		this.hibernateFormatSql = hibernateFormatSql;
	}

	public String[] getPackagesToScan() {
		return this.packagesToScan;
	}

	public void setPackagesToScan(final String[] packagesToScan) {
		this.packagesToScan = packagesToScan;
	}

}
