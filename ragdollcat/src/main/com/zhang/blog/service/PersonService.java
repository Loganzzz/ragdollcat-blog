package main.com.zhang.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import main.com.zhang.blog.entity.Person;

/** * @author zhang_chl 
    * @date 2017年7月14日下午7:24:39
    */

@Service("personService")
public class PersonService<T extends Person> extends ServiceImpl<T> {

	@Override
	public void create(T bean) {
		if (findPersonByName(bean.getName()) == null)
			throw new RuntimeException("账号"+bean.getName()+"已存在");
		this.getdAOImpl().create(bean);
	}

	@SuppressWarnings("unchecked")
	public T findPersonByName(String name){
		List<T> list = this.getdAOImpl().query("from Person p "
				+ "where (p.deleted = 0) and (p.name = :uname)"
				).setParameter("uname", name.trim()).list();
		if(list.size()>0)
			return list.get(0);
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public T findPersonByName(String name,String password){
		List<T> list = this.getdAOImpl().query("from Person p "
				+ "where (p.deleted = 0) and (p.name = :uname) and (p.password = :upassword)"
				).setParameter("uname", name.trim()).setParameter("upassword", password.trim()).list();
		if(list.size()>0)
			return list.get(0);
		return null;
	}

	@Override
	public void delete(T bean) {
		bean.setDeleted(true);
		getdAOImpl().update(bean);
	}
}
