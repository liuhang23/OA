package com.smartlab.oa.dao.impl;

import org.springframework.stereotype.Repository;

import com.smartlab.oa.base.DaoSupport;
import com.smartlab.oa.base.DaoSupportImpl;
import com.smartlab.oa.dao.UserDao;
import com.smartlab.oa.domain.User;
@Deprecated
@Repository  //放入容器中，获取SessionFactory
public class UserDaoImpl extends DaoSupportImpl<User> implements UserDao{

}
