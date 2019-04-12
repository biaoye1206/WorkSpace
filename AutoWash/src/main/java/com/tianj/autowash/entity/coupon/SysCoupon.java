package com.tianj.autowash.entity.coupon;

import java.sql.Timestamp;

/**
 * 系统优惠券
 * @author Zhangxq
 * @version v1.0
 * @CreateDate 2019-01-24 14:52
 */
public class SysCoupon extends BasicCoupon{
    private static final long serialVersionUID = -1889631805234356756L;
    /**
     * 活动开始时间
     */
    private Timestamp beginTime;

    /**
     * 活动结束时间
     */
    private Timestamp endTime;

    /**
     * 优惠券创建人
     */
    private String creator;

    public Timestamp getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Timestamp beginTime) {
        this.beginTime = beginTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}
