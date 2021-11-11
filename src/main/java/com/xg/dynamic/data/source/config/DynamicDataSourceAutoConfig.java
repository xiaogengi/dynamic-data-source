package com.xg.dynamic.data.source.config;

import com.xg.dynamic.data.source.DynamicDataSource;
import com.xg.dynamic.data.source.enums.DynamicDataSourceEnum;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;


/**
 * @program: dynamic-data-source
 * @description: 数据源配置类
 * @author: gzk
 * @create: 2020-07-06 14:23
 **/

@Configuration
public class DynamicDataSourceAutoConfig {

    @Bean("oneDataSource")
    @ConfigurationProperties(value = "spring.datasource.one")
    public DataSource oneDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean("twoDataSource")
    @ConfigurationProperties(value = "spring.datasource.two")
    public DataSource twoDataSource(){
        return DataSourceBuilder.create().build();
    }


    @Primary
    // 使用SqlSessionFactory 的时候，只需要把当前 bean 赋值到 SqlSessionFactory 的 dataSource 中即可
    @Bean("dataSource")
    public DynamicDataSource dataSource(@Qualifier("oneDataSource") DataSource oneDataSource, @Qualifier("twoDataSource") DataSource twoDataSource){
        HashMap<Object, Object> map = new HashMap<>();
        map.put(DynamicDataSourceEnum.ONE_DATA_SOURCE.getDataSourceName(), oneDataSource);
        map.put(DynamicDataSourceEnum.TWO_DATA_SOURCE.getDataSourceName(), twoDataSource);
        return new DynamicDataSource(oneDataSource, map);
    }


}
