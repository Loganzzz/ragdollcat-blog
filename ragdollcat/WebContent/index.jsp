<%@page import="main.com.zhang.blog.util.GetHttpUtil"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <link rel="icon" href="../../favicon.ico">
    
<title>登录</title>

	<link href="bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
	<script type="text/javascript" src="JQuery/jquery-3.2.1.js"></script> 
	<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<link href="">
    <!-- Custom styles for this template -->
    <link href="css/cover.css" rel="stylesheet">
	<link href="css/mask.css" rel="stylesheet">
    <link href="css/signin.css" rel="stylesheet">
</head>
<%
 	/*取得bing图片的url*/
	String url = "http://cn.bing.com/";
	String backurl = url + GetHttpUtil.getObject("az/hprichbg/rb/.+?jpg", GetHttpUtil.getHttp(url));
%>
<body style="background:url(<%=backurl%>)">

    <div class="site-wrapper">

      <div class="site-wrapper-inner">

        <div class="cover-container">

          <div class="masthead clearfix">
            <div class="inner">
              <h3 class="masthead-brand">布偶猫</h3>
              <nav>
                <ul class="nav masthead-nav container-fluid">
                  <li class="active"><a href="#">首页</a></li>
                  <li><a href="#" id="signin">登录</a></li>
                  <li><a href="#">关于</a></li>
                </ul>
              </nav>
            </div>
          </div>

          <div class="inner cover">
            <h1 class="cover-heading">欢迎来到我的个人小站</h1>
            <p class="lead">这里是正正和布偶猫的二人小站，这里记录着生活日常，也会有技术博客，
            有乐趣，也有心得，相信你会满意</p>
            <p class="lead">
              <a href="#" class="btn btn-lg btn-default">进入小站</a>
            </p>
          </div>

          <div class="mastfoot">
            <div class="inner">
              <p>for more contact<a href="https://twitter.com/mdo">@Logan</a>.</p>
            </div>
          </div>

        </div>

      </div>

    </div>
    
    <div class="container" style="width: 400px" id="LoginBox">

      <form class="form-signin">
        <h2 class="form-signin-heading">Please sign in</h2>
        <a href="javascript:void(0)" title="关闭窗口" class="close_btn" id="closeBtn">×</a>
        <label for="inputEmail" class="sr-only">Email address</label>
        <input type="email" id="inputEmail" class="form-control" placeholder="Email address" required autofocus>
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="inputPassword" class="form-control" placeholder="Password" required>
        <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me"> Remember me
          </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
      </form>
      
    </div> <!-- /container -->
    	<script type="text/javascript">
	$(function ($) {
		//弹出登录
		$("#signin").hover(function () {
			$(this).stop().animate({
				opacity: '1'
			}, 600);
		}, function () {
			$(this).stop().animate({
				opacity: '0.6'
			}, 1000);
		}).on('click', function () {
			$("body").append("<div id='mask'></div>");
			$("#mask").addClass("mask").fadeIn("slow");
			$("#LoginBox").fadeIn("slow");
		});
		$(".close_btn").hover(function () { $(this).css({ color: 'black' }) }, function () { $(this).css({ color: '#999' }) }).on('click', function () {
			$("#LoginBox").fadeOut("fast");
			$("#mask").css({ display: 'none' });
		});
	});
	</script>
</body>
</html>