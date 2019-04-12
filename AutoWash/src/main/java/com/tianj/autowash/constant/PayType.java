package com.tianj.autowash.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * 支付类型
 *
 * @author Administrator
 * @version v1.0
 * @update 2019-01-19 11:31
 */
public interface PayType {
    /**
     * 余额
     */
    String PAY_TYPE_BALANCE_TEXT = "余额支付";
    Integer PAY_TYPE_BALANCE_VALUE = 0;

    /**
     * 微信
     */
    String PAY_TYPE_WECHAT_TEXT = "微信支付";
    Integer PAY_TYPE_WECHAT_VALUE = 1;

    /**
     * 获取支付方式列表
     * @return 支付方式列表
     */
    static List<String> getPayTypes() {
        List<String> lists = new ArrayList<>(2);
        lists.add(PayType.PAY_TYPE_BALANCE_TEXT);
        lists.add(PayType.PAY_TYPE_WECHAT_TEXT);
        return lists;
    }
}
