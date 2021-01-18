package com.arpgalaxy.ink.core.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ArpGalaxy
 * @version 1.0
 * @description
 * @date 2021/1/18 9:26
 */
@Slf4j
@Aspect
@Component
public class QueryColumnNames {
    @Pointcut("execution(public * com.arpgalaxy.ink.core.controller.*.list(..)) && @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void pointCut(){}


    @After("pointCut()")
    public void after(JoinPoint joinPoint) throws Throwable{
        log.info("AOP:{}",joinPoint.getClass());
        String[] value=null;
//        RequestMapping annotation=null;
        Method[] list = joinPoint.getTarget().getClass().getDeclaredMethods();
        List<Method> methods = Arrays.asList(list);
        Iterator<Method> iterator = methods.iterator();
        while (iterator.hasNext()){
            RequestMapping annotation = iterator.next().getAnnotation(RequestMapping.class);
            if (annotation!=null && "/list".equals(annotation.value()[0]) ){
                log.info("AOP:{}",annotation.value()[0]);
            }
        }
    }
}
