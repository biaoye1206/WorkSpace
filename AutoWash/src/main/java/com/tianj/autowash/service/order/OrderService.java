package com.tianj.autowash.service.order;

import com.tianj.autowash.entity.order.OrderDetail;
import com.tianj.autowash.entity.order.SysOrder;
import com.tianj.autowash.entity.user.User;

import java.util.List;
import java.util.Map;

/**
 * 订单处理
 *
 * @author Administrator
 * @version v1.0
 * @update 2019-01-19 14:57
 */
public interface OrderService {
    /**
     * 生成订单详情
     *
     * @param qrCode 二维码
     * @param user   用户
     * @return 详情
     */
    OrderDetail orderDetail(String qrCode, User user);



    /**
     * 更新订单
     *
     * @param order   订单
     * @param order 订单对象
     */
    void update(SysOrder order);

    /**
     * 创建定单
     *
     * @param order 订单信息
     * @param user  用户
     * @return 支付时使用信息
     */
    Map<String, String> createOrder(SysOrder order, User user);

    /**
     * 更新订单
     *
     * @param map   微信回调用结果
     * @param order 订单对象
     */
    void updateOrderInfo(Map<String, String> map, SysOrder order);

    /**
     * 根据Id查询订单信息
     *
     * @param id Id
     * @return 订单信息
     */
    SysOrder findById(String id);

    /**
     * 根据用户ID查询订单列表
     *
     * @param userId 用户ID
     * @return 订单列表
     */
    List<SysOrder> getOrderListByUserId(String userId);
}
