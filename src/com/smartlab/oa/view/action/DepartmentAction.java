package com.smartlab.oa.view.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.smartlab.oa.base.BaseAction;
import com.smartlab.oa.domain.Department;
import com.smartlab.oa.service.DepartmentService;
import com.smartlab.oa.util.DepartmentUtils;

@Controller
@Scope("prototype")
public class DepartmentAction extends BaseAction<Department> {
	
	//上级部门
	private Long parentId;
	
	// 使用ModelDriven，Ognl
//	Department model = new Department();
//
//	@Override
//	public Department getModel() {
//		return model;
//	}

	// 列表
	public String list() throws Exception {
		
	    List<Department> departmentList = null;
	    if(parentId == null){
	    	departmentList = departmentService.findTopList(); //查询顶级部门列表
	    }else{
	    	departmentList = departmentService.findChildren(parentId); //查询子部门
	    	//查询上级的上级对象
	    	Department parent = departmentService.getById(parentId);
	    	ActionContext.getContext().put("parent", parent);
	    }
		ActionContext.getContext().put("departmentList", departmentList);
	    return "list";
	}

	// 删除
	public String delete() throws Exception {
		departmentService.delete(model.getId());
		return "toList";
	}

	// 添加页面
	public String addUI() throws Exception {
		//准备数据
		List<Department> topList  = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		return "saveUI";
	}

	// 添加
	public String add() throws Exception {
		//设置parentId,添加到model中,关联上级部门
		Department parent = departmentService.getById(parentId);
		model.setParent(parent);
		//保存到数据库
		departmentService.save(model);
		return "toList";
	}

	//修改页面
	public String editUI() throws Exception{
		//1.准备数据,departmentList
		//List<Department> departmentList = departmentService.findAll();
		List<Department> topList  = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList",departmentList);
		
		//2.准备回显数据
		Department department = departmentService.getById(model.getId());
		//将查询出的对象放入对象栈中，用户显示
		ActionContext.getContext().getValueStack().push(department);
		//添加parentId到model中
		if(department.getParent() != null){
			parentId = department.getParent().getId();
		}
		return "saveUI";
	}
	
	//修改
	public String edit() throws Exception{
		//从数据库中获取原对象
		Department department = departmentService.getById(model.getId());
		//设置要修改的属性
		department.setName(model.getName());
		department.setDescription(model.getDescription());
		department.setParent(departmentService.getById(parentId));//设置所属上级部门
		//更新到数据库
		departmentService.update(department);
		return "toList";
	}

	//
	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	
	
}


