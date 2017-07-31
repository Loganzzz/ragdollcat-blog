<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录</title>
<link rel="stylesheet" type="text/css" href=<%=request.getContextPath()+"/css/reset.css"%>>
<link rel="stylesheet" type="text/css" href=<%=request.getContextPath()+"/css/structure.css"%>>
<% 	Cookie[] cookies = request.getCookies(); 
	String username = ""; String password = "";
	if(cookies!=null && cookies.length!=0 ){
		for(int i=0;i<cookies.length;++i){
			if(cookies[i].getName().equals("user")){
				String[] s = cookies[i].getValue().split("#");
				username=s[0];
				password=s[1];
				break;
			}
		}
	}
%>
</head>
<body>
<form class="box login" method="post" action="dologin">
	<fieldset class="boxBody">
	  <label>用户名</label>
	  <input id="name" name="username" type="text" tabindex="1"  required>
	  <label><a href="#" class="rLink" tabindex="5">忘记密码?</a>密码</label>
	  <input id="pass" name="password" type="password" tabindex="2"  required>
	</fieldset>
	<footer>
	  <label><input id="check" name="remember"type="checkbox" tabindex="3" value="yes"  >记住密码</label>
	  <input type="submit" class="btnLogin" value="Login" tabindex="4">
	</footer>
</form>

<script type="text/javascript">
window.onload=function(){  
	var name='<%=username %>';
	var pass='<%=password %>';
	if(name){  
	    if(name!='' && name!=null &&name!=undefined){  
	        document.getElementById("name").value=name;
	        document.getElementById("pass").value=pass;
	        document.getElementById("check").setAttribute("checked","checked");
	    }  
	}  
	}  
</script>
</body>
</html>