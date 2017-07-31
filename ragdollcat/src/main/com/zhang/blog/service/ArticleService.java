package main.com.zhang.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import main.com.zhang.blog.entity.Article;
import main.com.zhang.blog.util.Pager;

/** * @author zhang_chl 
    * @date 2017年7月16日上午10:32:02
    */
@Service("articleService")
public class ArticleService<T extends Article> extends ServiceImpl<T> {
	
	@Override
	public void create(T bean) {
		getdAOImpl().create(bean);
	}

	public int getRecordCount(){
		return getdAOImpl().query("from Article a where a.deleted=0"
				).list().size();
	}
	
	@SuppressWarnings("unchecked")
	public List<Article> getReadOrderedArticles(){          //按阅读量排序
		return getdAOImpl().query("from Article a where a.deleted=0"
				+ " order by a.readtimes desc").list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Article> getDateOrderedArticles(){           //按时间排序
		List<Article> l = null;
		l = getdAOImpl().query("from Article a where a.deleted=0"
				+ " order by a.datecreated desc").list();
		return l;
	}

	@SuppressWarnings("unchecked")                //按时间分页排序
	public Pager<Article> getDateOrderedByPageArticles(int firstResult, int maxSize){
		
		Pager<Article> p = new Pager<Article>();
		List<Article> list = null;
		list = getdAOImpl().query("from Article a where a.deleted=0"
				+ " order by a.datecreated desc").setFirstResult(firstResult)
				.setMaxResults(maxSize).list();
		p.setList(list);
		p.setPageSize(maxSize);
		p.setCurrentPage((firstResult+1)/maxSize+1);
		p.setTotalRecords(getRecordCount());
		p.setTotalPages(p.getTotalRecords()/p.getPageSize()+1);
		p.setNextPage(p.getCurrentPage()==p.getTotalPages()? -1:p.getCurrentPage()+1);
		p.setLastPage(p.getCurrentPage()==1? -2:p.getCurrentPage()-1);
		return p;
	}
	
	@SuppressWarnings("unchecked")               //按阅读量排序
	public List<Article> getReadOrderedByPageArticles(int firstResult, int maxSize){
		List<Article> l = null;
		l = getdAOImpl().query("from Article a where a.deleted=0"
				+ " order by a.readtimes desc").setFirstResult(firstResult)
				.setMaxResults(maxSize).list();
		return l;
	}
	
	@SuppressWarnings("unchecked")
	public Article getLastArticle(int id){ //查询上一篇
		Article lastarticle = null;
		List<Integer> list = getdAOImpl().query("select min(a.aid) from Article a where a.deleted=0 and a.aid>:id")
				.setParameter("id", id).list();
		if(list!=null && list.size()!=0 && list.get(0)!=null)
			lastarticle = (Article)find((Class<T>) Article.class, list.get(0));
		return lastarticle;
	}
	
	@SuppressWarnings("unchecked")
	public Article getNextArticle(int id){  //查询下一篇
		Article nextarticle = null;
		List<Integer> list = getdAOImpl().query("select max(a.aid) from Article a where a.deleted=0 and a.aid<:id")
				.setParameter("id", id).list();
		if(list!=null && list.size()!=0 && list.get(0)!=null){			
			nextarticle= (Article)this.find( (Class<T>) Article.class, list.get(0));
		}
		return nextarticle;
	}
	
	@Override
	public void delete(T bean) {
		bean.setDeleted(true);
		getdAOImpl().update(bean);
	}
	
}
