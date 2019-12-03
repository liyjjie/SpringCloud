package com.jack.utils.hibernate;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;

/**
 * @author ：liyongjie
 * @ClassName ：DatabaseConfiguration
 * @date ： 2019-11-28 09:44
 * @modified By：主从模式 必须配置 两个数据源
 */
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.jack"})
public class DatabaseConfiguration implements EnvironmentAware {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseConfiguration.class);
    static final String PACKAGE = "com.jack";
    static final String PACKAGES_TO_SCAN = "com.jack.**.domain";
    private Environment environment;
    private RelaxedPropertyResolver propertyResolver;

    //0
    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
        this.propertyResolver = new RelaxedPropertyResolver(environment, "spring.datasource.");
    }

    private DruidDataSource generateDruidDataSource(String pre) throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(this.propertyResolver.getProperty(pre + ".driver-class-name"));
        druidDataSource.setUrl(this.propertyResolver.getProperty(pre + ".url"));
        druidDataSource.setUsername(this.propertyResolver.getProperty(pre + ".username"));
        druidDataSource.setPassword(this.propertyResolver.getProperty(pre + ".password"));
        druidDataSource.setInitialSize(Integer.parseInt(this.propertyResolver.getProperty(pre + ".initialSize")));
        druidDataSource.setMinIdle(Integer.parseInt(this.propertyResolver.getProperty(pre + ".minIdle")));
        druidDataSource.setMaxActive(Integer.parseInt(this.propertyResolver.getProperty(pre + ".maxActive")));
        druidDataSource.setMaxWait((long) Integer.parseInt(this.propertyResolver.getProperty(pre + ".maxWait")));
        druidDataSource.setTimeBetweenEvictionRunsMillis(Long.parseLong(this.propertyResolver.getProperty(pre + ".timeBetweenEvictionRunsMillis")));
        druidDataSource.setMinEvictableIdleTimeMillis(Long.parseLong(this.propertyResolver.getProperty(pre + ".minEvictableIdleTimeMillis")));
        druidDataSource.setValidationQuery(this.propertyResolver.getProperty(pre + ".validationQuery"));
        druidDataSource.setTestWhileIdle(Boolean.parseBoolean(this.propertyResolver.getProperty(pre + ".testWhileIdle")));
        druidDataSource.setTestOnBorrow(Boolean.parseBoolean(this.propertyResolver.getProperty(pre + ".testOnBorrow")));
        druidDataSource.setTestOnReturn(Boolean.parseBoolean(this.propertyResolver.getProperty(pre + ".testOnReturn")));
        druidDataSource.setPoolPreparedStatements(Boolean.parseBoolean(this.propertyResolver.getProperty(pre + ".poolPreparedStatements")));
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(Integer.parseInt(this.propertyResolver.getProperty(pre + ".maxPoolPreparedStatementPerConnectionSize")));
        druidDataSource.setFilters(this.propertyResolver.getProperty(pre + ".filters"));
        Collection connectionInitSqls = new ArrayList();
        connectionInitSqls.add("set names 'utf8mb4'");
        druidDataSource.setConnectionInitSqls(connectionInitSqls);
        logger.info(String.format("DruidDataSource[%s] is initing,url[%s]", pre, this.propertyResolver.getProperty(pre + ".url")));
        return druidDataSource;
    }
    //2
    @Bean(name = "masterDataSource", initMethod = "init", destroyMethod = "close")
    public DruidDataSource masterDataSource() throws SQLException {
        return this.generateDruidDataSource("master");
    }

    @Bean(name = "slaveDataSource", initMethod = "init", destroyMethod = "close")
    public DruidDataSource slaveDataSource(@Qualifier("masterDataSource") DruidDataSource masterDataSource) throws SQLException {
        return StringUtils.isNotBlank(this.propertyResolver.getProperty("slave.url")) ? this.generateDruidDataSource("slave") : masterDataSource;
    }

    @Bean(name = "dataSource")
    @Primary
    public DynamicDataSource dynamicDataSource(@Qualifier("masterDataSource") DataSource masterDataSource, @Qualifier("slaveDataSource") javax.sql.DataSource slaveDataSource) {
        Map<Object, Object> targetDataSources = new HashMap();
        targetDataSources.put("write", masterDataSource);
        if (slaveDataSource == null) {
            targetDataSources.put("read", masterDataSource);
            logger.info("slaveDataSource not configured, use the default masterDataSource");
        } else {
            targetDataSources.put("read", slaveDataSource);
        }

        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setTargetDataSources(targetDataSources);
        dynamicDataSource.setDefaultTargetDataSource(masterDataSource);
        return dynamicDataSource;
    }

    @Bean(name = {"sessionFactory"})
    @Primary
    public LocalSessionFactoryBean localSessionFactoryBean(@Qualifier("dataSource") DataSource dataSource) {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(dataSource);
        localSessionFactoryBean.setPackagesToScan(new String[]{"com.jack.**.domain"});
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.dialect", this.environment.getProperty("hibernate.dialect"));
        hibernateProperties.setProperty("hibernate.show_sql", this.environment.getProperty("hibernate.show_sql"));
        hibernateProperties.setProperty("hibernate.format_sql", this.environment.getProperty("hibernate.format_sql"));
        localSessionFactoryBean.setHibernateProperties(hibernateProperties);
        return localSessionFactoryBean;
    }

    @Bean(name = {"hibernateTransactionManager"})
    @Primary
    public HibernateTransactionManager hibernateTransactionManager(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
        hibernateTransactionManager.setSessionFactory(sessionFactory);
        return hibernateTransactionManager;
    }

    //1
    @Bean(name = {"persistenceExceptionTranslationPostProcessor"})
    @Primary
    public PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean(name = {"sessionFactorySupport"})
    @Primary
    public SessionFactorySupport sessionFactorySupport(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        SessionFactorySupport support = new SessionFactorySupport();
        support.setSessionFactory(sessionFactory);
        return support;
    }
}

