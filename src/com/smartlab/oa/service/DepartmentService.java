package com.smartlab.oa.service;

import java.util.List;

import com.smartlab.oa.base.DaoSupport;
import com.smartlab.oa.domain.Department;

public interface DepartmentService extends DaoSupport<Department> {

	// 查询顶级列表
	List<Department> findTopList();

	// 查询子部门列表
	List<Department> findChildren(Long parentId);

	// List<Department> findAll();
	//
	// void delete(Long id);
	//
	// void update(Department department);
	//
	// Department getById(Long id);
	//
	// void save(Department model);

}
