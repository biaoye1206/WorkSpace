package com.tianj.autowash.controller.user;


import com.tianj.autowash.basic.ResponseEntity;
import com.tianj.autowash.entity.user.User;
import com.tianj.autowash.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * @version v1.0
 * @update 2019-01-07 19:58
 */
@Controller
@Api(value = "小程序控制器")
@RequestMapping("/wechat")
public class WechatController {
    private static final Logger log = LoggerFactory.getLogger(WechatController.class);
    @Autowired
    protected UserService userService;

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ApiOperation(value = "小程序登陆", notes = "小程序登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phoneData", value = "电话号码加密数据", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(name = "phoneIv", value = "加密位移数据", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(name = "code", value = "登陆临时码", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(name = "userData", value = "用户数据", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(name = "userSignature", value = "用户数据签名", dataType = "string", paramType = "query", required = true)
    })
    public ResponseEntity login(String phoneData, String phoneIv, String code,
                                String userData, String userSignature) {
        Map<String, String> result = new HashMap<>(1);
        User user = userService.login(phoneData, phoneIv, code, userData, userSignature);
        result.put("token", user.getSession());
        log.debug("phoneData:" + phoneData + ",phoneIv:" + phoneIv + ",code:" + code + ",userData:" + userData + ",userSignature:" + userSignature);
        return new ResponseEntity<>(result);
    }
}
