package com.xg.dynamic.data.source.enums;

/**
 * 数据源枚举
 */
public enum DynamicDataSourceEnum {

    ONE_DATA_SOURCE("one"),
    TWO_DATA_SOURCE("two"),
    ;



    DynamicDataSourceEnum(String dataSourceName) {
        this.dataSourceName = dataSourceName;
    }

    private String dataSourceName;

    public String getDataSourceName() {
        return dataSourceName;
    }
}
