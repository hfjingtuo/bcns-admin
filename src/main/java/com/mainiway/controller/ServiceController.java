package com.mainiway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mainiway.bean.vo.LoansInfoVo;
import com.mainiway.bean.vo.Message;
import com.mainiway.bean.vo.OperationVo;
import com.mainiway.bean.vo.PageBase;
import com.mainiway.bean.vo.ServiceVo;
import com.mainiway.bean.vo.WorkFlowInfoVo;
import com.mainiway.common.base.BaseController;
import com.mainiway.service.ILoansInfoService;
import com.mainiway.service.IOperationService;
import com.mainiway.service.IService;
import com.mainiway.service.IWorkflowInfoService;
import com.mainiway.service.IWorkflowOrderService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/")
public class ServiceController extends BaseController {
//	private final static Logger logger = LoggerFactory.getLogger(ServiceController.class);
	
	@Autowired
	private IService iService;
	
	@Autowired
	private IWorkflowInfoService workflowInfoService;
	
	@Autowired
	private IWorkflowOrderService workflowOrderService;
	
	@Autowired
	private IOperationService operationService;
	
	@Autowired
	private ILoansInfoService loansInfoService;

	@ApiOperation(value = "服务注册")
	@RequestMapping(value = "registerService" ,method = { RequestMethod.POST })
	public Message registerService(@RequestBody ServiceVo serviceVo){
		Message msg = iService.registerService(serviceVo);
		return msg;
	}

	@ApiOperation(value = "服务删除")
	@RequestMapping(value = "removeService/{id}" ,method = { RequestMethod.GET })
	public Message removeService(@PathVariable String id){
		Message msg = iService.removeService(id);
		return msg;
	}
	
	@ApiOperation(value = "查询所有服务")
	@RequestMapping(value = "getService" ,method = { RequestMethod.POST })
	public Message getService(@RequestBody(required=false) PageBase pb){
		if (ObjectUtils.isEmpty(pb)) {
			pb = new PageBase();
		}
		Message msg =  iService.getService(pb);
		return msg;
	}
	
	@ApiOperation(value = "根据ID查询服务")
	@RequestMapping(value = "queryService/{id}" ,method = { RequestMethod.GET })
	public Message queryService(@PathVariable String id){
		Message msg = iService.queryServiceById(id);
		return msg;
	}
	
	@ApiOperation(value = "工作流服务新增")
	@RequestMapping(value = "registerWorkflow" ,method = { RequestMethod.POST })
	public Message registerWorkflow(@RequestBody WorkFlowInfoVo wf){
		Message msg = workflowInfoService.registerWorkflow(wf);
		return msg;
	}
	
	@ApiOperation(value = "工作流服务删除")
	@RequestMapping(value = "removeWorkflow/{id}" ,method = { RequestMethod.GET })
	public Message removeWorkflow(@PathVariable String id){
		Message msg = workflowInfoService.removeWorkflow(id);
		return msg;
	}
	
	@ApiOperation(value = "查询所有工作流服务")
	@RequestMapping(value = "getWorkflow" ,method = { RequestMethod.POST })
	public Message getWorkflow(@RequestBody(required=false) PageBase pb){
		if (ObjectUtils.isEmpty(pb)) {
			pb = new PageBase();
		}
		Message msg = workflowInfoService.getWorkflow(pb);
		return msg;
	}
	
	@ApiOperation(value = "根据ID查询工作流服务")
	@RequestMapping(value = "queryWorkflow/{id}" ,method = { RequestMethod.GET })
	public Message queryWorkflow(@PathVariable String id){
		Message msg = workflowInfoService.queryWorkflowById(id);
		return msg;
	}
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "根据ID查询工作流服务详情")
	@RequestMapping(value = "getWorkflowOrder/{id}" ,method = { RequestMethod.GET })
	public Message getWorkflowOrder(@PathVariable String id){
		Message msg = workflowOrderService.getWorkflowOrderFromInfo(id);
		return msg;
	}
	
	/**
	 * 录入新的操作记录（贷款记录+num+1）
	 * @param pv
	 * @return
	 */
	@ApiOperation(value = "操作记录新增")
	@RequestMapping(value = "reportOperation" ,method = { RequestMethod.POST })
	public Message reportOperation(@RequestBody OperationVo pv){
		if (StringUtils.isEmpty(pv.getProcessId()) || pv.getProcessId().equals("111111")) {
			pv.setProcessId("388180272895");			
		}
		Message msg = operationService.reportOperation(pv);
		return msg;
	}
	
	/**
	 * 获得或有的操作记录
	 * @param pb
	 * @return
	 */
	@ApiOperation(value = "查询所有操作记录")
	@RequestMapping(value = "getOperation" ,method = { RequestMethod.POST })
	public Message getOperation(@RequestBody(required=false) PageBase pb){
		if (ObjectUtils.isEmpty(pb)) {
			pb = new PageBase();
		}
		Message msg = operationService.getOperation(pb);
		return msg;
	}
	
	/**
	 * 根据processid查询本次申请的记录
	 * @param processId
	 * @return
	 */
	@ApiOperation(value = "根据processid查询本次申请的记录")
	@RequestMapping(value = "getUserOperation/{processId}" ,method = { RequestMethod.GET })
	public Message getUserOperation(@PathVariable String processId){
		Message msg = operationService.getUserOperation(processId);
		return msg;
	}
	
	/**
	 * 查询未完成的列表信息(size>num)
	 * @param userId
	 * @return
	 */
	@ApiOperation(value = "根据userId查询未完成的列表信息")
	@RequestMapping(value = "queryLoansList/{userId}" ,method = { RequestMethod.GET })
	public Message queryLoansList(@PathVariable String userId){
		Message msg = loansInfoService.queryLoansList(userId);
		return msg;
	}
	
	/**
	 * 根据id查询申请详情
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "根据id查询申请详情")
	@RequestMapping(value = "getLoans/{id}" ,method = { RequestMethod.GET })
	public Message getLoans(@PathVariable String id){
		Message msg =  loansInfoService.getLoans(id);
		return msg;
	}
	
	
	 
	/**
	 * 申请记录
	 * @param pv
	 * @return
	 */
	@ApiOperation(value = "申请记录")
	@RequestMapping(value = "registerLoans" ,method = { RequestMethod.POST })
	public Message registerLoans(@RequestBody LoansInfoVo pv){
		if (StringUtils.isEmpty(pv.getProcessId())||pv.getProcessId().equals("111111")) {
			pv.setProcessId("388180272895");			
		}
		Message msg = loansInfoService.registerLoans(pv);
		return msg;
	}
	
	/**
	 * 查询所有申请记录信息
	 * @param userId
	 * @return
	 */
	@ApiOperation(value = "查询所有申请记录信息")
	@RequestMapping(value = "queryLoans/{userId}" ,method = { RequestMethod.GET })
	public Message queryLoans(@PathVariable String userId){
		Message msg = loansInfoService.queryLoans(userId);
		return msg;
	}
	
	/**
	 * 获得服务总数量
	 * @return
	 */
	@ApiOperation(value = "获得服务总数量")
	@RequestMapping(value = "serviceCount" ,method = { RequestMethod.GET })
	public Message serviceCount(){
		Message msg =  iService.getServiceCount();
		return msg;
	}
	
	
	/**
	 * 获得工作流总数量
	 * @return
	 */
	@ApiOperation(value = "获得工作流总数量")
	@RequestMapping(value = "workflowCount" ,method = { RequestMethod.GET })
	public Message workflowCount(){
		Message msg =  workflowInfoService.getWorkflowCount();
		return msg;
	}
	
	
	/**
	 * 获得操作流程总数量
	 * @return
	 */
	@ApiOperation(value = "获得操作流程总数量")
	@RequestMapping(value = "operationCount" ,method = { RequestMethod.GET })
	public Message operationCount(){
		Message msg =  operationService.getOperationCount();
		return msg;
	}
	
}
