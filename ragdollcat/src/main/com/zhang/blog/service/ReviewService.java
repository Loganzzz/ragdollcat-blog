package main.com.zhang.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import main.com.zhang.blog.entity.Article;
import main.com.zhang.blog.entity.Review;
import main.com.zhang.blog.util.Pager;

/** * @author zhang_chl 
    * @date 2017年7月27日下午11:47:52
    */
@Service
public class ReviewService<T extends Review> extends ServiceImpl<T>{

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
	public List<Review> getReviewByArticle(Article article){
		List<Review> reviews = getdAOImpl().query("from Review r where r.deleted=0 and r.article=:a")
				.setParameter("a", article).list();
		if(reviews!=null && reviews.size()!=0 &&reviews.get(0)!=null)
			return reviews;
		return null;
	}

	@SuppressWarnings("unchecked")
	public Pager<Review> getDateOrderedByPageReviews(int firstResult, int maxSize,Article article){
		
	Pager<Review> p = new Pager<Review>();
		List<Review> list = null;
		list = getdAOImpl().query("from Review r where r.deleted=0 and r.article=:article"
				+ " order by r.datecreated desc").setParameter("article", article).setFirstResult(firstResult)
				.setMaxResults(maxSize).list();
		p.setList(list);
		p.setPageSize(maxSize);
		p.setCurrentPage((firstResult+1)/maxSize+1);
		p.setTotalRecords(getRecordCount());
		p.setTotalPages((int)Math.ceil(p.getTotalRecords()/p.getPageSize())==0?1:(int)Math.ceil(p.getTotalRecords()/p.getPageSize()));
		p.setNextPage(p.getCurrentPage()==p.getTotalPages()? -1:p.getCurrentPage()+1);
		p.setLastPage(p.getCurrentPage()==1? -2:p.getCurrentPage()-1);
		return p;
	}


	public int getRecordCount() {
		return getdAOImpl().query("from Review r where r.deleted=0"
				).list().size();
}
	
}
