package com.tianj.autowash.controller.order;
import com.tianj.autowash.basic.ResponseEntity;
import com.tianj.autowash.constant.ResponseCode;
import com.tianj.autowash.entity.order.OrderDetail;
import com.tianj.autowash.entity.order.SysOrder;
import com.tianj.autowash.entity.user.User;
import com.tianj.autowash.exception.CommonException;
import com.tianj.autowash.service.order.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 订单接口
 *
 * @author administrator
 * @version v1.0
 * @update 2019-01-19 11:21
 */

@RequestMapping("order")
@Controller
@Api(value = "订单接口")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ModelAttribute
    private void getUser(HttpServletRequest request, Model model) {
        model.addAttribute(request.getAttribute("user"));
    }

    /**
     * 拉取订单信息
     *
     * @param code 二维码数据
     * @param user 用户信息
     * @return 订单详情
     */
    @ResponseBody
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    @ApiOperation(value = "扫描二维码拉取订单详情", notes = "扫描二维码拉取订单详情", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "二维码数据", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity getDetail(String code, @ApiIgnore User user) {
        if (StringUtils.isEmpty(code)) {
            return new ResponseEntity<>().setCode(ResponseCode.FAIL);
        }
        OrderDetail detail = orderService.orderDetail(code, user);
        return new ResponseEntity<>(detail);
    }

    /**
     * 创建订单
     *
     * @param orderAmount      订单金额
     * @param usrCouponId      用户优惠券ID
     * @param facilityId       设备ID
     * @param serviceStationId 服务站ID
     * @param tradeType        交易类型(0洗车,1充值)
     * @param user             用户
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "create", method = RequestMethod.GET)
    @ApiOperation(value = "创建订单", notes = "创建订单", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderAmount", value = "订单金额", required = true, paramType = "query"),
            @ApiImplicitParam(name = "usrCouponId", value = "用户优惠券ID", paramType = "query"),
            @ApiImplicitParam(name = "facilityId", value = "硬件ID", required = true, paramType = "query"),
            @ApiImplicitParam(name = "serviceStationId", value = "服务站ID", required = true, paramType = "query"),
            @ApiImplicitParam(name = "tradeType", value = "交易类型(0:洗车,1:充值)", required = true, paramType = "query"),
            @ApiImplicitParam(name = "token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity createOrder(BigDecimal orderAmount, @RequestParam(required = false, defaultValue = "") String usrCouponId, String facilityId,
                                      String serviceStationId, String tradeType, @ApiIgnore User user) {
        SysOrder order = new SysOrder();
        order.setOrderAmount(orderAmount);
        order.setUsrCouponId(usrCouponId);
        order.setServiceStationId(serviceStationId);
        order.setTradeType(tradeType);
        order.setFacilityId(facilityId);
        Map<String, String> result = orderService.createOrder(order, user);
        return new ResponseEntity<>(result);
    }

    /**
     * 用户充值
     *
     * @param orderAmount 订单金额
     * @param tradeType   交易类型(0洗车,1充值)
     * @param user        用户
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "recharge", method = RequestMethod.GET)
    @ApiOperation(value = "创建订单", notes = "创建订单", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderAmount", value = "订单金额", required = true, paramType = "query"),
            @ApiImplicitParam(name = "tradeType", value = "交易类型(0:洗车,1:充值)", required = true, paramType = "query"),
            @ApiImplicitParam(name = "index", value = "面值索引", required = true, paramType = "query"),
            @ApiImplicitParam(name = "token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity createOrder(BigDecimal orderAmount, String tradeType, int index, @ApiIgnore User user) {
        if (index < 0 && index > 3) {
            throw new CommonException("参数错误", ResponseCode.FAIL);
        }
        SysOrder order = new SysOrder();
        order.setOrderAmount(orderAmount);
        order.setTradeType(tradeType);
        Map<String, String> result = orderService.createOrder(order, user);
        return new ResponseEntity<>(result);
    }


    /**
     * 获取历史订单
     *
     * @param user 用户信息
     * @return 历史订单列表
     */
    @ResponseBody
    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ApiOperation(value = "查询全部订单", notes = "查询全部订单", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity getOrderListByUserId(@ApiIgnore User user) {
        List<SysOrder> orders = orderService.getOrderListByUserId(user.getId());
        return new ResponseEntity<>(orders);
    }

    @ResponseBody
    @RequestMapping(value = "get", method = RequestMethod.POST, params = "id != null | id != ''")
    @ApiOperation(value = "查询订单", notes = "查询订单", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "id", value = "订单ID", required = true, dataType = "string", paramType = "Query")
    })
    public ResponseEntity getOrderById(String id) {
        SysOrder order = orderService.findById(id);
        return new ResponseEntity<>(order);
    }


    /**
     * 更新订单状态
     *
     * @param order 订单
     * @return 执行结果
     */
    @ResponseBody
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ApiOperation(value = "更新订单状态", notes = "更新订单状态", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity update(@RequestBody SysOrder order) {
        orderService.update(order);
        return new ResponseEntity();
    }
}
