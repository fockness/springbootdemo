package com.example.springbootdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.condition.RequestConditionHolder;

import javax.servlet.http.HttpServletRequest;

//切面类
@Aspect
@Component
public class HttpAspect {

    //定义一个日志记录对象
    private Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    //定义一个统一的切面,下面的方法都遵循这个切面
    @Pointcut("execution(public * com.example.springbootdemo.controller.GirlController.*(..))")//第一个.为有无返回值都会被拦截，第一个*表示拦截所有方法，最后的两个..表示任何参数都会被拦截下来
    public void log(){
    }

    //在执行girlList方法前执行这个方法
    @Before("log()")
    public void logBefore(JoinPoint joinPoint){
        //打印info的信息
        ServletRequestAttributes servletRequestAttributes =
                (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();

        //url
        logger.info("url={}", request.getRequestURL());//会将内容自动放入到{}去
        //method
        logger.info("method={}", request.getMethod());
        //ip
        logger.info("ip={}", request.getRemoteAddr());
        //类方法
        logger.info("class_method={}", joinPoint.getSignature().getDeclaringTypeName()//类名
                + "." + joinPoint.getSignature().getName());//类方法
        //参数
        logger.info("args={}", joinPoint.getArgs());
    }

    //在拦截方法之后执行
    @After("log()")
    public void logAfter(){
        logger.info("321");
    }

    @AfterReturning(returning = "object", pointcut = "log()")
    public void AfterReturning(Object object){
        logger.info("reponse={}", object.toString());
    }
}
