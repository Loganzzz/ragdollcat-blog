package main.com.zhang.blog.DAO.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import main.com.zhang.blog.DAO.interf.DAOInter;

/** * @author zhang_chl 
    * @date 2017年7月13日下午6:24:25
    */

public class DAOImpl<T> extends HibernateDaoSupport implements DAOInter<T> {

	/*sessionFactory is required*/

	@Override
	public void save(T bean) {
		getHibernateTemplate().save(bean);
	}
	@Override
	public void update(T bean){
		getHibernateTemplate().update(bean);
	}
	
	@Override
	public void delete(T bean) {
		
		getHibernateTemplate().delete(bean);
	}

	@Override
	public T find(Class<T> clazz, int id) {
		return getHibernateTemplate().get(clazz, id);
	}

	@Override
	public void create(T bean) {
		getHibernateTemplate().persist(bean);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> list(String hql) {
		return (List<T>) getHibernateTemplate().find(hql);
	}

	@Override
	public int getTotalCount(String hql, Object... params) {
		Query query = query(hql);
		for(int i=0; i<params.length; ++i)
			query.setParameter(i+1, params[i]);
		return ((Long)query.uniqueResult()).intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> list(String hql, int firstResult, int maxSize, Object... params) {
		Query query = query(hql);
		for(int i=0; i<params.length; ++i)
			query.setParameter(i+1, params[i]);
		List<T> list = (List<T>) query.setFirstResult(firstResult).setMaxResults(maxSize).list();		
		return list;
	}

	@Override
	public Query query(String hql) {
		return getSessionFactory().getCurrentSession().createQuery(hql);
	}

}
