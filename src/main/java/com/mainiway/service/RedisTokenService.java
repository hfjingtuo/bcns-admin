package com.mainiway.service;

import com.mainiway.bean.po.TokenModel;

/**
 * Author: zhangxin
 * Date: Created in 2018/05/25 23:13
 * Copyright: Copyright (c) 2018
 * Description： 对 token 进行操作的接口
 */
public interface RedisTokenService {

    /**
     * 创建一个 token 关联上指定用户
     * @param userName 指定用户的 id
     * @return 生成的 token
     */
    public TokenModel createToken(String userName);

    /**
     * 检查 token 是否有效
     * @param model token
     * @return 是否有效
     */
    public boolean checkToken (TokenModel model);

    /**
     * 从字符串中解析 token
     * @param authentication 加密后的字符串
     * @return
     */
    public TokenModel getToken (String authentication);

    /**
     * 清除 token
     * @param userName
     */
    public void deleteToken (String userName);

}
