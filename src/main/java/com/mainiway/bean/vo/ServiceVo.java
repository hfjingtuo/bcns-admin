package com.mainiway.bean.vo;

public class ServiceVo extends BaseVo {

	private static final long serialVersionUID = 1L;

	/** ip. */
	private String ip;

	/** name. */
	private String name;

	/** url. */
	private String url;

	/** 服务. */
	private String service;

	/** 版本. */
	private String version;

	/** 供应商. */
	private String vendor;

	/** 描述. */
	private String description;

	/** 入参 */
	private Object request;

	/** 出参 */
	private Object response;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Object getRequest() {
		return request;
	}

	public void setRequest(Object request) {
		this.request = request;
	}

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}


}
