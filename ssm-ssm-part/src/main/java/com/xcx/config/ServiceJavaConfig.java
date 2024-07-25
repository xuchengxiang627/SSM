package com.xcx.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * 业务层配置
 *      1. service
 *      2. aop注解支持 aspectj: @Before @After @Around @AfterReturning @AfterThrowing
 *      3. 事务管理 tx: 1) @Transactional  2) 对应的事务管理器实现
 */

@Configuration
@ComponentScan(basePackages = "com.xcx.service") // 1
@EnableAspectJAutoProxy // 2
@EnableTransactionManagement // 3
public class ServiceJavaConfig {

    // 事务管理器实现
    @Bean
    public TransactionManager transactionManager(DataSource dataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }
}
