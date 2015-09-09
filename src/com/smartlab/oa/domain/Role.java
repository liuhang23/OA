package com.smartlab.oa.domain;

import java.util.Set;


//岗位管理
public class Role implements java.io.Serializable{
	private Long id;
	private String name; // 岗位名称
	private String description; // 岗位说明
	private Set<User> users; // 用户

	private Set<Privilege> privileges; //岗位对应的权限

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Set<Privilege> privileges) {
		this.privileges = privileges;
	}

}
