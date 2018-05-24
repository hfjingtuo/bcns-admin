package com.mainiway.service;

import com.mainiway.bean.po.UserPayAccount;
import com.mainiway.bean.vo.UserPayAccountVo;
import com.mainiway.common.base.IBaseService;

/**
 * Created by Administrator on 2018-05-22.
 */
public interface IUserPayAccountService extends IBaseService<UserPayAccount> {

    //查询开户信息
    UserPayAccount getUserPayAccountByUserId(String userId);

}
