package com.smartlab.oa.service;

import java.util.Collection;
import java.util.List;

import com.smartlab.oa.base.DaoSupport;
import com.smartlab.oa.domain.Privilege;

public interface PrivilegeService  extends DaoSupport<Privilege>{

	//查询所有顶级的权限
	List<Privilege> findTopList();

	//查询所有权限对用的URL集合（不重复）
	Collection<Privilege> getAllPrivilegeUrls();
}
