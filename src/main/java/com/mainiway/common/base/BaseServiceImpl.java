package com.mainiway.common.base;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.mainiway.utils.RandomUtil;

public abstract class BaseServiceImpl<T extends BaseEntry>{

	@Autowired
	private IBaseDao<T> mapper;
	
	public void insert(T model){
		if (StringUtils.isEmpty(model.getId())) {
			model.setId(RandomUtil.randomUUID());			
		}
		mapper.insert(model);
	}

	public void update(T model){
		model.setUpdateDate(new Date());
		mapper.updateByPrimaryKeySelective(model);
	}

	public void delete(T model){
		model.setUpdateDate(new Date());
		model.setDelFlag(true);
		mapper.updateByPrimaryKeySelective(model);
	}
	
	public List<T> selectByRecord(T model) {
		model.setDelFlag(false);
		List<T> list = mapper.selectByRecord(model);
		Collections.sort(list,Comparator.comparing(T::getCreateDate).reversed());
		return list;
	}
	
	public List<T> selectPageByExample(T model) {
		model.setDelFlag(false);
		List<T> list = mapper.selectPageByExample(model);
		Collections.sort(list,Comparator.comparing(T::getCreateDate).reversed());
		return list;
	}
	
	public int selectCountByExample(T model) {
		model.setDelFlag(false);
		return mapper.selectCount(model);
	}
	
	public T selectById(String id) {
		return mapper.selectById(id);
	}
}
