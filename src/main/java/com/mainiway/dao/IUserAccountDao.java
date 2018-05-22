package com.mainiway.dao;

import com.mainiway.bean.po.UserAccount;
import com.mainiway.common.base.IBaseDao;

/**
 * Created by Administrator on 2018-05-22.
 */
public interface IUserAccountDao extends IBaseDao <UserAccount>{

    UserAccount getAccountByUserId(String userId);

}
