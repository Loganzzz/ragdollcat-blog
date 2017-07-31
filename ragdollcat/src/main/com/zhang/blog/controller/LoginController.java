package main.com.zhang.blog.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import main.com.zhang.blog.entity.Article;
import main.com.zhang.blog.entity.Person;
import main.com.zhang.blog.service.ArticleService;
import main.com.zhang.blog.service.PersonService;
import main.com.zhang.blog.util.Pager;

/** * @author zhang_chl 
    * @date 2017年7月17日下午7:16:51
    */

@Controller
@RequestMapping("login")
public class LoginController {
	@Autowired
	private PersonService<Person> personService;
	@Autowired
	private ArticleService<Article> articleService;
/*	@RequestMapping("/tologin")
	public String login(){
		return "login";	
	}*/
	
	@RequestMapping("/dologin")
	public String dologin(String username,String password,String remember, 
			HttpServletRequest request,HttpServletResponse response,HttpSession session,Model model){
		Person person = personService.findPersonByName(username);
		if(person!=null){
			if(person.getPassward().equals(password)){
				
				//保存用户到会话
				session.setAttribute("person", person);				
				
				if("yes".equals(remember)){
					//添加cookie
					Cookie cookie = new Cookie("user", username+"#"+password);
					cookie.setMaxAge(2592000);
					response.addCookie(cookie);
				} else{  
					//否则删除cookie
					Cookie[] cookies =request.getCookies();
					if(cookies!=null && cookies.length!=0 ){
						for(int i=0;i<cookies.length;++i){
							if(cookies[i].getName().equals("user")){
								cookies[i].setMaxAge(0);
								response.addCookie(cookies[i]);
								break;
							}
						}
					}
				}
				//待改进，返回登陆成功的提示信息
			}			
		}
		else{
			return "login";
			//待改进，返回用户名或密码错误的提示信息
		}
		
		Integer current = 1;
		Integer maxSize = 6;
		Pager<Article> pager = (Pager<Article>) articleService.getDateOrderedByPageArticles((current-1)*maxSize, maxSize);
		model.addAttribute("pages", pager);
		return "list";      //登陆之后返回分页的文章列表
	}
}
