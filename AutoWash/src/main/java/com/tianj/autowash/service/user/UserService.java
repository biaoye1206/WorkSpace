package com.tianj.autowash.service.user;

import com.tianj.autowash.entity.user.User;
import com.tianj.autowash.basic.BasicService;

/**
 * @author Administrator
 * @version v1.0
 * @update 2019-01-09 17:04
 */
public interface UserService extends BasicService<User> {
    /**
     * 小程序登陆
     *
     * @param phoneData     手机数据
     * @param phoneIv       手机数据签名
     * @param code          临时登陆code
     * @param userData      用户数据
     * @param userSignature 用户数据签名
     * @return 用户对象
     */
    User login(String phoneData, String phoneIv, String code, String userData, String userSignature);

    /**
     * 通过sessionId查询用户
     *
     * @param sessionId sessionId
     * @return 用户
     */
    User findUserBySessionId(String sessionId);
}
