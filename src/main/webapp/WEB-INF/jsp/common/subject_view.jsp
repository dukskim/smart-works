<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="header">
	<h1 class="cur_pnt_1" onclick="location.href='/home/main.do'">
	<font color="#FFFFFF">Smart</font>&nbsp;<font color="#FFDF8C">Portal</font>
	</h1>
	<h2>Developer Space</h2>
</div>
<div id="navcontainer">
	<ul id="navlist">
	<c:forEach var="item" items="${menuList}">
	<li><div><a href="/board/boardList.do?boardId=${item.boardId}"
	<c:if test="${item.boardId eq menu}">id="current"</c:if>
	><c:out value="${item.boardKor}" /></a></div></li>
	</c:forEach>
	</ul>
</div>
