package com.mainiway.dao;

import com.mainiway.bean.po.UserInfo;
import com.mainiway.common.base.IBaseDao;

public interface IUserInfoDao  extends IBaseDao<UserInfo>{

    UserInfo getUserInfoByUserName(String userName);
}
