package com.mainiway.bean.po;

/**
 * Author: zhangxin
 * Date: Created in 2018/05/25 23:12
 * Copyright: Copyright (c) 2018
 * Description： Token 的 Model 类，可以增加字段提高安全性，例如时间戳、url 签名
 */
public class TokenModel {

    // 用户 id
    private String userName;

    // 随机生成的 uuid
    private String token;

    public TokenModel (String userName, String token) {
        this.userName = userName;
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName (String userName) {
        this.userName = userName;
    }

    public String getToken () {
        return token;
    }

    public void setToken (String token) {
        this.token = token;
    }
}