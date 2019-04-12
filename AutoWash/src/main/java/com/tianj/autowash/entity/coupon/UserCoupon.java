package com.tianj.autowash.entity.coupon;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 用户优惠券
 * @author Zhangxq
 * @version v1.0
 * @CreateDate 2019-01-24 13:55
 */
public class UserCoupon extends BasicCoupon{
    private static final long serialVersionUID = -1084423678587685887L;
    /**
     * 用户id
     */
    @JsonIgnore
    private String usrUserId;

    /**
     * 优惠券id
     */
    @JsonIgnore
    private String sysCouponId;

    public String getUsrUserId() {
        return usrUserId;
    }

    public void setUsrUserId(String usrUserId) {
        this.usrUserId = usrUserId;
    }

    public String getSysCouponId() {
        return sysCouponId;
    }

    public void setSysCouponId(String sysCouponId) {
        this.sysCouponId = sysCouponId;
    }

}
