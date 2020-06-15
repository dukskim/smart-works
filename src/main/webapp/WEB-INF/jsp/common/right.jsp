<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
<!--
function secede(){
	if (confirm('탈퇴하시겠습니까?')){
		location.href='/user/secede.do';
	}
}

function update_user(){
	if (confirm('회원정보를 수정하시겠습니까?')){
		location.href='/user/modUserView.do';
	}
}

//-->
</script>
<h3>사용자 정보</h3>

<p>
ID:[<c:out value="${userInfo.userId}"/>]&nbsp;<a href="/login/logout.do">로그아웃</a>
<br/>
<c:out value="${userInfo.nickNm}"/> 님 안녕하세요
<br/>
<c:out value="${userInfo.email}"/>
<br/>
Point:<c:out value="${userInfo.point}"/>
<br/>
등급:<c:out value="${userInfo.userLevel}"/>
<br/>
소속:<c:out value="${userInfo.deptId}"/>
<br/>
가입상태:<c:out value="${userInfo.regStatus}"/>
<br/>
<span onclick="secede()" class="cur_pnt_1">회원탈퇴</span>&nbsp;|&nbsp;<span onclick="update_user()" class="cur_pnt_1">회원수정</span>
</p>

<c:if test="${userInfo.userLevel >= 10}">		
<h3>관리자 메뉴</h3>
<div class='featurebox_side'>
	<a href="/admin/addBoardGroupView.do">게시판추가</a>
	<br/>
	<a href="/admin/boardGroupListView.do">게시판목록</a>
	<br/>
	<a href="/admin/addCategoryView.do">카테고리추가</a>
	<br/>
	<a href="/admin/categoryListView.do">카테고리목록</a>
	<br/>
	<a href="/admin/userListView.do">회원목록</a>
</div>
</c:if>

<p>
</p>

<h3>개인 마당</h3>
<div class='featurebox_side'>
	<a href="/user/myBoardView.do">내가 쓴 글</a>
</div>
