package com.mainiway.controller.user;

import com.mainiway.bean.dto.ResponseResult;
import com.mainiway.bean.po.UserAccount;
import com.mainiway.common.base.BaseController;
import com.mainiway.consts.IStatusMessage;
import com.mainiway.service.IUserAccountService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by Administrator on 2018-05-22.
 */
@RestController
@RequestMapping("/userAccount")
public class UserAccountController extends BaseController {

    @Autowired
    private IUserAccountService iUserAccountService;

    @ApiOperation(value = "查询账户余额")
    @RequestMapping("/getAccountByUserId/{userId}")
    public ResponseResult getAccountByUserId(@PathVariable String userId) {
            UserAccount userAccount = iUserAccountService.getAccountByUserId(userId);
            return getResponseResult(userAccount,IStatusMessage.ResultStatus.SUCCESS);
    }

}
