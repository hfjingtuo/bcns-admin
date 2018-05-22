package com.mainiway.service;

import java.util.List;

import com.mainiway.bean.po.WorkflowOrder;
import com.mainiway.bean.vo.Message;
import com.mainiway.common.base.IBaseService;

public interface IWorkflowOrderService extends IBaseService<WorkflowOrder>{

	Message getWorkflowOrderFromInfo(String id);

	List<WorkflowOrder> getWorkflowOrderListFromInfoId(String id);
	
}
