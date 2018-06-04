package com.mainiway.common.base;

import com.mainiway.bean.dto.ResponseResult;
import com.mainiway.exception.NoTokenException;
import com.mainiway.exception.TokenErrorException;
import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class BaseController {
	private static Logger log = Logger.getLogger(BaseController.class);

	@ExceptionHandler
	protected ResponseResult exception(Exception e) {
		Object object = null;
		IStatusMessage iStatusMessage =null;
		if(e instanceof NoTokenException){
			object = "501，Token Null";
			iStatusMessage = IStatusMessage.AuthStatus.NULL;
		}else if(e instanceof TokenErrorException){
			object = "502，Token Invalid";
			iStatusMessage = IStatusMessage.AuthStatus.FAILED;
		}else if(e instanceof Exception){
			object = "500，Server Error";
			iStatusMessage = IStatusMessage.ResultStatus.ERROR;
		}
		log.error(getTrace(e));
		return getResponseResult(object, iStatusMessage);
	}

	public static String getTrace(Throwable e) {
		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		e.printStackTrace(writer);
		StringBuffer buffer = stringWriter.getBuffer();
		return "[" + e.getMessage() + "]" + buffer.toString();
	}

	protected ResponseResult getResponseResult(Object object, IStatusMessage status) {
		ResponseResult responseResult = new ResponseResult();
		responseResult.setObj(object);
		responseResult.setCode(status.getCode());
		responseResult.setMessages(status.getMessage());
		return responseResult;
	}

	protected ResponseResult getResponseResult(ResponseResult responseResult, Object object, IStatusMessage status) {
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
		String validErrorMsg;
		while (it.hasNext()) {
			ObjectError objectError = it.next();
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
