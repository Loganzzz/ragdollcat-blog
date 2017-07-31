<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"
	import="main.com.zhang.blog.entity.Person"
	import="main.com.zhang.blog.entity.Category"
	import="main.com.zhang.blog.service.CategoryService"    
%>
<%@ page import="org.springframework.context.ApplicationContext"%>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%
	ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
	CategoryService<Category> categoryService = (CategoryService<Category>)ctx.getBean("categoryService", CategoryService.class);
	List<Category> categoryList = categoryService.list("from Category where deleted=0");
%>

<title>发表新文章</title>
    <script type="text/javascript" charset="utf-8" src=<%=request.getContextPath() +"/ueditor/ueditor.config.js"%>></script>
    <script type="text/javascript" charset="utf-8" src=<%=request.getContextPath() +"/ueditor/ueditor.all.min.js"%>> </script>
	<script type="text/javascript" charset="utf-8" src=<%=request.getContextPath() +"/ueditor/lang/zh-cn/zh-cn.js"%>></script>
	<script type="text/javascript">
	function changeDetailSetting(){
		detailSetting.style.display=(detailSetting.style.display=="none"?"":"none");
	} ;
	   
	function uptext(){
			if('<%=session.getAttribute("person")==null%>'=="true"){
				alert("请先登陆!");
			}
			else if (!UE.getEditor('container').hasContents()){
				alert('请先填写内容!');
			}else{
				document.EditForm.content.value=UE.getEditor('container').getContent();
				document.EditForm.submit(); 
			}          
		}
	</script>
    <style type="text/css">
        div{
            width:100%;
        }
    </style>
</head>
<body>
	<div>
    <h1>新文章</h1>
    <table width="99%"  border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
  <tr>
    <td height="38" align="center" valign="bottom">
    <table width="95%" height="34"  border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td valign="middle">
        <table width="53%"  border="0" cellspacing="0" cellpadding="0">
          <tr>
               
                <td width="90%" valign="bottom" class="titletxt">发表新blog</td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  
  <tr>
    <td align="center" valign="top">
	
	<form id="EditForm" name="EditForm" method="post" action="create" enctype=”multipart/form-data”>		        
       <table width="85%"   border="1" cellpadding="0" cellspacing="1">
	          <tr> 
	            <td width="80" height="23" align="right" class="formLable">标题</td>
		            <td  colspan="3" width="900"  class="formText"> 
			            <font color="#FF0000"> 
			              <textarea name="title" rows="2" id="title" style="width:98%" ></textarea>
			            *</font>
		            </td>
	          </tr>
	          <tr> 
	            <td  width="80"  align="right" >文章简介</td>
	            <td colspan="3"width="900">
	            	<textarea name="intro" rows="3"  id="intro" style="width:99%"></textarea >
	            </td>
	          </tr>
        
	          <tr> 
	            <td  width="80"  align="right" class="formLable">文章内容<br>
	            </td>
	            <td>
	                 <script id="container" type="text/plain" style="width:1033px;height:500px;"></script>
	                 <input name="content" id="content" type="hidden">
	             </td>
	          </tr>
	          
			   <tr > 
	            <td colspan="4"  align="right"><a href="#" onClick="changeDetailSetting()"><font color="#0000CC">显示详细选项&gt;&gt;</font></a>  &nbsp;&nbsp;&nbsp;&nbsp;</td>
	          </tr>
			    <tr> 
		            <td colspan="4"  align="right"  >			
		            <table width="100%"  border="1" id="detailSetting" style="display:none">	  
			          	<tr> 
				            <td width="73"  align="right" class="formLable">审核</td>
				            <td >
					            <table width="100%"  border="0" height="100%">
					              <tr>
						                <td><input type="checkbox" name="check"  value="1" ></td>
						                <td >推荐</td>
						                <td><input name="recommand" type="checkbox" value="1"  ></td>
						                <td><span >作者</span></td>
						               <%String author="";if(session.getAttribute("person")!=null) author=((Person)session.getAttribute("person")).getNickname(); %>
						                <td><input name="author" type="text" value=<%=author%> size="8"></td>
						                <td>评论：</td>
						                <td><input type="radio" checked="checked" name="enablereview" value="true">
						                  	允许 
						                    <input type="radio" name="enablereview" value="false">
						                                             禁止
						                </td>
				              		</tr>
				            	</table>
				            </td>
			            	<td align="right"  class="formLable">直接跳转URL</td>
			            	<td    class="formText"><input name="url" disabled="disabled"  type="text" id="url" value="功能暂未开通" size="20" ></td>
			          </tr>           
		            </table>
		            </td>
	          </tr>
	          <tr> 
	            <td width="80" height="23" align="right" class="formLable">所属栏目</td>
	            <td  align="left"   class="formText">
		            <select name="category">
 					<%for(int i=0;i<categoryList.size();++i) {%>
 						<option value=<%=categoryList.get(i).getTitle() %>><%=categoryList.get(i).getTitle() %></option>   
 					<%} %>		            	           	   
		            </select>
		        </td>            	    
	          </tr>
          </table>

          <table>
          		<tr>
          			<td>                     
          				<input type="button" name="loginin" value="发表" onclick="uptext();">
          			</td>
          		</tr>
          
          </table>
    	</form>
    </td>
  </tr>
</table>     
    
    <script type="text/javascript" src=<%=request.getContextPath() +"/ueditor/ueditor.config.js"%>></script>

    <script type="text/javascript" src=<%=request.getContextPath() +"/ueditor/ueditor.all.js"%>></script>
    <!-- 实例化编辑器 -->
    <script type="text/javascript">
        var editor = UE.getEditor('container');
      /*   UE.getEditor('editorID', {
        	allowDivTransToP: false
        	}) */
        	//复写UEDITOR的getActionUrl 方法,定义自己的Action
		UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
		UE.Editor.prototype.getActionUrl = function(action) {
			if (action == 'uploadimg' || action == 'uploadfile') {
						return '<%=request.getContextPath()+"/uploadup/ue/uploadimg"%>';
			} else {
 						return this._bkGetActionUrl.call(this, action);
			}
		}; 
    </script>
</div>
</body>
</html>