package com.smartlab.oa.base;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

//数据服务层支持类 , 需要管理事务
@Transactional
@SuppressWarnings("unchecked")
// abstact类不能有实例，BaseDaoImpl不能有实例，则this表示子类new的实例
public abstract class DaoSupportImpl<T> implements DaoSupport<T> {

	@Resource
	// 注入获取容器中SessionFacotry,用于创建Session
	private SessionFactory sessionFactory;

	// 获取具体实例 (包.类)
	private Class<T> clazz;

	public DaoSupportImpl() {
		// 使用反射技术得到T的真实类型 ParameterizedType（泛型化类型）
		ParameterizedType pt = (ParameterizedType) this.getClass()// this表示当前new的实例（对象）,getClass获得一个实例的类型类(一个类型的类)
				.getGenericSuperclass();// 获取当前new的对象的 泛型的父类 类型
		this.clazz = (Class<T>) pt.getActualTypeArguments()[0]; // 获取第一个类型参数的真实类型
		System.out.println("clazz --->" + clazz + " ===" + pt);
	}

	// 获取当前可用Session,避免子类多次注入，使用同一个session
	protected Session getSession() {
		// 是获取当前上下文一个session对象,处理同一个事务
		return sessionFactory.getCurrentSession();
	}

	// 保存实体
	@Override
	public void save(T entity) {
		getSession().save(entity);
	}

	// 删除实体
	@Override
	public void delete(Long id) {
		Object obj = getById(id);
		if (obj != null) {
			getSession().delete(obj);
		}
	}

	// 更新实体
	@Override
	public void update(T entity) {

		getSession().update(entity);

	}

	// 通过id查询
	@Override
	public T getById(Long id) {
		if (id == null) {
			return null;
		} else {
			return (T) getSession().get(clazz, id);
		}
	}

	// 同HQl语句进行查询
	@Override
	public List<T> getByIds(Long[] ids) {
		if (ids == null || ids.length == 0) {
			return Collections.EMPTY_LIST;
		} else {
			return getSession().createQuery(//
					"FROM " + clazz.getSimpleName() + " WHERE id IN(:ids)")// IN(?)// 数组（集合）类型参数// IN(1,2,3...)
					.setParameterList("ids", ids)// 设置集合类型（数组类型）参数
					.list();
		}

	}

	// 查询实体所有信息
	@Override
	public List<T> findAll() {
		return getSession().createQuery(//
				"FROM " + clazz.getSimpleName())// 返回源代码中给出的底层类的简称
				.list();
	}

}
