package com.smartlab.oa.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartlab.oa.base.DaoSupportImpl;
import com.smartlab.oa.dao.DepartmentDao;
import com.smartlab.oa.domain.Department;
import com.smartlab.oa.service.DepartmentService;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class DepartmentServiceImpl extends DaoSupportImpl<Department> implements DepartmentService{

//	@Resource
//	private DepartmentDao departmentDao;
	
	@Resource
	private SessionFactory sessionFactory;
	
	//查询顶级部门
	@Override
	public List<Department> findTopList() {
		return sessionFactory.getCurrentSession().createQuery(//
				"FROM Department d WHERE d.parent IS NULL")//
				.list();
	}
 
	//查询下级部门
	@Override
	public List<Department> findChildren(Long parentId) {
		return sessionFactory.getCurrentSession().createQuery(//
				"FROM Department d WHERE d.parent.id = ?")//
				.setParameter(0, parentId)//
				.list();
	}
	
//	@Override
//	public List<Department> findAll() {
//		return departmentDao.findAll();
//	}
//
//	@Override
//	public void delete(Long id) {
//		 departmentDao.delete(id);
//	}
//
//	@Override
//	public void update(Department department) {
//		departmentDao.update(department);
//	}
//
//	@Override
//	public Department getById(Long id) {
//		return departmentDao.getById(id);
//	}
//
//	@Override
//	public void save(Department model) {
//		departmentDao.save(model);
//		
//	}

	
}
