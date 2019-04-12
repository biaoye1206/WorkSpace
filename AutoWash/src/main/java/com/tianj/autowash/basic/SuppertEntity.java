package com.tianj.autowash.basic;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author zhangxq
 * @version v1.0
 * @update 2018-12-14 11:53
 */
public interface SuppertEntity {
    /**
     * 通用状态 正常
     */
    @JsonIgnore
    Character DATA_STATUS_NORMAL = 0;
    /**
     * 通用状态 删除
     */
    @JsonIgnore
    Character DATA_STATUS_DEL = 1;
    /**
     * 设备使用 异常
     */
    @JsonIgnore
    Character DATA_STATUS_ANOMALY = 2;
    /**
     * 设备使用 休息
     */
    @JsonIgnore
    Character DATA_STATUS_REST = 3;

}
