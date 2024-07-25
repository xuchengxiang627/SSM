package com.xcx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  // 启动类
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);  // 自动创建ioc容器，启动tomcat
        // controller 与 该 Main 在同一个包下，可以自动扫描
    }
}
