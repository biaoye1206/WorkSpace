package com.tianj.autowash.dao.server;


import com.tianj.autowash.basic.BaseDao;
import com.tianj.autowash.entity.server.Server;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 服务类
 * @author Administrator
 * @version v1.0
 * @update 2018-12-17 09:49
 */
@Repository
public interface ServerDao extends BaseDao<Server> {
    /**
     * 获取指定范围服务站
     *
     * @param souLon 西南经度
     * @param souLat 西南纬度
     * @param norLon 东北经度
     * @param norLat 东北纬度
     * @return
     */
    List<Map<String, Object>> getServicePointsByScope(@Param("souLon") Double souLon,
                                                      @Param("souLat") Double souLat,
                                                      @Param("norLon") Double norLon,
                                                      @Param("norLat") Double norLat);
}
