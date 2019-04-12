package com.tianj.autowash.entity.server;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tianj.autowash.basic.BaseEntity;
import com.tianj.autowash.entity.device.Device;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.internal.engine.groups.Group;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 服务站
 *
 * @author zhangxq
 * @version v1.0
 * @update 2018-12-14 11:43
 */
@ApiModel(value = "服务站信息")
public class Server extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -3105487639127484542L;
    /**
     * 服务站名称
     */
    @NotNull(message = "参数不能为空")
    @Length(min = 3, max = 12, message = "名称长度3-12")
    @ApiModelProperty(value = "服务站名称,长度3-12", required = true)
    private String name;
    /**
     * 服务地址
     */
    @NotNull(message = "参数不能为空")
    @Length(min = 5, max = 22, message = "名称长度5-22")
    @ApiModelProperty(value = "服务站地址,长度3-22", required = true)
    private String address;
    /**
     * 坐标经度
     */
    @NotNull(message = "参数不能为空",groups = {Group.class})
    @Digits(integer = 2, fraction = 3)
    @ApiModelProperty(value = "服务站坐标经度", required = true, dataType = "double")
    private BigDecimal longitude;
    /**
     * 坐标纬度
     */
    @NotNull(message = "参数不能为空")
    @Digits(integer = 2, fraction = 3)
    @ApiModelProperty(value = "服务站坐标纬度", required = true, dataType = "double")
    private BigDecimal latitude;
    /**
     * 服务站设备运行状态
     */
    @ApiModelProperty(hidden = true)
    private Integer status;
    /**
     * 硬件信息
     */
    @ApiModelProperty(hidden = true)
    @JsonIgnore
    private Device device;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }
}
