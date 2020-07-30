package com.xg.dynamic.data.source;


import com.xg.dynamic.data.source.service.UserService;
import com.xg.dynamic.data.source.test.DynamicDataSourceTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DynamicDataSourceApplicationTests {

    @Autowired
    private DynamicDataSourceTest dataSourceTest;

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {


//        dataSourceTest.notAnnotation();
//        System.out.println();
//
//        dataSourceTest.defaultDataSource();
//        System.out.println();
//
//        dataSourceTest.oneDataSource();
//        System.out.println();
//
//        dataSourceTest.twoDataSource();
        try {
            userService.save();
        } catch (Exception e){

        }

        try {
            userService.query();
        } catch (Exception e){

        }
    }

}
