/*
--+
    | Project ACCOUNT SERVICE API - Java Class File : 1.0.0 Data: 10/06/2018
    | Copyright(c) by ProfitCode IT Solutions
    |
    | All rights reserved.
    |
    | This software is confidential and proprietary information of
    | ProfitCode IT Solutions ("Confidential Information").
    | You shall not disclose such Confidential Information and shall 
    | use it only in accordance with the terms of the license agreement
    | you entered with ProfitCode IT Solutions.
 +--
 */
package br.com.solutis.piloto.repository;

import br.com.solutis.piloto.util.AccountServiceUtil;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ProfitCode IT Solutions
 *
 * JPA Persistence configurations.
 *
 */

@Log4j2
@Configuration
@EnableJpaRepositories(basePackages = "br.com.solutis.piloto.entity")
public class Persistence {

	@Bean
    public ComboPooledDataSource dataSource() {

        ComboPooledDataSource dataSource = new ComboPooledDataSource();

        try {
            dataSource.setDriverClass(System.getenv().get("DATABASE_DRIVER_CLASS"));
        } catch (PropertyVetoException e){
        	log.error("Cannot load datasource driver (" + System.getenv().get("DATABASE_DRIVER_CLASS") +") : " + e.getMessage());
            return null;
        }
        
        dataSource.setJdbcUrl(System.getenv().get("DATABASE_URL_SERVER"));
        dataSource.setUser(AccountServiceUtil.getDataBaseUsername());
        dataSource.setPassword(AccountServiceUtil.getDataBasePassword());
        dataSource.setMinPoolSize(Integer.parseInt("10"));
        dataSource.setMaxPoolSize(Integer.parseInt("50"));
        dataSource.setMaxIdleTime(Integer.parseInt("300"));
 
        return dataSource;
    }
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory (DataSource dataSource) {
		 
	    LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
	    
	    entityManagerFactory.setDataSource(dataSource);
	    entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
	    entityManagerFactory.setJpaDialect(new HibernateJpaDialect());
	    entityManagerFactory.setPackagesToScan("br.com.solutis.piloto.entity");
	    
	    Map<String, String> props = new HashMap<String,String>();
		
		props.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		props.put("hibernate.show_sql", "true");
		props.put("hibernate.format_sql", "true");
		props.put("hibernate.jdbc.lob.non_contextual_creation", "true");
		props.put("hibernate.jdbc.wrap_result_sets", "false");
	    
	    entityManagerFactory.setJpaPropertyMap(props);
	    
	    return entityManagerFactory;
	}
	
	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		
	    JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
	    jpaTransactionManager.setEntityManagerFactory(emf);
	    return jpaTransactionManager;
	}
}
