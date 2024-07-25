package com.xcx.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * 控制层配置类  controller + springmvc配置
 *      1. controller
 *      2. 全局异常处理器
 *      3. handlerAdapter、handlerMapping
 *      4. 静态资源处理
 *      5. jsp 视图解析器前后缀
 *      6. json转化器
 *      7. 拦截器
 *
 */

@Configuration
@ComponentScan(basePackages = {"com.xcx.controller", "com.xcx.exceptionhandler"}) // 1 2
@EnableWebMvc // 3 6
public class WebMvcJavaConfig implements WebMvcConfigurer {

    // 4
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    // 5
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/views", ".jsp");
    }

    // 7
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/login");
    }
}
