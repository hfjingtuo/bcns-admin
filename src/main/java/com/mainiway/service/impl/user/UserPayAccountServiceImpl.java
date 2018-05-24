package com.mainiway.service.impl.user;

import com.mainiway.bean.po.UserPayAccount;
import com.mainiway.common.base.BaseServiceImpl;
import com.mainiway.dao.IUserPayAccountDao;
import com.mainiway.service.IUserPayAccountService;
import com.sun.tools.javac.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018-05-22.
 */
@Service
public class UserPayAccountServiceImpl extends BaseServiceImpl<UserPayAccount> implements IUserPayAccountService {

    @Autowired
    private IUserPayAccountDao iUserPayAccountDao;

    @Override
    public void register(UserPayAccount userPayAccount) {
        iUserPayAccountDao.register(userPayAccount);
    }

    @Override
    public UserPayAccount getUserPayAccountByUserId(String userId) {
        return iUserPayAccountDao.getUserPayAccountByUserId(userId);
    }
}
