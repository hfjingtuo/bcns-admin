package com.mainiway.service;

import com.mainiway.bean.po.UserInfo;
import com.mainiway.common.base.IBaseService;

public interface IUserInfoService extends IBaseService<UserInfo>{

    UserInfo getUserInfoByUserName(String userName);

    boolean comparePassword(UserInfo user, UserInfo userInDataBase);
}
