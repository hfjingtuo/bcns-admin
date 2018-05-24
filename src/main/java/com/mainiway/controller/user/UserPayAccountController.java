package com.mainiway.controller.user;

import com.mainiway.bean.dto.ResponseResult;
import com.mainiway.bean.po.UserInfo;
import com.mainiway.bean.po.UserPayAccount;
import com.mainiway.common.base.BaseController;
import com.mainiway.consts.IStatusMessage;
import com.mainiway.service.IUserPayAccountService;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2018-05-22.
 */
@RestController
@RequestMapping("/register")
public class UserPayAccountController extends BaseController {

    @Autowired
    private IUserPayAccountService iUserPayAccountService;

    @ApiOperation(value = "注册开户")
    @RequestMapping("/register")
    public ResponseResult register(@RequestBody UserPayAccount userPayAccount) {
        // TODO: 2018-05-23  开户接口
        System.out.println(userPayAccount.toString());
        userPayAccount.setId(UUID.randomUUID().toString());
        iUserPayAccountService.register(userPayAccount);
        return getResponseResult(userPayAccount, IStatusMessage.ResultStatus.SUCCESS);
    }

    @ApiOperation(value = "查询开户信息")
    @RequestMapping("/getUserPayAccountByUserId/{userId}")
    public ResponseResult getUserPayAccountByUserId(@PathVariable String userId) {
        UserPayAccount userPayAccount = iUserPayAccountService.getUserPayAccountByUserId(userId);
        return getResponseResult(userPayAccount, IStatusMessage.ResultStatus.SUCCESS);
    }

}
