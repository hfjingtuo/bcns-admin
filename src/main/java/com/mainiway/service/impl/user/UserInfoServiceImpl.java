package com.mainiway.service.impl.user;

import com.mainiway.bean.po.UserTransactionRecord;
import com.mainiway.dao.IUserInfoDao;
import com.mainiway.exception.TransferException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mainiway.bean.po.UserInfo;
import com.mainiway.common.base.BaseServiceImpl;
import com.mainiway.service.IUserInfoService;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfo> implements IUserInfoService {
    @Autowired
    private IUserInfoDao userInfoDao;

    @Override
    public UserInfo getUserInfoByUserName(String userName) {
        return userInfoDao.getUserInfoByUserName(userName);
    }

    /**
     * @Author: 张立华
     * @Description:转账
     * @params: *
     * @return: *
     * @Date: 14:13 2018/5/22
     */
    @Transactional
    public synchronized Boolean transferAccount(UserTransactionRecord userTransactionRecord){
       //判断是否存在该用户，并且余额足够
        UserInfo fromUser =  super.selectById(userTransactionRecord.getFromUserId());
        UserInfo ToUser =  super.selectById(userTransactionRecord.getToUserId());
        if(null != fromUser ){
//            new TransferException();
        }
        return true ;
    }
}
