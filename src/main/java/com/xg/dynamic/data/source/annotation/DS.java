package com.xg.dynamic.data.source.annotation;

import com.xg.dynamic.data.source.enums.DynamicDataSourceEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 动态数据源切换注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface DS {

    DynamicDataSourceEnum dataSourceName() default DynamicDataSourceEnum.ONE_DATA_SOURCE;

}
