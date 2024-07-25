package com.xcx;


import org.springframework.stereotype.Component;

/**
 * 实现计算接口,单纯添加 + - * / 实现! 掺杂其他功能!
 */
@Component
public class CalculatorPureImpl implements Calculator {
    
    @Override
    public int add(int i, int j) {
        System.out.println("add...");
        return i + j;
    }
    
    @Override
    public int sub(int i, int j) {
        System.out.println("sub...");
        return i - j;
    }
    
    @Override
    public int mul(int i, int j) {
        System.out.println("mul...");
        return i * j;
    }
    
    @Override
    public int div(int i, int j) {
        System.out.println("div...");
        return i / j;
    }
}