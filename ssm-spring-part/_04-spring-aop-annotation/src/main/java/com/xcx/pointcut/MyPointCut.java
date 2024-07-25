package com.xcx.pointcut;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
public class MyPointCut {
    @Pointcut("execution(* com.xcx.CalculatorPureImpl.*(..))")
    public void My_PointCut() {
    }
}
