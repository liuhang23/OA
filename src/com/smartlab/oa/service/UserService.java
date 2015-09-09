package com.smartlab.oa.service;

import com.smartlab.oa.base.DaoSupport;
import com.smartlab.oa.domain.User;


public interface UserService extends DaoSupport<User>{

	//通过用户名和密码登陆
	User findByLoginNameAndPassword(String loginName, String password);

}
