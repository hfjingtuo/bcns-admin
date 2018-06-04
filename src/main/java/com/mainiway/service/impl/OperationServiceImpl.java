package com.mainiway.service.impl;

import com.mainiway.bean.po.*;
import com.mainiway.bean.vo.Message;
import com.mainiway.bean.vo.OperationVo;
import com.mainiway.bean.vo.PageBase;
import com.mainiway.bean.vo.ResultMap;
import com.mainiway.common.base.BaseServiceImpl;
import com.mainiway.service.*;
import com.mainiway.utils.IdGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class OperationServiceImpl extends BaseServiceImpl<Operation> implements IOperationService{
	private final static Logger logger = LoggerFactory.getLogger(OperationServiceImpl.class);
	
	@Autowired
	private IService iService;
	
	@Autowired
	private IWorkflowInfoService workflowInfoService;
	
	@Autowired
	private IWorkflowOrderService workflowOrderService;
	
	@Autowired
	private ILoansInfoService loansInfoService;

	@Override
	@Transactional
	public Message reportOperation(OperationVo pv) {
		Operation operation = new Operation();
		BeanUtils.copyProperties(pv, operation);
		if (StringUtils.isEmpty(pv.getWorkflowId())) {
			//部分数据不完成，从db中查询并赋值
			queryParmsFromDb(operation);
		}
		if ("业务链".equals(operation.getReporter())) {
			operation.setCode("YW"+IdGenerator.generateCommdityId());
		}else if ("诚信链".equals(operation.getReporter())) {
			operation.setCode("CX"+IdGenerator.generateCommdityId());
		}else {
			operation.setCode("OT"+IdGenerator.generateCommdityId());
		}
		//查询serviceid是否存在workflowOrder中，及已存在operation中，如果都满足，就不用贷款信息表，
		boolean flag = queryIsWorkFlowOrder(operation);
		//先判断，再保存
		insert(operation);
		if (flag==false) {
			logger.error("保存非WorkFlowOrder流程：" + operation.getDescription());
			return new Message("2000", "保存成功", new ResultMap(ResultMap.PROCESS_ID, operation.getProcessId()));
		}
		//更新贷款记录表，num+1，并更新service_id的值
		LoansInfo update = new LoansInfo();
		update.setProcessId(operation.getProcessId());
		update.setServiceId(operation.getServiceId());
		//增加了一个判断（对比相同的service_id），如果一个serviceid，多次连续调用，就不更新num了
		loansInfoService.updateWorkflowOrderNum(update);
		return new Message("2000", "保存成功", new ResultMap(ResultMap.PROCESS_ID, operation.getProcessId()));
	}

	private boolean queryIsWorkFlowOrder(Operation operation) {
		WorkflowOrder query = new WorkflowOrder();
		query.setWorkflowId(operation.getWorkflowId());
		query.setServiceId(operation.getServiceId());
		List<WorkflowOrder> list = workflowOrderService.selectByRecord(query);
		//第一次判断
		if (ObjectUtils.isEmpty(list) ) {  //order里没有，就不用更新，是错误的serviceid
			return false;			
		}
		Operation operationQuery = new Operation();
		operationQuery.setProcessId(operation.getProcessId());
		operationQuery.setServiceId(operation.getServiceId());
		List<Operation> operationList = selectByRecord(operationQuery);
		//第二次判断
		if (!ObjectUtils.isEmpty(operationList)) {  //不等于空，就不用更新
			return false;		
		}
		return true;
	}

	/** 
	 * 根据processid查询所有Opertion对象，并为新对象赋值
	 * @param operation    
	 * void    
	 * @throws  if
	 * @author  fuxinle
	 * @Date 2018年1月17日 下午1:13:52 
	 */
	private void queryParmsFromDb(Operation operation) {
		LoansInfo query = new LoansInfo();
		query.setProcessId(operation.getProcessId());
		List<LoansInfo> list = loansInfoService.selectByRecord(query);
		if (!ObjectUtils.isEmpty(list)) {
			LoansInfo firstOne = list.get(0);
			operation.setWorkflowId(firstOne.getWorkflowId());
			operation.setUserId(firstOne.getUserId());
			operation.setUserName(firstOne.getCorporation());
		}
	}

	@Override
	public Message getOperation(PageBase pb) {
		Operation query = new Operation();
		query.setPageNum(pb.getPageNum());
		query.setPageSize(pb.getPageSize());
		List<Operation> list = selectPageByExample(query);
		List<OperationVo> listVo = new ArrayList<>();
		for (Operation operation : list) {
			OperationVo vo = new OperationVo();
			BeanUtils.copyProperties(operation, vo);
			//查询serviceName
			Service service = iService.selectById(operation.getServiceId());
			if (!ObjectUtils.isEmpty(service)) {
				vo.setServiceName(service.getService());				
			}
			//查询workflowName
			WorkflowInfo workflowInfo = workflowInfoService.selectById(operation.getWorkflowId());
			if (!ObjectUtils.isEmpty(workflowInfo)) {
				vo.setWorkflowName(workflowInfo.getName());
			}
			listVo.add(vo);
		}
		return new Message("2000", "查询成功", listVo);
	}

	@Override
	public Message getUserOperation(String processId) {
		Operation query = new Operation();
		query.setProcessId(processId);
		List<Operation> list = selectByRecord(query);
		if (ObjectUtils.isEmpty(list)) {
			return new Message("2000", "查询结果为空", null);
		}
		List<OperationVo> listVo = new ArrayList<>();
		for (Operation operation : list) {
			OperationVo vo = new OperationVo();
			listVo.add(vo);
			BeanUtils.copyProperties(operation, vo);
			//查询workflowinfo
			WorkflowInfo workflowInfo = workflowInfoService.selectById(operation.getWorkflowId());
			if (!ObjectUtils.isEmpty(workflowInfo)) {
				vo.setWorkflowName(workflowInfo.getName());				
			}
			//查询serviceName
			Service service = iService.selectById(operation.getServiceId());
			if (!ObjectUtils.isEmpty(service)) {
				vo.setServiceName(service.getService());				
			}
		}
		return new Message("2000", "查询成功", listVo);
	}

	@Override
	public Message getOperationCount() {
		Operation query = new Operation();
		int count = selectCountByExample(query);
		return new Message("2000", "查询成功", new ResultMap("count", String.valueOf(count)));
	}
}
