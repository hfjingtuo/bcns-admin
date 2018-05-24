package com.mainiway.controller.user;

import com.gpm.pay.entity.zlian.MarginRegisterDTO;
import com.gpm.pay.entity.zlian.MarginSmsDTO;
import com.gpm.pay.utils.IdUtil;
import com.mainiway.bean.dto.ResponseResult;
import com.mainiway.bean.po.UserInfo;
import com.mainiway.bean.po.UserPayAccount;
import com.mainiway.bean.vo.UserPayAccountVo;
import com.mainiway.common.base.BaseController;
import com.mainiway.consts.IStatusMessage;
import com.mainiway.service.IUserPayAccountService;

import com.mainiway.service.impl.user.UserPayAccountServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Administrator on 2018-05-22.
 */
@RestController
@RequestMapping("/user")
public class UserPayAccountController extends BaseController {

    @Autowired
    private UserPayAccountServiceImpl userPayAccountServiceImpl;

    @ApiOperation(value = "注册开户")
    @RequestMapping("/marginRegister")
    public ResponseResult marginRegister(@RequestBody MarginRegisterDTO marginRegisterDTO) {
        // TODO: 2018-05-23  开户接口
        System.out.println(marginRegisterDTO.toString());
        Map map = userPayAccountServiceImpl.marginRegister(marginRegisterDTO);
        return getPayResponseResult(map);
    }

    @ApiOperation(value = "查询开户信息")
    @RequestMapping("/queryUserPayAccount/{userId}")
    public ResponseResult getUserPayAccountByUserId(@PathVariable String userId) {
        UserPayAccount userPayAccount = userPayAccountServiceImpl.getUserPayAccountByUserId(userId);
        return getResponseResult(userPayAccount, IStatusMessage.ResultStatus.SUCCESS);
    }

    /**
     * @Author: 张立华
     * @Description: 转账业务
     * @params: *
     * @return: *
     * @Date: 12:28 2018/5/22
     */
    @ApiOperation(value = "开户短信")
    @RequestMapping(value="/marginRegisterSms",method = RequestMethod.POST)
    public ResponseResult marginRegisterSms(@RequestParam("userNameText") String userNameText,@RequestParam("mobile") String mobile) {
        MarginSmsDTO marginSmsDTO  = new MarginSmsDTO();
        marginSmsDTO.setUserNameText(userNameText);
        marginSmsDTO.setMobile(mobile);
        marginSmsDTO.setMerchantSeqId(IdUtil.randomBase62(32));
        Map map = userPayAccountServiceImpl.marginRegisterSms(marginSmsDTO);
        return getPayResponseResult(map);
    }

}
