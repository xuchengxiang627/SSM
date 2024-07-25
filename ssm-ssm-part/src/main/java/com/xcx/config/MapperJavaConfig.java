package com.xcx.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;

/**
 * 持久层配置
 *      1. 连接池
 *      2. sqlSessionFactory
 *      3. Mapper代理对象
 */

@Configuration
public class MapperJavaConfig {

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        Resource resource = new ClassPathResource("mybatis-config.xml");
        sqlSessionFactoryBean.setConfigLocation(resource);
        return sqlSessionFactoryBean;
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        // Mapper代理对象的FactoryBean -> 指定一个包 -> mapper接口 -> sqlSessionFactory -> sqlSession -> getMapper -> mapper代理对象 -> ioc
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.xcx.mapper");  // mapper接口和mapper.xml所在的共同包
        return mapperScannerConfigurer;
    }
}
