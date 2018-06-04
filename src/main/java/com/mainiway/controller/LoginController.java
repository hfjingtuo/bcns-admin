package com.mainiway.controller;

import com.mainiway.annotation.AuthAccess;
import com.mainiway.bean.dto.ResponseResult;
import com.mainiway.bean.po.TokenModel;
import com.mainiway.bean.po.UserInfo;
import com.mainiway.common.base.BaseController;
import com.mainiway.common.base.IStatusMessage;
import com.mainiway.controller.user.UserController;
import com.mainiway.service.IUserInfoService;
import com.mainiway.service.RedisTokenService;
import com.mainiway.config.Constants;
import com.mainiway.utils.MD5Util;
import com.mainiway.utils.RegexCheck;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: zhangxin
 * Date: Created in 2018/05/25 23:14
 * Copyright: Copyright (c) 2018
 * Description： 登录注册管理
 * 获取和删除 token 的请求地址，在 Restful 设计中其实就对应着登录和退出登录的资源映射
 */
@RestController
@RequestMapping("")
public class LoginController extends BaseController {

    private static Logger log = Logger.getLogger(UserController.class);

    @Autowired
    private IUserInfoService userInfoService;

    @Autowired
    private RedisTokenService redisTokenService;

    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public ResponseResult login(@RequestBody UserInfo user) {
        Map map = new HashMap();
        if (user != null) {
            String userName = user.getUserName();
            String password = user.getPassword();
            if (StringUtils.isNotBlank(userName) && StringUtils.isNotBlank(password)) {
                UserInfo userInfo = userInfoService.getUserInfoByUserName(userName);
                if (userInfo != null && userInfo.getPassword().equals(MD5Util.toMD5(password))) {
                    //登录成功 生成token 保存用户登录状态
                    TokenModel model = redisTokenService.createToken(userName);
                    String token = model.getToken();
                    map.put("token", token);
                    map.put("user", userInfo);
                    System.out.println(userName + "登录成功 token==>" + token);
                    log.info(userName + "登录成功 token==>" + token);
                    return getResponseResult(map, IStatusMessage.LoginStatus.SUCCESS);
                }
            }
        }
        map.put("user", user);
        return getResponseResult(map, IStatusMessage.LoginStatus.USER_OR_PASSWORD_ERROR);
    }

    @AuthAccess
    @ApiOperation(value = "退出登录")
    @PostMapping("/logout")
    public ResponseResult logout(HttpServletRequest request) {
        String token = request.getHeader(Constants.AUTHORIZATION);
        if (StringUtils.isNotBlank(token) && token.contains("@")){
            String userName = token.split("@")[0];
            redisTokenService.deleteToken(userName);
            return getResponseResult("", IStatusMessage.LogoutStatus.SUCCESS);
        }else {
            return getResponseResult("", IStatusMessage.LogoutStatus.FAIL);
        }
    }

    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    public ResponseResult register(@RequestBody UserInfo user) {
        if (user != null && StringUtils.isNotBlank(user.getUserName()) && StringUtils.isNotBlank(user.getPassword())) {
            String userName = user.getUserName();
            String password = user.getPassword();
            if (StringUtils.isNotBlank(userName) && StringUtils.isNotBlank(password)) {
                if (!RegexCheck.checkUsername(userName,6,20)){
                    return getResponseResult("用户名不合法", IStatusMessage.ResultStatus.ERROR);
                }
                UserInfo userInfo = new UserInfo();
                userInfo.setUserName(userName);
                userInfo.setPassword(MD5Util.toMD5(user.getPassword()));
                userInfo.setPhone(user.getPhone());
                userInfo.setEmail(user.getEmail());
                userInfo.setCreateDate(new Date());
                userInfo.setRemarks(user.getRemarks());
                userInfo.setDelFlag(false);
                userInfo.setCreateBy("system");
                userInfoService.insert(userInfo);
            }
            return getResponseResult("注册成功", IStatusMessage.ResultStatus.SUCCESS);
        } else {
            return getResponseResult("注册失败", IStatusMessage.ResultStatus.ERROR);
        }
    }

}
