package com.smartlab.oa.util;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.smartlab.oa.domain.User;

public class CheckPrivilegeInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// System.out.println("------->之前");
		// String result = invocation.invoke();//放行
		// System.out.println("------->之后");
		// return result;

		// 获取信息
		User user = (User)ActionContext.getContext().getSession().get("user"); //当前登陆用户
		String namespace = invocation.getProxy().getNamespace();
		String actionName = invocation.getProxy().getActionName();
		String privUrl = namespace + actionName; //对应权限URL

		// 如果未登陆，就跳转到登陆页面
		if (user == null) {
			if(privUrl.startsWith("/user_login")){  // "/user_loginUI , /user_login"
				//如果是去登陆，就放行
				return invocation.invoke();
			}else{
				//如果不是去登陆，就转到登陆页面
				return "loginUI";
			}
		}else { 	// 如果已登陆，就判断权限
			if (user.hasPrivilegeByUrl(privUrl)) {
				// >>如果有权限，就放行
				return invocation.invoke();
			} else {
				// >>如果没有权限，就跳转到提示页面
				return "noPrivilegeError";
			}
		}

	}

}
