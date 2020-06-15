<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
$(document).ready(function(){
	$("#btnSave").click(function(){
		if (validate(true) == false) return;
		$.ajax({
			type : 'POST',
			url : '/admin/addBoardGroupCheck.do',
			data : $('#addForm').serialize(),
			dataType : 'json',
			cache:false,
			success : function(returnData, textStatus, xhr){
				if (returnData.isBoard > 0 ){
					alert('중복된 아이디 혹은 테이블명이 있습니다.');
				} else {
					$('#addForm').submit();
				}
			}, error : function(xhr, status, e){
			}
		});
	});
	
	$("#btnCancel").click(function(){
		location.href = "/admin/boardGroupListView.do";
	});
});

function validate(isMsg){
	var checkStr = "";
	
	checkStr = $('#boardNm').val().replace(/ /gi, '');
	if (checkStr.length == 0){
		if (isMsg) alert('게시판테이블명을 입력해 주세요.');
		return false;
	}
	checkStr = $('#boardId').val().replace(/ /gi, '');
	if (checkStr.length == 0){
		if (isMsg) alert('게시판아이디를 입력해 주세요.');
		return false;
	}
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
	checkStr = $('#boardAppraisalNm').val().replace(/ /gi, '');
	if (checkStr.length == 0){
		if (isMsg) alert('글평가테이블명을 입력해 주세요.');
		return false;
	}
	checkStr = $('#boardCommentNm').val().replace(/ /gi, '');
	if (checkStr.length == 0){
		if (isMsg) alert('댓글테이블명을 입력해 주세요.');
		return false;
	}
	checkStr = $('#sortSeq').val().replace(/ /gi, '');
	if (checkStr.length == 0){
		if (isMsg) alert('순서를 입력해 주세요.');
		return false;
	}
	var checkVal = $('#boardNm').val().replace(/ /gi, '');
	var checkVal2 = $('#boardAppraisalNm').val().replace(/ /gi, '');
	var checkVal3 = $('#boardCommentNm').val().replace(/ /gi, '');
	if ((checkVal == checkVal2) || (checkVal == checkVal3) || (checkVal2 == checkVal3)){
		if (isMsg) alert('테이블명은 동일하게 설정할 수 없습니다.');
		return false;
	}
}

</script>

<div class='featurebox_center white'>
<h4>게시판 추가</h4>
</div>

<div class='featurebox_center'>
<form id="addForm" name="addForm" action="/admin/addBoardGroup.do" method="POST">
<table>
<colgroup>
	<col>
	<col>
</colgroup>
<tbody>
	<tr>
	<td>게시판테이블명</td>
	<td><input type="text" name="boardNm" id="boardNm" ></td>
	</tr>
	<tr>
	<td>게시판아이디</td>
	<td><input type="text" name="boardId" id="boardId" ></td>
	</tr>
	<tr>
	<td>게시판명</td>
	<td><input type="text" name="boardKor" id="boardKor" ></td>
	</tr>
	<tr>
	<td>게시판설명</td>
	<td><input type="text" name="boardDetail" id="boardDetail" ></td>
	</tr>
	<tr>
	<td>글평가테이블명</td>
	<td><input type="text" name="boardAppraisalNm" id="boardAppraisalNm" ></td>
	</tr>
	<tr>
	<td>글평가사용여부</td>
	<td>
		<select name="boardAppraisalYn" id="boardAppraisalYn">
			<option value="Y">사용</option>
			<option value="N">미사용</option>
		</select>
	</td>
	</tr>
	<tr>
	<td>댓글테이블명</td>
	<td><input type="text" name="boardCommentNm" id="boardCommentNm" ></td>
	</tr>
	<tr>
	<td>댓글사용여부</td>
	<td>
		<select name="boardCommentYn" id="boardCommentYn">
			<option value="Y">사용</option>
			<option value="N">미사용</option>
		</select>
	</td>
	</tr>
	<tr>
	<td>메인화면표시여부</td>
	<td>
		<select name="showMainYn" id="showMainYn">
			<option value="Y">사용</option>
			<option value="N" selected="selected">미사용</option>
		</select>
	</td>
	</tr>
	<tr>
	<td>카테고리</td>
	<td>
		<select name="category" id="category">
		<c:forEach var="item" items="${categoryList}" varStatus="status">
			<option value="<c:out value="${item.codeId}" />"><c:out value="${item.codeNm}" /></option>
		</c:forEach>
		</select>
	</td>
	</tr>
	<tr>
	<td>순서</td>
	<td><input type="text" name="sortSeq" id="sortSeq" ></td>
	</tr>
	<tr>
	<td>사용여부</td>
	<td>
		<select name="useYn" id="useYn">
			<option value="Y">사용</option>
			<option value="N">미사용</option>
		</select>
	</td>
	</tr>
</tbody>
</table>
<br/>
<br/>
<input type="button" id="btnSave" value="추가" class="btn_list50" />
<input type="button" id="btnCancel" value="취소" class="btn_list50" />
</form>
</div>

