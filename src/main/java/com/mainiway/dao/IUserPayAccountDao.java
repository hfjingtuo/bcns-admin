package com.mainiway.dao;

import com.mainiway.bean.po.UserPayAccount;
import com.mainiway.common.base.IBaseDao;

/**
 * Created by Administrator on 2018-05-22.
 */
public interface IUserPayAccountDao extends IBaseDao<UserPayAccount> {

    void register(UserPayAccount userPayAccount);

    UserPayAccount getUserPayAccountByUserId(String userId);
}
