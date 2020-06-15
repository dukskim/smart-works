<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome To SmartPortal</title>
<link rel="stylesheet" type="text/css" href="/resources/css/cmload.css" >
<script type="text/javascript" src="/resources/js/jquery-1.7.2.js"></script>
</head>
<body>
<form id="loginForm" name="loginForm" action="/login/login.do" method="POST" >
<div style="width:800px;height:600px;background:url('/resources/images/bg_login.png');position:relative;margin:auto;padding:0px;text-align:left;border:solid 1px;border-color: gray;">
	<div style="position:absolute;left:266px;top:270px;"><input type="text" name="userId" id="userId" style="width:270px;height:30px;line-height:30px;" class="Logininput" tabindex="1" value=""></div>
	<div style="position:absolute;left:266px;top:322px;"><input type="password" style="width:270px;height:30px;line-height:30px;" class="Logininput"name="pwd" id="pwd" tabindex="2" value=""></div>
	<div style="position:absolute;left:266px;top:378px;height:35px;width:270px;" onmouseover="this.style.cursor='pointer'" onclick="javascript:document.forms.loginForm.submit();"></div>
	<div style="position:absolute;left:486px;top:425px;" onmouseover="this.style.cursor='pointer'" onclick="javascript:location.href='/login/joinUserView.do';"><span style="font-size: 12px;"><strong>회원가입</strong></span></div>
</div>
</form>
</body>
</html>