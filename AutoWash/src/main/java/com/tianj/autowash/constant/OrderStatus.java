package com.tianj.autowash.constant;

/**
 * 交易结果(0、"未完成" 1、"取消",2、"完成")
 *
 * @author Zhangxq
 * @version v1.0
 * @CreateDate 2019-01-24 11:33
 */
public interface OrderStatus {
    /**
     * 未完成
     */
    Character ORDER_STATUS_UNDONE = '0';

    /**
     * 取消
     */
    Character ORDER_STATUS_CANCEL = '1';

    /**
     * 完成
     */
    Character ORDER_STATUS_COMPLETE = '2';

}
