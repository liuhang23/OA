package com.smartlab.oa.test;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

@Component
@Scope("prototype")
public class TestAction extends ActionSupport {
	
	@Resource
	private TestService testService;
	
	public String execute(){
		System.out.println("--> TestAction.execute()");
		testService.saveTwoUsers();
		return  "success";
	}

}
