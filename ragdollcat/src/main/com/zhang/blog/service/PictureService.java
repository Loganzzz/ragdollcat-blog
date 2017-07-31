package main.com.zhang.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import main.com.zhang.blog.entity.Album;
import main.com.zhang.blog.entity.Picture;

/** * @author zhang_chl 
    * @date 2017年7月24日上午9:56:29
    */

@Service
public class PictureService<T extends Picture> extends ServiceImpl<T> {

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
	public Picture findByName(String name){
		 Picture picture = null;
		 List<Picture> list = getdAOImpl().query("from Picture p where p.deleted=0 and p.name=:pname")
				.setParameter("pname", name).list();
		 if(list!=null &&list.size()!=0 &&list.get(0)!=null)
			 picture = list.get(0);
		return picture;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Picture> getPicturesByAlbum(Album album){
		List<Picture> list = getdAOImpl().query("from Picture p where p.deleted=0 and p.album=:id")
				.setParameter("id", album).list();
		return list;
	}
}
