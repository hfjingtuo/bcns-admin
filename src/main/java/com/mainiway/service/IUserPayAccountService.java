package com.mainiway.service;

import com.mainiway.bean.po.UserPayAccount;
import com.mainiway.common.base.IBaseService;

/**
 * Created by Administrator on 2018-05-22.
 */
public interface IUserPayAccountService extends IBaseService<UserPayAccount> {

    //注册开户
    void register(UserPayAccount userPayAccount);
    //查询开户信息
    UserPayAccount getUserPayAccountByUserId(String userId);

}
