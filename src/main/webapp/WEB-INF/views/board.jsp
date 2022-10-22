<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="loginOutLink" value="${sessionScope.id==null ? '/login/login' : '/login/logout'}"/>
<c:set var="loginOut" value="${sessionScope.id==null ? '로그인' : '로그아웃'}"/>
<!DOCTYPE html>
<style>
  * {
    box-sizing: border-box;
    margin : 0;
    padding: 0;
  }

  a { text-decoration: none;  }

  ul {
    margin: auto;
    list-style-type: none;
    height: 100%;
    width: 80%;
    display: flex;
  }

  ul > li {
    color: lightgray;
    height : 100%;
    display:flex;
    align-items: center;
  }

  ul > li > a {
    color: #cccccc;
    margin:auto;
    padding: 10px;
    font-size:17px;
    align-items: center;
  }

  ul > li > a:hover {
    color :white;
    border-bottom: 3px solid #cccccc;
  }

  .logo {
    color:white;
    font-size: 18px;
    padding-left:40px;
    margin-right:auto;
    display: flex;
  }
  .menu_wrap { width: 100%; height: 50px; background-color: #30426E; border-bottom: 2px solid #999999;}
  .menu {width: 1080px;}
  .content_wrap { margin:auto; width: 1080px; border : 2px solid #999999; border-top: none;}
</style>
<html>
<head>
  <meta charset="UTF-8">
  <title>Community</title>
  <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
</head>
<body>
<header class="menu_wrap">
  <ul class="menu">
    <li class="logo">Community</li>
    <li><a href="<c:url value='/'/>">커뮤니티홈</a></li>
    <li><a href="<c:url value='/board/list'/>">게시판</a></li>
    <li><a href="<c:url value='${loginOutLink}'/>">${loginOut}</a></li>
    <c:if test="${sessionScope.id==null}">
      <li><a href="<c:url value='/register/add'/>">회원가입</a></li>
    </c:if>
  </ul>
</header>
<div class="content_wrap">
  <h2>게시물 읽기</h2>
  <form action="" id="form">
    <input type="text" name="bno" value="${boardDto.bno}" readonly>
    <input type="text" name="title" value="${boardDto.title}" readonly>
    <textarea name="content" id="" cols="30" rows="10" readonly>${boardDto.content}</textarea>
    <button type="button" id="writeBtn" class="btn">등록</button>
    <button type="button" id="modifyBtn" class="btn">수정</button>
    <button type="button" id="removeBtn" class="btn">삭제</button>
    <button type="button" id="listBtn" class="btn">목록</button>
  </form>
</div>
<script>
  $(document).ready(function(){
    $("#listBtn").on("click", function(){
      location.href="<c:url value='/board/list?page=${page}&pageSize=${pageSize}'/>";
    });
    $("#removeBtn").on("click", function(){
      if (!confirm("정말로 삭제하시겠습니까?")) return;
      let form = $("#form");
      form.attr("action", "<c:url value='/board/remove?page=${page}&pageSize=${pageSize}'/>");
      form.attr("method", "post");
      form.submit();
    });
  });
</script>
</body>
</html>