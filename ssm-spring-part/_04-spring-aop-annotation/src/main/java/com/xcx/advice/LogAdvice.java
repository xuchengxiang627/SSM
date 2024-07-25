package com.xcx.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 * AOP编程
 * 日志增强类
 * 用于定义增强方法,使用注解指定插入目标方法位置
 *      前置增强    @Before
 *      返回增强    @AfterReturning
 *      异常增强    @AfterThrowing
 *      后置增强    @After
 *      环绕增强    @Around
 *------------------------------------------
 *      try {
 *          前置增强
 *          目标方法
 *          返回增强
 *      } catch (Exception e) {
 *          异常增强
 *      } finally {
 *          后置增强
 *      }
 *
 */
@Component
@Aspect // 定义切面
// @Order(1) // 优先级,值越小优先级越高,优先级高的前置增强会先执行，而后置增强会后执行
public class LogAdvice {

    // 切点表达式的提取和复用
    @Pointcut("execution(* com.xcx.CalculatorPureImpl.*(..))")
    public void pointCut() {
    }
    // 此时可以用pointCut()代替切点表达式
    // 如 @Before("execution(* com.xcx.CalculatorPureImpl.*(..))")  -> @Before("pointCut()")
    // 或使用其他类中的切点表达式 @Before("com.xcx.pointcut.MyPointCut.My_PointCut()")


    // * com.xcx.CalculatorPureImpl.*(..))  切点表达式
    // 访问修饰符 返回值类型 包名.类名.方法名(参数列表)
    @Before("execution(* com.xcx.CalculatorPureImpl.*(..))")  // 定义切点
    public void start(JoinPoint joinPoint) {        // joinPoint包含目标方法名,参数列表等信息
        System.out.println("开始执行...");
        // 获取类信息
        String simpleName = joinPoint.getTarget().getClass().getSimpleName();
        System.out.println("    执行类名: " + simpleName);
        // 获取访问修饰符
        String modifier = Modifier.toString(joinPoint.getSignature().getModifiers());
        System.out.println("    访问修饰符: " + modifier);
        // 获取方法名
        String name = joinPoint.getSignature().getName();
        System.out.println("    执行方法名: " + name);
        // 获取参数列表
        Object[] args = joinPoint.getArgs();
        System.out.println("    参数列表: " + Arrays.toString(args));

    }

    @AfterReturning(value = "execution(* com.xcx.CalculatorPureImpl.*(..))", returning = "result")
    public void success(JoinPoint joinPoint, Object result) {
        System.out.println("执行成功...");
        // 获取返回值
        System.out.println("    执行结果: " + result);
    }

    @AfterThrowing(value = "com.xcx.pointcut.MyPointCut.My_PointCut()", throwing = "e")
    public void error(Throwable e) {
        System.out.println("出现异常...");
        // 获取异常信息
        System.out.println("    异常信息: " + e.getMessage());
    }

    @After("pointCut()")
    public void end() {
        System.out.println("执行结束...");
    }

    /**
     * 环绕增强 需要自己定义目标方法的执行
     * @param joinPoint 目标方法，相比于JoinPoint,多了一个proceed()方法
     * @return 目标方法的返回值
     */
    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        Object result = null;
        Object[] args = joinPoint.getArgs();
        try {
            System.out.println("环绕增强: 前置增强");
            result = joinPoint.proceed(args);
            System.out.println("环绕增强: 返回增强");
        } catch (Throwable e) {
            System.out.println("环绕增强: 异常增强");
            // 必须抛出异常
            throw new RuntimeException(e);
        }
        System.out.println("环绕增强: 后置增强");
        return result;
    }

}
