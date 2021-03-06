package com.tianj.autowash.entity.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tianj.autowash.basic.BaseEntity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 微信用户信息
 *
 * @author Administrator
 * @version v1.0
 * @update 2019-01-09 16:27
 */
public class User extends BaseEntity implements Serializable{

    private static final long serialVersionUID = -3026812345711292882L;
    /**
     * 用户唯一标识
     */
    private String openid;

    /**
     * 会话密钥
     */
    @JsonProperty("session_key")
    private String sessionKey;

    /**
     * 用户信息ID
     */
    private String usrUserInfoId;

    /**
     * 用户与后台交互session
     */
    private String session;


    /**
     * 余额
     */
    private BigDecimal balance;
    /**
     * 用户资料信息
     */
    private UserInfo userInfo;

    /**
     * 错误码
     */

    private Integer errcode;

    /**
     * 错误信息
     */

    private String errmsg;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getUsrUserInfoId() {
        return usrUserInfoId;
    }

    public void setUsrUserInfoId(String usrUserInfoId) {
        this.usrUserInfoId = usrUserInfoId;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
