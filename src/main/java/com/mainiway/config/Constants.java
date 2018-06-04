package com.mainiway.config;

/**
 * Author: zhangxin
 * Date: Created in 2018/05/25 23:14
 * Copyright: Copyright (c) 2018
 * Description：
 */
public class Constants {
    /**
     * 存储当前登录用户userName
     */
    public static final String CURRENT_USER_NAME = "CURRENT_USER_NAME";

    /**
     * 存储当前登录用户
     */
    public static final String CURRENT_USER = "CURRENT_USER";

    /**
     * header中存放token的标识
     */
    public static final String AUTHORIZATION = "token";

    /**
     * 过期时间,测试使用60秒
     */
    public static final long TOKEN_EXPIRED = 1000 * 60;

}