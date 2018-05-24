package com.mainiway.bean.po;

import javax.persistence.Table;

import com.mainiway.common.base.BaseEntry;
import lombok.Data;

//@Table(name = "user_info")
@Data
public class UserInfo extends BaseEntry {

	private static final long serialVersionUID = 1L;

	private String userName;
	private String password;
}
