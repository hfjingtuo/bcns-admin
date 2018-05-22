package com.mainiway.bean.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ResponseResult implements Serializable {
	private static final long serialVersionUID = 7285065610386199394L;
	private String code;
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
