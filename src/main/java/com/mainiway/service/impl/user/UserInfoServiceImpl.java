package com.mainiway.service.impl.user;

import com.alibaba.fastjson.JSONObject;
import com.gpm.pay.entity.zlian.MarginSmsDTO;
import com.gpm.pay.enums.zlian.SmsTradeTypeEnum;
import com.mainiway.bean.po.UserAccount;
import com.mainiway.bean.po.UserTransactionRecord;
import com.mainiway.dao.IUserInfoDao;
import com.mainiway.enums.TransferEnum;
import com.mainiway.utils.HttpUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mainiway.bean.po.UserInfo;
import com.mainiway.common.base.BaseServiceImpl;
import com.mainiway.service.IUserInfoService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;


@Service
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfo> implements IUserInfoService {
    @Autowired
    private IUserInfoDao userInfoDao;
    @Autowired
    private UserAccountServiceImpl userAccountServiceImpl;

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
    public synchronized TransferEnum transferAccount(UserTransactionRecord userTransactionRecord){
        //判断是否存在该用户，并且余额足够
        UserInfo fromUser =  super.selectById(userTransactionRecord.getFromUserId());
        UserInfo ToUser =  super.selectById(userTransactionRecord.getToUserId());
        UserAccount account = userAccountServiceImpl.getAccountByUserId(userTransactionRecord.getFromUserId());
        //如果余额不足，则提示余额不足
        if(null == account || null == account.getAmount() || userTransactionRecord.getAmt().compareTo(account.getAmount()) == -1){
            return TransferEnum.ACCOUT_BALANCE_LESS_FAIL;
        }
        //todo  执行证联支付接口
        //todo  扣减付款方金额
        //todo  增加收款方金额
        //todo  增加交易流水
        return TransferEnum.SUCCESS ;
    }


}
