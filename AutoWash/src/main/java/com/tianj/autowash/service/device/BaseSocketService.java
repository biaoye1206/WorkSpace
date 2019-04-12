package com.tianj.autowash.service.device;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tianj.autowash.entity.device.DataTransfer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 硬件服务类
 *
 * @author zhangxq
 * @version v1.0
 * @update 2018-12-13 14:07
 */

public abstract class BaseSocketService extends Thread implements ApplicationListener<ContextClosedEvent> {
    /**
     * 硬件注册中心
     */
    protected static Map<String, PrintWriter> devRegister = new ConcurrentHashMap(100);

    /**
     * 根据facilityID查询设备是否正在运行
     * @param facilityID
     * @return
     */
    public static boolean isRun(String facilityID) {
        if (devRegister.get(facilityID) != null) {
            return true;
        }
        return false;
    }

    /**
     * json转换对象
     */
    protected ObjectMapper json = new ObjectMapper();

    /**
     * 发送数据到硬件
     *
     * @param data  数据体
     * @param devId 硬件Id
     * @return 是否成功
     */
    public boolean postData(DataTransfer data, String devId) {
        // 转换后的信息
        String msg;
        try {
            data.setDevicesId(devId);
            msg = json.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            return false;
        }
        // 获取硬件数据接收通道并发送数据
        PrintWriter writer = getWriter(devId);
        if (writer != null) {
            writer.printf(msg);
            return true;
        }
        return false;
    }

    /**
     * 获取硬件数据接收通道
     *
     * @param devId 硬件id
     * @return
     */
    private PrintWriter getWriter(String devId) {
        return devRegister.get(devId);
    }

    /**
     * 关闭服务器
     *
     * @throws IOException
     */
    public abstract void serverClose() throws IOException;

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        try {
            System.out.println("关闭服务器-------------------------------");
            serverClose();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
