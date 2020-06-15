<%@page import="com.dhkim.sworks.sql.domain.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="divComment" value="<%= Board.DIV_COMMENT %>" />

	<table class="list center_size
	<c:if test="${boardDiv eq divComment}">
	td_height_auto
	</c:if>
	">
	<colgroup>
		<c:choose>
		<c:when test="${boardDiv ne divComment}">
		<col width="80px">
		<col width="248px">
		<col width="90px">
		<col width="90px">
		<col width="120px">
		</c:when>
		<c:otherwise>
		<col width="80px">
		<col width="428px">
		<col width="120px">
		</c:otherwise>
		</c:choose>
	</colgroup>
	<thead>
	<tr>
	<c:choose>
	<c:when test="${boardDiv ne divComment}">
	<th>번호</th>
	<th>제목</th>
	<th>조회수</th>
	<th>추천수</th>
	<th>등록일</th>
	</c:when>
	<c:otherwise>
	<th>번호</th>
	<th>내용</th>
	<th>등록일</th>
	</c:otherwise>
	</c:choose>
	</tr>
	</thead>
	<tbody>
	<c:forEach var="item" items="${boardList}" varStatus="status">
	<tr onMouseOver="this.style.backgroundColor='#e6e6fa'" onMouseOut="this.style.backgroundColor='#FFFFFF'">

	<c:choose>
	<c:when test="${boardDiv ne divComment}">
	<td align="center" title="${item.boardSeq}"><c:out value="${item.boardSeq}" /></td>
	<td title="${item.title}">
		<c:forEach var="cnt" begin="1" end="${item.boardStep}" >&nbsp;&nbsp;</c:forEach>
		<a href="/board/viewBoard.do?boardId=${boardId}&boardSeq=${item.boardSeq}">
		<c:out value="${item.title}" />
		<c:if test="${item.commentCnt > 0}">
		&nbsp;<strong>[<c:out value="${item.commentCnt}" />]</strong>
		</c:if>
		</a>
	</td>
	<td align="center" title="${item.showCnt}"><c:out value="${item.showCnt}" /></td>
	<td align="center" title="${item.recomCnt}"><c:out value="${item.recomCnt}" /></td>	
	<td align="center" title=""><fmt:formatDate pattern="yy/MM/dd HH:mm" value="${item.regDt}" type="both"/></td>
	</c:when>
	<c:otherwise>
	<td align="center" title="${item.boardSeq}"><c:out value="${item.boardSeq}" /></td>
	<td title="${item.commentContent}" style="text-align: left;">
		<div class="viewComent cur_pnt_1" style="width: 100%;" onclick="location.href='/board/viewBoard.do?boardId=${boardId}&boardSeq=${item.boardSeq}';">
		<pre><c:out value="${item.commentContent}" escapeXml="false" /></pre>
		</div>
	</td>
	<td align="center" title=""><fmt:formatDate pattern="yy/MM/dd HH:mm" value="${item.regDt}" type="both"/></td>
	</c:otherwise>
	</c:choose>
	</tr>
	</c:forEach>
	</tbody>
	</table>
	
	<!-- 페이징 처리 -->
	<table class="paging01 center_size">
	<tr>
	<td align="left" style="text-align:left;">총 <c:out value="${paging.count}" />건</td>
	</tr>
	<tr>
	<td>
		<c:if test="${paging.bPreviousView}">
			<a href="javascript:pagingList(${paging.previousParam})">[이전]</a>
		</c:if> 
		<c:forEach var="pCnt" items="${paging.listParam}">
		<c:choose>
			<c:when test="${paging.currentPage == pCnt}">
				[<c:out value="${pCnt}" />]
			</c:when>
			<c:otherwise>
				<a href="javascript:pagingList(${pCnt})">[<c:out value="${pCnt}" />]</a>
			</c:otherwise>
		</c:choose>
		</c:forEach>		
		<c:if test="${paging.bNextView}">
			<a href="javascript:pagingList(${paging.nextParam})">[다음]</a>
		</c:if></td>
	</tr>
	</table>
	<form id="boardPagingForm" name="boardPagingForm" method="post">
	<input type="hidden" name="boardId" id="pagingBoardId" value="${boardId}" />
	<input type="hidden" name="boardDiv" id="pagingBoardDiv" value="${boardDiv}" />
	<input type="hidden" name="pageNo" id="pagingPageNo" />
	</form>
	<script type="text/javascript">
	function pagingList(pageNo){
		$("form[id=boardPagingForm] input[id=pagingPageNo]").val(pageNo);
		listPaging($("form[id=boardPagingForm]"));
	}
	</script>
	<!-- 페이징 처리 끝 -->