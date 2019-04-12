package com.tianj.autowash.dao.user;

import com.tianj.autowash.basic.BaseDao;
import com.tianj.autowash.entity.user.User;
import org.springframework.stereotype.Repository;

/**
 * @author Administrator
 * @version v1.0
 * @update 2019-01-09 17:44
 */
@Repository
public interface UserDao extends BaseDao<User> {
    /**
     * 通过openid查询微信用户信息
     * @param openid
     * @return
     */
    User findByOpenid(String openid);

    /**
     * 通过sessionId查询用户信息
     * @param sessionId
     * @return
     */
    User findUserBySessionId(String sessionId);
}
