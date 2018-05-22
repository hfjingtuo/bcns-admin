package com.mainiway.service;

import com.mainiway.bean.po.WorkflowInfo;
import com.mainiway.bean.vo.Message;
import com.mainiway.bean.vo.PageBase;
import com.mainiway.bean.vo.WorkFlowInfoVo;
import com.mainiway.common.base.IBaseService;

public interface IWorkflowInfoService extends IBaseService<WorkflowInfo>{
	
	/** 
	 * 
	 * @param vo
	 * @return    
	 * Message    
	 * @throws  if
	 * @author  fuxinle
	 * @Date 2018年1月13日 上午11:24:50 
	 */
	Message registerWorkflow(WorkFlowInfoVo vo);

	Message removeWorkflow(String id);

	Message getWorkflow(PageBase pb);

	Message queryWorkflowById(String id);

	Message getWorkflowCount();
}
