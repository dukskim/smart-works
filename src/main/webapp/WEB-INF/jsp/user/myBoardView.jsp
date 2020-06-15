<%@page import="com.dhkim.sworks.sql.domain.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
$(document).ready(function(){
	$("#boardId").change(searching);
	$("input:radio[name=boardDiv]").change(searching);
});

function searching(){
	if (!validate(true)) return;
	listPaging($('#searchForm'));
}

function listPaging(pform){
	$("#myBoardList").load("/user/myBoard.do?"+pform.serialize());
}

function validate(isMsg){
	var checkStr = "";
	checkStr = $("#boardId option:selected").val();
	if (checkStr == ""){
		if (isMsg) alert('게시판을 선택해 주세요.');
		return false;
	}
	checkStr = $("input:radio[name=boardDiv]:checked").val();
	if (!checkStr){
		if (isMsg) alert('작성한 게시글 타입을 선택해 주세요.');
		return false;
	}
	return true;
}

</script>

<c:set var="divNormal" value="<%= Board.DIV_NORMAL %>" />
<c:set var="divComment" value="<%= Board.DIV_COMMENT %>" />
<c:set var="divReplay" value="<%= Board.DIV_REPLAY %>" />

<div class="center_size">
	<div class='featurebox_center'>
	<form id="searchForm" name="searchForm" method="post">
		<select name="boardId" id="boardId" >
			<option value="">선택하세요</option>
			<c:forEach var="item" items="${boardGroupList}">
			<option value="${item.boardId}"><c:out value="${item.boardKor}" /></option>
			</c:forEach>
		</select>
		<input type="radio" name="boardDiv" value="<c:out value="${divNormal}"/>" checked="checked" />일반
		<input type="radio" name="boardDiv" value="<c:out value="${divComment}"/>" />답글
		<input type="radio" name="boardDiv" value="<c:out value="${divReplay}"/>" />리플
	</form>
	</div>
	
	<div id="myBoardList"></div>
	
</div>
