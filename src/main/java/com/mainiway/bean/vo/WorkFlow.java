package com.mainiway.bean.vo;

public class WorkFlow extends BaseVo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7477894967575310719L;

	/** 工作流详情id. */
	private String workflowId;

	/** 序号. */
	private Integer order;

	/** 服务id. */
	private String serviceId;

	/** 服务name. */
	private String serviceName;

	/** 描述. */
	private String description;

	public String getWorkflowId() {
		return workflowId;
	}

	public void setWorkflowId(String workflowId) {
		this.workflowId = workflowId;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
