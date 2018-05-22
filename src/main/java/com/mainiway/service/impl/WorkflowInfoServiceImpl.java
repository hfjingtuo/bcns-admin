package com.mainiway.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.mainiway.bean.po.Service;
import com.mainiway.bean.po.WorkflowInfo;
import com.mainiway.bean.po.WorkflowOrder;
import com.mainiway.bean.vo.Message;
import com.mainiway.bean.vo.PageBase;
import com.mainiway.bean.vo.ResultMap;
import com.mainiway.bean.vo.WorkFlow;
import com.mainiway.bean.vo.WorkFlowInfoVo;
import com.mainiway.common.base.BaseServiceImpl;
import com.mainiway.service.IService;
import com.mainiway.service.IWorkflowInfoService;
import com.mainiway.service.IWorkflowOrderService;
import com.mainiway.utils.RandomUtil;

@org.springframework.stereotype.Service
public class WorkflowInfoServiceImpl extends BaseServiceImpl<WorkflowInfo> implements IWorkflowInfoService {
	
	@Autowired
	private IWorkflowOrderService workflowOrderService;
	
	@Autowired
	private IService iService;

	@Override
	@Transactional
	public Message registerWorkflow(WorkFlowInfoVo vo) {
		// 保存Workflowinfo
		WorkflowInfo workflowInfo = new WorkflowInfo();
		BeanUtils.copyProperties(vo, workflowInfo);
		String workflowInfoId = RandomUtil.randomUUID();
		workflowInfo.setId(workflowInfoId);
		insert(workflowInfo);
		// 保存WorkflowOrder
		List<WorkFlow> list = vo.getWorkflow();
		for (WorkFlow workflow : list) {
			WorkflowOrder workflowOrder = new WorkflowOrder();
			BeanUtils.copyProperties(workflow, workflowOrder);
			workflowOrder.setNum(workflow.getOrder());
			workflowOrder.setWorkflowId(workflowInfoId);
			workflowOrder.setId(RandomUtil.randomUUID());
			workflowOrderService.insert(workflowOrder);
		}
		return new Message("2000", "保存成功", new ResultMap(ResultMap.WORKFLOW_ID, workflowInfoId));
	}

	@Override
	public Message removeWorkflow(String id) {
		WorkflowInfo workflowInfo = new WorkflowInfo();
		workflowInfo.setId(id);
		delete(workflowInfo);
		return  new Message("2000", "删除成功", id);
	}

	@Override
	public Message getWorkflow(PageBase pb) {
		WorkflowInfo query = new WorkflowInfo();
		query.setPageNum(pb.getPageNum());
		query.setPageSize(pb.getPageSize());
		List<WorkflowInfo> list = selectPageByExample(query);
		List<WorkFlowInfoVo> listVo = new ArrayList<>();
		for (WorkflowInfo workflowInfo : list) {
			WorkFlowInfoVo vo = convertPoToVo(workflowInfo);
			listVo.add(vo);
		}
		return new Message("2000", "删除成功", listVo);
	}

	private WorkFlowInfoVo convertPoToVo(WorkflowInfo workflowInfo) {
		WorkFlowInfoVo vo = new WorkFlowInfoVo();
		BeanUtils.copyProperties(workflowInfo, vo);
		return vo;
	}

	@Override
	public Message queryWorkflowById(String id) {
		WorkflowInfo workflowInfo = selectById(id);
		if (ObjectUtils.isEmpty(workflowInfo)) {
			return new Message("2000", "查询结果为空", null);
		}
		WorkFlowInfoVo vo = new WorkFlowInfoVo();
		BeanUtils.copyProperties(workflowInfo, vo);
		//查询workorder
		List<WorkflowOrder> workflowOrderList = workflowOrderService.getWorkflowOrderListFromInfoId(id);
		List<WorkFlow> workFlows = new ArrayList<>();
		for (WorkflowOrder workflowOrder : workflowOrderList) {
			WorkFlow workFlow = new WorkFlow();
			BeanUtils.copyProperties(workflowOrder, workFlow);
			workFlow.setOrder(workflowOrder.getNum());
			Service service = iService.selectById(workflowOrder.getServiceId());
			if (!ObjectUtils.isEmpty(service)) {
				workFlow.setServiceId(workflowOrder.getServiceId());
				workFlow.setServiceName(service.getService());
			}
			workFlows.add(workFlow);
		}
		vo.setWorkflow(workFlows);
		return new Message("2000", "查询成功", vo);
	}

	@Override
	public Message getWorkflowCount() {
		WorkflowInfo query = new WorkflowInfo();
		int count = selectCountByExample(query);
		return new Message("2000", "查询成功", new ResultMap("count", String.valueOf(count)));
	}
}
