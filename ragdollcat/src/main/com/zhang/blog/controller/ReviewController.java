package main.com.zhang.blog.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import main.com.zhang.blog.entity.Article;
import main.com.zhang.blog.entity.Person;
import main.com.zhang.blog.entity.Review;
import main.com.zhang.blog.service.ArticleService;
import main.com.zhang.blog.service.PersonService;
import main.com.zhang.blog.service.ReviewService;
import main.com.zhang.blog.util.Pager;
import main.com.zhang.blog.util.ReviewUtil;


/** * @author zhang_chl 
    * @date 2017年7月27日下午11:43:27
    */
@Controller
@RequestMapping("review")
public class ReviewController {
	@Autowired
	private ReviewService<Review> reviewService;
	
	@Autowired
	private ArticleService<Article> articleService;
	
	@Autowired
	private PersonService<Person> personService;
	
	@ResponseBody
	@RequestMapping(value="/publish", method=RequestMethod.POST)
	public void publish(String content,int  articleid,int personid,
			HttpServletRequest request) throws UnsupportedEncodingException{	
		if(content!=null){
				content = URLEncoder.encode(content, "utf-8");
			Review review = new Review();
			Article article = articleService.find(Article.class, articleid);
			Person person = personService.find(Person.class, personid);
			String ip = request.getRemoteHost();
			review.setArticle(article);
			review.setPerson(person);
			review.setContent(content);
			review.setIp(ip);
			review.setDatecreated(new Timestamp(System.currentTimeMillis()));
			review.setStatus(1);
			review.setDeleted(false);
			reviewService.create(review);
		}
	}
	
	//用json格式传值
	@ResponseBody
	@RequestMapping("/comment")
	public  Map<String,Object> getReviewByArticle(Integer articleid,Integer pn, HttpServletResponse response) throws UnsupportedEncodingException{
		Map<String,Object> map = new HashMap<String, Object>();
		Integer current = 1;
		if(pn!=null)
			current = pn;
		Integer maxSize = 6;
		Article article = articleService.find(Article.class, articleid);
		Pager<Review> pager = reviewService.getDateOrderedByPageReviews((current-1)*maxSize, maxSize, article);
		List<ReviewUtil> utilList = new ArrayList<ReviewUtil>();
		List<Review> reviewList = pager.getList();
		for(int i=0;i<reviewList.size();++i){
			Review r = reviewList.get(i);
			ReviewUtil e = new ReviewUtil(r.getPerson().getBlogtitle(), r.getPerson().getNickname(), r.getPerson().getPid(),URLDecoder.decode(r.getContent(), "utf-8") );
			utilList.add(e);
		}
		map.put("list", utilList);
		map.put("currentPage", pager.getCurrentPage());
		map.put("totalPages", pager.getTotalPages());
		return map;
	}
}
