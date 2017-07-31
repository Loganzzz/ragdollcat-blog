<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="java.util.Set,main.com.zhang.blog.entity.Album,main.com.zhang.blog.entity.Picture"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<%String path=request.getContextPath(); %>
	<title>相册</title>
	<!-- Theme style  -->
	<link rel="stylesheet" href=<%= path+"/css/style.css"%>>
	<link rel="stylesheet" href=<%=request.getContextPath()+"/css/headcss.css" %>>
</head>
<body>
<header>
  <div class=" f_l"> <a href="/"><img src=<%=path +"/images/logo.png"%>></a> </div>
  <nav id="topnav" class="f_r" width: 55%>
    <ul>
      <a href="index.html" target="_blank">首页</a>      
      <a href="index.html" target="_blank">文章</a>      
      <a href=<%=path+"/article/newArticle" %> target="_blank">发表</a> 
      <a id="topnav_current" href=<%=path+"/album/showall"%>  target="_blank">相册</a>
      <a href=<%=path+"/"%> >登录</a>
      <a href="news.html" target="_blank">关于我</a>
    </ul>
  </nav>
</header>
<div style="padding-top:80px">
	<h6>个人相册</h6>
	<div id="fh5co-main">
	<c:forEach items="${listForPerson}" var="item" varStatus="status">
	<c:set var="setvar" scope="page" value="${item.pictures }"></c:set>
	<%	int picsize= 0; String picurl="";
		if(pageContext.getAttribute("setvar")!=null&&pageContext.getAttribute("setvar")!="") {
		Picture[] pics = ((Set<Picture>)pageContext.getAttribute("setvar")).toArray(new Picture[0]); 
		picsize= pics.length; picurl=pics[0].getUrl(); }
	%>
		<div class="fh5co-gallery">
			<a class="gallery-item" href=<%=path+"/album/showall?album="%>${item.id}>
				<img src=<%= picurl%> alt=${item.name }>
				<span class="overlay">
					<h2>${item.name }</h2>
					<span><%= picsize%> Photos</span>
				</span>
			</a>		
		</div>	
	</c:forEach>
	</div>
</div>

<div style="padding-top:40px">
	<h6>游客相册</h6>
	<div id="fh5co-main">
	<c:forEach items="${listForVisitor}" var="visitoritem" varStatus="status">
	<c:set var="visitorset" scope="page" value="${visitoritem.pictures }"></c:set>
	<%	int visitor_picsize= 0; String visitor_picurl="";
		if(pageContext.getAttribute("visitorset")!=null&&pageContext.getAttribute("visitorset")!="") {
		Picture[] visitor_pics = ((Set<Picture>)pageContext.getAttribute("visitorset")).toArray(new Picture[0]); 
		visitor_picsize= visitor_pics.length; visitor_picurl=visitor_pics[0].getUrl(); }
	%>
		<div class="fh5co-gallery">
			<a class="gallery-item" href=<%=path+"/album/showall?album="%>${visitoritem.id}>
				<img src=<%= visitor_picurl%> alt=${visitoritem.name }>
				<span class="overlay">
					<h2>${visitoritem.name }</h2>
					<span><%= visitor_picsize%> Photos</span>
				</span>
			</a>		
		</div>	
	</c:forEach>
</div>
 
</body>
</html>