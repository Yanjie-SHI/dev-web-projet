<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View</title>
</head>
<body>
<h1>View</h1>
	<form action="<%=request.getContextPath() %>/BlogUpdate" method="post">
	<input type="hidden" value="${blogs.id}" name="id" id="id"/>
		Title：<input type="text" value="${blogs.title}" name="title" id="title"/><br/>
		Author：<input type="text" value="${blogs.author}" name="author" id="author"/><br/>
 		<div id="div1" >
        	<p>${blogs.content}</p>
    	</div>
    	<textarea id="text1" style="display:none;" name="content"></textarea>
		<input type="submit" value="Submit">
	</form>
	<button onclick="reSet()">Reset</button>
</body>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/wangEditor.js"></script>
<script type="text/javascript">
      var E = window.wangEditor;
      var editor = new E('#div1');
      var $text1 = $('#text1');
      editor.customConfig.onchange = function (html) {
           $text1.val(html);
      }
      editor.create()
       $text1.val(editor.txt.html())
  </script>
<script type="text/javascript">
var title = $("#title");
var author = $("#author");
var content = $("#content");

var reSet = function(){
	title.val(  "${blogs.title}");
	author.val("${blogs.author}");
	content.val("${blogs.content}") ;
}

var code = ${code};
if(code!=""){
	if(code==200){
		alert("Success！");
		window.location.href="<%=request.getContextPath()%>/BlogListServlet";
	}else{
		alert("Failed！");
	}
}
</script>
</html>