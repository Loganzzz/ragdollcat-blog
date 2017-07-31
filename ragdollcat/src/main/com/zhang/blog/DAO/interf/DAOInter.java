package main.com.zhang.blog.DAO.interf;

import java.util.List;

import org.hibernate.Query;

/** * @author zhang_chl 
    * @date 2017年7月13日下午6:09:53
    */
public interface DAOInter<T> {                        //使用泛型
	public void save(T bean);
	public void delete(T bean);
	public T find(Class<T> clazz, int id);
	public void create(T bean);
	public List<T> list(String hql);
	public int getTotalCount(String hql, Object...params);   //查询总数
	public List<T> list(String hql, int firstResult, int maxSize, Object...params);   //分页查询
	public Query query(String hql);							//创建Query
	public void update(T bean);
}
