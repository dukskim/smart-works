<%@page import="com.dhkim.sworks.sql.domain.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="divNormal" value="<%= Board.DIV_NORMAL %>" />
<c:set var="divComment" value="<%= Board.DIV_COMMENT %>" />
<c:set var="divReplay" value="<%= Board.DIV_REPLAY %>" />

<script type="text/javascript">
$(document).ready(function(){
	$("#btnSearch").click(function(){
		$("#searchForm").submit();
	});
});

</script>

	<div class="center_size">
	<div class='featurebox_center'>
	<form id="searchForm" name="searchForm" action="/board/boardList.do" method="post">
		<select name="searchGroup" id="searchGroup" >
			<c:forEach var="item" items="${boardGroupList}">
				<option value="${item.boardId}"
				<c:if test="${item.boardId == boardGroup.boardId}" >
				selected="selected"
				</c:if>
				><c:out value="${item.boardKor}" /></option>
			</c:forEach>
		</select>
		<select name="searchType" id="searchType" >
			<option value="1"
				<c:if test="${board.searchType == '1'}" >
				selected="selected"
				</c:if>
			>제목</option>
			<option value="2"
				<c:if test="${board.searchType == '2'}" >
				selected="selected"
				</c:if>
			>내용</option>
			<option value="3"
				<c:if test="${board.searchType == '3'}" >
				selected="selected"
				</c:if>
			>제목+내용</option>
			<option value="4"
				<c:if test="${board.searchType == '4'}" >
				selected="selected"
				</c:if>
			>작성자(대화명)</option>
		</select>
		<input type="text" name="searchVal" id="searchVal" size="30" value="${board.searchVal}" /> 
		<input type="button" id="btnSearch" value="검색" class="btn_search50" >
	</form>
	</div>
	<br/>
	<div class='featurebox_center white'>
		<div style="float: left;">
		<h4><c:out value="${boardGroup.boardKor}" /></h4>
		</div>
		<div style="float: right;">
			<input type="button" id="btnAdd" value="글쓰기" onclick="location.href='/board/addBoardView.do?boardId=${boardGroup.boardId}';" class="btn_list50" >
		</div>
	</div>
	<table class="list center_size">
	<colgroup>
		<col width="80px">
		<col width="258px">
		<col width="50px">
		<col width="100px">
		<col width="80px">
		<col width="80px">
		<col width="110px">
	</colgroup>
	<thead>
	<tr>
	<th>번호</th>
	<th>제목</th>
	<th>구분</th>
	<th>작성자</th>
	<th>조회수</th>
	<th>추천수</th>
	<th>등록일</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach var="item" items="${boardList}" varStatus="status">
	<tr onMouseOver="this.style.backgroundColor='#e6e6fa'" onMouseOut="this.style.backgroundColor='#FFFFFF'">
	<td align="center" title="${item.boardSeq}"><c:out value="${item.boardSeq}" /></td>
	<td title="${item.title}">
	<c:forEach var="cnt" begin="1" end="${item.boardStep}" >&nbsp;&nbsp;</c:forEach>
	<a href="/board/viewBoard.do?boardId=${boardGroup.boardId}&boardSeq=${item.boardSeq}">
	<c:out value="${item.title}" />
		<c:if test="${item.commentCnt > 0}">
		&nbsp;<strong>[<c:out value="${item.commentCnt}" />]</strong>
		</c:if>
	</a>
	</td>
	<td align="center">
	<c:choose>
		<c:when test="${item.boardDiv eq divNormal}">일반</c:when>
		<c:when test="${item.boardDiv eq divReplay}">답변</c:when>
		<c:when test="${item.boardDiv eq divComment}">댓글</c:when>
		<c:otherwise></c:otherwise>
	</c:choose>
	</td>
	<td align="center" title="${item.nickNm}">
	<c:choose>
	<c:when test="${empty item.nickNm}">알수없음</c:when>
	<c:otherwise>
		<span class="cur_pnt_1" onclick="javascript:window.open('/user/userInfoView.do?userId=${item.userId}', 'userInfoView', 'width=300px,height=220');">
		<c:out value="${item.nickNm}" />
		</span>
	</c:otherwise>
	</c:choose>
	</td>
	<td align="center" title="${item.showCnt}"><c:out value="${item.showCnt}" /></td>
	<td align="center" title="${item.recomCnt}"><c:out value="${item.recomCnt}" /></td>
	<td align="center" title=""><fmt:formatDate pattern="yy/MM/dd HH:mm" value="${item.regDt}" type="both"/></td>
	</tr>
	</c:forEach>
	</tbody>
	</table>
	</div>
	
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
	<form id="boardPagingForm" name="boardPagingForm" action="/board/boardList.do" method="post">
	<input type="hidden" name="boardId" id="pagingBoardId" value="${boardGroup.boardId}" />
	<input type="hidden" name="pageNo" id="pagingPageNo" />
	<input type="hidden" name="searchType" id="pagingSearchType" value="${board.searchType}" />
	<input type="hidden" name="searchVal" id="pagingSearchVal" value="${board.searchVal}" />
	<input type="hidden" name="searchGroup" id="pagingSearchGroup" value="${board.searchGroup}" />
	</form>
	<script type="text/javascript">
	function pagingList(pageNo){
		$("form[id=boardPagingForm] input[id=pagingPageNo]").val(pageNo);
		$("form[id=boardPagingForm]").submit();
	}
	</script>
	<!-- 페이징 처리 끝 -->
	<br/>
	<br/>