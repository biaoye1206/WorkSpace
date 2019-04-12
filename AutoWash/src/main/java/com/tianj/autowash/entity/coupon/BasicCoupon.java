package com.tianj.autowash.entity.coupon;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tianj.autowash.basic.BaseEntity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Zhangxq
 * @version v1.0
 * @CreateDate 2019-01-24 14:32
 */
class BasicCoupon extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -5838279162757872776L;
    /**
     * 标题
     */
    private String title;

    /**
     * 优惠券额度
     */
    private BigDecimal amount;

    /**
     * 优惠券折扣
     */
    private BigDecimal discount;

    /**
     * 优惠券类型(额度 0/折扣 1)
     */
    @JsonIgnore
    private String type;

    /**
     * 有效天数(0长期有效)
     */
    @JsonIgnore
    private int validDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getValidDate() {
        return validDate;
    }

    public void setValidDate(int validDate) {
        this.validDate = validDate;
    }
}
