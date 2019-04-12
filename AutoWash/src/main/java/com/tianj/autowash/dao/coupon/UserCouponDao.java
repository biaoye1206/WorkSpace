package com.tianj.autowash.dao.coupon;

import com.tianj.autowash.basic.BaseDao;
import com.tianj.autowash.entity.coupon.UserCoupon;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Zhangxq
 * @version v1.0
 * @CreateDate 2019-01-24 16:50
 */
@Repository
public interface UserCouponDao extends BaseDao<UserCoupon> {
    /**
     * 通过用户ID查询用户优惠券列表
     *
     * @param userId 用户id
     * @return 用户优惠券列表
     */
    List<UserCoupon> findValidCouponsByUserId(String userId);

}
