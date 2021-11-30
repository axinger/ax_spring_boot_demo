package com.ax.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect //生成代理对象
@Order(0) // 多个代理,设置顺序,
public class UserProxy {

    /// execution 切入点表达式
    /// 重用切入点定义,封装
    @Pointcut(value = "execution(* com.ax.aop.model.AopUser.*(..))")
    public void pointValue() {


    }
//    @Before(value = "execution(* com.ax.aop.model.AopUser.*(..))")
//    public void before(){
//        System.out.println("UserProxy.before");
//    }

    @Before(value = "pointValue()")
    public void before() {
        System.out.println("UserProxy.before");
    }

    @After(value = "pointValue()")
    public void after() {
        System.out.println("UserProxy.after");
    }

    @AfterReturning(value = "pointValue()")
    public void afterReturning() {
        System.out.println("UserProxy.afterReturning");
    }

    @AfterThrowing(value = "pointValue()")
    public void afterThrowing() {
        System.out.println("UserProxy.afterThrowing");
    }

    /// 环绕
    @Around(value = "pointValue()")
    public void around(ProceedingJoinPoint point) throws Throwable {
        System.out.println("UserProxy.around 前");

        /// 被增强方法执行
        point.proceed();

        System.out.println("UserProxy.around 后");
    }
}
