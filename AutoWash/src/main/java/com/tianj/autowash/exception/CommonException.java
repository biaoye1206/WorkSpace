package com.tianj.autowash.exception;

import com.tianj.autowash.constant.ResponseCode;

/**
 * 通用异常
 * 用于业务需求处理，非错误级异常
 * @author Zhangxq
 * @version v1.0
 * @CreateDate 2019-03-22 10:53
 */
public class CommonException extends RuntimeException {
    /**
     * @see ResponseCode
     */
    private Integer responseStatus;
    /**
     *
     * @param message 业务消息
     * @param cause  错误对象
     * @param responseStatus 业务处理状态 {@link com.tianj.autowash.constant.ResponseCode}
     */
    public CommonException(String message, Throwable cause, Integer responseStatus) {
        super(message, cause);
        this.responseStatus = responseStatus;
    }

    /**
     *
     * @param message 业务消息
     * @param responseStatus 业务处理状态 {@link com.tianj.autowash.constant.ResponseCode}
     */
    public CommonException(String message, Integer responseStatus) {
        super(message);
        this.responseStatus = responseStatus;
    }

    public Integer getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(Integer responseStatus) {
        this.responseStatus = responseStatus;
    }
}
