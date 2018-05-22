package com.mainiway.bean.po;

import com.mainiway.common.base.BaseEntry;

public class LoansInfo extends BaseEntry {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** 用户id. */
	private String userId;

	/** 税号. */
	private String uscc;

	/** 公司名称. */
	private String company;

	/** 法人代表. */
	private String corporation;

	/** 工作流id. */
	private String workflowId;

	/** 贷款金额. */
	private String money;

	/** 截止日期. */
	private String deadline;

	/** 工作流中的节点数字(执行到第几步). */
	private Integer num;

	/** 工作流总数节点. */
	private Integer workflowSize;

	/** 本次请求流程中,唯一id. */
	private String processId;

	/** service_id. */
	private String serviceId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUscc() {
		return uscc;
	}

	public void setUscc(String uscc) {
		this.uscc = uscc;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCorporation() {
		return corporation;
	}

	public void setCorporation(String corporation) {
		this.corporation = corporation;
	}

	public String getWorkflowId() {
		return workflowId;
	}

	public void setWorkflowId(String workflowId) {
		this.workflowId = workflowId;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getWorkflowSize() {
		return workflowSize;
	}

	public void setWorkflowSize(Integer workflowSize) {
		this.workflowSize = workflowSize;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

}
