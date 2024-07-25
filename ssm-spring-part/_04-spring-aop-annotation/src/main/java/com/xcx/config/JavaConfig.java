package com.xcx.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.xcx") // advice和组件都要扫描
@EnableAspectJAutoProxy // 开启aspect注解功能, 等同于xml中<aop:aspectj-autoproxy/>
public class JavaConfig {
}
