<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javascript">
$(document).ready(function(){
	$("#btnSave").click(function(){
		$.ajax({
			type : 'POST',
			url : '/admin/addCategoryCheck.do',
			data : $('#addForm').serialize(),
			dataType : 'json',
			cache:false,
			success : function(returnData, textStatus, xhr){
				if (returnData.isCategory > 0 ){
					alert('중복된 코드ID가 있습니다.');
				}
				else {
					$('#addForm').submit();
				}
			}, error : function(xhr, status, e){
			}
		});
	});
	
	$("#btnCancel").click(function(){
		location.href = "/admin/categoryListView.do";
	});
});

</script>


<div class='featurebox_center white'>
<h4>카테고리 추가</h4>
</div>

<div class='featurebox_center'>
<form id="addForm" name="addForm" action="/admin/addCategory.do" method="POST">
<table>
<colgroup>
	<col>
	<col>
</colgroup>
<tbody>
	<tr>
	<td>코드ID</td>
	<td><input type="text" name="codeId" id="codeId" ></td>
	</tr>
	<tr>
	<td>코드명</td>
	<td><input type="text" name="codeNm" id="codeNm" ></td>
	</tr>
	<tr>
	<td>코드상세</td>
	<td><input type="text" name="codeDetail" id="codeDetail" ></td>
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
<input type="hidden" name="groupCodeNm" value="카테고리">
<br/>
<br/>
<input type="button" id="btnSave" value="추가" class="btn_list50" />
<input type="button" id="btnCancel" value="취소" class="btn_list50" />
</form>
</div>

