package com.xg.dynamic.data.source.aspect;

import com.xg.dynamic.data.source.DynamicDataSource;
import com.xg.dynamic.data.source.annotation.DS;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @program: dynamic-data-source
 * @description: 动态切换数据源切面类
 * @author: gzk
 * @create: 2020-07-06 14:40
 **/
@Component
@Aspect
@Order(-1) // @Order(-1) 表示在事物切面之前 就切换数据源
public class DynamicDataSourceAspect {

    @Around("@annotation(com.xg.dynamic.data.source.annotation.DS)")
    public Object dynamicDataSource(ProceedingJoinPoint point) throws Throwable {
        String threadName = Thread.currentThread().getName();

        MethodSignature signature =(MethodSignature) point.getSignature();
        DS ds = signature.getMethod().getAnnotation(DS.class);

        String dataSourceName = ds.dataSourceName().getDataSourceName();
        DynamicDataSource.setDataSource(dataSourceName);
        System.out.println(threadName + " dataSource is > > > > " + dataSourceName + " < < < <");

        try {
            return point.proceed();
        } finally {
            DynamicDataSource.remove();
            System.out.println(threadName + "----------------------------------> dataSource remove <----------------------------------");
        }
    }
}
