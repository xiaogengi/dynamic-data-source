package com.xg.dynamic.data.source.test;

import com.xg.dynamic.data.source.annotation.DS;
import com.xg.dynamic.data.source.enums.DynamicDataSourceEnum;
import org.springframework.stereotype.Component;

/**
 * @program: dynamic-data-source
 * @description: 测试数据源切换
 * @author: gzk
 * @create: 2020-07-06 15:24
 **/
@Component
public class DynamicDataSourceTest {


    public void notAnnotation(){
        System.out.println("无注解！！！");
    }

    @DS
    public void defaultDataSource(){
        System.out.println("defaultDataSource");
    }

    @DS(dataSourceName = DynamicDataSourceEnum.ONE_DATA_SOURCE)
    public void oneDataSource(){
        System.out.println("oneDataSource");
    }

    @DS(dataSourceName = DynamicDataSourceEnum.TWO_DATA_SOURCE)
    public void twoDataSource(){
        System.out.println("twoDataSource");
    }

}
