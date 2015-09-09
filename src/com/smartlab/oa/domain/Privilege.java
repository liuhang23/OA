package com.smartlab.oa.domain;

import java.util.Set;

public class Privilege implements java.io.Serializable{
	private Long id;
	private String name; // 权限名字
	private String url;  //权限路径
	
	private Set<Role> roles;   //岗位对应的权限  
	private Privilege parent;   //权限上级
	private Set<Privilege> children; //权限下级
	
	public Privilege(){
		
	}
	//构造函数
	public Privilege(String name,String url,Privilege parent){
		this.name = name;
		this.url = url;
		this.parent = parent;
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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Privilege getParent() {
		return parent;
	}

	public void setParent(Privilege parent) {
		this.parent = parent;
	}

	public Set<Privilege> getChildren() {
		return children;
	}

	public void setChildren(Set<Privilege> children) {
		this.children = children;
	}

}
