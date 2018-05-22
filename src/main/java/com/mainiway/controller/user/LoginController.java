package com.mainiway.controller.user;

import com.mainiway.bean.dto.ResponseResult;
import com.mainiway.bean.po.UserInfo;
import com.mainiway.common.base.BaseController;
import com.mainiway.common.util.code.MD5;
import com.mainiway.consts.IStatusMessage;
import com.mainiway.controller.user.UserController;
import com.mainiway.service.IUserInfoService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("")
public class LoginController extends BaseController {

	private static Logger log = Logger.getLogger(UserController.class);

	@Autowired
	private IUserInfoService userInfoService;

	@ApiOperation(value = "用户登录")
	@RequestMapping("/login")
	public ResponseResult login(@RequestBody UserInfo user) {
		Map map = new HashMap();
		if (user != null) {
			UserInfo userInfo = userInfoService.getUserInfoByUserName(user.getUserName());
			if (userInfo != null && StringUtils.isNotBlank(user.getPassword()) && userInfo.getPassword().equals(MD5.toMD5(user.getPassword()))) {
				String token = MD5.toMD5(user.getUserName());
				map.put("token",token);
				map.put("user",userInfo);
				return getResponseResult(map, IStatusMessage.LoginStatus.SUCCESS);
			} else {
				map.put("user",user);
				return getResponseResult(map, IStatusMessage.LoginStatus.USER_OR_PASSWORD_ERROR);
			}
		}else{
			map.put("user",user);
			return getResponseResult(map, IStatusMessage.LoginStatus.USER_OR_PASSWORD_ERROR);
		}
	}

	@ApiOperation(value = "用户注册")
	@RequestMapping("/register")
	public ResponseResult register(@RequestBody UserInfo user) {
		if (user != null && StringUtils.isNotBlank(user.getUserName()) && StringUtils.isNotBlank(user.getPassword())){
			UserInfo userInfo = new UserInfo();
			userInfo.setUserName(user.getUserName());
			userInfo.setPassword(MD5.toMD5(user.getPassword()));
			userInfo.setCreateDate(new Date());
			userInfo.setDelFlag(false);
			userInfoService.insert(userInfo);
			return getResponseResult("",IStatusMessage.ResultStatus.SUCCESS);
		}else {
			return getResponseResult("",IStatusMessage.ResultStatus.ERROR);
		}
	}

}
