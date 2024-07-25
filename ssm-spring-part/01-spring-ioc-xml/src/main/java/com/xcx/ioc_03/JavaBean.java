package com.xcx.ioc_03;

// ioc组件生命周期
public class JavaBean {

    // 必须是 public void 无参数方法
    public void init(){
        System.out.println("初始化方法");
    }

    public void destroy(){
        System.out.println("销毁方法");
    }
}
