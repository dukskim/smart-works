<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="leng" value="${fn:length(viewBoardGroupList)}" />
<div style="float: left;">
<c:forEach var="boardGroupList" items="${viewBoardGroupList}" varStatus="st">
<div style="display: inline-block; width:380px;height:240px;">
	<div class='featurebox_center white' style="margin-bottom: 10px;">
		<div style="float: left;">
		<h4>
		<c:out value="${boardGroupList.boardGroup.boardKor}" />
		</h4>
		</div>
		<div style="float: right;"><a href="/board/boardList.do?boardId=${boardGroupList.boardGroup.boardId}">More...</a></div>
	</div>
	<table class="list">
	<colgroup>
		<col width="235px">
		<col width="50px">
		<col width="90px">
	</colgroup>
	<thead>
	<tr>
	<th>제목</th>
	<th>추천수</th>
	<th>등록일</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach var="item" items="${boardGroupList.boardList}" varStatus="status">
	<tr onMouseOver="this.style.backgroundColor='#e6e6fa'" onMouseOut="this.style.backgroundColor='#FFFFFF'">
	<td title="${item.title}">
	<c:if test="${item.boardStep > 0}"><c:out value="${item.refBoardSeq}"/>&nbsp;&gt;</c:if>
	<a href="/board/viewBoard.do?boardId=${boardGroupList.boardGroup.boardId}&boardSeq=${item.boardSeq}">
	<c:out value="${item.title}" />
		<c:if test="${item.commentCnt > 0}">
		&nbsp;<strong>[<c:out value="${item.commentCnt}" />]</strong>
		</c:if>
	</a>
	</td>
	<td align="center" title="${item.recomCnt}"><c:out value="${item.recomCnt}" /></td>
	<td align="center" title=""><fmt:formatDate pattern="yy/MM/dd HH:mm" value="${item.regDt}" type="both"/></td>
	</tr>
	</c:forEach>
	</tbody>
	</table>
</div>
</c:forEach>
</div>