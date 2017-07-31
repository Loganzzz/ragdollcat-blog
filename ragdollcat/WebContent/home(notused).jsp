<%@page import="org.springframework.http.HttpRequest"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="org.springframework.context.ApplicationContext"%>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@ page import="main.com.zhang.blog.entity.Article" %>
<%@ page import="main.com.zhang.blog.entity.Person" %>
<%@ page import="main.com.zhang.blog.service.ArticleService" %>
<%@ page import="main.com.zhang.blog.service.PersonService" %>
<%@ page import="main.com.zhang.blog.util.Pager" %>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<link href=<%=request.getContextPath() +"/css/base.css"%> rel="stylesheet">
<link href=<%=request.getContextPath() +"/css/index.css"%> rel="stylesheet">
<script type="text/javascript" src=<%=request.getContextPath() +"/js/jquery.min.js"%>></script>
<script type="text/javascript" src=<%=request.getContextPath() +"/js/sliders.js"%>></script>

<%-- <!-- 返回顶部调用 begin -->
<script type="text/javascript" src=<%=request.getContextPath() +"/js/up/jquery.js"%>></script>
<script type="text/javascript" src=<%=request.getContextPath() +"/js/up/js.js"%>></script>
<!-- 返回顶部调用 end--> --%>


<%ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
ArticleService<Article> articleService = ctx.getBean("articleService", ArticleService.class);
PersonService<Person> personService = ctx.getBean("personService", PersonService.class);
List<Article> articleList = articleService.getReadOrderedByPageArticles(0, 6);//阅读排行
Pager<Article> pager = articleService.getDateOrderedByPageArticles(0, 6);//时间排行
if(session.getAttribute("person")==null){
	session.setAttribute("person", personService.find(Person.class, 2));
}
String path= request.getContextPath();
%>

<title>博客zzz</title>
</head>
<body>
<header>
  <div class="logo f_l"> <a href="/"><img src=<%=request.getContextPath() +"/images/logo.png"%>></a> </div>
  <nav id="topnav" class="f_r">
    <ul>
      <a href="index.html" target="_blank">首页</a> 
      <a href="news.html" target="_blank">关于我</a> 
      <a href="p.html" target="_blank">文章</a> 
      <a id="login" href=<%=request.getContextPath()+"/login/tologin"%> >登陆</a>
      <a href=<%=path+"/article/newArticle" %> target="_blank">发表</a> 
      <a href="b.html" target="_blank">相册</a>
    </ul>
    <script src=<%=request.getContextPath() +"/js/nav.js"%>></script> 
  </nav>
</header>
<article>
  <div class="l_box f_l">
    <div class="banner">
      <div id="slide-holder">
        <div id="slide-runner"> <a href="/" target="_blank"><img id="slide-img-1" src=<%=path+"/images/a1.jpg" %>  alt="" /></a> <a href="/" target="_blank"><img id="slide-img-2" src=<%=path+"/images/a2.jpg" %>  alt="" /></a> <a href="/" target="_blank"><img id="slide-img-3" src=<%=path+"/images/a3.jpg" %>  alt="" /></a> <a href="/" target="_blank"><img id="slide-img-4" src=<%=path+"/images/a4.jpg"%>  alt="" /></a>
          <div id="slide-controls">
            <p id="slide-client" class="text"><strong></strong><span></span></p>
            <p id="slide-desc" class="text"></p>
            <p id="slide-nav"></p>
          </div>
        </div>
      </div>
      <script>
	  if(!window.slider) {
		var slider={};
	}

	slider.data= [
    {
        "id":"slide-img-1", // 与slide-runner中的img标签id对应
        "client":"标题1",
        "desc":"这里修改描述 这里修改描述 这里修改描述" //这里修改描述
    },
    {
        "id":"slide-img-2",
        "client":"标题2",
        "desc":"add your description here"
    },
    {
        "id":"slide-img-3",
        "client":"标题3",
        "desc":"add your description here"
    },
    {
        "id":"slide-img-4",
        "client":"标题4",
        "desc":"add your description here"
    } 
	];

	  </script> 
    </div>
    <!-- banner代码 结束 -->
    <div class="topnews">
      <h2><span><a href="/" target="_blank">栏目标题</a><a href="/" target="_blank">栏目标题</a><a href="/" target="_blank">栏目标题</a></span><b>文章</b>推荐</h2>
      
      <% for(int i=0;i<pager.getList().size();++i) { %>
      <div class="blogs">
        <figure><img src=<%=path+"/images/01.jpg" %>></figure>
        <ul>
          <h3><a href=<%=request.getContextPath()+"/article/detail?id="+pager.getList().get(i).getAid()%>><%=pager.getList().get(i).getTitle() %></a></h3>
          <div style="height:75px"><p ><%=pager.getList().get(i).getIntro() %>...</p></div>
          <p class="autor"><span class="lm f_l"><a href="/"><%=pager.getList().get(i).getPerson().getNickname() %></a></span>
          <span class="dtime f_l"><%=pager.getList().get(i).getDatecreated().toString().split(" ")[0] %></span>
          <span class="viewnum f_r">浏览（<a href="/"><%=pager.getList().get(i).getReadtimes() %></a>）</span>
          <span class="pingl f_r">评论（<a href="/"><%=pager.getList().get(i).getReviews().size() %></a>）</span></p>
        </ul>
      </div>
      <% }%>
   <!--    <div class="blogs">
        <figure><img src="images/01.jpg"></figure>
        <ul>
          <h3><a href="/">住在手机里的朋友</a></h3>
          <p>通信时代，无论是初次相见还是老友重逢，交换联系方式，常常是彼此交换名片，然后郑重或是出于礼貌用手机记下对方的电话号码。在快节奏的生活里，我们不知不觉中就成为住在别人手机里的朋友。又因某些意外，变成了别人手机里匆忙的过客，这种快餐式的友谊 ...</p>
          <p class="autor"><span class="lm f_l"><a href="/">个人博客</a></span><span class="dtime f_l">2014-02-19</span><span class="viewnum f_r">浏览（<a href="/">459</a>）</span><span class="pingl f_r">评论（<a href="/">30</a>）</span></p>
        </ul>
      </div> -->
     
    </div>
  </div>
  <div class="r_box f_r">
    <div class="tit01">
      <h3>关注我</h3>
      <div class="gzwm">
        <ul>
          <li><a class="xlwb" href="#" target="_blank">新浪微博</a></li>
          <li><a class="txwb" href="#" target="_blank">腾讯微博</a></li>
          <li><a class="rss" href="portal.php?mod=rss" target="_blank">RSS</a></li>
          <li><a class="wx" href="mailto:admin@admin.com">邮箱</a></li>
        </ul>
      </div>
    </div>
    <!--tit01 end-->
    <div class="ad300x100"> <img src=<%=path+"/images/ad300x100.jpg"%>> </div>
    <div class="moreSelect" id="lp_right_select"> 
      <script>
window.onload = function ()
{
	var oLi = document.getElementById("tab").getElementsByTagName("li");
	var oUl = document.getElementById("ms-main").getElementsByTagName("div");
	
	for(var i = 0; i < oLi.length; i++)
	{
		oLi[i].index = i;
		oLi[i].onmouseover = function ()
		{
			for(var n = 0; n < oLi.length; n++) oLi[n].className="";
			this.className = "cur";
			for(var n = 0; n < oUl.length; n++) oUl[n].style.display = "none";
			oUl[this.index].style.display = "block"
		}	
	}
}
</script>
      <div class="ms-top">
        <ul class="hd" id="tab">
          <li class="cur"><a href="/">点击排行</a></li>
          <li><a href="/">最新文章</a></li>
          <li><a href="/">站长推荐</a></li>
        </ul>
      </div>
      <div class="ms-main" id="ms-main">
        <div style="display: block;" class="bd bd-news" >
      <ul>      
           	<% for(int i=0;i<articleList.size();++i) { String title=articleList.get(i).getTitle();
           	if(title.length()>15) title = title.substring(0, 15);%>
      			<li><a href=<%=request.getContextPath()+"/article/detail?id="+articleList.get(i).getAid()%> target="_blank"><%=title+"..." %></a></li>
      	   	<% }%>          
          </ul>
        </div>
        <div  class="bd bd-news">
          <ul>      
           	<% for(int i=0;i<pager.getList().size();++i) { String title=pager.getList().get(i).getTitle();
           	if(title.length()>15) title = title.substring(0, 15);%>
      			<li><a href=<%=request.getContextPath()+"/article/detail?id="+pager.getList().get(i).getAid()%> target="_blank"><%=title+"..." %></a></li>
      	   	<% }%>          
          </ul>
        </div>
        <div class="bd bd-news">
          <ul>
            <li><a href="/" target="_blank">手机的16个惊人小秘密，据说99.999%的人都不知</a></li>
            <li><a href="/" target="_blank">你面对的是生活而不是手机</a></li>
            <li><a href="/" target="_blank">住在手机里的朋友</a></li>
            <li><a href="/" target="_blank">豪雅手机正式发布! 在法国全手工打造的奢侈品</a></li>
            <li><a href="/" target="_blank">教你怎样用欠费手机拨打电话</a></li>
            <li><a href="/" target="_blank">原来以为，一个人的勇敢是，删掉他的手机号码...</a></li>
          </ul>
        </div>
      </div>
      <!--ms-main end --> 
    </div>
    <!--切换卡 moreSelect end -->
    
    <div class="cloud">
      <h3>标签云</h3>
      <ul>
        <li><a href="/">个人博客</a></li>
        <li><a href="/">web开发</a></li>
        <li><a href="/">前端设计</a></li>
        <li><a href="/">Html</a></li>
        <li><a href="/">CSS3</a></li>
        <li><a href="/">Html5+css3</a></li>
        <li><a href="/">百度</a></li>
        <li><a href="/">Javasript</a></li>
        <li><a href="/">web开发</a></li>
        <li><a href="/">前端设计</a></li>
        <li><a href="/">Html</a></li>
        <li><a href="/">CSS3</a></li>
        <li><a href="/">Html5+css3</a></li>
        <li><a href="/">百度</a></li>
      </ul>
    </div>
    <div class="tuwen">
      <h3>图文推荐</h3>
      <ul>
        <li><a href="/"><img src=<%=path+"/images/01.jpg"%>><b>住在手机里的朋友</b></a>
          <p><span class="tulanmu"><a href="/">手机配件</a></span><span class="tutime">2015-02-15</span></p>
        </li>
        <li><a href="/"><img src=<%=path+"/images/02.jpg"%>><b>教你怎样用欠费手机拨打电话</b></a>
          <p><span class="tulanmu"><a href="/">手机配件</a></span><span class="tutime">2015-02-15</span></p>
        </li>
        <li><a href="/" title="手机的16个惊人小秘密，据说99.999%的人都不知"><img src=<%=path+"/images/03.jpg"%>><b>手机的16个惊人小秘密，据说...</b></a>
          <p><span class="tulanmu"><a href="/">手机配件</a></span><span class="tutime">2015-02-15</span></p>
        </li>
        <li><a href="/"><img src=<%=path+"/images/06.jpg"%>><b>住在手机里的朋友</b></a>
          <p><span class="tulanmu"><a href="/">手机配件</a></span><span class="tutime">2015-02-15</span></p>
        </li>
        <li><a href="/"><img src=<%=path+"/images/04.jpg"%>><b>教你怎样用欠费手机拨打电话</b></a>
          <p><span class="tulanmu"><a href="/">手机配件</a></span><span class="tutime">2015-02-15</span></p>
        </li>
      </ul>
    </div>
    <div class="ad"> <img src=<%=path+"/images/03.jpg"%>> </div>
    <div class="links">
      <h3><span>[<a href="/">申请友情链接</a>]</span>友情链接</h3>
      <ul>
        <li><a href="/">杨青个人博客</a></li>
        <li><a href="/">web开发</a></li>
        <li><a href="/">前端设计</a></li>
        <li><a href="/">Html</a></li>
        <li><a href="/">CSS3</a></li>
        <li><a href="/">Html5+css3</a></li>
        <li><a href="/">百度</a></li>
      </ul>
    </div>
  </div>
  <!--r_box end --> 
</article>
<footer>
  <p class="ft-copyright"> Design by Logan  蜀ICP备11002373号-1</p>
  <!-- <div id="tbox"> <a id="togbook" href="/"></a> <a id="gotop" href="javascript:void(0)"></a> </div> -->
</footer>




</body>
</html>