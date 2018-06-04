package com.mainiway.service.impl.user;

import com.mainiway.bean.po.UserAccount;
import com.mainiway.bean.po.UserInfo;
import com.mainiway.bean.po.UserTransactionRecord;
import com.mainiway.common.base.BaseServiceImpl;
import com.mainiway.dao.IUserInfoDao;
import com.mainiway.enums.TransferEnum;
import com.mainiway.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


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

    private String passwordToHash(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(password.getBytes());
            byte[] src = digest.digest();
            StringBuilder stringBuilder = new StringBuilder();
            // 字节数组转16进制字符串
            // https://my.oschina.net/u/347386/blog/182717
            for (byte aSrc : src) {
                String s = Integer.toHexString(aSrc & 0xFF);
                if (s.length() < 2) {
                    stringBuilder.append('0');
                }
                stringBuilder.append(s);
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException ignore) {
        }
        return null;
    }

    public boolean comparePassword(UserInfo user, UserInfo userInDataBase) {
        return passwordToHash(user.getPassword())      // 将用户提交的密码转换为 hash
                .equals(userInDataBase.getPassword()); // 数据库中的 password 已经是 hash，不用转换
    }

}
