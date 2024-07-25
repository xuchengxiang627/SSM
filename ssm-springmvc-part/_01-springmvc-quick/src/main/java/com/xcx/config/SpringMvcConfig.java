package com.xcx.config;// TODO: SpringMVC对应组件的配置类 [声明SpringMVC需要的组件信息]

import com.xcx.interceptor.MyInterceptor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

// TODO: 导入handlerMapping和handlerAdapter的三种方式
 // 1.自动导入handlerMapping和handlerAdapter [推荐]
 // 2.可以不添加,springmvc会检查是否配置handlerMapping和handlerAdapter,没有配置默认加载
 // 3.使用@Bean方式配置handlerMapper和handlerAdapter
@EnableWebMvc  // handlerAdapter配置了json ，自动添加handlerAdapter和handlerMapping
@Configuration
@ComponentScan(basePackages = "com.xcx.controller") // TODO: 进行controller扫描
// WebMvcConfigurer springMvc进行组件配置的规范,配置组件,提供各种方法! 前期可以实现
public class SpringMvcConfig implements WebMvcConfigurer {
    // 加了@EnableWebMvc以下可不写
    // @Bean
    // public HandlerMapping handlerMapping(){
    //     return new RequestMappingHandlerMapping();
    // }
    //
    // @Bean
    // public HandlerAdapter handlerAdapter(){
    //     return new RequestMappingHandlerAdapter();
    // }


    // TODO: 配置视图解析器
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        // 使用registry.jsp()快速添加前后缀
        registry.jsp("/WEB-INF/views", ".jsp");
    }

    // TODO: 开启静态资源查找
    // 若handlerMapping中没有对应的handler,则找是否有静态资源
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    // TODO: 配置拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor());  // 拦截全部请求

        // registry.addInterceptor(new MyInterceptor()).addPathPatterns("/user/**"); // 拦截/user/**
        // registry.addInterceptor(new MyInterceptor()).addPathPatterns("/user/**").excludePathPatterns("/user/login"); // 拦截/user/**,排除/user/login
    }
}
