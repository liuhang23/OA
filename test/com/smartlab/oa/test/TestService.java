package com.smartlab.oa.test;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartlab.oa.domain.User;

@Service
public class TestService {
	
	@Resource
	private SessionFactory sessionFactory;
	
	@Transactional
	public void saveTwoUsers() {
		Session session = sessionFactory.getCurrentSession();
		session.save(new User());
		//int a = 1/0;   //这里会抛出异常
		session.save(new User());
	}

}
