package com.mainiway.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import com.mainiway.bean.po.Service;
import com.mainiway.bean.po.WorkflowOrder;
import com.mainiway.bean.vo.Message;
import com.mainiway.bean.vo.WorkFlow;
import com.mainiway.common.base.BaseBeanServiceImpl;
import com.mainiway.service.IService;
import com.mainiway.service.IWorkflowOrderService;

@org.springframework.stereotype.Service
public class WorkflowOrderServiceImpl extends BaseBeanServiceImpl<WorkflowOrder> implements IWorkflowOrderService{

	@Autowired
	private IService iService;
	
	@Override
	public List<WorkflowOrder> getWorkflowOrderListFromInfoId(String id){
		WorkflowOrder query = new WorkflowOrder();
		query.setWorkflowId(id);
		List<WorkflowOrder> list = selectByRecord(query);
		return list;
	}
	
	
	@Override
	public Message getWorkflowOrderFromInfo(String id) {
		WorkflowOrder query = new WorkflowOrder();
		query.setWorkflowId(id);
		List<WorkflowOrder> list = selectByRecord(query);
		if (ObjectUtils.isEmpty(list)) {
			return new Message("2000", "查询结果为空", null);
		}
		//根据num排序
		Collections.sort(list, Comparator.comparingInt(WorkflowOrder::getNum));
		List<WorkFlow> listVo = new ArrayList<>();
		for (WorkflowOrder workflowOrder : list) {
			WorkFlow vo = ConvertPoToVo(workflowOrder);
			listVo.add(vo);
		}
		return new Message("2000", "查询成功", listVo);
	}

	private WorkFlow ConvertPoToVo(WorkflowOrder workflowOrder) {
		WorkFlow vo = new WorkFlow();
		BeanUtils.copyProperties(workflowOrder, vo);
		vo.setOrder(workflowOrder.getNum());
		Service service = iService.selectById(workflowOrder.getServiceId());
		if (!ObjectUtils.isEmpty(service)) {
			vo.setServiceName(service.getService());
		}
		return vo;
	}
}
