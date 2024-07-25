package com.xcx;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Java配置类，替代xml文件
 * 1. 包扫描注解配置
 * 2. 引用外部的配置文件
 * 3. 声明第三方依赖的bean
 *
 * 步骤：
 * 1. 创建配置类，使用@Configuration注解
 * 2. 实现上面三个功能注解
 */

@Import(JavaConfigurationA.class)  // 将JavaConfigurationA类的配置整合到当前配置类中
@Configuration
@ComponentScan("com.xcx")
@PropertySource("classpath:jdbc.properties")
public class JavaConfiguration {
    @Value("${atguigu.url}")
    private String url;
    @Value("${atguigu.driver}")
    private String driver;
    @Value("${atguigu.username}")
    private String username;
    @Value("${atguigu.password}")
    private String password;

    // 配置第三方bean 使用@Bean注解
    // 方法返回值类型 -> bean组件的类型或者他的接口和父类
    // 方法名 -> bean组件的id
    // @Bean name属性 -> bean组件的id，可以覆盖方法名
    //      initMethod -> 初始化方法
    //      destroyMethod -> 销毁方法
    // @Scope -> bean组件的作用域
    @Bean
    public DruidDataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driver);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        // 引用其他bean
        // 1. 若其他组件也是@Bean注解，则直接调用
        jdbcTemplate.setDataSource(dataSource());
        return jdbcTemplate;
    }

    @Bean
    public JdbcTemplate jdbcTemplate1(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        // 引用其他bean
        // 2. 形参列表声明想要的组件类型，可以是一个或多个
        // 若对应组件有多个，则依照属性名作为组件id选择组件
        jdbcTemplate.setDataSource(dataSource);
        return jdbcTemplate;
    }


}
