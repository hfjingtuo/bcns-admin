package com.mainiway.common.base;

import java.util.List;

public interface IBaseService<T> {
	
	void insert(T model);

	void update(T model);

	void delete(T model);
	
	List<T> selectByRecord(T model);
	
	List<T> selectPageByExample(T model);
	
	int selectCountByExample(T model);
	
	T selectById(String id);
}
