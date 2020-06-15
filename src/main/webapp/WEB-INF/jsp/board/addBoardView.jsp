<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript" src="/resources/editor/js/HuskyEZCreator.js" charset="utf-8" ></script>

<script type="text/javascript">
function addFileUI() {
	var objTbl = document.all["tblAttFiles"];
	var objRow = objTbl.insertRow();
	var objCell1 = objRow.insertCell();
	var objCell2 = objRow.insertCell();

	objCell1.align = "right";
	objCell2.align = "left";
	objCell1.innerHTML = '<input type="file" name="attachFile" maxlength="50" size="50" />';
	objCell2.innerHTML = '<input type="checkbox" name="cbox" onclick="delFileUI();">';
}
function delFileUI() {
	var objTbl = document.all["tblAttFiles"];
	var objChk = document.all["cbox"];
	var aRow = objTbl.rows;

	for ( var i = aRow.length - 1; i >= 0; i--) {
		if (isNaN(objChk.length)) {
			if (objChk.checked)
				objTbl.deleteRow();
		} else {
			if (objChk[i].checked)
				objTbl.deleteRow(i);
		}
	}
}
</script>

<form id="addForm" name="addForm" action="/board/addBoard.do" method="POST" enctype="multipart/form-data">
<table class="center_size">
	<tbody>
	<tr>
	<td>
		<div class='featurebox_center'>
			<table>
			<colgroup>
				<col width="100px">
				<col width="665px">
			</colgroup>
			<tbody>
				<tr>
				<td><b>카테고리</b></td>
				<td>
				<c:choose>
				<c:when test="${board.boardDiv == '2'}">
					<c:out value="${boardGroup.boardKor}" />
					<input type="hidden" name="boardId" id="boardId" value="${boardGroup.boardId}" />
					<input type="hidden" name="refBoardSeq" id="refBoardSeq" value="${orgBoard.boardSeq}">
				</c:when>
				<c:otherwise>
					<select name="boardId" id="boardId">
						<c:forEach var="item" items="${boardGroupList}">
							<option value="${item.boardId}"
							<c:if test="${item.boardId == boardGroup.boardId}" >
							selected="selected"
							</c:if>
							><c:out value="${item.boardKor}" /></option>
						</c:forEach>
					</select>
				</c:otherwise>
				</c:choose>
				</td>
				</tr>
				<tr>
				<td><b>제목</b></td>
				<td>
				<c:choose>
				<c:when test="${board.boardDiv == '2'}">
					<input type="text" name="title" id="title" size="100" value="<c:out value="[답변] ${orgBoard.title}" />"/>
				</c:when>
				<c:otherwise>
					<input type="text" name="title" id="title" size="100" />
				</c:otherwise>
				</c:choose>
				</td>
				</tr>
				<tr>
				<td><b>첨부파일추가</b></td>
				<td>
					<input type="button" value="추가" onclick="addFileUI();" class="btn_list50" /><br/>
					<table id="tblAttFiles"></table>
				</td>
				</tr>
			</tbody>
			</table>
		</div>
	</td>
	</tr>
	<tr>
	<td>
		<textarea name="boardContent" id="boardContent" wrap="hard" rows="10" cols="100" style="width:766px; height:412px; display:none;"></textarea>
		<!-- p>
			<input type="button" onclick="pasteHTML();" value="본문에 내용 넣기" />
			<input type="button" onclick="showHTML();" value="본문 내용 가져오기" />
			<input type="button" onclick="submitContents(this);" value="서버로 내용 전송" />
			<input type="button" onclick="setDefaultFont();" value="기본 폰트 지정하기 (궁서_24)" />
		</p -->
	</td>
	</tr>
	<tr>
	<td align="right">
		<input type="hidden" name="userId" id="userId" value="${userInfo.userId}" />
		<input type="hidden" name="boardDiv" id="boardDiv" value="${board.boardDiv}" />
		
		<input type="button" onclick="submitContents(this);" value="저장" class="btn_list50" />
		<input type="button" onclick="location.href='/board/boardList.do?boardId=${boardGroup.boardId}';" value="취소" class="btn_list50" />
	</td>
	</tr>
</tbody>
</table>
</form>
<script type="text/javascript">
var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors,
	elPlaceHolder: "boardContent",
	sSkinURI: "/resources/editor/SmartEditor2Skin.html",
	htParams : {bUseToolbar : true,
		fOnBeforeUnload : function(){
			//alert("아싸!");	
		}
	}, //boolean
	fCreator: "createSEditor2"
});

function pasteHTML() {
	var sHTML = "<span style='color:#FF0000;'>이미지도 같은 방식으로 삽입합니다.<\/span>";
	oEditors.getById["boardContent"].exec("PASTE_HTML", [sHTML]);
}

function showHTML() {
	var sHTML = oEditors.getById["boardContent"].getIR();
	alert(sHTML);
}


function submitContents(elClickedObj) {
	
	var checkStr = $('#title').val().replace(/ /gi, '');
	if (checkStr.length == 0){
		alert('제목을 입력해 주세요.');
		return;
	}
	
	oEditors.getById["boardContent"].exec("UPDATE_CONTENTS_FIELD", []);	// 에디터의 내용이 textarea에 적용됩니다.
	
	// 에디터의 내용에 대한 값 검증은 이곳에서 document.getElementById("ir1").value를 이용해서 처리하면 됩니다.
	
	try {
		elClickedObj.form.submit();
	} catch(e) {}
}

function setDefaultFont() {
	var sDefaultFont = '궁서';
	var nFontSize = 24;
	oEditors.getById["boardContent"].setDefaultFont(sDefaultFont, nFontSize);
}

</script>

<br/>
<br/>
