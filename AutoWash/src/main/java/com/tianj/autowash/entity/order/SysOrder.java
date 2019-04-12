package com.tianj.autowash.entity.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tianj.autowash.basic.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Zhangxq
 * @version v1.0
 * @CreateDate 2019-01-24 10:50
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel
public class SysOrder extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1511614239873645101L;
    /**
     * 用户id
     */
    @ApiModelProperty(name = "用户id")
    private String usrUserId;

    /**
     * 交易金额
     */
    @ApiModelProperty(name = "交易金额")
    private BigDecimal orderAmount;

    /**
     * 实际支付金额
     */
    @ApiModelProperty(name = "实际支付金额")
    private BigDecimal payAmout;

    /**
     * 用户优惠券id
     */
    @ApiModelProperty(name = "优惠券ID")
    private String usrCouponId;

    /**
     * 交易结果(0、 未完成 1、 取消 ，2、 完成 )
     */
    @ApiModelProperty(name = "交易结果(0、 未完成 1、 取消 ，2、 完成 )")
    private Character orderResult;

    /**
     * 取消原因
     */
    @ApiModelProperty(name = "取消原因")
    private String cancelCause;

    /**
     * 交易类型
     */
    @ApiModelProperty(name = "交易类型")
    private String tradeType;

    /**
     * 支付类型
     */
    @ApiModelProperty(name = "支付类型")
    private Integer payType;
    /**
     * 设备id
     */
    @ApiModelProperty(name = "设备id")
    private String facilityId;

    /**
     * 服务站id
     */
    @ApiModelProperty(name = "服务站id")
    private String serviceStationId;

    /**
     * 微信支付订单号
     */
    @ApiModelProperty(name = "微信支付订单号")
    private String transactionId;


    public String getUsrUserId() {
        return usrUserId;
    }

    public void setUsrUserId(String usrUserId) {
        this.usrUserId = usrUserId;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public BigDecimal getPayAmout() {
        return payAmout;
    }

    public void setPayAmout(BigDecimal payAmout) {
        this.payAmout = payAmout;
    }

    public String getUsrCouponId() {
        return usrCouponId;
    }

    public void setUsrCouponId(String usrCouponId) {
        this.usrCouponId = usrCouponId;
    }

    public Character getOrderResult() {
        return orderResult;
    }

    public void setOrderResult(Character orderResult) {
        this.orderResult = orderResult;
    }

    public String getCancelCause() {
        return cancelCause;
    }

    public void setCancelCause(String cancelCause) {
        this.cancelCause = cancelCause;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(String facilityId) {
        this.facilityId = facilityId;
    }

    public String getServiceStationId() {
        return serviceStationId;
    }

    public void setServiceStationId(String serviceStationId) {
        this.serviceStationId = serviceStationId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
