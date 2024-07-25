package com.xcx.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.logging.slf4j.Slf4jImpl;
import org.apache.ibatis.session.AutoMappingBehavior;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * 持久层配置
 *      1. 连接池
 *      2. sqlSessionFactory
 *      3. Mapper代理对象
 *
 * 不使用mybatis-config.xml
 */

@Configuration
@PropertySource("classpath:jdbc.properties")
public class MapperJavaConfigNew {

    @Value("${jdbc.user}")
    private String user;
    @Value("${jdbc.password}")
    private String password;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.driver}")
    private String driver;

    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setPassword(password);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        return dataSource;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);

        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        // <!--自动将数据库中下划线转换为驼峰命名法,但只能映射一层结构，多层建议在Mapper.xml中使用resultMap-->
        // <setting name="mapUnderscoreToCamelCase" value="true"/>
        configuration.setMapUnderscoreToCamelCase(true);
        // <!--&lt;!&ndash;开启Mybatis的日志输出&ndash;&gt;-->
        // <setting name="logImpl" value="SLF4j"/>
        configuration.setLogImpl(Slf4jImpl.class);
        // <!--自动映射resultMap中的属性 此时只用写resultMap中的 id association collection-->
        // <setting name="autoMappingBehavior" value="FULL"/>
        configuration.setAutoMappingBehavior(AutoMappingBehavior.FULL);

        sqlSessionFactoryBean.setConfiguration(configuration);

        // 起别名
        // <typeAliases>
        //     <package name="com.xcx.pojo"/>
        // </typeAliases>
        sqlSessionFactoryBean.setTypeAliasesPackage("com.xcx.pojo");

        // <!--分页插件-->
        // <plugins>
        //     <plugin interceptor="com.github.pagehelper.PageInterceptor">
        //         <property name="helperDialect" value="mysql"/>
        //     </plugin>
        // </plugins>
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("helperDialect", "mysql");
        pageInterceptor.setProperties(properties);
        sqlSessionFactoryBean.addPlugins(pageInterceptor);

        return sqlSessionFactoryBean;
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        // Mapper代理对象的FactoryBean -> 指定一个包 -> mapper接口 -> sqlSessionFactory -> sqlSession -> getMapper -> mapper代理对象 -> ioc
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.xcx.mapper");  // mapper接口和mapper.xml所在的共同包
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");
        return mapperScannerConfigurer;
    }
}
