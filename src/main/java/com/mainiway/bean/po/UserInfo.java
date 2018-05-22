package com.mainiway.bean.po;

import javax.persistence.Table;

import com.mainiway.common.base.BaseEntry;

@Table(name = "user_info")
public class UserInfo extends BaseEntry {

	private static final long serialVersionUID = 1L;

	private String userName;
	private String password;
	
	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserInfo [userName=" + userName + ", password=" + password + ", id=" + id + ", remarks=" + remarks
				+ ", createBy=" + createBy + ", createDate=" + createDate + ", updateBy=" + updateBy + ", updateDate="
				+ updateDate + ", delFlag=" + delFlag + ", getUserName()=" + getUserName() + ", getPassword()="
				+ getPassword() + ", getPageNum()=" + getPageNum() + ", getPageSize()=" + getPageSize() + ", getId()="
				+ getId() + ", getRemarks()=" + getRemarks() + ", getCreateBy()=" + getCreateBy() + ", getCreateDate()="
				+ getCreateDate() + ", getUpdateBy()=" + getUpdateBy() + ", getUpdateDate()=" + getUpdateDate()
				+ ", isDelFlag()=" + isDelFlag() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
}
