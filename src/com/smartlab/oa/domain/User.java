package com.smartlab.oa.domain;

import java.util.Collection;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;

public class User  implements java.io.Serializable{
	private Long id;
	private String name; // 用户姓名
	private String loginName; // 登陆名
	private String password; // 登陆密码
	private String gender; // 性别
	private String phoneNumber; // 电话
	private String email; // 邮箱
	private String description; // 说明

	private Department department; // 部门
	private Set<Role> roles; // 岗位

	// 判断本用户是否有指定名称的权限
	public boolean hasPrivilegeByName(String name) {
		// 超级管理员有所有的权限
		if (isAdmin()) {
			return true;
		}

		// 普通用户需要判断是否含有这个权限
		for (Role role : roles) { // 遍历用户对象
			for (Privilege privilege : role.getPrivileges()) {
				if (privilege.getName().equals(name)) {
					return true;
				}
			}
		}
		return false;
	}

	// 判断本用户是否有指定URL的权限
	public boolean hasPrivilegeByUrl(String privUrl) {
		// 超级管理员有所有的权限
		if (isAdmin()) {
			return true;
		}

		// >>去掉后面的参数 department_delete?id=%{id}&parentId=%{parent.id}
		int pos = privUrl.indexOf("?");
		if (pos > -1) { // 未
			privUrl = privUrl.substring(0, pos);
		}
		// >>去掉UI后缀 department_editUI?id=%{id}
		if (privUrl.endsWith("UI")) {
			privUrl = privUrl.substring(0, privUrl.length() - 2);
		}

	    //判断本URL不需要控制，则登陆用户就可以使用
		Collection<String> allPrivilegeUrls = (Collection<String>)ActionContext.getContext().getApplication().get("allPrivilegeUrls");  //在最大作用域中取值
		if(!allPrivilegeUrls.contains(privUrl)){
			return true;
		}else {
			// 普通用户要判断是否含有这个权限
			for (Role role : roles) { // 遍历用户对象
				for (Privilege privilege : role.getPrivileges()) {
					if (privUrl.equals(privilege.getUrl())) {
						return true;
					}
				}
			}
			return false;
		}	
	}

	// 判断本用户是否是超级管理员
	public boolean isAdmin() {
		return "admin".equals(loginName);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

}
