package com.tianj.autowash.service.device;

import com.tianj.autowash.dao.device.DeviceDao;
import com.tianj.autowash.entity.device.Device;
import com.tianj.autowash.basic.BasicServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 服务站
 * @author Administrator
 * @version v1.0
 * @update 2018-12-19 17:41
 */
@Service
public class DeviceServiceImpl extends BasicServiceImpl<Device, DeviceDao> implements DeviceService{
    @Override
    public Device findByDevId(String devId) {
        return dao.findByDevId(devId);
    }

    @Override
    public Device findById(String id) {
        return super.findById(id);
    }
}
