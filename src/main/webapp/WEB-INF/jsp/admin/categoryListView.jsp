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
		$("#clForm").attr("action", "/admin/modifyCategoryView.do");
		$("#clForm").attr("method", "post");
		$("#clForm").submit();
	});
	
	$("#btnCancel").click(function(){
		if( $(":checkbox[id='chkList']:checked").length==0 ){
			alert("삭제할 항목을 하나이상 체크해주세요.");
			return false;
		}
		if (confirm('정말로 삭제하시겠습니까?')){
			$.ajax({
				type : 'POST',
				url : '/admin/removeCategoryCheck.do',
				data : $('#clForm').serialize(),
				dataType : 'json',
				cache:false,
				success : function(returnData, textStatus, xhr){
					if (returnData.isCategory > 0 ){
						alert('[코드ID : '+returnData.codeId+'] 카테고리는 사용중입니다. 삭제할 수 없습니다.');
					}
					else {
						$("#clForm").attr("action", "/admin/removeCategory.do");
						$("#clForm").attr("method", "post");
						$("#clForm").submit();
					}
				}, error : function(xhr, status, e){
				}
			});
		}
		
	});
});

</script>
<div class='featurebox_center white'>
<h4>카테고리 목록</h4>
</div>
<form id="clForm" name="clForm" method="post" >
<table class="list center_size">
<colgroup>
	<col width="100px">
	<col width="180px">
	<col width="238px">
	<col width="50px">
	<col width="50px">
	<col width="100px">
	<col width="50px">
</colgroup>
<thead>
	<tr>
	<th>코드ID</th>
	<th>코드명</th>
	<th>설명</th>
	<th>순서</th>
	<th>사용</th>
	<th>등록일</th>
	<th>선택</th>
	</tr>
</thead>
<tbody>
<c:forEach var="item" items="${categoryList}">
	<tr onMouseOver="this.style.backgroundColor='#e6e6fa'" onMouseOut="this.style.backgroundColor='#FFFFFF'">
	<td title="${item.codeId}"><c:out value="${item.codeId}" /></td>
	<td title="${item.codeNm}"><c:out value="${item.codeNm}" /></td>
	<td title="${item.codeDetail}"><c:out value="${item.codeDetail}" /></td>
	<td align="center" title="${item.sortSeq}"><c:out value="${item.sortSeq}" /></td>
	<td align="center"><c:out value="${item.useYn}" /></td>
	<td align="center"><fmt:formatDate pattern="yyyy-MM-dd" value="${item.regDt}" type="both"/></td>
	<td align="center">
	<input type="checkbox" name="codeId" id="chkList" value="${item.codeId}" />
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
