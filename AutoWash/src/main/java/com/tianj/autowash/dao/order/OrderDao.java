package com.tianj.autowash.dao.order;

import com.tianj.autowash.basic.BaseDao;
import com.tianj.autowash.entity.order.SysOrder;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author Zhangxq
 * @version v1.0
 * @CreateDate 2019-01-24 11:24
 */
@Repository
public interface OrderDao extends BaseDao<SysOrder> {
    /**
     * 创建洗车订单
     *
     * @param order
     */
    void weChatInsert(SysOrder order);

    /**
     * 创建充值订单
     *
     * @param order
     */
    void recharge(SysOrder order);

    /**
     * 通过用户id查询订单
     *
     * @param userId 用户ID
     * @return
     */
    List<SysOrder> getOrderListByUserId(String userId);



}

