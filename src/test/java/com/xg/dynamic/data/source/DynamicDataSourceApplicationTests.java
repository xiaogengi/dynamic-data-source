package com.xg.dynamic.data.source;


import com.xg.dynamic.data.source.test.DynamicDataSourceTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DynamicDataSourceApplicationTests {

    @Autowired
    private DynamicDataSourceTest dataSourceTest;

    @Test
    void contextLoads() {


        dataSourceTest.notAnnotation();

        dataSourceTest.defaultDataSource();

        dataSourceTest.oneDataSource();

        dataSourceTest.twoDataSource();


    }

}
