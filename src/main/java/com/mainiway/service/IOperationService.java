package com.mainiway.service;

import com.mainiway.bean.po.Operation;
import com.mainiway.bean.vo.Message;
import com.mainiway.bean.vo.OperationVo;
import com.mainiway.bean.vo.PageBase;
import com.mainiway.common.base.IBaseService;

public interface IOperationService extends IBaseService<Operation>{

	Message reportOperation(OperationVo pv);

	Message getOperation(PageBase pb);

	Message getUserOperation(String processId);

	Message getOperationCount();
	
}
