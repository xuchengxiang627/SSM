package com.xcx.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 接收cookie
 */
@Controller
@RequestMapping("/cookie")
@ResponseBody
public class CookieController {
    @RequestMapping("/data")
    public String data(@CookieValue(value = "cookieName") String value) {
        System.out.println("cookie value: " + value);
        return value;
    }

    @RequestMapping("/save")
    public String save(HttpServletResponse response) {
        Cookie cookie = new Cookie("cookieName", "xxxxxxyyyyy");
        response.addCookie(cookie);
        return "ok";
    }
}
