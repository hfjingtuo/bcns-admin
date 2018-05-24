package com.mainiway.service.impl.user;

import com.alibaba.fastjson.JSONObject;
import com.gpm.pay.entity.zlian.Common208Result;
import com.gpm.pay.entity.zlian.MarginRegisterDTO;
import com.gpm.pay.entity.zlian.MarginSmsDTO;
import com.gpm.pay.enums.zlian.SmsTradeTypeEnum;
import com.gpm.pay.utils.IdUtil;
import com.mainiway.bean.po.UserAccount;
import com.mainiway.bean.po.UserPayAccount;
import com.mainiway.common.base.BaseServiceImpl;
import com.mainiway.common.constants.Global;
import com.mainiway.consts.IStatusMessage;
import com.mainiway.dao.IUserAccountDao;
import com.mainiway.dao.IUserPayAccountDao;
import com.mainiway.service.IUserPayAccountService;
import com.mainiway.utils.HttpUtil;
import com.mainiway.utils.RandomUtil;
import com.mainiway.utils.SecretKeyUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by Administrator on 2018-05-22.
 */
@Service
public class UserPayAccountServiceImpl extends BaseServiceImpl<UserPayAccount> implements IUserPayAccountService {

    @Autowired
    private IUserPayAccountDao iUserPayAccountDao;
    @Autowired
    private IUserAccountDao iUserAccountDao;


    @Override
    public UserPayAccount getUserPayAccountByUserId(String userId) {
        return iUserPayAccountDao.getUserPayAccountByUserId(userId);
    }

    /**
     * @Author: 张立华
     * @Description: 开户注册短信发送
     * @params: *
     * @return: *
     * @Date: 11:37 2018/5/24
     */
    public Map marginRegisterSms(MarginSmsDTO marginSmsDTO){
        marginSmsDTO.setTradeType(SmsTradeTypeEnum.PROTOCOL_REGISTRATION.getCode());
        String marginSmsDTOStr = JSONObject.toJSON(marginSmsDTO).toString();
        String url = Global.PAY_MARGIN_SMS_URL ;
        return HttpUtil.sendMsg(url,marginSmsDTOStr);
    }

    @Transactional
    public Map marginRegister(MarginRegisterDTO marginRegisterDTO) {
        String userId = marginRegisterDTO.getResv();
        marginRegisterDTO.setResv("");
        marginRegisterDTO.setFundSeqId(IdUtil.randomBase62(32));
        String marginSmsDTOStr = JSONObject.toJSON(marginRegisterDTO).toString();
        String url = Global.PAY_MARGIN_REGISTER_URL ;
        Map map = HttpUtil.sendMsg(url,marginSmsDTOStr);
        Map result = (Map)map.get("data");
        if(result !=null && result.get("respCode").toString().equals("RC00")){
            Common208Result common208Result = (Common208Result)result.get("data");
            //开户成功，将结果写入到数据库中
            UserPayAccount userPayAccount = new UserPayAccount();
            userPayAccount.setUserId(userId);
            userPayAccount.setUserNameText(marginRegisterDTO.getUserNameText());
            userPayAccount.setType("1");
            userPayAccount.setBankCode(marginRegisterDTO.getBankCode());
            userPayAccount.setBankProvinceCode(marginRegisterDTO.getBankProvinceCode());
            userPayAccount.setBankRegionCode(marginRegisterDTO.getBankRegionCode());
            userPayAccount.setCardNo(marginRegisterDTO.getCardNo());
            userPayAccount.setCertId(marginRegisterDTO.getCertId());
            userPayAccount.setCertType(marginRegisterDTO.getCertType());
            userPayAccount.setCustType(marginRegisterDTO.getCustType());
            userPayAccount.setMobile(marginRegisterDTO.getMobile());
            userPayAccount.setOrgId(marginRegisterDTO.getOrgId());
            userPayAccount.setPayUserId(common208Result.getUserId());
            super.insert(userPayAccount);
            //添加一个账号
            UserAccount userAccount = new UserAccount();
            userAccount.setUserId(userId);
            userAccount.setAmount(new BigDecimal(0));
            if(StringUtils.isNotBlank(marginRegisterDTO.getPayPassWord())){
                userAccount.setPayPassword(SecretKeyUtil.payPassword(marginRegisterDTO.getPayPassWord()));
            }
            userAccount.setId(RandomUtil.randomUUID());
            iUserAccountDao.insert(userAccount);
        }
        return map ;
    }

}
