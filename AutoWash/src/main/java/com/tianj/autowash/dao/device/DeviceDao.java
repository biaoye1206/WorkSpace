package com.tianj.autowash.dao.device;


import com.tianj.autowash.basic.BaseDao;
import com.tianj.autowash.entity.device.Device;
import org.springframework.stereotype.Repository;

/**
 * @author Administrator
 * @version v1.0
 * @update 2018-12-17 09:49
 */
@Repository
public interface DeviceDao extends BaseDao<Device> {
    /**
     * 根据设备id查询信息
     *
     * @param devId
     * @return
     */
    Device findByDevId(String devId);

    /**
     * 根据服务站ID查询设备信息
     * @param ServerId  服务站ID
     * @return 设备信息
     */
    Device findByServerId(String ServerId);

}
