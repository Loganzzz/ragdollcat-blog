<%@page import="org.springframework.web.portlet.ModelAndView"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.List"%>
<%@ page import="main.com.zhang.blog.entity.Person"%>
<%@ page import="main.com.zhang.blog.entity.Article"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>${article.title}</title>
<%String path=request.getContextPath(); %>
<link href=<%=path+"/css/basedetail.css" %> rel="stylesheet">
<link href=<%=path+"/css/detail.css"%> rel="stylesheet">
<script type="text/javascript" src=<%=path+"/ueditor/third-party/SyntaxHighlighter/shCore.js"%>></script>
<link href=<%=path+"/ueditor/third-party/SyntaxHighlighter/shCoreDefault.css"%> rel="stylesheet">
<script type="text/javascript">SyntaxHighlighter.all();</script>
<!-- 表情 -->
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/emojione/1.5.2/assets/sprites/emojione.sprites.css">                
<link rel="stylesheet" href=<%=path+"/css/emojionearea.min.css"%>>
<script type="text/javascript" src=<%=path+"/js/jquery.min.js"%>></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/emojione/1.5.2/lib/js/emojione.min.js"></script>
 <script type="text/javascript" src=<%=path+"/js/emojionearea.min.js"%>></script>

<style type="text/css">
	.f_l { float: left }
	.f_r { float: right }
	header { width: 1000px; margin: 20px auto; overflow: hidden; clear: both; }
	#topnav { width: 55%; background: #48AE15; height: 46px; line-height: 46px; text-align: center; border-radius: 46px; }
	#topnav a { display: inline-block; font-size: 18px; font-family: "Microsoft Yahei", Arial, Helvetica, sans-serif; padding: 0 20px; }
	#topnav a:hover { background: #fff; color: #000; }
	#topnav a { color: #FFF }
	#topnav_current { background: #F3FF41; }/* 高亮选中颜色 */
	a #topnav_current { color: #000 }
</style>

 <!--[if lt IE 9]>
<script src="/ragdollcat/js/js/modernizr.js"></script>
<![endif]--> 

</head>
<body>
	<header>
		<div class="logo f_l"> <a href="/"><img src=<%=path +"/images/logo.png"%>></a> </div>
		 <nav id="topnav" class="f_r">
		   <ul>
		     <a href="index.html" target="_blank">首页</a> 	      
		    
		     <a id="topnav_current" href="index.html" target="_blank">文章</a>
		     <a href=<%=path+"/article/newArticle" %> target="_blank">发表</a> 
		     <a href=<%=path+"/album/showall" %> target="_blank">相册</a>
		     <a href=<%=path+"/"%> target="_blank">登录</a> 
		     
		     <a href="news.html" target="_blank">关于我</a>
		   </ul>
		   <%-- <script src=<%=path +"/js/nav.js"%>></script> --%> 
		 </nav>
	</header>
	<article class="blogs">
	  <h1 class="t_nav"><span>您当前的位置：<a href="/list">首页</a>&nbsp;&gt;&nbsp;<a href="/news/s/">慢生活</a>&nbsp;&gt;&nbsp;<a href="/news/s/">日记</a></span><a href="/" class="n1">网站首页</a><a href="/" class="n2">${article.title}</a></h1>
	  <div class="index_about" >
	    <h2 class="c_titile">${article.title}</h2>
	    <p class="box_c"><span class="d_time">发布时间：${fn:split(article.datecreated, ".")[0]}</span><span>作者：${article.person.nickname}</span></p>
	    <ul class="infos">
	      	${article.content}
	    </ul>
	    <div class="keybq">
	    <p><span>关键字词</span>：测试、示例</p>
	    
	    </div>
	    <div class="ad"> </div>
	    <div class="nextinfo">
	      <p id="lastp">上一篇：<a id="lastarticle">程序员应该如何高效的工作学习</a></p>
	      <p id="nextp">下一篇：<a id="nextarticle">柴米油盐的生活才是真实</a></p>
	    </div>
	    <!-- 显示上一篇的js逻辑 -->
	    <script type="text/javascript">
	    	if('${lastarticle}'==''){
	    		document.getElementById("lastp").setAttribute("style","display:none");
	    	}else{
	    		document.getElementById("lastarticle").href='detail?id=${lastarticle.aid}';
	    		document.getElementById("lastarticle").innerHTML="${lastarticle.title}";
	    	}
	    	if('${nextarticle}'==''){
	    		document.getElementById("nextp").setAttribute("style","display:none");
	    	}else{
	    		document.getElementById("nextarticle").href='detail?id=${nextarticle.aid}';
	    		document.getElementById("nextarticle").innerHTML="${nextarticle.title}";
	    	}
	    </script>
		<div class="otherlink">
	      <h2> 评论</h2>
	       <div class="publish">
	      	 	<div class="personimg" >
	      	 		<img src=<%=path+"/images/head.jpg"%> alt="头像">
	      	 	</div>
	      	 	<div style="display: inline-block;width: 90%;height: 100%; vertical-align: top; margin-left: 1%"> 		
	      	 		<form action="">
	      	 			<input type="text" id="review" name="content"  style="width: 98%;"  >
	      	 			<input type="hidden" id="articleid" value="${article.aid}">
	      	 			<input type="hidden" id="personid" value=<%=((Person)session.getAttribute("person")).getPid() %>>
	      	 			<input id="reviewbtn" class="btnreview"  type="button" name="reviewbtn" value="评论">
	      	 		</form>
	      	 	</div> 
	      	 </div>
	      	<p style="margin-bottom: 10px;margin-left: 8px;"><span><strong>按时间</strong></span>&nbsp;&nbsp;<span>按热度</span></p>
	      <ul id="reviewlist">    	</ul>
	    
	    </div>
	  </div>
	  <aside class="right">
	    <!-- Baidu Button BEGIN -->
	    <div id="bdshare" class="bdshare_t bds_tools_32 get-codes-bdshare"><a class="bds_tsina"></a><a class="bds_qzone"></a><a class="bds_tqq"></a><a class="bds_renren"></a><span class="bds_more"></span><a class="shareCount"></a></div>
	    <script type="text/javascript" id="bdshare_js" data="type=tools&amp;uid=6574585" ></script> 
	    <script type="text/javascript" id="bdshell_js"></script> 
	    <script type="text/javascript">
	document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion=" + Math.ceil(new Date()/3600000)
	</script> 
	    <!-- Baidu Button END -->
	    <div class="blank"></div>
	    <div class="news">
	      <h3>
	        <p>栏目<span>最新</span></p>
	      </h3>
	      <ul class="rank">
	        <li><a href="/" title="Column 三栏布局 个人网站模板" target="_blank">Column 三栏布局 个人网站模板</a></li>
	        <li><a href="/" title="with love for you 个人网站模板" target="_blank">with love for you 个人网站模板</a></li>
	        <li><a href="/" title="免费收录网站搜索引擎登录口大全" target="_blank">免费收录网站搜索引擎登录口大全</a></li>
	        <li><a href="/" title="做网站到底需要什么?" target="_blank">做网站到底需要什么?</a></li>
	        <li><a href="/" title="企业做网站具体流程步骤" target="_blank">企业做网站具体流程步骤</a></li>
	        <li><a href="/" title="建站流程篇――教你如何快速学会做网站" target="_blank">建站流程篇――教你如何快速学会做网站</a></li>
	        <li><a href="/" title="box-shadow 阴影右下脚折边效果" target="_blank">box-shadow 阴影右下脚折边效果</a></li>
	        <li><a href="/" title="打雷时室内、户外应该需要注意什么" target="_blank">打雷时室内、户外应该需要注意什么</a></li>
	      </ul>
	      <h3 class="ph">
	        <p>点击<span>排行</span></p>
	      </h3>
	      <ul class="paih">
	        <li><a href="/" title="Column 三栏布局 个人网站模板" target="_blank">Column 三栏布局 个人网站模板</a></li>
	        <li><a href="/" title="withlove for you 个人网站模板" target="_blank">with love for you 个人网站模板</a></li>
	        <li><a href="/" title="免费收录网站搜索引擎登录口大全" target="_blank">免费收录网站搜索引擎登录口大全</a></li>
	        <li><a href="/" title="做网站到底需要什么?" target="_blank">做网站到底需要什么?</a></li>
	        <li><a href="/" title="企业做网站具体流程步骤" target="_blank">企业做网站具体流程步骤</a></li>
	      </ul>
	    </div>
	    <div class="visitors">
	      <h3>
	        <p>最近访客</p>
	      </h3>
	      <ul>
	      </ul>
	    </div>
	  </aside>
	</article>
	<footer>
	  <p>Logan <a href="http://www.miitbeian.gov.cn/" target="_blank">蜀ICP备11002373号-1</a> <a href="/">网站统计</a></p>
	</footer>
<script type="text/javascript">
	var aid=document.getElementById("articleid").value;
	var pid=document.getElementById("personid").value;
	var currentpage=1;
	var totalpages=2;
	var isloading = false; //避免重复加载
  $(document).ready(function() {
    $("#review").emojioneArea({template: "<filters/><tabs/><editor/>"});
    
    getReview(1);
    
    scrollEvent();   //事件
    
    $("#reviewbtn").click(function(){ 
    	//提交保存评论的请求
    	var con=document.getElementById("review").value;
    	$.ajax({
    		type:"POST",
    		url:"/ragdollcat/review/publish",
    		data:{
    			articleid: aid,
    			personid: pid,
    			content:  con,
    		},
    		success: function (data) {
    			//先清除原有评论
    			cleanReview();
    			
    			isloading=false;
    			//重新加载评论
    			getReview(1);
    			scrollEvent();  //重新加载以后还要再绑定事件
    		}		
    		
    	});
    });  
  });
	
	
  	//清空评论函数
  	function cleanReview(){
  		$("#reviewlist").html("");
  	}
  	//请求评论函数
  	function getReview(pageNum){
  		if(pageNum>totalpages){//进入函数的时候先判断是否不用继续加载，解绑事件
  			return;//解绑 但是错误了， 选择直接return吧
  		}
  		if(isloading){
  			return;
  		}else{
  			isloading=true;
  		}
  		$.ajax({
    		type:"POST",
    		url:"/ragdollcat/review/comment",
    		data:{
    			articleid:aid,
    			pn:pageNum,
    		},  		
    		dataType:"json",
    		success: function (data) {
    			//加载评论	
    			currentpage = data.currentPage;
    			totalpages = data.totalPages;
    			if(totalpages==0){
    				$("#reviewlist").html("*暂无评论");
    			}else{
	    			var reviewlist = data.list;//懒加载Personcategory出问题
	    			show(reviewlist);
	    			isloading=false;   //加载完成之后重新把islaoding 设为false
    			}
    		},		
    	});
  	} 
  	
  	//展示评论函数
  	function show(reviewlist){
  		//待写
  		$.each(reviewlist,function(i,item){
  			var reviewshow = "<li><div class='headimg' ><img src="+ item.imgUrl +" alt='头像'></div><p><span class='name'>"+item.personName+"&#8197;:&#8197;</span>"+item.content+"</p></li>";		
  			$("#reviewlist").append(reviewshow);
  		});
  	}
  	
  	function scrollEvent(){	
	  		$(window).scroll(function(){	
		  			var docHeight = $(document).height();
		  			var winHeight = $(window).height();
		  			var scrollHeight=$(window).scrollTop();
		  			if(docHeight-30<=winHeight+scrollHeight){
		  				getReview(currentpage+1);
	  				}
	  			
	  		});
  	}
</script>
</body>
</html>