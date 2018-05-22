package com.mainiway.service;

import com.mainiway.bean.po.UserAccount;
import com.mainiway.common.base.IBaseService;

/**
 * Created by Administrator on 2018-05-22.
 */
public interface IUserAccountService extends IBaseService<UserAccount> {

    UserAccount getAccountByUserId(String userId);

}
