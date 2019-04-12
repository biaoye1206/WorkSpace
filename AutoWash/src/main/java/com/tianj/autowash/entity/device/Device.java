package com.tianj.autowash.entity.device;


import com.tianj.autowash.basic.BaseEntity;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.util.Objects;

/**
 * 硬件
 *
 * @author zhangxq
 * @version v1.0
 * @update 2018-12-14 11:43
 */
@ApiModel(value = "硬件信息")
public class Device extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 5132417578409088748L;
    /**
     * 设备ID
     */
    private String facilityId;

    /**
     * 服务站Id
     *
     * @return
     */
    private String serviceStationId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Device)) {
            return false;
        }
        Device device = (Device) o;
        return Objects.equals(getFacilityId(), device.getFacilityId()) &&
                Objects.equals(getServiceStationId(), device.getServiceStationId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFacilityId(), getServiceStationId());
    }
}
