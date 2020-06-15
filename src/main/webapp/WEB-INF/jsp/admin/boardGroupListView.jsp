<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript">
$(document).ready(function(){
	$("#btnUpdate").click(function(){
		if( $(":checkbox[id='chkList']:checked").length != 1){
			alert("수정할 항목을 하나만 체크해주세요.");
			return false;
		}
		$("#bgForm").attr("action", "/admin/modifyBoardGroupView.do");
		$("#bgForm").attr("method", "post");
		$("#bgForm").submit();
	});
	
	$("#btnCancel").click(function(){
		if( $(":checkbox[id='chkList']:checked").length==0 ){
			alert("삭제할 항목을 하나이상 체크해주세요.");
			return false;
		}
		if (confirm('정말로 삭제하시겠습니까?')){
			$("#bgForm").attr("action", "/admin/removeBoardGroup.do");
			$("#bgForm").attr("method", "post");
			$("#bgForm").submit();
		}
	});
});

</script>

<div class='featurebox_center white'>
<h4>게시판 목록</h4>
</div>
<form id="bgForm" name="bgForm" method="post" >
<table class="list center_size">
<colgroup>
	<col width="110px">
	<col width="80px">
	<col width="80px">
	<col width="108px">
	<col width="50px">
	<col width="50px">
	<col width="50px">
	<col width="110px">
	<col width="80px">
	<col width="50px">
</colgroup>
<thead>
	<tr>
	<th>테이블명</th>
	<th>아이디</th>
	<th>이름</th>
	<th>설명</th>
	<th>구분</th>
	<th>순서</th>
	<th>사용</th>
	<th>평가테이블명</th>
	<th>등록일</th>
	<th>선택</th>
	</tr>
</thead>
<tbody>
<c:forEach var="item" items="${boardGroupList}">
	<tr onMouseOver="this.style.backgroundColor='#e6e6fa'" onMouseOut="this.style.backgroundColor='#FFFFFF'">
	<td title="${item.boardNm}"><c:out value="${item.boardNm}" /></td>
	<td title="${item.boardId}"><c:out value="${item.boardId}" /></td>
	<td title="${item.boardKor}"><c:out value="${item.boardKor}" /></td>
	<td title="${item.boardDetail}"><c:out value="${item.boardDetail}" /></td>
	<td title="${item.categoryNm}"><c:out value="${item.categoryNm}" /></td>
	<td align="center" title="${item.sortSeq}"><c:out value="${item.sortSeq}" /></td>
	<td align="center"><c:out value="${item.useYn}" /></td>
	<td align="center"><c:out value="${item.boardAppraisalNm}" /></td>
	<td align="center"><fmt:formatDate pattern="yyyy-MM-dd" value="${item.regDt}" type="both"/></td>
	<td align="center">
	<input type="checkbox" name="boardId" id="chkList" value="${item.boardId}" />
	</td>
	</tr>
</c:forEach>
</tbody>
</table>
</form>
<br/>
<input type="button" value="수정" id="btnUpdate" class="btn_list50" />
<input type="button" value="삭제" id="btnCancel" class="btn_list50" />
<br/>
<br/>
