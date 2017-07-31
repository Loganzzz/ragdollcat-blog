package main.com.zhang.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import main.com.zhang.blog.entity.Album;
import main.com.zhang.blog.entity.Person;

/** * @author zhang_chl 
    * @date 2017年7月24日上午10:01:52
    */
@Service
public class AlbumService <T extends Album>extends ServiceImpl<T> {

	@Override
	public void delete(T bean) {
		bean.setDeleted(true);
		getdAOImpl().update(bean);
	}

	@Override
	public void create(T bean) {
		getdAOImpl().create(bean);
	}

	@SuppressWarnings("unchecked")
	public List<Album> getAllAlbum(){
		List<Album> list = null;
		list = getdAOImpl().query("from Album a where a.deleted=0").list();
		if(list!=null && list.size()!=0)
			return list;
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Album> getAlbumByPerson( Person person){
		List<Album> list =getdAOImpl().query("from Album a where a.deleted=0 and a.person=:person order by a.datecreated desc")
			.setParameter("person", person).list();
	if(list!=null && list.size()!=0 && list.get(0)!=null )
		return list;
	return null;
	}
}
