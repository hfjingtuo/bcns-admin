package com.mainiway.bean.vo;

import java.util.HashMap;

public class ResultMap extends HashMap<String, String>{

	private static final long serialVersionUID = 1L;

	public static final String SERVICE_ID = "serviceId";
	public static final String WORKFLOW_ID = "workflowId";
	public static final String PROCESS_ID = "processId";
	public static final String LOANS_INFO_ID = "loansinfoId";
	
	public ResultMap(String key,String id) {
		put(key, id);
	}
}
