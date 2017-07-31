package main.com.zhang.blog.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.baidu.ueditor.ActionEnter;

import main.com.zhang.blog.entity.Article;
import main.com.zhang.blog.entity.Category;
import main.com.zhang.blog.entity.Person;
import main.com.zhang.blog.service.ArticleService;
import main.com.zhang.blog.service.CategoryService;
import main.com.zhang.blog.util.Pager;

/** * @author zhang_chl 
    * @date 2017年7月16日上午11:00:35
    */
@Controller
@RequestMapping("article")
public class ArticleController {
	@Autowired
	private ArticleService<Article> articleService;
	@Autowired
	private CategoryService<Category> categoryService;
	
	@RequestMapping(value="/newArticle")
	public String newArticle(){
		return "newArticle";
	}
	
	@RequestMapping(value="/create",method=RequestMethod.POST)
	public void createArticle(String title,String intro,String content,
			String enablereview, String category, HttpServletResponse response, HttpSession session) throws IOException{  //与前台参数要匹配
		Article article = new Article();
		Timestamp datecreated = new Timestamp(System.currentTimeMillis());
		Person author = (Person) session.getAttribute("person");
		Category c = categoryService.findCategoryByTitle(category);
		article.setTitle(title);
		article.setContent(content);
		article.setIntro(intro);
		article.setCategory(c);
		article.setDatecreated(datecreated);
		article.setDeleted(false);
		article.setEnablereview(("true".equals(enablereview)?true:false));
		article.setReadtimes(0);
		article.setStatus(1);
		article.setTemplate("default");
		article.setTop(false);
		article.setType(2);
		article.setPerson(author);
		articleService.create(article);
		response.sendRedirect("bypage?pn=1");
	}
	@RequestMapping(value="/delete")
	public void deleteArticle(HttpServletResponse response,Article article) throws IOException{    //删除
		articleService.delete(article);
		response.sendRedirect("bypage?pn=1");
	}
	@RequestMapping(value="/restore")
	public void restoreArticle(HttpServletResponse response,Article article) throws IOException{    //恢复
		article.setDeleted(false);
		articleService.save(article);
		response.sendRedirect("bypage?pn=1");
	}
	
	
	public Article findArticleById(int id){
		return articleService.find(Article.class, id);
	}
	
	@RequestMapping(value="/detail")
	public String readArticleById(int id,Model model){
		Article article = findArticleById(id);		
		Article lastarticle = articleService.getLastArticle(id);
		Article nextarticle = articleService.getNextArticle(id);
		if(lastarticle!=null)
			model.addAttribute("lastarticle", lastarticle);
		if(nextarticle!=null)
			model.addAttribute("nextarticle", nextarticle);
		article.setReadtimes(article.getReadtimes()+1);
		articleService.update(article);     //增加浏览次数
		model.addAttribute("article", article);
		return "detail";
	}
	
	@RequestMapping(value="/bypage")    //分页显示
	public String getArticleByDateAndPage(Integer pn, Model model){
		Integer current = 1;
		if(pn!=null)
			current = pn;
		Integer maxSize = 6;
		Pager<Article> pager = (Pager<Article>) articleService.getDateOrderedByPageArticles((current-1)*maxSize, maxSize);
		model.addAttribute("pages", pager);
		return "list";
	}

}
