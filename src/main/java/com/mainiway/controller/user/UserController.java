package com.mainiway.controller.user;

import java.util.List;

import com.mainiway.bean.po.UserTransactionRecord;
import com.mainiway.service.impl.user.UserInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.mainiway.bean.dto.ResponseResult;
import com.mainiway.bean.po.UserInfo;
import com.mainiway.common.base.BaseController;
import com.mainiway.common.base.IStatusMessage;
import com.mainiway.service.IUserInfoService;

//@RestController
@RequestMapping("/userInfo")
public class UserController extends BaseController {

//	private static Logger log = Logger.getLogger(UserController.class);

	@Autowired
	private IUserInfoService userInfoService;
	@Autowired
	private UserInfoServiceImpl userInfoServiceImpl;

	@RequestMapping("/findAll")
	public ResponseResult findAll() {
		UserInfo model = new UserInfo();
		List<UserInfo> list = userInfoService.selectByRecord(model);
		System.out.println("--------------------" + list.size());
		return getResponseResult(list, IStatusMessage.ResultStatus.SUCCESS);
	}
	
	@RequestMapping("/insert")
	public ResponseResult insert(@Validated @RequestBody UserInfo entry, BindingResult binder) {
		if (binder.hasErrors()) {
			return getResponseResult(binder.getAllErrors(), IStatusMessage.ResultStatus.ERROR);
		}
		if (binder.hasErrors()) {
			return validateMessage(binder, true);
		}
		userInfoService.insert(entry);
		return getResponseResult(entry, IStatusMessage.ResultStatus.SUCCESS);
	}

	@RequestMapping("/update")
	public ResponseResult update(@Validated @RequestBody UserInfo entry, BindingResult binder) {
		if (binder.hasErrors()) {
			return getResponseResult(binder.getAllErrors(), IStatusMessage.ResultStatus.ERROR);
		}
		userInfoService.update(entry);
		return getResponseResult(entry, IStatusMessage.ResultStatus.SUCCESS);
	}

	@RequestMapping("/delete")
	public ResponseResult delete(@RequestBody UserInfo entry) {
		userInfoService.delete(entry);
		return getResponseResult(entry, IStatusMessage.ResultStatus.SUCCESS);
	}

	@RequestMapping("/findAllByPage")
	public ResponseResult findAllByPage(@RequestBody UserInfo entry) {
		List<UserInfo> list = userInfoService.selectPageByExample(entry);
		Integer count = userInfoService.selectCountByExample(entry);
		System.out.println("-----------------------"+list.size());
		System.out.println("-----------------------总数::"+count);
		return getResponseResult(list, IStatusMessage.ResultStatus.SUCCESS);
	}
	
	@RequestMapping("/queryUser/{id}")
	public ResponseResult queryUser(@PathVariable String id) {
		UserInfo userInfo = userInfoService.selectById(id);
		return getResponseResult(userInfo, IStatusMessage.ResultStatus.SUCCESS);
	}

	/**
	 * @Author: 张立华
	 * @Description: 转账业务
	 * @params: *
	 * @return: *
	 * @Date: 12:28 2018/5/22
	 */
	@RequestMapping(value="/transferAccount",method = RequestMethod.POST)
	public ResponseResult transferAccount(@RequestBody UserTransactionRecord userTransactionRecord) {
		userInfoServiceImpl.transferAccount(userTransactionRecord);
		return getResponseResult("", IStatusMessage.ResultStatus.SUCCESS);
	}



	
}
