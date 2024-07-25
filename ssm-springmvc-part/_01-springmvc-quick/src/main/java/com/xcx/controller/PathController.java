package com.xcx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 路径传参
 */
@Controller
@RequestMapping("/path")
@ResponseBody
public class PathController {

    // /path/账号/密码
    // 动态路径设计 {key}相当于 *， {key}可以在形参列表获取传入的参数
    @RequestMapping("/{username}/{password}")
    public String path(@PathVariable String username, @PathVariable String password) {
        // 必须使用@PathVariable注解获取路径参数，否则会认为是param参数
        System.out.println(username + ":" + password);
        return username + ":" + password;
    }
}
