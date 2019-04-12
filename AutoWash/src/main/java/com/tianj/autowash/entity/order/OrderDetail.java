package com.tianj.autowash.entity.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tianj.autowash.basic.BaseEntity;
import com.tianj.autowash.entity.coupon.UserCoupon;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 订单详情
 *
 * @author Administrator
 * @version v1.0
 * @update 2019-01-19 11:23
 */
@ApiModel("订单详情")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDetail extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -4044636464676548121L;
    /**
     * 设备ID
     */
    @ApiModelProperty(value = "facilityId",name = "硬件ID",dataType = "string",required = true)
    private String facilityId;
    /**
     * 服务站ID
     */
    @ApiModelProperty(value = "serviceStationId",name = "服务站ID",dataType = "string",required = true)
    private String serviceStationId;
    /**
     * 服务站名称
     */
    @ApiModelProperty(value = "serviceStationName",name = "服务站名称",dataType = "string",required = true)
    private String serviceStationName;
    /**
     * 服务站地址
     */
    private String serviceStationAddr;
    /**
     * 洗车金额
     */
    private BigDecimal amount;
    /**
     * 支付方式
     */
    private List<String> payTypes;
    /**
     * 优惠券
     */
    private List<UserCoupon> coupon;

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public String getServiceStationName() {
        return serviceStationName;
    }

    public void setServiceStationName(String serviceStationName) {
        this.serviceStationName = serviceStationName;
    }

    public String getServiceStationAddr() {
        return serviceStationAddr;
    }

    public void setServiceStationAddr(String serviceStationAddr) {
        this.serviceStationAddr = serviceStationAddr;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public List<String> getPayTypes() {
        return payTypes;
    }

    public void setPayTypes(List<String> payTypes) {
        this.payTypes = payTypes;
    }

    public List<UserCoupon> getCoupon() {
        return coupon;
    }

    public void setCoupon(List<UserCoupon> coupon) {
        this.coupon = coupon;
    }
}
