<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Blog-List</title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/List.css">
     <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.4.1.min.js"></script>
    <script src="<%=request.getContextPath() %>/js/List.js"></script>
</head>
<body>
<div class="whole">
    <div class="top_bg">
        <div class="content">
            <div class="title">
                <span>My Blog</span>
                <div class="nav">
                    <ul>

                        <li class="listStyle"><a href="#">My Blog</a></li>

                    </ul>
                    <div class="write">
                    	<img src="<%=request.getContextPath()%>/images/Blog/pen.png"/>
                    	<button onclick="createBlog()">Write Blog</button>
                    </div>
                </div>
            </div>
            <div class="main">
                <c:forEach var="blog" items="${blogs }">
                <div class="blog_box" >
                    <div class="blog_title">
                        <span class="title_style">${blog.title }</span>
                        <span>Java</span>
                        <span class="time">${blog.date }</span>
                        <span >By - ${blog.author }</span>
                        <div class="btn">
                            <span class="updateSubmit" update="${blog.id }">View</span>
                            <span onclick="deleteBlog(${blog.id})">Delete</span>
                        </div>
                    </div>
                    <div class="blog_content">
                            <span>${blog.description }</span>
                        <img src="" alt="">
                    </div>
                </div>
                    <hr>

                    <hr>
                </c:forEach>
            </div>
            <form action="<%=request.getContextPath() %>/BlogListServlet">
                <input type="number" name="pageIndex" id="pageIndex"  />
                <input type="submit" value="Go"/>
                <p>Current：<span id="page"></span>Page</p>
                <button id="frontPage">Back</button>
                <button id="nextPage">Next</button>
                <button id="firstPage">First</button>
                <button id="lastPage">End</button>
            </form>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    var index = ${pageIndex};
    var count = ${count};
    var countX = Math.ceil(count/4);

    var pageIndex = 1;
    var inputValue = document.getElementById("pageIndex")

    if(index==1||index<1){
        document.getElementById("frontPage").disabled="disabled";
    }else if(index>1){
        document.getElementById("frontPage").disabled="";
    }

    if(index==countX||index>countX){
        document.getElementById("nextPage").disabled="disabled";
    }else if(index<countX){
        document.getElementById("nextPage").disabled="";
    }

    var i =${pageIndex}
        document.getElementById("page").innerHTML = i;
    document.getElementById("pageIndex").value = i;

    function front(event){
        inputValue.value--;
    }
    function next(event){
        inputValue.value++;
    }
    function goFirst(event){
        inputValue.value = 1;
    }
    function goLast(event){
        inputValue.value = countX;
    }

    document.getElementById("frontPage").addEventListener("click",front,false);
    document.getElementById("nextPage").addEventListener("click",next,false);
    document.getElementById("firstPage").addEventListener("click",goFirst,false);
    document.getElementById("lastPage").addEventListener("click",goLast,false);


    $(".updateSubmit").on("click",function(){
        var bid = $(this).attr("update");
        window.location.href="<%=request.getContextPath()%>/BlogUpdate?bid=" + bid;
    });

    /* 	var deleteBlog = function(id){
            alert(id);
        } */
    var deleteBlog = function(id){
        if(confirm("Delete？")){
            window.location.href="<%=request.getContextPath()%>/BlogDelete?bid=" + id;
        }
    }


    var createBlog = function(){
        window.location.href="<%=request.getContextPath()%>/BlogList/blogPublish.jsp";
    }
</script>
</html>