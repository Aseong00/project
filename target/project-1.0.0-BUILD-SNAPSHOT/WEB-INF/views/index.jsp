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
  .header_menu_last {padding-right: 40px;}
  .content_wrap { margin:auto; width: 1080px; height: 500px; border : 2px solid #999999; border-top: none; display: flex;}
  .content_wrap_menu_left { background-color: blue; width: 20%; height: 100%; display: flex;}
  .content_wrap_menu_right {background-color: red; width: 80%; height: 100%; display: flex;}
</style>
<html>
<head>
  <meta charset="UTF-8">
  <title>Community</title>
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
<section class="content_wrap">
  <div class="content_wrap_menu_left"></div>
  <div class="content_wrap_menu_right"></div>
</section>
</body>
</html>