package com.tianj.autowash.service.user;

import com.tianj.autowash.basic.BasicServiceImpl;
import com.tianj.autowash.constant.ConstWeChat;
import com.tianj.autowash.dao.user.UserDao;
import com.tianj.autowash.dao.user.UserInfoDao;
import com.tianj.autowash.entity.user.User;
import com.tianj.autowash.entity.user.UserInfo;
import com.tianj.autowash.exception.ValidateException;
import com.tianj.autowash.utils.DataTools;
import com.tianj.autowash.utils.HttpTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * @version v1.0
 * @update 2019-01-08 14:20
 */
@Service
@CacheConfig(cacheNames = {"userCache"})
public class UserServiceImpl extends BasicServiceImpl<User, UserDao> implements UserService {

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private HttpTools http;

    /**
     * 小程序用户登陆处理
     *
     * @param phoneData
     * @param phoneIv
     * @param code
     * @param userData
     * @param userSignature
     */

    @CachePut(value = "userCache", key = "#result.session")
    @Override
    public User login(String phoneData, String phoneIv, String code,
                      String userData, String userSignature) {
        UserInfo userInfo;
        // 访问微信平台获取session_key与openId，并解析到用户对象中
        User weChatUser = null;
        try {
            weChatUser = jscode2session(code);
            if (weChatUser.getErrcode() != null && weChatUser.getErrcode() != 0) {
                log.error("登录凭证校验错误 =====> " + weChatUser.getErrcode());
                throw new RuntimeException("登录凭证校验错误");
            }
            String sessionKey = weChatUser.getSessionKey();
            // 校验用户信息
            // 参数校验错误
            if (DataTools.checkDigest(userData, sessionKey, userSignature)) {
                Map phoneTemp, userTemp;
                //解密用户手机号码数据
                String decodePhone = DataTools.decode(phoneData, sessionKey, phoneIv);
                if (decodePhone == null) {
                    log.error("数据解码错误 =====> phoneData:{s%},sessionKey:{s%},phoneIv:{s%}", phoneData, sessionKey, phoneIv);
                    throw new RuntimeException("数据解析错误");
                }
                // 解析小程序电话信息
                phoneTemp = json.readValue(decodePhone, Map.class);
                // 解析小程序用户信息
                userTemp = json.readValue(userData, Map.class);
                userInfo = new UserInfo();
                // 设置数据
                userInfo.setNickName((String) userTemp.get("nickName"));
                userInfo.setCity((String) userTemp.get("city"));
                userInfo.setGender((Integer) userTemp.get("gender"));
                userInfo.setProvince((String) userTemp.get("province"));
                userInfo.setPhone((String) phoneTemp.get("phoneNumber"));

                // 设置session
                weChatUser.setSession(DataTools.generateId());
                User user = dao.findByOpenid(weChatUser.getOpenid());
                //已存在用户
                if (user != null) {
                    // 处理微信实体数据
                    weChatUser.setId(user.getId());
                    weChatUser.setUsrUserInfoId(user.getUsrUserInfoId());
                    insertOrUpdate(weChatUser);
                    // 处理用户信息数据
                    userInfo.setId(user.getUsrUserInfoId());
                    dataProcess(userInfo, userInfoDao);
                } else {
                    // 不存在用户
                    UserInfo preUser = userInfoDao.findByPhone(userInfo.getPhone());
                    if (preUser != null) {
                        userInfo.setId(preUser.getId());
                    }
                    //自动选择更新或添加
                    dataProcess(userInfo, userInfoDao);
                    // 设置用户信息Id
                    weChatUser.setUsrUserInfoId(userInfo.getId());
                    insertOrUpdate(weChatUser);
                    weChatUser.setUserInfo(userInfo);
                }
            } else {
                throw new RuntimeException("参数数据校验错误");
            }
        } catch (Exception e) {
            if (e instanceof RuntimeException) {
                throw new ValidateException(e.getMessage());
            }
        }
        return weChatUser;
    }

    /**
     * 通过sessionId获取用户信息
     *
     * @param sessionId
     */

    @Cacheable(value = "userCache", key = "#sessionId")
    @Override
    public User findUserBySessionId(String sessionId) {
        return dao.findUserBySessionId(sessionId);
    }


    /**
     * 登录凭证校验
     *
     * @param code 登录凭证 code
     * @return 用户唯一标识 & 会话密钥
     */
    private User jscode2session(String code) throws IOException {
        Map<String, String> body = new HashMap(3);
        body.put("appid", ConstWeChat.APP_ID);
        body.put("secret", ConstWeChat.SECRET);
        body.put("js_code", code);
        body.put("grant_type", "authorization_code");
        String result = http.http("https://api.weixin.qq.com/sns/jscode2session",
                HttpMethod.GET, body,
                new HashMap<String, String>(1) {{
                    put("content-type", "application/json");
                }});
        // 解析返回数据
        return json.readValue(result, User.class);
    }
}