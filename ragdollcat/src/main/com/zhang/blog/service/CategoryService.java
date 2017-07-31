package main.com.zhang.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import main.com.zhang.blog.entity.Category;

/** * @author zhang_chl 
    * @date 2017年7月17日下午3:07:59
    */
@Service("categoryService")
public class CategoryService<T extends Category> extends ServiceImpl<T> {

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
	public Category findCategoryByTitle(String t){
		List<Category> list = getdAOImpl().query("from Category c where c.deleted=0 and c.title=:utitle"
				).setParameter("utitle", t.trim()).list();
		return list.get(0);
	}
}
