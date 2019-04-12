package com.tianj.autowash.exception;

/**
 * 设备断开
 * @author zhangxq
 * @version v1.0
 * @update 2018-12-13 15:43
 */
public class ClientOutException extends Exception{
    public ClientOutException(String message) {
        super(message);
    }
    public ClientOutException() {
    }
}
