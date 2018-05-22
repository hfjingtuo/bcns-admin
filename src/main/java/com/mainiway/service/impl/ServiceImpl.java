package com.mainiway.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;

import com.alibaba.fastjson.JSONObject;
import com.mainiway.bean.po.Service;
import com.mainiway.bean.vo.Message;
import com.mainiway.bean.vo.PageBase;
import com.mainiway.bean.vo.ResultMap;
import com.mainiway.bean.vo.ServiceVo;
import com.mainiway.common.base.BaseServiceImpl;
import com.mainiway.service.IService;

@org.springframework.stereotype.Service
public class ServiceImpl extends BaseServiceImpl<Service> implements IService{
	
	@Override
	public Message registerService(ServiceVo vo) {
		Service service = new Service();
		BeanUtils.copyProperties(vo, service);
		try {
			service.setRequest(vo.getRequest().toString());
			service.setResponse(vo.getResponse().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		insert(service);
		return new Message("2000", "保存成功", new ResultMap(ResultMap.SERVICE_ID, service.getId()));
	}

	@Override
	public Message removeService(String id) {
		Service service = new Service();
		service.setId(id);
		delete(service);
		return new Message("2000", "删除成功", null);
	}

	@Override
	public Message getService(PageBase pb) {
		Service query = new Service();
		query.setPageNum(pb.getPageNum());
		query.setPageSize(pb.getPageSize());
		List<Service> resultList = selectPageByExample(query);
		List<ServiceVo> listVo = new ArrayList<>();
		try {
			for (Service service : resultList) {
				ServiceVo vo = convertPoToVo(service);
				listVo.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new Message("2000", "查询成功", listVo);
	}

	@Override
	public Message queryServiceById(String id) {
		Service service = selectById(id);
		if (ObjectUtils.isEmpty(service)) {
			return new Message("2001", "查询失败", null);
		}
		ServiceVo vo =  convertPoToVo(service);
		return new Message("2000", "查询成功", vo);
	}

	private ServiceVo convertPoToVo(Service service) {
		ServiceVo vo = new ServiceVo();
		BeanUtils.copyProperties(service, vo);
		try {
			vo.setRequest(JSONObject.parse(service.getRequest()));			
		} catch (Exception e) {
			vo.setRequest(service.getRequest());		
		}
		try {
			vo.setResponse(JSONObject.parse(service.getResponse()));
		} catch (Exception e) {
			vo.setResponse(service.getResponse());		
		}
		return vo;
	}

	@Override
	public Message getServiceCount() {
		Service query = new Service();
		int count = selectCountByExample(query);
		return new Message("2000", "查询成功", new ResultMap("count", String.valueOf(count)));
	}
	
	
}
