package main.com.zhang.blog.service;

import java.util.List;

import javax.annotation.Resource;

import main.com.zhang.blog.DAO.impl.DAOImpl;

/** * @author zhang_chl 
    * @date 2017年7月13日下午8:00:41
    */
public abstract class ServiceImpl<T> implements ServiceInter<T> {

	@Resource
	private DAOImpl<T> dAOImpl;                //DAO层的对象通过Spring的IOC注入
	@Override
	public T find(Class<T> clazz, int id) {
		return dAOImpl.find(clazz, id);
	}

	@Override
	public void save(T bean) {
		dAOImpl.save(bean);
	}

	@Override
	public void update(T bean){
		dAOImpl.update(bean);
	}
	
	@Override
	abstract public void delete(T bean);

	@Override
	abstract public void create(T bean);     //创建实体，由不同的service类自己实现

	@Override
	public List<T> list(String hql) {
		return dAOImpl.list(hql);
	}

	@Override
	public int getTotalCount(String hql, Object... params) {
		return dAOImpl.getTotalCount(hql, params);
	}

	@Override
	public List<T> list(String hql, int firstResult, int maxSize, Object... params) {
		return dAOImpl.list(hql, firstResult, maxSize, params);
	}

	public DAOImpl<T> getdAOImpl() {
		return dAOImpl;
	}

	public void setdAOImpl(DAOImpl<T> dAOImpl) {
		this.dAOImpl = dAOImpl;
	}

}
