package com.tianj.autowash.service.device;

import com.tianj.autowash.entity.device.Device;
import com.tianj.autowash.basic.BasicService;

/**
 * 设备服务接口
 *
 * @author Administrator
 * @version v1.0
 * @update 2018-12-19 17:41
 */
public interface DeviceService extends BasicService<Device> {
    /**
     * 根据设备id查询信息
     *
     * @param devId
     * @return
     */
    Device findByDevId(String devId);
}
