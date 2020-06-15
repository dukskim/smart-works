<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript">
$(document).ready(function(){
});

</script>
<div class='featurebox_center white'>
<h4>회원 목록</h4>
</div>
<form id="ulForm" name="ulForm" method="post" >
<!-- .center_size {width: 770px;} -->
<table class="list center_size">
<colgroup>
	<col width="60px">
	<col width="60px">
	<col width="70px">
	<col width="80px">
	<col width="50px">
	<col width="50px">
	<col width="80px">
	<col width="80px">
	<col width="70px">
	<col width="50px">
	<col width="70px">
	<col width="50px">
</colgroup>
<thead>
	<tr>
	<th>ID</th>
	<th>이름</th>
	<th>별명</th>
	<th>이메일</th>
	<th>POINT</th>
	<th>등급</th>
	<th>전화번호</th>
	<th>휴대폰</th>
	<th>소속</th>
	<th>상태</th>
	<th>가입일</th>
	<th>선택</th>
	</tr>
</thead>
<tbody>
<c:forEach var="item" items="${userInfoList}">
	<tr onMouseOver="this.style.backgroundColor='#e6e6fa'" onMouseOut="this.style.backgroundColor='#FFFFFF'">
	<td title="${item.userId}"><c:out value="${item.userId}" /></td>
	<td title="${item.userNm}"><c:out value="${item.userNm}" /></td>
	<td title="${item.nickNm}"><c:out value="${item.nickNm}" /></td>
	<td title="${item.email}"><c:out value="${item.email}" /></td>
	<td align="center" title="${item.point}"><c:out value="${item.point}" /></td>
	<td align="center" title="${item.userLevel}"><c:out value="${item.userLevel}" /></td>
	<td title="${item.telNum}"><c:out value="${item.telNum}" /></td>
	<td title="${item.mobile}"><c:out value="${item.mobile}" /></td>
	<td title="${item.deptId}"><c:out value="${item.deptId}" /></td>
	<td align="center" title="${item.regStatus}"><c:out value="${item.regStatus}" /></td>
	<td align="center"><fmt:formatDate pattern="yy-MM-dd" value="${item.regDt}" type="both"/></td>
	<td align="center">
	<!-- 
	<input type="checkbox" name="userId" id="chkList" value="${item.userId}"
	<c:if test="${item.userLevel eq 10 }"> disabled="disabled" </c:if>
	 />
	 -->
	</td>
	</tr>
</c:forEach>
</tbody>
</table>
</form>

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
	<form id="userPagingForm" name="userPagingForm" action="/admin/userListView.do" method="post">
	<input type="hidden" name="pageNo" id="pagingPageNo" />
	<input type="hidden" name="searchType" id="pagingSearchType" value="${userInfo.searchType}" />
	<input type="hidden" name="searchVal" id="pagingSearchVal" value="${userInfo.searchVal}" />
	</form>
	<script type="text/javascript">
	function pagingList(pageNo){
		$("form[id=userPagingForm] input[id=pagingPageNo]").val(pageNo);
		$("form[id=userPagingForm]").submit();
	}
	</script>
	<!-- 페이징 처리 끝 -->
<br/>
<!-- 
<input type="button" value="승인" id="btnUpdate" class="btn_list50" />
<input type="button" value="삭제" id="btnCancel" class="btn_list50" />
 -->
<br/>
<br/>
