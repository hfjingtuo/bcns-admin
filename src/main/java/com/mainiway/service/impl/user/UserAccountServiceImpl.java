package com.mainiway.service.impl.user;

import com.mainiway.bean.po.UserAccount;
import com.mainiway.common.base.BaseServiceImpl;
import com.mainiway.dao.IUserAccountDao;
import com.mainiway.service.IUserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018-05-22.
 */
@Service
public class UserAccountServiceImpl extends BaseServiceImpl<UserAccount> implements IUserAccountService{

    @Autowired
    private IUserAccountDao iUserAccountDao;

    @Override
    public UserAccount getAccountByUserId(String userId) {
        return iUserAccountDao.getAccountByUserId(userId);
    }
}
