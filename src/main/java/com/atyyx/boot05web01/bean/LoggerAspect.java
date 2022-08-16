package com.atyyx.boot05web01.bean;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterAll;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
@Aspect // 声明是一个切面类
public class LoggerAspect {

    /**
     * 这里需要用全类名去表示  com.atyyx.boot05web01.bean.CalculatorImpl
     * 既然里面的四个方法都是，那么就可以用通配符 *  去匹配
     * 用(..) 去表示是两个参数的方法
     * @Before的意思就是在方法执行之前，就先执行我这个方法
     * @param joinPoint
     */
    @Before("execution(public Integer com.atyyx.boot05web01.bean.CalculatorImpl.*(..))")
    public void before(JoinPoint joinPoint)
    {
        String name = joinPoint.getSignature().getName();//获取方法名
        Object[] args = joinPoint.getArgs();// 获取参数
        // 将数组转换成字符串
        String strArgs = Arrays.toString(args);
        log.info(name+"方法的参数是"+strArgs);

    }

    /**
     * JoinPoint只能获取方法名，没有办法知道方法返回的结果
     * @After是在方法执行之后去执行的，但是无法获取返回值
     * @param joinPoint
     */
    @AfterReturning(value = "execution(public int com.atyyx.boot05web01.bean.CalculatorImpl.*(..))",returning = "result")
    public void afterReturing(JoinPoint joinPoint,Object result)
    {
        String name = joinPoint.getSignature().getName();//获取方法名
        log.info(name+"方法的结果是"+result);
    }
}
