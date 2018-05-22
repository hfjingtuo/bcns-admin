package com.mainiway.bean.vo;

public class PageBase  extends BaseVo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6681243000319455499L;

	private int pageSize = 20;   //每页显示的数量
	
	private int pageNum = 1;     //第几页

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	
	
}
