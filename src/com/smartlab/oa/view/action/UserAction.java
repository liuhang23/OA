package com.smartlab.oa.view.action;

import java.util.HashSet;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.smartlab.oa.base.BaseAction;
import com.smartlab.oa.domain.Department;
import com.smartlab.oa.domain.Role;
import com.smartlab.oa.domain.User;
import com.smartlab.oa.util.DepartmentUtils;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {
	
	//获取页面上传id
	private Long departmentId;
	//岗位id数组
	private Long[] roleIds;
	
	// 列表
	public String list() throws Exception {
		List<User> userList = userService.findAll();
		ActionContext.getContext().put("userList", userList);
		return "list";
	}

	// 删除
	public String delete() throws Exception {
		userService.delete(model.getId());
		return "toList";
	}

	// 添加页面
	public String addUI() throws Exception {
		// 准备数据 departmentList
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);

		// 准备数据 roleLsit
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		return "saveUI";
	}

	// 添加
	public String add() throws Exception {
		//封装到model(封装model中没有的属性)
		//>>设置所属部门
		model.setDepartment(departmentService.getById(departmentId));
		//>>设置关联岗位 
		List<Role> roleList = roleService.getByIds(roleIds);
		model.setRoles(new HashSet<Role>(roleList));  //hashSet集合接受另外一个集合参数
		//>>设置默认密码
		String md5Digest = DigestUtils.md5Hex("1234");
		model.setPassword(md5Digest);
		//>>保存到数据库
		userService.save(model);
		return "toList";
	}

	// 初始化密码
	public String InitPassword() throws Exception {
		//>1.通过id在数据库中查询出原对象
		User user = userService.getById(model.getId());
		//>2.设置默认密码
		String md5Digest = DigestUtils.md5Hex("1234");
		user.setPassword(md5Digest);
		//>3.修改到数据库
		userService.update(user);
		return "toList";
	}

	// 修改页面
	public String editUI() throws Exception {
		//1.准备回显数据DepartmentList
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList",departmentList);
		//2.roleLsit
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		//3.user
		User user = userService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(user); //将user对象放入对象栈中
		if(user.getDepartment() != null){ //用户存在部门
			departmentId = user.getDepartment().getId();
		}
		if(user.getRoles() != null){
			int index = 0; 
			roleIds = new Long[user.getRoles().size()];
			for(Role role : user.getRoles()){
				roleIds[index] = role.getId();
			}
		}
		
		return "saveUI";
	}

	// 修改
	public String edit() throws Exception {
		//1.从数据库取出原对象
		User user = userService.getById(model.getId());
		//2.设置要修改的属性
		user.setLoginName(model.getLoginName());
		user.setDescription(model.getDescription());
		user.setEmail(model.getEmail());
		user.setName(model.getName());
		user.setPhoneNumber(model.getPhoneNumber());
		user.setGender(model.getGender());
		//>>设置修改的部门
		user.setDepartment(departmentService.getById(departmentId));
		//>>修改关联的岗位
		List<Role> roleList = roleService.getByIds(roleIds);
		user.setRoles(new HashSet<Role>(roleList));  //setRoles存放set集合
		//3.更新到数据库
		userService.update(user);
		return "toList";
	}

	//登陆页面
	public String loginUI() throws Exception{
		return "loginUI";
	}
	
	//登陆 : 重定向到首页
	public String login() throws Exception{
		User user = userService.findByLoginNameAndPassword(model.getLoginName(),model.getPassword());
		if(user == null){
			addFieldError("login", "登陆名或密码错误");
			return "loginUI";
		}else{
			//登陆成功
			ActionContext.getContext().getSession().put("user", user);
			return "toIndex";
		}
	}
	
	
	//注销
	public String logout() throws Exception{
		//将user对象从值栈中移除
		ActionContext.getContext().getSession().remove("user");
		return "logout";
	}
	
	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public Long[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Long[] roleIds) {
		this.roleIds = roleIds;
	}
	

}
