package com.mainiway.common.base;

import java.io.Serializable;

import javax.persistence.Id;

public class BaseBeanEntry implements Serializable {
	private static final long serialVersionUID = -5360098335792171953L;
	@Id
	protected String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
