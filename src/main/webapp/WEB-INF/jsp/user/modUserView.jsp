<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript">
	$(document).ready(function(){

		$("#canBtn").click(function(){
			location.href="/home/main.do";
		});

		
		$("#regBtn").click(function(){
			var isCheck = validat();
			if (isCheck == false) return;
			
			fnTelNum();
			fnMobile();
			fnEmail();
			
			$.ajax({
				data: $("#userJoinForm").serialize(),
				type: 'POST',
				dataType: 'json',
				url: "/user/modUser.do",
				cache:false,
				success: function(returnData, textStatus, xhr){
					if (returnData.result == '1'){
						alert("수정되었습니다.");
						location.href = "/home/main.do";
					}
				},
				error: function(xhr, status, e) {
					alert("insert error : "+e);
				}
			});
		});
		
	});
	
	function fnTelNum() {
		if($("#telNum1").val() != '' && $("#telNum2").val() != '' && $("#telNum3").val() != '') {
			var telNum = $("#telNum1").val() +'-'+ $("#telNum2").val() +'-'+ $("#telNum3").val();
			$("#telNum").val(telNum);
		}
	}
	
	function fnMobile() {
		if($("#mobile1").val() != '' && $("#mobile2").val() != '' && $("#mobile3").val() != '') {
			var mobile = $("#mobile1").val() +'-'+ $("#mobile2").val() +'-'+ $("#mobile3").val();
			$("#mobile").val(mobile);
		}
	}

	function fnEmail() {
		if($("#email1").val() != '' && $("#email2").val() != '' ) {
			var email = $("#email1").val() + "@" + $("#email2").val();
			$("#email").val(email);
		}
	}
	
	function validat(){
		var checkVal = '';
		var ret = 0;
		// 아이디
		checkVal = $('#userId');
		ret = emptySpaceCheck(checkVal.val());
		if (ret == 1){
			alert('아이디를 입력하세요');
			checkVal.focus();
			return false;
		} else if (ret == 2){
			alert('공백이 포함된 입력값은 사용할 수 없습니다.');
			checkVal.focus();
			return false;
		}
		// 암호
		checkVal = $('#pwd');
		ret = emptySpaceCheck(checkVal.val());
		if (ret == 1){
			alert('암호를 입력하세요');
			checkVal.focus();
			return false;
		} else if (ret == 2){
			alert('공백이 포함된 입력값은 사용할 수 없습니다.');
			checkVal.focus();
			return false;
		}
		var checkVal2 = $('#confirmPwd');
		ret = emptySpaceCheck(checkVal2.val());
		if (ret == 1){
			alert('암호확인을 입력하세요');
			checkVal2.focus();
			return false;
		} else if (ret == 2){
			alert('공백이 포함된 입력값은 사용할 수 없습니다.');
			checkVal2.focus();
			return false;
		}
		if (checkVal.val() != checkVal2.val()){
			alert('암호와 암호확인의 입력값이 같지 않습니다.');
			checkVal2.focus();
			return false;
		}
		// 이름
		checkVal = $('#userNm');
		ret = emptySpaceCheck(checkVal.val());
		if (ret == 1){
			alert('이름을 입력하세요');
			checkVal.focus();
			return false;
		} else if (ret == 2){
			alert('공백이 포함된 입력값은 사용할 수 없습니다.');
			checkVal.focus();
			return false;
		}
		// 닉네임
		checkVal = $('#nickNm');
		ret = emptySpaceCheck(checkVal.val());
		if (ret == 1){
			alert('닉네임을 입력하세요');
			checkVal.focus();
			return false;
		} else if (ret == 2){
			alert('공백이 포함된 입력값은 사용할 수 없습니다.');
			checkVal.focus();
			return false;
		}
		// 이메일
		checkVal = $('#email1');
		ret = emptySpaceCheck(checkVal.val());
		if (ret == 1){
			alert('이메일을 입력하세요');
			checkVal.focus();
			return false;
		} else if (ret == 2){
			alert('공백이 포함된 입력값은 사용할 수 없습니다.');
			checkVal.focus();
			return false;
		}
		checkVal = $('#email2');
		ret = emptySpaceCheck(checkVal.val());
		if (ret == 1){
			alert('이메일을 입력하세요');
			checkVal.focus();
			return false;
		} else if (ret == 2){
			alert('공백이 포함된 입력값은 사용할 수 없습니다.');
			checkVal.focus();
			return false;
		}
		// 전화번호
		checkVal = $('#telNum1');
		ret = emptySpaceCheck(checkVal.val());
		if (ret == 1){
			alert('전화번호를 입력하세요');
			checkVal.focus();
			return false;
		} else if (ret == 2){
			alert('공백이 포함된 입력값은 사용할 수 없습니다.');
			checkVal.focus();
			return false;
		}
		checkVal = $('#telNum2');
		ret = emptySpaceCheck(checkVal.val());
		if (ret == 1){
			alert('전화번호를 입력하세요');
			checkVal.focus();
			return false;
		} else if (ret == 2){
			alert('공백이 포함된 입력값은 사용할 수 없습니다.');
			checkVal.focus();
			return false;
		}
		checkVal = $('#telNum3');
		ret = emptySpaceCheck(checkVal.val());
		if (ret == 1){
			alert('전화번호를 입력하세요');
			checkVal.focus();
			return false;
		} else if (ret == 2){
			alert('공백이 포함된 입력값은 사용할 수 없습니다.');
			checkVal.focus();
			return false;
		}
		// 휴대폰
		checkVal = $('#mobile1');
		ret = emptySpaceCheck(checkVal.val());
		if (ret == 1){
			alert('휴대폰을 입력하세요');
			checkVal.focus();
			return false;
		} else if (ret == 2){
			alert('공백이 포함된 입력값은 사용할 수 없습니다.');
			checkVal.focus();
			return false;
		}
		checkVal = $('#mobile2');
		ret = emptySpaceCheck(checkVal.val());
		if (ret == 1){
			alert('휴대폰을 입력하세요');
			checkVal.focus();
			return false;
		} else if (ret == 2){
			alert('공백이 포함된 입력값은 사용할 수 없습니다.');
			checkVal.focus();
			return false;
		}
		checkVal = $('#mobile3');
		ret = emptySpaceCheck(checkVal.val());
		if (ret == 1){
			alert('휴대폰을 입력하세요');
			checkVal.focus();
			return false;
		} else if (ret == 2){
			alert('공백이 포함된 입력값은 사용할 수 없습니다.');
			checkVal.focus();
			return false;
		}
		// 부서
		checkVal = $('#deptId');
		ret = emptySpaceCheck(checkVal.val());
		if (ret == 1){
			alert('소속을 입력하세요');
			checkVal.focus();
			return false;
		} else if (ret == 2){
			alert('공백이 포함된 입력값은 사용할 수 없습니다.');
			checkVal.focus();
			return false;
		}
		return true;
	}
	function emptySpaceCheck(checkVal){
		if (!checkVal || checkVal.length == 0){
			return 1;
		}
		if (checkVal.indexOf(' ') >= 0){
			return 2;
		}
		return 0;
	}
</script>
<c:set var="telNum" value="${fn:split(userInfo.telNum, '-')}"/>
<c:set var="mobile" value="${fn:split(userInfo.mobile, '-')}"/>
<c:set var="email" value="${fn:split(userInfo.email, '@')}"/>
	<div class="login_box">
		<form id="userJoinForm" name="userJoinForm">
			<input type="hidden" id="mobile" name="mobile" value=""/>
			<input type="hidden" id="telNum" name="telNum" value=""/>
			<input type="hidden" id="email" name="email" value=""/>
			<table class="tb_login_box">
				<colgroup>
					<col style="width:130px;" />
					<col style="width:auto;" />
				</colgroup>
				<thead>
					<tr>
						<th colspan="2">회원정보수정</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>아이디</td>
						<td><input type="text" name="userId" id="userId" size="13" value="<c:out value="${userInfo.userId}"/>" readonly="readonly" ></td>
					</tr>
					<tr>
						<td>암호</td>
						<td><input type="password" name="pwd" id="pwd" size="13" value=""></td>
					</tr>
					<tr>
						<td>암호확인</td>
						<td><input type="password" name="confirmPwd" id="confirmPwd" size="13" value=""></td>
					</tr>
					<tr>
						<td>이름</td>
						<td><input type="text" name="userNm" id="userNm" size="13" value="<c:out value="${userInfo.userNm}"/>"></td>
					</tr>
					<tr>
						<td>닉네임</td>
						<td><input type="text" name="nickNm" id="nickNm" size="13" value="<c:out value="${userInfo.nickNm}"/>"></td>
					</tr>
					<tr>
						<td>이메일</td>
						<td><input type="text" name="email1" id="email1" size="13" value="<c:out value="${email[0]}"/>">@<input type="text" name="email2" id="email2" size="11" value="<c:out value="${email[1]}"/>"></td>
					</tr>
					<tr>
						<td>전화번호</td>
						<td>
							<input type="text" name="telNum1" id="telNum1" size="2" value="<c:out value="${telNum[0]}"/>">
							-<input type="text" name="telNum2" id="telNum2" size="2" value="<c:out value="${telNum[1]}"/>">
							-<input type="text" name="telNum3" id="telNum3" size="2" value="<c:out value="${telNum[2]}"/>">
						</td>
					</tr>
					<tr>
						<td>휴대폰</td>
						<td>
							<input type="text" name="mobile1" id="mobile1" size="2" value="<c:out value="${mobile[0]}"/>">
							-<input type="text" name="mobile2" id="mobile2" size="2" value="<c:out value="${mobile[1]}"/>">
							-<input type="text" name="mobile3" id="mobile3" size="2" value="<c:out value="${mobile[2]}"/>">
						</td>
					</tr>
					<tr>
						<td>소속</td>
						<td><input type="text" name="deptId" id="deptId" size="13" value="<c:out value="${userInfo.deptId}"/>"></td>
					</tr>
				</tbody>
			</table>
			<input type="button" id="regBtn" value="수정" class="btn_list50">
			<input type="button" id="canBtn" value="취소" class="btn_list50"/>
		</form>
	</div>
