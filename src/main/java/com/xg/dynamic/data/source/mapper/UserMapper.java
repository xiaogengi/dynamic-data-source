package com.xg.dynamic.data.source.mapper;

import com.xg.dynamic.data.source.annotation.DS;
import com.xg.dynamic.data.source.enums.DynamicDataSourceEnum;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    @DS(dataSourceName = DynamicDataSourceEnum.ONE_DATA_SOURCE)
    void save();


    @DS(dataSourceName = DynamicDataSourceEnum.TWO_DATA_SOURCE)
    void query();

}
