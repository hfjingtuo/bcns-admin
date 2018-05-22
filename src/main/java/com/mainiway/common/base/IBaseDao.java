package com.mainiway.common.base;

import java.util.List;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 继承自己的MyMapper
 *
 * @author liuzh
 * @since 2015-09-06 21:53
 */
public interface IBaseDao<T> extends Mapper<T>, MySqlMapper<T> {
    //TODO
    //FIXME 特别注意，该接口不能被扫描到，否则会出错

	List<T> selectPageByExample(T model);
	T selectById(String id);
	List<T> selectByRecord(T model);
}
