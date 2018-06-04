package com.mainiway.bean.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: zhangxin
 * Date: Created in 2018/05/25 23:14
 * Copyright: Copyright (c) 2018
 * Description： 自定义返回结果
 */
public class ResponseResult implements Serializable {
	private static final long serialVersionUID = 7285065610386199394L;
	/**
	 * 返回码
	 */
	private String code;
	/**
	 * 返回结果描述
	 */
	private List<String> messages;
	private Object obj;

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<String> getMessages() {
		return this.messages;
	}

	public void setMessages(String message) {
		if (this.messages == null) {
			this.messages = new ArrayList<>(1);
		}
		this.messages.add(message);
	}
	
	public void setNullMessages() {
		this.messages = new ArrayList<>(1);
	}

	public Object getObj() {
		return this.obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
}
