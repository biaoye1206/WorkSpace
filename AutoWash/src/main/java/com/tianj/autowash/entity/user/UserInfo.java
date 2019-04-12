package com.tianj.autowash.entity.user;


import com.tianj.autowash.basic.BaseEntity;

import java.io.Serializable;
import java.util.Objects;

/**
 * 用户实体类
 * @author zhangxq
 * @version v1.0
 * @update 2018-12-08 11:57
 */
public class UserInfo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 9090162230085784206L;
    /**
     * 用户姓名
     */
    private String name;
    /**
     * 电话
     */
    private String phone;
    /**
     * 性别
     */
    private Integer gender;
    /**
     * 城市
     */
    private String city;
    /**
     * 省
     */
    private String province;
    /**
     * 用户呢称
     */
    private String nickName;
    /**
     * 车牌号
     */
    private String carNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserInfo)) {
            return false;
        }
        UserInfo userInfo = (UserInfo) o;
        return Objects.equals(getName(), userInfo.getName()) &&
                Objects.equals(getPhone(), userInfo.getPhone()) &&
                Objects.equals(getGender(), userInfo.getGender()) &&
                Objects.equals(getCity(), userInfo.getCity()) &&
                Objects.equals(getProvince(), userInfo.getProvince()) &&
                Objects.equals(getNickName(), userInfo.getNickName()) &&
                Objects.equals(getCarNumber(), userInfo.getCarNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPhone(), getGender(), getCity(), getProvince(), getNickName(), getCarNumber());
    }
}
