package main.com.zhang.blog.service;

import java.util.List;

/** * @author zhang_chl 
    * @date 2017年7月13日下午7:55:34
    */
public interface ServiceInter <T>{        //定义所有Service的基类
	public T find(Class<T> clazz, int id);//根据id查找实体
	public void save(T bean);
	public void delete(T bean);
	public void create(T bean);
	public void update(T bean);
	public List<T> list(String hql);       //查询所有实体
	public int getTotalCount(String hql, Object...params);   //查询总数
	public List<T> list(String hql, int firstResult, int maxSize, Object...params); //分页查询
}
