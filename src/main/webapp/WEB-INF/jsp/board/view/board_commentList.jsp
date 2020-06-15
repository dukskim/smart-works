<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

댓글목록(<c:out value="${paging.count}" /><!-- c:out value="${fn:length(boardList)}" /-->)
<table>
<colgroup>
	<col width="90px">
	<col width="470px">
	<col width="110px;">
	<col width="60px;">
</colgroup>
<tbody>
	<c:forEach var="item" items="${boardCommentList}" varStatus="status">
	<tr>
	<td>
		<c:choose>
		<c:when test="${empty item.nickNm}">알수없음</c:when>
		<c:otherwise><span class="cur_pnt_1" onclick="javascript:window.open('/user/userInfoView.do?userId=${item.userId}', 'userInfoView', 'width=300px,height=220');"><c:out value="${item.nickNm}" /></span></c:otherwise>
		</c:choose>
	</td>
	<td><div class="viewComent" style="width: 470px;"><pre><c:out value="${item.commentContent}" escapeXml="false" /></pre></div></td>
	<td align="center"><fmt:formatDate pattern="yy/MM/dd HH:mm" value="${item.regDt}" type="both"/></td>
	<td align="center" >
		<c:if test="${userInfo.userId eq item.userId || userInfo.userLevel >= 10}">
		<div style="width:100%;text-align:center;">
			<div style="margin:0 auto;line-height:normal;">
				<img src="/resources/images/btn_delete.gif" onclick="deleteComment('<c:out value="${item.commentSeq}" escapeXml="false" />')" style="cursor: pointer;vertical-align: middle;">
			</div>
		</div>
		</c:if>
	</td>
	</tr>
	<c:if test="${not status.last}">
	<tr>
	<td colspan="4"><div style="width:100%; border-bottom: 1px solid; border-color: #C0C0C0"></div></td>
	</tr>
	</c:if>
	</c:forEach>
</tbody>
</table>

<!-- 페이징 처리 -->
<table class="paging01 center_size">
<!-- 
<tr>
<td align="left" style="text-align:left;">총 <c:out value="${paging.count}" />건</td>
</tr>
 -->
<tr>
<td>
	<c:if test="${paging.bPreviousView}">
		<a href="javascript:pagingCommentList(${paging.previousParam})">[이전]</a>
	</c:if> 
	<c:forEach var="pCnt" items="${paging.listParam}">
	<c:choose>
		<c:when test="${paging.currentPage == pCnt}">
			[<c:out value="${pCnt}" />]
		</c:when>
		<c:otherwise>
			<a href="javascript:pagingCommentList(${pCnt})">[<c:out value="${pCnt}" />]</a>
		</c:otherwise>
	</c:choose>
	</c:forEach>		
	<c:if test="${paging.bNextView}">
		<a href="javascript:pagingCommentList(${paging.nextParam})">[다음]</a>
	</c:if></td>
</tr>
</table>
<form id="boardCommentPagingForm" name="boardCommentPagingForm" action="" method="post">
<input type="hidden" name="boardId" id="pagingCommentBoardId" value="${boardGroup.boardId}" />
<input type="hidden" name="boardSeq" id="pagingCommentBoardSeq" value="${boardComment.boardSeq}" /> 
<input type="hidden" name="pageNo" id="pagingCommentPageNo" />
</form>
<script type="text/javascript">
function pagingCommentList(pageNo){
	$("form[id=boardCommentPagingForm] input[id=pagingCommentPageNo]").val(pageNo);
	$("#commentList").load("/board/board_commentList.do?"+$('#boardCommentPagingForm').serialize());
}
</script>
<!-- 페이징 처리 끝 -->
