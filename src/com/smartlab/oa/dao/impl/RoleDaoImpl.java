package com.smartlab.oa.dao.impl;

import org.springframework.stereotype.Repository;

import com.smartlab.oa.base.DaoSupport;
import com.smartlab.oa.base.DaoSupportImpl;
import com.smartlab.oa.dao.RoleDao;
import com.smartlab.oa.domain.Role;
@Deprecated
@Repository   //放入spring容器中
public class RoleDaoImpl extends DaoSupportImpl<Role> implements RoleDao{

}
