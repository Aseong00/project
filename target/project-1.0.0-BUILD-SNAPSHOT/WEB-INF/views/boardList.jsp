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
  <table>
    <tr>
      <th>번호</th>
      <th>제목</th>
      <th>이름</th>
      <th>등록일</th>
      <th>조회수</th>
    </tr>
    <c:forEach var="boardDto" items="${list}">
      <tr>
        <th>${boardDto.bno}</th>
        <th><a href="<c:url value='/board/read?bno=${boardDto.bno}&page=${page}&pageSize=${pageSize}'/>">${boardDto.title}</a></th>
        <th>${boardDto.writer}</th>
        <th>${boardDto.reg_date}</th>
        <th>${boardDto.view_cnt}</th>
      </tr>
    </c:forEach>
  </table>
  <br>
  <div>
    <c:if test="${ph.showPrev}">
      <a href="<c:url value='/board/list?page=${ph.beginPage-1}&pageSize=${ph.pageSize}'/>">&lt;</a>
    </c:if>
    <c:forEach var="i" begin="${ph.beginPage}" end="${ph.endPage}">
      <a href="<c:url value='/board/list?page=${i}&pageSize=${ph.pageSize}'/>">${i}</a>
    </c:forEach>
    <c:if test="${ph.showNext}">
      <a href="<c:url value='/board/list?page=${ph.endPage+1}&pageSize=${ph.pageSize}'/>">&gt;</a>
    </c:if>
  </div>
</div>
<script>
  let msg= "${msg}"
  if (msg == "DEL_OK") {
    alert("성공적으로 삭제되었습니다.");
  }
  if (msg == "DEL_ERR") {
    alert("삭제에 실패했습니다.");
  }

</script>
</body>
</html>