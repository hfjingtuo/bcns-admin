package com.mainiway.dao;

import java.util.List;

import com.mainiway.bean.po.LoansInfo;
import com.mainiway.common.base.IBaseDao;

public interface ILoansInfoDao  extends IBaseDao<LoansInfo>{

	/** 
	 * 更新贷款记录中的，workflow_order.num字段
	 * @param update
	 * @return    
	 * int    
	 * @throws  if
	 * @author  fuxinle
	 * @Date 2018年1月15日 上午10:56:16 
	 */
	int updateWorkflowOrderNum(LoansInfo update);

	/** 
	 * 查询未完成的列表信息(size>num)
	 * @param userId
	 * @return    
	 * List<LoansInfo>    
	 * @throws  if
	 * @author  fuxinle
	 * @Date 2018年1月15日 上午11:30:36 
	 */
	List<LoansInfo> queryLoansList(String userId); 
}
