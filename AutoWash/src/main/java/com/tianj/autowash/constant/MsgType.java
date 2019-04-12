package com.tianj.autowash.constant;

/**
 * 硬件通讯数据的类型
 *
 * @author Zhangxq
 * @version v1.0
 * @CreateDate 2019-03-11 16:08
 */
public interface MsgType {
    /**
     * 命令
     */
    Integer TYPE_COMMAND = 2001;

    /**
     * 数据
     */
    Integer TYPE_DATA = 2002;

    /**
     * 错误
     */
    Integer TYPE_ERROR = 2003;

    /**
     * 消息应答
     */
    Integer TYPE_REPLY = 2004;

}
