<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set,main.com.zhang.blog.entity.Album,main.com.zhang.blog.entity.Picture"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge; charset=utf-8">
<title>相册</title>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<%String path=request.getContextPath(); %>
<link rel="stylesheet" href=<%=request.getContextPath()+"/css/amazeui.min.css" %>>
<link rel="stylesheet" href=<%=request.getContextPath()+"/css/headcss.css" %>>

</head>
<body >
<header>
  <div class=" f_l"> <a href="/"><img src=<%=path +"/images/logo.png"%>></a> </div>
  <nav id="topnav" class="f_r" width: 55%>
    <ul style="height:100%">
      <a href="index.html" target="_blank">首页</a>  
    
      <a href="index.html" target="_blank">文章</a>
      
      <a href=<%=path+"/article/newArticle" %> target="_blank">发表</a> 
      <a id="topnav_current" href=<%=path+"/album/showall" %> target="_blank">相册</a>
      <a href=<%=path+"/"%> >登录</a>
      <a href="news.html" target="_blank">关于我</a>
    </ul>
  </nav>
</header>

<div class="left">导航</div>
<div class="right">
	<div style="display:block;padding-left:90px; padding-right:90px ;padding-top: 80px;padding-bottom:70px;"align="center">
	
	<ul data-am-widget="gallery" class="am-gallery am-avg-sm-2
	am-avg-md-3 am-avg-lg-4 am-gallery-overlay" data-am-gallery="{ pureview: true }" style="padding-left: 185px;" >
	<c:forEach items="${pictureList}" var="item" varStatus="status">
	<li style="width:22%;float: left;" >
		<div class="am-gallery-item" ><!-- EL如何处理set --><!-- 关闭懒加载 -->
			<a href="${item.url }" class="">
				<img name="pic" src="${item.url }" 
				 alt="${item.name }" />
				<h3 class="am-gallery-title">${item.name }</h3>
				<div class="am-gallery-desc">"${fn:split(item.datecreated," ")[0]}"</div>
			</a>
		</div>
	</li>
	</c:forEach>
	</ul>
	</div>
</div>


	<footer>
	  <p>Logan <a href="http://www.miitbeian.gov.cn/" target="_blank">蜀ICP备11002373号-1</a> <a href="/">网站统计</a></p>
	</footer>
<script src=<%=request.getContextPath()+"/js/jquery.min.js"%>></script>
<script src=<%=request.getContextPath()+"/js/amazeui.js"%>></script>
</body>
</html>