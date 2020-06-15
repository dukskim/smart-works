<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript">
$(document).ready(function(){
	$("#btnCancel").click(function(){
		location.href = "/admin/categoryListView.do";
	});
	$("#btnUpdate").click(function(){
		$('#modifyForm').submit();
	});
});
</script>
<div class='featurebox_center white'>
<h4>카테고리 수정</h4>
</div>
<div class='featurebox_center'>
<form id="modifyForm" name="modifyForm" action="/admin/modifyCategory.do" method="POST">
<table>
<colgroup>
	<col>
	<col>
</colgroup>
<tbody>
	<tr>
	<td>코드ID</td>
	<td><c:out value="${codeInfo.codeId}" /></td>
	</tr>
	<tr>
	<td>코드명</td>
	<td><input type="text" name="codeNm" id="codeNm" value="<c:out value="${codeInfo.codeNm}" />"></td>
	</tr>
	<tr>
	<td>코드상세</td>
	<td><input type="text" name="codeDetail" id="codeDetail" value="<c:out value="${codeInfo.codeDetail}" />" ></td>
	</tr>
	<tr>
	<td>순서</td>
	<td><input type="text" name="sortSeq" id="sortSeq" value="<c:out value="${codeInfo.sortSeq}" />" ></td>
	</tr>
	<tr>
	<td>사용여부</td>
	<td>
 		<select name="useYn" id="useYn">
			<option value="Y" 
			<c:if test="${codeInfo.useYn eq 'Y'}">
				selected="selected"
			</c:if>
			>사용</option>
			<option value="N"
			<c:if test="${codeInfo.useYn eq 'N'}">
				selected="selected"
			</c:if>
			>미사용</option>
		</select>
 	</td>
	</tr>
	<tr>
	<td>등록일</td>
	<td><fmt:formatDate pattern="yyyy-MM-dd" value="${codeInfo.regDt}" type="both"/></td>
	</tr>
</tbody>
</table>
<input type="hidden" name="codeId" value="<c:out value="${codeInfo.codeId}" />">
<br/>
<br/>
<input type="button" id="btnUpdate" value="수정" class="btn_list50" />
<input type="button" id="btnCancel" value="취소" class="btn_list50" />
</form>

</div>
