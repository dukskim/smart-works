<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome To Reverse Thinking </title>
<link rel="stylesheet" type="text/css" href="/reources/css/cmload.css" />
<style type="text/css">
html { height: 100%;}
body { height: 100%;}
.tb_all {width:100%; height:100%;}
.tb_all td.td_all {width:100%; text-align: center; vertical-align: middle; height:100%;}
.login_box {width: 400px;text-align: center;margin: 0 auto;padding: 20px;background-color: white;border:#6A71BD double 3px;}
.tb_login_box {cellpadding:0;cellspacing:0;width: 100%;font-size: 13px;}
.tb_login_box td {text-align: left;height: 28px;}
</style>
<script type="text/javascript" src="/reources/js/jquery-1.7.2.js"></script>

<script type="text/javascript">
	$(document).ready(function(){
		
		$("#canBtn").click(function(){
			location.href="/login/loginView.do";
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
				url: "/login/addUser.do",
				cache:false,
				success: function(returnData, textStatus, xhr){
					if (returnData.result == '1'){
						alert("가입되었습니다.");
						location.href = "/login/loginView.do";
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
</head>
<body>
	<table class="tb_all">
	<tr><td class="td_all">
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
						<th colspan="2">회원가입</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>아이디</td>
						<td><input type="text" name="userId" id="userId" size="13"></td>
					</tr>
					<tr>
						<td>암호</td>
						<td><input type="password" name="pwd" id="pwd" size="13"></td>
					</tr>
					<tr>
						<td>암호확인</td>
						<td><input type="password" name="confirmPwd" id="confirmPwd" size="13"></td>
					</tr>
					<tr>
						<td>이름</td>
						<td><input type="text" name="userNm" id="userNm" size="13"></td>
					</tr>
					<tr>
						<td>닉네임</td>
						<td><input type="text" name="nickNm" id="nickNm" size="13"></td>
					</tr>
					<tr>
						<td>이메일</td>
						<td><input type="text" name="email1" id="email1" size="13">@<input type="text" name="email2" id="email2" size="11"></td>
					</tr>
					<tr>
						<td>전화번호</td>
						<td>
							<input type="text" name="telNum1" id="telNum1" size="2">
							-<input type="text" name="telNum2" id="telNum2" size="2">
							-<input type="text" name="telNum3" id="telNum3" size="2">
						</td>
					</tr>
					<tr>
						<td>휴대폰</td>
						<td>
							<input type="text" name="mobile1" id="mobile1" size="2">
							-<input type="text" name="mobile2" id="mobile2" size="2">
							-<input type="text" name="mobile3" id="mobile3" size="2">
						</td>
					</tr>
					<tr>
						<td>소속</td>
						<td><input type="text" name="deptId" id="deptId" size="13"></td>
					</tr>
				</tbody>
			</table>
			<input type="button" id="regBtn" value="가입" class="btn_list50">
			<input type="button" id="canBtn" value="취소" class="btn_list50"/>
		</form>
	</div>
	</td></tr>
	</table>
</body>
</html>