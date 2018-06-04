package com.mainiway.utils;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mainiway.bean.dto.ResponseResult;
import com.mainiway.common.constants.Message.Result;
import com.mainiway.common.base.IStatusMessage.ResultStatus;

/*****************************************************************************
 *模块名 : TODO RestClient
 *创建时间 : 2017年10月20日
 *实现功能 : TODO 封装后的类，调用rest接口responseResult对象使用
 *作者 : fuxinle
 *版本 : v0.0.1
-----------------------------------------------------------------------------
 *修改记录:
 *日 期 版本 修改人 修改内容
 *2017年10月20日 v0.0.1 fuxinle 创建
 *****************************************************************************/
@Component
public class RestClient {
	private static Logger logger = LoggerFactory.getLogger(RestClient.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ObjectMapper objectMapper;

	/**====================================================================
	 *函 数 名： postForResponseResult
	 *@param url 请求url
	 *@param T 请求的返回值对象
	 *@return
	 *功 能： TODO
	----------------------------------------------------------------------
	 *修改记录 ：无参数Post获取信息调用接口
	 *日 期  版本 修改人 修改内容
	 *2017年10月20日 v0.0.1 fuxinle 创建
	====================================================================
	 * @throws Exception */
	public <T> T postForResponseResult(Class<T> clazz,String url) throws Exception {
		HttpEntity<String> httpEntity = createHttpEntity(null,null);
		ResponseEntity<T> entry = null;
		try {
			entry = restTemplate.postForEntity(url, httpEntity, clazz);
		} catch (Exception e) {
			logger.error(Result._900301.msg(),e);
			throw new Exception(Result._900301.msg());
		}
		// 对结果的状态进行判断
		if (entry == null || entry.getStatusCode().value() != 200) {
			return null;
		}
		if (entry.hasBody()) {
			T result = entry.getBody();
			return result;
		}
		return null;
	}
	
	/**====================================================================
	 *函 数 名： postForResponseResult
	 *@param url
	 *@return
	 *功 能： TODO
	----------------------------------------------------------------------
	 *修改记录 ：无参数，但有Header信息，Post获取信息调用接口
	 *日 期  版本 修改人 修改内容
	 *2017年10月20日 v0.0.1 fuxinle 创建
	====================================================================
	 * @throws Exception */
	public <T> T postForResponseResult(Class<T> clazz,String url,Map<String,String> headerMap) throws Exception {
		HttpEntity<String> httpEntity = createHttpEntity(headerMap,null);
		ResponseEntity<T> entry = null;
		try {
			entry = restTemplate.postForEntity(url, httpEntity, clazz);
		} catch (Exception e) {
			logger.error(Result._900301.msg(),e);
			throw new Exception(Result._900301.msg());
		}
		// 对结果的状态进行判断
		if (entry == null || entry.getStatusCode().value() != 200) {
			return null;
		}
		if (entry.hasBody()) {
			T result = entry.getBody();
			return result;
		}
		return null;
	}
	
	/**====================================================================
	 *函 数 名： postForResponseResult
	 *@param url
	 *@return
	 *功 能： TODO
	----------------------------------------------------------------------
	 *修改记录 ：有参数，Post获取或提交调用接口
	 *日 期  版本 修改人 修改内容
	 *2017年10月20日 v0.0.1 fuxinle 创建
	====================================================================
	 * @throws Exception */
	public <T> T  postForResponseResult(Class<T> clazz,String url,Object requestBody) throws Exception {
		HttpEntity<String> httpEntity = createHttpEntity(null,requestBody);
		ResponseEntity<T> entry = null;
		try {
			entry = restTemplate.postForEntity(url, httpEntity, clazz);
		} catch (Exception e) {
			logger.error(Result._900301.msg(),e);
			throw new Exception(Result._900301.msg());
		}
		// 对结果的状态进行判断
		if (entry == null || entry.getStatusCode().value() != 200) {
			return null;
		}
		if (entry.hasBody()) {
			T result = entry.getBody();
			return result;
		}
		return null;
	}

	/**====================================================================
	 *函 数 名： postForResponseResult
	 *@param url
	 *@return
	 *功 能： TODO
	----------------------------------------------------------------------
	 *修改记录 ：有参数，有header信息，Post获取或提交调用接口
	 *日 期  版本 修改人 修改内容
	 *2017年10月20日 v0.0.1 fuxinle 创建
	====================================================================
	 * @throws Exception */
	public <T>T postForResponseResult(Class<T> clazz,String url,Object requestBody,Map<String, String> headerMap) throws Exception {
		HttpEntity<String> httpEntity = createHttpEntity(headerMap,requestBody);
		ResponseEntity<T> entry = null;
		try {
			entry = restTemplate.postForEntity(url, httpEntity, clazz);
		} catch (Exception e) {
			logger.error(Result._900301.msg(),e);
			throw new Exception(Result._900301.msg());
		}
		// 对结果的状态进行判断
		if (entry == null || entry.getStatusCode().value() != 200) {
			return null;
		}
		if (entry.hasBody()) {
			T result = entry.getBody();
			return result;
		}
		return null;
	}

	/**====================================================================
	 *函 数 名： createHttpEntity
	 *@param headerMap
	 *@param object
	 *@return
	 *功 能： TODO 根据header头和入参对象创建httpEntity
	----------------------------------------------------------------------
	 *修改记录 ：
	 *日 期  版本 修改人 修改内容
	 *2017年10月20日 v0.0.1 fuxinle 创建
	====================================================================*/
	private HttpEntity<String> createHttpEntity(Map<String,String> headerMap, Object object) {
		HttpHeaders headers = new HttpHeaders(); 
		MediaType type = MediaType.parseMediaType("application/x-www-form-urlencoded; charset=UTF-8");
		headers.setContentType(type);
		if (headerMap != null) {
			addHeaderMap(headers,headerMap);
		}
		String reqJsonStr = null;
		if (object != null) {
			headers.setContentType(MediaType.APPLICATION_JSON);
			reqJsonStr = convertObjectToJsonString(object);
		}
		HttpEntity<String> entity = new HttpEntity<String>(reqJsonStr,headers);
		return entity;
	}
	
	private String convertObjectToJsonString(Object object) {
		String reqJsonStr = "";
		try {
			reqJsonStr = objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			logger.error("转化对象为jsonString失败。",e);
		}
		return reqJsonStr;
	}

	//添加头部信息
	private void addHeaderMap(HttpHeaders headers, Map<String, String> headerMap) {
		for (Map.Entry<String, String> entry : headerMap.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			headers.add(key, value);
		}
	}
	
	/**====================================================================
	 *函 数 名： createErrorResponse
	 *@return
	 *功 能： TODO 当无法获取接口返回值时，对前端口返回一下异常的response
	----------------------------------------------------------------------
	 *修改记录 ：
	 *日 期  版本 修改人 修改内容
	 *2017年10月25日 v0.0.1 fuxinle 创建
	====================================================================*/
	public ResponseResult createErrorResponse(){
		ResponseResult result = new ResponseResult();
		result.setObj(null);
		result.setCode(ResultStatus.ERROR.getCode());
		result.setMessages(Result._900301.msg());
		return result;
	}
}
