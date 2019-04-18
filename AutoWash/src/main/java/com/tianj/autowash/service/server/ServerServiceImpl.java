package com.tianj.autowash.service.server;

import com.tianj.autowash.basic.BasicServiceImpl;
import com.tianj.autowash.constant.ResponseCode;
import com.tianj.autowash.dao.server.ServerDao;
import com.tianj.autowash.entity.device.Device;
import com.tianj.autowash.entity.server.Server;
import com.tianj.autowash.exception.CommonException;
import com.tianj.autowash.service.device.BaseSocketService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 设备服务
 *
 * @author Administrator
 * @version v1.0
 * @update 2018-12-19 17:41
 */
@Service
public class ServerServiceImpl extends BasicServiceImpl<Server, ServerDao> implements ServerService {

    @Override
    public List<Map<String, Object>> getServiceStationsByScope(Double souLon, Double souLat, Double norLon, Double norLat) {
        List<Map<String, Object>> points = dao.getServicePointsByScope(souLon, souLat, norLon, norLat);
        for (Map<String, Object> point : points) {
            point.put("zIndex", 0);
            point.put("iconPath", "/image/ic_tomap.png");
        }
        return points;
    }

    @Override
    public Server findById(String id) {
        Server server = super.findById(id);
        if (server == null){
            throw new CommonException("未查到相关数据", ResponseCode.OK);
        }
        Device device = server.getDevice();
        int status = 0;
        if (device == null){
            status = 2;
        }else if (BaseSocketService.isRun(device.getFacilityId())){
            status = 1;
        }
        // 临时,后期需考虑设备故障（1正在运行，0未工作, 2 未知道情况）
        server.setStatus(status);
        return server;
    }
}
