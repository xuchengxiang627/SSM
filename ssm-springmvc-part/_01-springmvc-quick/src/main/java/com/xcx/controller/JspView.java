package com.xcx.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/jsp")
public class JspView {

    /**
     * TODO: 快速查找视图
     *   1. 返回值为字符串类型
     *   2. 不能添加@ResponseBody注解
     */
    @GetMapping("data")    // 通过浏览器访问 http://localhost:8080/jsp/data 访问 /WEB-INF/views/index.jsp
    public String data(HttpServletRequest request) {
        request.setAttribute("msg", "Hello SpringMVC");
        return "/index";
    }

    /**
     * TODO: 转发  只能是项目下的资源
     *     1. 返回值为字符串类型
     *     2. 不能添加@ResponseBody注解
     *     3. 返回的字符串前 forward:/转发地址
     */
    @GetMapping("forward")
    public String forward(HttpServletRequest request) {
        return "forward:/jsp/data";
    }

    /**
     * TODO: 重定向  可以是项目外的资源，因此项目下的地址可以与转发一样，但项目外的地址必须写全
     *     1. 返回值为字符串类型
     *     2. 不能添加@ResponseBody注解
     *     3. 返回的字符串前 redirect:/重定向地址
     */
    @GetMapping("redirect")
    public String redirect() {
        return "redirect:/jsp/data";
    }
    @GetMapping("redirect/baidu")
    public String redirectBaidu() {
        return "redirect:https://www.baidu.com/";
    }


}
