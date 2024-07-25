package com.xcx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 获取请求头
 */
@Controller
@ResponseBody
@RequestMapping("/header")
public class HeaderController {
    @RequestMapping("/data")
    public String data(@RequestHeader("Host") String hostq) {
        System.out.println(hostq);
        return hostq;
    }
}
