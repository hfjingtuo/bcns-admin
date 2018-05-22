package com.mainiway.bean.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class OperationVo extends BaseVo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5148818919828892528L;

	/** process_uuid. */
	private String processId;

	/** 工作流id. */
	private String workflowId;

	/** 工作流name. */
	private String workflowName;

	/** 服务id. */
	private String serviceId;

	/** 服务name. */
	private String serviceName;

	/** 录入人员. */
	private String reporter;

	/** 操作时间. */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date timestamp;

	/** 用户id. */
	private String userId;

	/** 用户人员. */
	private String userName;

	/** 结果描述. */
	private String description;

	/** 存证编号. */
	private String code;

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getWorkflowId() {
		return workflowId;
	}

	public void setWorkflowId(String workflowId) {
		this.workflowId = workflowId;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getReporter() {
		return reporter;
	}

	public void setReporter(String reporter) {
		this.reporter = reporter;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getWorkflowName() {
		return workflowName;
	}

	public void setWorkflowName(String workflowName) {
		this.workflowName = workflowName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
