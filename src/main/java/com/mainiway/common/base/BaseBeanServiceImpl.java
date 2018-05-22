package com.mainiway.common.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.mainiway.utils.RandomUtil;

public abstract class BaseBeanServiceImpl<T extends BaseBeanEntry>{

	@Autowired
	private IBaseDao<T> mapper;
	
	public void insert(T model){
		if (StringUtils.isEmpty(model.getId())) {
			model.setId(RandomUtil.randomUUID());			
		}
		mapper.insert(model);
	}

	public void update(T model){
		mapper.updateByPrimaryKeySelective(model);
	}

	public void delete(T model){
		mapper.deleteByPrimaryKey(model.getId());
	}
	
	public List<T> selectByRecord(T model) {
		return mapper.selectByRecord(model);
	}
	
	public List<T> selectPageByExample(T model) {
		return mapper.selectPageByExample(model);
	}
	
	public int selectCountByExample(T model) {
		return mapper.selectCount(model);
	}
	
	public T selectById(String id) {
		return mapper.selectById(id);
	}
}
