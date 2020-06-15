<%@page import="com.dhkim.sworks.sql.domain.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="divNormal" value="<%= Board.DIV_NORMAL %>" />
<c:set var="divComment" value="<%= Board.DIV_COMMENT %>" />
<c:set var="divReplay" value="<%= Board.DIV_REPLAY %>" />

<script type="text/javascript">
$(document).ready(function(){
	
	$("#commentList").load("/board/board_commentList.do?boardId="+$("#boardId").val()+"&boardSeq="+$("#boardSeq").val());
	
	$("#btnAddComt").click(function(){

		if( $("#commentBoardContent").val() == ''){
			alert("댓글을 입력해 주세요");
			return false;
		}
		
		$.ajax({
			type : 'POST',
			url : '/board/addComment.do',
			data : $('#addCommentForm').serialize(),
			dataType : 'json',
			cache:false,
			success : function(returnData, textStatus, xhr){
				if (returnData.result == '1'){
					$("#commentBoardContent").val("");
					$("#commentList").load("/board/board_commentList.do?boardId="+$("#boardId").val()+"&boardSeq="+$("#boardSeq").val());
				}
			}, error : function(xhr, status, e){
			}
		});
	});
	
	$("#spanViewAttachFile").click(function(){
		var display = $("#divViewAttachFile").css('display');
		if (display != "none") {
			$(this).text('첨부파일▼');
			$("#divViewAttachFile").slideUp();
			//$("#divViewAttachFile").hide();
		} else {
			$(this).text('첨부파일▲');
			$("#divViewAttachFile").fadeIn();
			//$("#divViewAttachFile").show();
		}
	});
	
});

function execBtn(type, boardId, boardSeq){
	switch(type){
		case 1 : // 삭제
			if (confirm('게시물을 삭제하시겠습니까?')){
				location.href='/board/removeBoard.do?boardId='+boardId+'&boardSeq='+boardSeq;
			}
			break;
		case 2 : // 수정
			location.href='/board/modBoardView.do?boardId='+boardId+'&boardSeq='+boardSeq;
			break;
		case 3 : // 답변
			location.href='/board/addBoardView.do?boardId='+boardId+'&boardSeq='+boardSeq+'&boardDiv=2';
			break;
		case 4 : // 목록
			location.href='/board/boardList.do?boardId='+boardId;
			break;
		default : 
			break;
	}
	
}

function deleteComment(commentSeq){
	var seq = Number(commentSeq);
	if (isNaN(seq)){
		return false;
	}
	if (confirm('댓글을 삭제하시겠습니까?')){
		$.ajax({
			type : 'POST',
			url : '/board/deleteComment.do',
			data : {'boardId' : $("#boardId").val(), 'commentSeq' : seq},
			dataType : 'json',
			cache:false,
			success : function(returnData, textStatus, xhr){
				if (returnData.result == '1'){
					$("#commentList").val("");
					$("#commentList").load("/board/board_commentList.do?boardId="+$("#boardId").val()+"&boardSeq="+$("#boardSeq").val());
				}
			}, error : function(xhr, status, e){
			}
		});
	}
}

function modRecomCountPlus(){
	if (confirm('추천하시겠습니까?')){
		$.ajax({
			type : 'POST',
			url : '/board/modRecomCountPlus.do',
			data : {
				'boardId' : $("#boardId").val(), 
				'boardSeq' : $("#boardSeq").val(), 
				'userId' : '<c:out value="${userInfo.userId}" escapeXml="false" />' 
				},
			dataType : 'json',
			cache:false,
			success : function(returnData, textStatus, xhr){
				if (returnData.result == '1'){
					alert('추천되었습니다.');
				} else{
					alert('이미 추전하셨습니다.');
				}
			}, error : function(xhr, status, e){
				alert(e);
			}
		});
	}	
}
</script>
<div class="viewTitle center_size">
	<div class='featurebox_center white'>
	<h4><c:out value="${board.title}" /></h4>
	<p style="color: gray;">
	게시글ID : <c:out value="${board.boardSeq}" />&nbsp;
	<c:choose>
		<c:when test="${board.boardDiv eq divNormal}">(일반)</c:when>
		<c:when test="${board.boardDiv eq divReplay}">(답변)</c:when>
		<c:when test="${board.boardDiv eq divComment}">(댓글)</c:when>
		<c:otherwise></c:otherwise>
	</c:choose>
	<br/>
	작성자 :
	<span class="cur_pnt_1" onclick="javascript:window.open('/user/userInfoView.do?userId=${board.userId}', 'userInfoView', 'width=300px,height=220');">
	<c:out value="${board.nickNm}" />
	</span>
	 / 조회수 : <c:out value="${board.showCnt}" /> / 추천수 : <c:out value="${board.recomCnt}" /> / 등록시간 : <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${board.regDt}" type="both"/>
	</p>
	</div>
</div>

<table class="center_size">
	<tbody>
	<tr>
	<td align="right">
		<c:if test="${board.userId eq userInfo.userId || userInfo.userLevel >= 10}">
		<input type="button" onclick="execBtn(1, '${board.boardId}','${board.boardSeq}');" value="삭제" class="btn_list50" />
		<input type="button" onclick="execBtn(2, '${board.boardId}','${board.boardSeq}');" value="수정" class="btn_list50" />
		</c:if>
		<input type="button" onclick="execBtn(3, '${board.boardId}','${board.boardSeq}');" value="답변" class="btn_list50" />
		<input type="button" onclick="execBtn(4, '${board.boardId}', null);" value="목록" class="btn_list50" />
	</td>
	</tr>
	<tr>
	<td>
		<c:if test="${!empty boardAttachList}">
		<span id="spanViewAttachFile" style="font-size: 9pt;cursor: pointer;">첨부파일▲</span>
		<div id="divViewAttachFile" class="viewAttach">
			<c:forEach var="item" items="${boardAttachList}">
			<div>
				<table>
				  <tbody>
				  <tr>
				    <td>
						<span style="font-size: 10pt;color: black;"><c:out value="${item.fileNm}" escapeXml="false" /></span>
						&nbsp;&nbsp;
				    </td>
				    <td>
						<a href="/board/fileDownload.do?fileId=<c:out value="${item.fileId}" escapeXml="false" />" class="m1">
							<img src="/resources/images/btn_filedown.gif">
						</a>
				    </td>
				  </tr>
				  </tbody>
				</table>
			</div>
			</c:forEach>
		</div>
		</c:if>
	</td>
	</tr>
	<tr>
	<td>
		<div class="viewContent"> 
		<pre><c:out value="${board.boardContent}" escapeXml="false" /></pre>
		</div>
	</td>
	</tr>
	<!-- 추천버튼 -->
	<c:if test="${boardGroup.boardAppraisalYn == 'Y'}">
	<tr>
		<td>
		<div class='featurebox_center' style="text-align: center;">
			<img alt="" src="/resources/images/btn_good.png" class="cur_pnt_1" onclick="modRecomCountPlus();">
		</div>
		</td>
	</tr>
	</c:if>
	
	<!-- 댓글 쓰기  -->
	<tr>
	<td>
		<div class='featurebox_center'>
		<form name="addCommentForm" id="addCommentForm" >
			<input type="hidden" name="boardId" id="boardId" value="${board.boardId}" /> 
			<input type="hidden" name="boardSeq" id="boardSeq" value="${board.boardSeq}" /> 
			<input type="hidden" name="userId" id="userId" value="${userInfo.userId}" /> 
			<table>
			<colgroup>
				<col width="660px">
				<col width="90px">
			</colgroup>
			<tbody>
				<tr>
				<td align="center"><textarea rows="3" cols="71" name="commentContent" id="commentBoardContent" wrap="hard"></textarea></td>
				<td align="center"><input type="button" name="btnAddComt" id="btnAddComt" value="댓글" class="btn_list50"></td>
				</tr>
			</tbody>
			</table>
		</form>
		</div>
	</td>
	</tr>
	<!-- 댓글 -->
	<tr>
	<td>
		<div class='featurebox_center' id="commentList" >
		</div>
	</td>
	</tr>
	<!-- 답변, 원본글 목록 -->
	<tr>
	<td></td>
	</tr>
	<tr>
	<td align="right"><div class='featurebox_center white'><a href='#TOP'>▲맨위로</a></div></td>
	</tr>
</tbody>
</table>

<br/>
<br/>
