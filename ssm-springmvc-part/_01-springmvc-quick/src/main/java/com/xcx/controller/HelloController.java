package com.xcx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/hello") // 类上通用地址
public class HelloController {

    // handlers

    /**
     *  路径设置为 /product/*
     *    /* 为单层任意字符串  /product/a  /product/aaa 可以访问此handler
     *    /product/a/a 不可以
     *  路径设置为 /product/**
     *   /** 为任意层任意字符串  /product/a  /product/aaa 可以访问此handler
     *   /product/a/a 也可以访问
     *
     *   method = RequestMethod.POST 可以指定单个或者多个请求方式!
     *
     *   还有 `@RequestMapping` 的 HTTP 方法特定快捷方式变体：
     *
     * - `@GetMapping`
     * - `@PostMapping`
     * - `@PutMapping`
     * - `@DeleteMapping`
     * - `@PatchMapping`
     *
     * - @RequestMapping(value="/login",method=RequestMethod.GET) = @GetMapping(value="/login")
     */

    /**
     * handler就是controller内部的具体方法
     * @RequestMapping("/springmvc/hello") 就是用来向handlerMapping中注册的方法注解!
     * @ResponseBody 代表向浏览器直接返回数据!
     *
     */
    @RequestMapping(value = "/springmvc/hello")  // /hello/springmvc/hello  若仅有@RequestMapping则为/hello
    @ResponseBody
    public String hello(){
        System.out.println("HelloController.hello");
        return "hello springmvc!!";
    }
}
