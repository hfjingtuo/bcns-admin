package com.mainiway.service;

import com.mainiway.bean.po.LoansInfo;
import com.mainiway.bean.vo.LoansInfoVo;
import com.mainiway.bean.vo.Message;
import com.mainiway.common.base.IBaseService;

public interface ILoansInfoService extends IBaseService<LoansInfo>{

	int updateWorkflowOrderNum(LoansInfo update);

	/** 
	 * 查询未完成的列表信息(size>num)
	 * @param userId
	 * @return    
	 * Message    
	 * @throws  if
	 * @author  fuxinle
	 * @Date 2018年1月15日 上午11:27:40 
	 */
	Message queryLoansList(String userId);

	/** 
	 * 根据id查询申请详情
	 * @param id
	 * @return    
	 * Message    
	 * @throws  if
	 * @author  fuxinle
	 * @Date 2018年1月15日 上午11:33:52 
	 */
	Message getLoans(String id);

	/** 
	 * 贷款申请记录
	 * @param pv
	 * @return    
	 * Message    
	 * @throws  if
	 * @author  fuxinle
	 * @Date 2018年1月15日 下午1:03:14 
	 */
	Message registerLoans(LoansInfoVo pv);

	/** 
	 * 查询用户下所有的列表信息
	 * @param userId
	 * @return    
	 * Message    
	 * @throws  if
	 * @author  fuxinle
	 * @Date 2018年1月15日 下午1:12:02 
	 */
	Message queryLoans(String userId);

}
