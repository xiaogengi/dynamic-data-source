package com.xg.dynamic.data.source;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @program: dynamic-data-source
 * @description: 线程中数据源处理
 * @author: gzk
 * @create: 2020-07-06 14:47
 **/
public class DynamicDataSource extends AbstractRoutingDataSource {

    public DynamicDataSource(DataSource dataSource, Map<Object, Object> dataSourceMap) {
        super.setDefaultTargetDataSource(dataSource);
        super.setTargetDataSources(dataSourceMap);
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return getDataSource();
    }

    public static final ThreadLocal<String> DATA_SOURCE = new ThreadLocal<String>();


    // 当前线程的数据源存放到 ThreadLocal 中
    public static void setDataSource(String dataSourceName){
        DATA_SOURCE.set(dataSourceName);
    }

    // 当前线程的数据源从 ThreadLocal 取出
    public static String getDataSource(){
        return DATA_SOURCE.get();
    }

    // 当前线程的数据源从 ThreadLocal 清除
    public static void remove(){
        DATA_SOURCE.remove();
    }


}
