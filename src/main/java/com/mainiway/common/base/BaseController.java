package com.mainiway.common.base;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mainiway.bean.dto.ResponseResult;
import com.mainiway.consts.IStatusMessage;

public class BaseController {
	private static Logger log = Logger.getLogger(BaseController.class);

	@ExceptionHandler({ Exception.class })
	protected ResponseResult handleException(Exception e) {
		log.error("Server error", e);
		return getResponseResult("500，Server Error", IStatusMessage.ResultStatus.ERROR);
	}

	protected ResponseResult getResponseResult(Object object, IStatusMessage status) {
		ResponseResult responseResult = new ResponseResult();
		responseResult.setObj(object);
		responseResult.setCode(status.getCode());
		responseResult.setMessages(status.getMessage());
		return responseResult;
	}

	protected ResponseResult getResponseResult(
			ResponseResult responseResult, Object object, IStatusMessage status) {
		responseResult.setObj(object);
		responseResult.setCode(status.getCode());
		responseResult.setNullMessages();
		responseResult.setMessages(status.getMessage());
		return responseResult;
	}

	protected ResponseResult validateMessage(BindingResult binder, boolean returnOne) {
		List<ObjectError> objectErrors = binder.getAllErrors();
		Iterator<ObjectError> it = objectErrors.iterator();
		ResponseResult responseResult = new ResponseResult();
		String validErrorMsg = null;

		while (it.hasNext()) {
			ObjectError objectError = (ObjectError) it.next();
			if (objectError instanceof FieldError) {
				FieldError fieldError = (FieldError) objectError;
				validErrorMsg = fieldError.getField() + ":" + fieldError.getDefaultMessage();
			} else {
				validErrorMsg = objectError.getObjectName() + ":" + objectError.getDefaultMessage();
			}

			responseResult.setMessages(validErrorMsg);
			if (returnOne) {
				break;
			}
		}
		responseResult.setCode("500");

		return responseResult;
	}


	protected ResponseResult getPayResponseResult(Map map) {
		Map result = (Map)map.get("data");
		if(result !=null && result.get("respCode").toString().equals("RC00")){
			return getResponseResult("", IStatusMessage.ResultStatus.SUCCESS);
		}else{
			if(result !=null && result.get("respDesc") !=null ){
				return getResponseResult(result.get("respDesc"), IStatusMessage.ResultStatus.ERROR);
			}else{
				return getResponseResult(map.get("msg"), IStatusMessage.ResultStatus.ERROR);
			}
		}
	}
}
