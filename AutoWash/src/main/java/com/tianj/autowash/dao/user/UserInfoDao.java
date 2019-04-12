package com.tianj.autowash.dao.user;

import com.tianj.autowash.basic.BaseDao;
import com.tianj.autowash.entity.user.UserInfo;
import org.springframework.stereotype.Repository;

/**
 * 用户数据接口
 * @author Administrator
 * @version v1.0
 * @update 2019-01-10 11:34
 */
@Repository
public interface UserInfoDao extends BaseDao<UserInfo> {
    /**
     * 通过手机查询用户
     * @param phone
     * @return
     */
    UserInfo findByPhone(String phone);
}
