package com.arpgalaxy.ink.core.aop;

import com.arpgalaxy.ink.common.utils.PageUtils;
import com.arpgalaxy.ink.common.utils.response.R;
import com.arpgalaxy.ink.common.utils.response.ResponseEntity;
import com.arpgalaxy.ink.common.utils.string.CamelCase;
import com.arpgalaxy.ink.core.service.CommonService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private CommonService commonService;

    @Pointcut("execution(public * com.arpgalaxy.ink.core.controller.*.list(..))")
    public void pointCut(){}


    @AfterReturning(value = "pointCut()",returning = "result")
    public void after(JoinPoint joinPoint, R result) throws Throwable{
        log.info("AOP:{}",joinPoint.getClass());
        String[] value=null;
//        RequestMapping annotation=null;
        Method[] list = joinPoint.getTarget().getClass().getDeclaredMethods();
        List<Method> methods = Arrays.asList(list);
        Iterator<Method> iterator = methods.iterator();
        while (iterator.hasNext()){
            RequestMapping annotation = iterator.next().getAnnotation(RequestMapping.class);
            if (annotation!=null && "/list".equals(annotation.value()[0]) ){
                log.info("AOP--:{}",annotation.value()[0]);
                String name = joinPoint.getTarget().getClass().getName();
                log.info("查询表类{}对应表的ColumnNames",name);
                String s = name.replaceAll("com.arpgalaxy.ink.core.controller.", "");
                s = s.replaceAll("Controller","");
                s = CamelCase.toUnderlineCase(s);
                List<String> columnNames = commonService.queryColumnNames("ink"+s);
                //数据库列名list转驼峰,toArray(new String[columnNames.size()])中<T>声明是泛型方法，在方法上才能使用泛型。所以返回的是String数组,否则强转也会出现object转string错误
                List<String> strings = Arrays.asList(CamelCase.toCamelCase(columnNames.toArray(new String[columnNames.size()])));
                PageUtils page = (PageUtils) result.get("page");
                page.setColumnNames(strings);
            }
        }
    }
}
