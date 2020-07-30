package com.xg.dynamic.data.source.service;

import com.xg.dynamic.data.source.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: dynamic-data-source
 * @description:
 * @author: gzk
 * @create: 2020-07-30 19:04
 **/
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;


    public void save(){
        userMapper.save();
    }


    public void query(){
        userMapper.query();
    }


}
