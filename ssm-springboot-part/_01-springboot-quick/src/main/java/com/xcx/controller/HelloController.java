package com.xcx.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello")
public class HelloController {
    @RequestMapping("boot")
    public String hello(){
        return "hello springboot";
    }
}
