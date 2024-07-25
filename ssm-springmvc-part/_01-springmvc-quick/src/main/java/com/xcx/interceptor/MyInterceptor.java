package com.xcx.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 拦截器
 */
public class MyInterceptor implements HandlerInterceptor {

    /**
     * 执行handler之前调用的拦截方法
     * @param request 请求对象
     * @param response 响应对象
     * @param handler 调用的方法对象
     * @return true: 放行, false: 拦截
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("request = " + request + ", response = " + response + ", handler = " + handler);
        return true;
    }

    /**
     * 执行handler之后调用的拦截方法
     * @param request 请求对象
     * @param response 响应对象
     * @param handler 调用的方法对象
     * @param modelAndView handler返回的视图和共享域数据对象
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("request = " + request + ", response = " + response + ", handler = " + handler + ", modelAndView = " + modelAndView);
    }

    /**
     * 整体执行完之后调用的拦截方法
     * @param request 请求对象
     * @param response 响应对象
     * @param handler 调用的方法对象
     * @param ex handler报错的异常对象
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("request = " + request + ", response = " + response + ", handler = " + handler + ", ex = " + ex);
    }
}
