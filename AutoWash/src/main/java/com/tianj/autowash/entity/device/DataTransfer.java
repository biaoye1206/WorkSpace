package com.tianj.autowash.entity.device;

import java.io.Serializable;
import java.util.Map;

/**
 * 与硬件交换数据实体
 *
 * @author zhangxq
 * @version v1.0
 * @update 2018-12-13 19:12
 */
public class DataTransfer implements Serializable {
    private static final long serialVersionUID = 6165102452943563664L;
    /**
     * 接收设备ID
     */
    private String devicesId;
    /**
     * 硬件通讯数据的类型
     */
    private Integer type;
    /**
     * 时间戳
     */
    private String timestamp;
    /**
     * 数据体
     */
    private Map<String, String> data;

    public DataTransfer() {
    }

    public DataTransfer(Map<String, String> data, Integer type) {
        this(data,type,String.valueOf(System.currentTimeMillis()));
    }
    public DataTransfer(Map<String, String> data, Integer type,String timestamp) {
        this.data = data;
        this.type = type;
        this.timestamp = timestamp;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getDevicesId() {
        return devicesId;
    }

    public void setDevicesId(String devicesId) {
        this.devicesId = devicesId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "DataTransfer{" +
            "devicesId='" + devicesId + '\'' +
                    ", type=" + type +
                    ", timestamp='" + timestamp + '\'' +
                    ", data=" + data +
                    '}';
    }
}
