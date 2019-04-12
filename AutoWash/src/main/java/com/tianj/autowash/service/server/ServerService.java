package com.tianj.autowash.service.server;


import com.tianj.autowash.entity.server.Server;
import com.tianj.autowash.basic.BasicService;

import java.util.List;
import java.util.Map;

/**
 * 设备服务接口
 *
 * @author Administrator
 * @version v1.0
 * @update 2018-12-19 17:41
 */
public interface ServerService extends BasicService<Server> {
    /**
     * 获取服务指定范围服务点
     *
     * @param souLon 西南经度
     * @param souLat 西南纬度
     * @param norLon 东北经度
     * @param norLat 东北纬度
     * @return 范围服务列表
     */
    List<Map<String, Object>> getServiceStationsByScope(Double souLon, Double souLat, Double norLon, Double norLat);
}
