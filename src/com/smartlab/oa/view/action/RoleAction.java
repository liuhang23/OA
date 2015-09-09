package com.smartlab.oa.view.action;

import java.util.HashSet;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.smartlab.oa.base.BaseAction;
import com.smartlab.oa.domain.Privilege;
import com.smartlab.oa.domain.Role;

@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role>{
	
	private Long[] privilegeIds;  //权限id数组
	
	//private Long id;
	//private String name;
	//private String description;
	
//	private Role model = new Role();
//	
//	@Override
//	public Role getModel() {
//		return model;
//	}

	// 列表()
	public String list() throws Exception {
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		return "list";
	}

	// 删除(重定到action)
	public String delete() throws Exception {
		roleService.delete(model.getId());
		return "toList";
	}

	// 添加页面(转发)
	public String addUI() throws Exception {
		return "saveUI";
	}

	// 添加
	public String add() throws Exception {
		//封装到对象
//		Role role  = new Role();
//		role.setName(model.getName());
//		role.setDescription(model.getDescription());
		//保存到数据库
		roleService.save(model);
		return "toList";
	}

	// 修改页面
	public String editUI() throws Exception {
		//准备回显数据
		Role role = roleService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(role);
 		return "saveUI";
	}

	// 修改
	public String edit() throws Exception {
		//从数据库中获取原对象
		Role role = roleService.getById(model.getId());
		//设置要修改的属性
		role.setName(model.getName());
		role.setDescription(model.getDescription());
		//更新到数据库
		roleService.update(role);
		return "toList";
	}
	
	//设置权限页面(类似修改)
	public String setPrivilegeUI() throws Exception{
		//准备回显数据
		Role role = roleService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(role);
		//role中没有privilegeIds
		if(role.getPrivileges() != null){
			int index = 0;
			privilegeIds = new Long[role.getPrivileges().size()];
			for(Privilege privilege : role.getPrivileges()){
				privilegeIds[index++] = privilege.getId();
			}
		}
		
		//privilegeList
		List<Privilege> privilegeList = privilegeService.findAll();
		ActionContext.getContext().put("privilegeList", privilegeList);
		return "setPrivilegeUI";
	}
	
	//设置权限
	public String setPrivilege() throws Exception{
		//1.从数据库取出原对象
		Role role = roleService.getById(model.getId());
		//2.设置要修改的权限
		List<Privilege> privilegeList = privilegeService.getByIds(privilegeIds);
		role.setPrivileges(new HashSet<Privilege>(privilegeList));
		//3.更新到数据
		roleService.update(role);
		return "toList";
	}

	
	//
	public Long[] getPrivilegeIds() {
		return privilegeIds;
	}

	public void setPrivilegeIds(Long[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}
	
	
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getDescription() {
//		return description;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
//	}

	

}
