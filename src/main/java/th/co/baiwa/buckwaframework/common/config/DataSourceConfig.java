package th.co.baiwa.buckwaframework.common.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.repository.support.CommonSimpleJpaRepository;

@Configuration
@EnableJpaRepositories(
	basePackages = {
		"th.co.baiwa.buckwaframework",
		"th.go.excise.ims"
	},
	repositoryBaseClass = CommonSimpleJpaRepository.class
)
@EnableTransactionManagement
public class DataSourceConfig {
	
	@Autowired
	private DataSource dataSource;
	
	@Bean(name = "commonJdbcTemplate")
	public CommonJdbcTemplate CommonJdbcTemplate() {
		return new CommonJdbcTemplate(dataSource);
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan(
			"th.co.baiwa.buckwaframework",
			"th.go.excise.ims"
		);
		factory.setDataSource(dataSource);
		
		return factory;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(emf);
		return txManager;
	}
	
}
