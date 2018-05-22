package com.mainiway.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import com.mainiway.bean.po.LoansInfo;
import com.mainiway.bean.po.Service;
import com.mainiway.bean.po.WorkflowInfo;
import com.mainiway.bean.po.WorkflowOrder;
import com.mainiway.bean.vo.LoansInfoVo;
import com.mainiway.bean.vo.Message;
import com.mainiway.bean.vo.ResultMap;
import com.mainiway.common.base.BaseServiceImpl;
import com.mainiway.dao.ILoansInfoDao;
import com.mainiway.service.ILoansInfoService;
import com.mainiway.service.IService;
import com.mainiway.service.IWorkflowInfoService;
import com.mainiway.service.IWorkflowOrderService;

@org.springframework.stereotype.Service
public class LoansInfoServiceImpl extends BaseServiceImpl<LoansInfo> implements ILoansInfoService, ConvertPoToVo{

	@Autowired
	private ILoansInfoDao dao;
	
	@Autowired
	private IService iService;
	
	@Autowired
	private IWorkflowInfoService workflowInfoService;
	
	@Autowired
	private IWorkflowOrderService workflowOrderService;
	
	@Override
	public int updateWorkflowOrderNum(LoansInfo update) {
		return dao.updateWorkflowOrderNum(update);
	}

	@Override
	public Message queryLoansList(String userId) {
		List<LoansInfo> list =  dao.queryLoansList(userId);		
		List<LoansInfoVo> listVo = new ArrayList<>();
		for (LoansInfo loansInfo : list) {
			LoansInfoVo vo = convertPoToVo(loansInfo);
			listVo.add(vo);
		}
		return new Message("2000", "查询成功", listVo);
	}

	@Override
	public Message getLoans(String id) {
		LoansInfo loansInfo = selectById(id);
		LoansInfoVo vo = convertPoToVo(loansInfo);
		return new Message("2000", "查询成功", vo);
	}

	@Override
	public Message registerLoans(LoansInfoVo pv) {
		LoansInfo loansInfo = new LoansInfo();
		BeanUtils.copyProperties(pv, loansInfo);
		WorkflowOrder record = new WorkflowOrder();
		record.setWorkflowId(pv.getWorkflowId());
		List<WorkflowOrder> list = workflowOrderService.selectByRecord(record);
		loansInfo.setWorkflowSize(list.size());
		loansInfo.setNum(0);
		//先判断processId是否存在，存在就更新
		LoansInfo query = new LoansInfo();
		query.setProcessId(pv.getProcessId());
		List<LoansInfo> queryList = selectByRecord(query);
		if (ObjectUtils.isEmpty(queryList)) {
			insert(loansInfo);			
		}else {
			loansInfo.setId(queryList.get(0).getId());
			update(loansInfo);
		}
		
		return new Message("2000", "申请成功", new ResultMap(ResultMap.LOANS_INFO_ID, loansInfo.getId()));
	}

	@Override
	public Message queryLoans(String userId) {
		LoansInfo query = new LoansInfo();
		query.setUserId(userId);
		List<LoansInfo> list =  selectByRecord(query);
		List<LoansInfoVo> listVo = new ArrayList<>();
		for (LoansInfo loansInfo : list) {
			LoansInfoVo vo = convertPoToVo(loansInfo);
			listVo.add(vo);
		}
		return new Message("2000", "查询成功", listVo);
	}

	private LoansInfoVo convertPoToVo(LoansInfo loansInfo) {
		LoansInfoVo vo = new LoansInfoVo();
		BeanUtils.copyProperties(loansInfo, vo);
		vo.setNum(loansInfo.getNum().toString());
		vo.setWorkflowSize(loansInfo.getWorkflowSize().toString());
		getWorkflowInfoName(loansInfo, vo);
		getServiceName(loansInfo, vo);
		return vo;
	}

	private void getServiceName(LoansInfo loansInfo, LoansInfoVo vo) {
		//查询service
		Service service = iService.selectById(loansInfo.getServiceId());
		if (!ObjectUtils.isEmpty(service)) {
			vo.setServiceName(service.getService());
		}
	}

	private void getWorkflowInfoName(LoansInfo loansInfo, LoansInfoVo vo) {
		//查询workflowinfo
		WorkflowInfo workflowInfo = workflowInfoService.selectById(loansInfo.getWorkflowId());
		if (!ObjectUtils.isEmpty(workflowInfo)) {
			vo.setWorkflowName(workflowInfo.getName());
		}
	}
}
