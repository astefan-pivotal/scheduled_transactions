package com.foo.bar;

import java.io.IOException;
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
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan({ "com.foo.bar" })
@PropertySource("classpath:target-h2.properties")
@EnableTransactionManagement
@EnableScheduling
public class PersistenceConfig {

    @Autowired
    Environment env;

    @Scheduled(fixedRate = 5000)
    public void run() {
        userRatingManager().updateAllUsers();
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource(
        		env.getProperty("connection.url"), 
        		env.getProperty("connection.username"), 
        		env.getProperty("connection.password"));
        driverManagerDataSource.setDriverClassName("org.h2.Driver");
        return driverManagerDataSource;
    }

    public PersistenceConfig() {
        super();
    }

    @Bean
    public UserRatingManager userRatingManager() {
        return new DefaultUserRatingManager();
    }

    @Bean
    public LocalSessionFactoryBean runnableSessionFactory() {
        LocalSessionFactoryBean factoryBean = null;
        try {
            factoryBean = createBaseSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return factoryBean;
    }


    private LocalSessionFactoryBean createBaseSessionFactory() throws IOException {
        LocalSessionFactoryBean factoryBean;
        factoryBean = new LocalSessionFactoryBean();
        Properties pp = new Properties();
        pp.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        pp.setProperty("hibernate.max_fetch_depth", "3");
        pp.setProperty("hibernate.show_sql", "false");
        pp.setProperty("hibernate.hbm2ddl.auto", "create");
        
        factoryBean.setDataSource(dataSource());
        factoryBean.setPackagesToScan(new String[] { "com.foo.bar" });
        factoryBean.setHibernateProperties(pp);
        //factoryBean.afterPropertiesSet();
        return factoryBean;
    }

//    @Bean(name = "txName")
//    public HibernateTransactionManager runnableTransactionManager() {
//        HibernateTransactionManager htm = new HibernateTransactionManager(runnableSessionFactory().getObject());
//        return htm;
//    }
    
    @Bean(name = "txName")
    @Autowired
    public HibernateTransactionManager runnableTransactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager htm = new HibernateTransactionManager();
        htm.setSessionFactory(sessionFactory);
        return htm;
    }
}
