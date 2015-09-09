package com.smartlab.oa.test;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class SpringTest {
	//获取Spring中定义的bean实例(对象)
	private ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	//测试struts2与Spiring整合
	@Test
	public void testBean() throws Exception{
		TestAction testAction = (TestAction)ac.getBean("testAction"); 
		System.out.println(testAction);
	}
	
	
	//测试hibernate与Spring整合
	//1.测试SessionFacoty
	@Test
	public void testSessionFactory() throws Exception{
		SessionFactory sessionFactory = (SessionFactory) ac.getBean("sessionFactory");
		System.out.println(sessionFactory);
	}
	
	//2.测试事务
	@Test
	public void testTransaction() throws Exception{
		TestService testService = (TestService)ac.getBean("testService");
		testService.saveTwoUsers();
	}
}
