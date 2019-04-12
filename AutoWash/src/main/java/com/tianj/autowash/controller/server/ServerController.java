package com.tianj.autowash.controller.server;

import com.tianj.autowash.basic.ResponseEntity;
import com.tianj.autowash.entity.server.Server;
import com.tianj.autowash.service.server.ServerService;
import com.tianj.autowash.utils.BeanVlidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 服务站控制器
 *
 * @author Administrator
 * @version v1.0
 * @update 2018-12-24 14:30
 */
@Controller
@RequestMapping("/svr")
@Validated
@Api(value = "服务站接口")
public class ServerController {
    /**
     * 服务站数据提供对象
     */
    @Autowired
    private ServerService poiService;

    /**
     * 获取附近服务站
     *
     * @return 服务站列表
     */
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "souLon", value = "西南经度", required = true, dataType = "double", paramType = "query"),
            @ApiImplicitParam(name = "souLat", value = "西南纬度", required = true, dataType = "double", paramType = "query"),
            @ApiImplicitParam(name = "norLon", value = "东北经度", required = true, dataType = "double", paramType = "query"),
            @ApiImplicitParam(name = "norLat", value = "东北纬度", required = true, dataType = "double", paramType = "query")
    })
    @RequestMapping(value = "/findServicePointsByScope", method = RequestMethod.GET)
    @ApiOperation(value = "通过范围获取服务站列表", notes = "通过范围获取服务站列表", httpMethod = "GET")
    public ResponseEntity findServicePointsByScope(Double souLon, Double souLat, Double norLon, Double norLat) {
        List<Map<String, Object>> points
                = poiService.getServiceStationsByScope(souLon, souLat, norLon, norLat);
        return new ResponseEntity<>(points);
    }

    /**
     * 添加一条服务站数据
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "添加一条服务站数据", notes = "添加一条服务站数据")
    @ApiImplicitParam(name = "token", value = "token", required = true, dataType = "string", paramType = "header")
    public ResponseEntity insert(@RequestBody Server server) {
        BeanVlidator.validate(server);
        poiService.insertOrUpdate(server);
        return new ResponseEntity<>();
    }

    /**
     * 根据Id获取服务站
     *
     * @return 服务站信息
     */
    @ResponseBody
    @RequestMapping(value = "/findServicePointById", method = RequestMethod.GET)
    @ApiOperation(value = "根据Id获取服务站", notes = "根据Id获取服务站")
    @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "string", paramType = "query")
    public ResponseEntity findServicePointById(String id) {
        Server server = poiService.findById(id);
        return new ResponseEntity<>(server);
    }
}
