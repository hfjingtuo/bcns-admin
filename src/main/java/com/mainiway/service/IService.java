package com.mainiway.service;

import com.mainiway.bean.po.Service;
import com.mainiway.bean.vo.Message;
import com.mainiway.bean.vo.PageBase;
import com.mainiway.bean.vo.ServiceVo;
import com.mainiway.common.base.IBaseService;

public interface IService extends IBaseService<Service>{
	
	Message registerService(ServiceVo vo);

	Message removeService(String id);

	Message getService(PageBase pb);

	Message queryServiceById(String id);

	Message getServiceCount();
}
