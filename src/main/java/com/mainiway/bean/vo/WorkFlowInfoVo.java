package com.mainiway.bean.vo;

import java.util.List;

public class WorkFlowInfoVo extends BaseVo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4798189023916573854L;

	/** id. */
	private String id;

	/** name. */
	private String name;

	/** 供应商. */
	private String vendor;

	/** 审查. */
	private String audit;

	/** 描述. */
	private String description;

	private List<WorkFlow> workflow;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getAudit() {
		return audit;
	}

	public void setAudit(String audit) {
		this.audit = audit;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<WorkFlow> getWorkflow() {
		return workflow;
	}

	public void setWorkflow(List<WorkFlow> workflow) {
		this.workflow = workflow;
	}

}
