package com.xcx.interceptors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xcx.utils.JwtHelper;
import com.xcx.utils.Result;
import com.xcx.utils.ResultCodeEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 登录保护拦截器
 */
@Component
public class LoginProtectedInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        // System.out.println(jwtHelper);
        if (token != null && !jwtHelper.isExpiration(token)) {
            return true;
        }
        Result result = Result.build(null, ResultCodeEnum.NOTLOGIN);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(result);
        response.getWriter().write(json);
        return false;
    }
}
