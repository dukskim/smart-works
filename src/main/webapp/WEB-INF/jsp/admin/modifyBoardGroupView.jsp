<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
$(document).ready(function(){
	$("#btnCancel").click(function(){
		location.href = "/admin/boardGroupListView.do";
	});
	$("#btnUpdate").click(function(){
		if (validate(true) == false) return;
		$('#modifyForm').submit();
	});
});

function validate(isMsg){
	var checkStr = "";
	
	checkStr = $('#boardKor').val().replace(/ /gi, '');
	if (checkStr.length == 0){
		if (isMsg) alert('게시판명을 입력해 주세요.');
		return false;
	}
	checkStr = $('#boardDetail').val().replace(/ /gi, '');
	if (checkStr.length == 0){
		if (isMsg) alert('게시판설명을 입력해 주세요.');
		return false;
	}
	checkStr = $('#sortSeq').val().replace(/ /gi, '');
	if (checkStr.length == 0){
		if (isMsg) alert('순서를 입력해 주세요.');
		return false;
	}

}
</script>
<div class='featurebox_center white'>
<h4>게시판 추가</h4>
</div>

<div class='featurebox_center'>
<form id="modifyForm" name="modifyForm" action="/admin/modifyBoardGroup.do" method="POST">
<table>
<colgroup>
	<col>
	<col>
</colgroup>
<tbody>
	<tr>
	<td>게시판테이블명</td>
	<td>
		<c:out value="${boardGroup.boardNm}" />
		<input type="hidden" name="boardNm" value="<c:out value="${boardGroup.boardNm}"/>" />
	</td>
	</tr>
	<tr>
	<td>게시판명</td>
	<td><input type="text" name="boardKor" id="boardKor" value="<c:out value="${boardGroup.boardKor}" />" ></td>
	</tr>
	<tr>
	<td>게시판설명</td>
	<td><input type="text" name="boardDetail" id="boardDetail" value="<c:out value="${boardGroup.boardDetail}" />" ></td>
	</tr>
	<tr>
	<td>글평가테이블명</td>
	<td>
		<c:out value="${boardGroup.boardAppraisalNm}" />
		<input type="hidden" name="boardAppraisalNm" value="<c:out value="${boardGroup.boardAppraisalNm}" />" >
	</td>
	</tr>
	<tr>
	<td>글평가사용여부</td>
	<td>
		<select name="boardAppraisalYn" id="boardAppraisalYn">
			<option value="Y"
			<c:if test="${boardGroup.boardAppraisalYn eq 'Y'}">
				selected="selected"
			</c:if>
			>사용</option>
			<option value="N"
			<c:if test="${boardGroup.boardAppraisalYn eq 'N'}">
				selected="selected"
			</c:if>
			>미사용</option>
		</select>
	</td>
	</tr>
	<tr>
	<td>댓글테이블명</td>
	<td>
		<c:out value="${boardGroup.boardCommentNm}" />
		<input type="hidden" name="boardAppraisalNm" value="<c:out value="${boardGroup.boardCommentNm}" />" >
	</td>
	</tr>
	<tr>
	<td>댓글사용여부</td>
	<td>
		<select name="boardCommentYn" id="boardCommentYn">
			<option value="Y"
			<c:if test="${boardGroup.boardCommentYn eq 'Y'}">
				selected="selected"
			</c:if>
			>사용</option>
			<option value="N"
			<c:if test="${boardGroup.boardCommentYn eq 'N'}">
				selected="selected"
			</c:if>
			>미사용</option>
		</select>
	</td>
	</tr>
	<tr>
	<td>메인화면표시여부</td>
	<td>
		<select name="showMainYn" id="showMainYn">
			<option value="Y"
			<c:if test="${boardGroup.showMainYn eq 'Y'}">
				selected="selected"
			</c:if>
			>사용</option>
			<option value="N"
			<c:if test="${boardGroup.showMainYn eq 'N'}">
				selected="selected"
			</c:if>
			>미사용</option>
		</select>
	</td>
	</tr>
	<tr>
	<td>카테고리</td>
	<td>
		<select name="category" id="category">
		<c:forEach var="item" items="${categoryList}" varStatus="status">
			<option value="<c:out value="${item.codeId}" />" 
				<c:if test="${boardGroup.category eq item.codeId}">
				selected="selected"
				</c:if>
			><c:out value="${item.codeNm}" /></option>
		</c:forEach>
		</select>
	</td>
	</tr>
	<tr>
	<td>순서</td>
	<td><input type="text" name="sortSeq" id="sortSeq" value="<c:out value="${boardGroup.sortSeq}" />" ></td>
	</tr>
	<tr>
	<td>사용여부</td>
 	<td>
 		<select name="useYn" id="useYn">
			<option value="Y" 
			<c:if test="${boardGroup.useYn eq 'Y'}">
				selected="selected"
			</c:if>
			>사용</option>
			<option value="N"
			<c:if test="${boardGroup.useYn eq 'N'}">
				selected="selected"
			</c:if>
			>미사용</option>
		</select>
 	</td>
	</tr>
</tbody>
</table>
<br/>
<br/>
<input type="button" id="btnUpdate" value="수정" class="btn_list50" />
<input type="button" id="btnCancel" value="취소" class="btn_list50" />
</form>

</div>
