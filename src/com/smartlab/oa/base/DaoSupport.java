package com.smartlab.oa.base;

import java.util.List;

public interface DaoSupport<T> {
	// 保存实体
	void save(T entity);

	// 删除
	void delete(Long id);

	// 修改
	void update(T entity);

	// 通过Id查询
	T getById(Long id);

	// 通过多个Id查询
	List<T> getByIds(Long[] ids);

	// 查询所有
	List<T> findAll();

}
