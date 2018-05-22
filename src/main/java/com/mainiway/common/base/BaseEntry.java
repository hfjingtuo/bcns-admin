package com.mainiway.common.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class BaseEntry implements Serializable {
	private static final long serialVersionUID = -5360098335792171953L;
	@Id
	protected String id;
	protected String remarks;
	protected String createBy;
	protected Date createDate;
	protected String updateBy;
	protected Date updateDate;
	protected boolean delFlag = false;
	
	//分页查询条件
	@Transient
	@JsonIgnore
	private int pageNum = 1;
	@Transient
	@JsonIgnore
	private int pageSize = 10;
	@Transient
	@JsonIgnore
	private int pageStart;

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getId() {
		return id;
	}

	public String getRemarks() {
		return remarks;
	}

	public String getCreateBy() {
		return createBy;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getCreateDate() {
		return createDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getUpdateDate() {
		return updateDate;
	}

	public boolean isDelFlag() {
		return delFlag;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public void setDelFlag(boolean delFlag) {
		this.delFlag = delFlag;
	}

	public int getPageStart() {
		pageStart = (pageNum -1)*pageSize;
		return pageStart;
	}

	public void setPageStart(int pageStart) {
		this.pageStart = pageStart;
	}


}
