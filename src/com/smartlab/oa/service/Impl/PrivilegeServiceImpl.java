package com.smartlab.oa.service.Impl;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartlab.oa.base.DaoSupportImpl;
import com.smartlab.oa.domain.Privilege;
import com.smartlab.oa.service.PrivilegeService;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class PrivilegeServiceImpl extends DaoSupportImpl<Privilege> implements PrivilegeService {

	@Override
	public List<Privilege> findTopList() {
		return getSession().createQuery(//
				"FROM Privilege p WHERE p.parent IS NULL")// HQL查询
				.list();
	}

	@Override
	public Collection<Privilege> getAllPrivilegeUrls() {

		return getSession().createQuery(// distinct 查询不重复的结果
				"SELECT DISTINCT p.url FROM Privilege p WHERE p.url IS NOT NULL")//
				.list();
	}

}
