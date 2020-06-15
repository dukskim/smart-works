<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사용자 정보</title>
<link rel="stylesheet" type="text/css" href="/resources/css/cmload.css" />
<style type="text/css">
div {
	margin-top: 10px;
	width: 290px;
	height : 200px;
	background: #F1F6FE url('/resources/images/bg_side.gif') bottom left repeat-x;
}

p {
	margin: 10px;
	margin-top: 15px;
	margin-bottom: 15px;
	font-family: verdana, arial, sans-serif;
	font-size: 11px;
	line-height: 16px;
	color: #333;
}

h3 {
	margin-top: 5px;
	margin-bottom: 10px;
	margin-left: 5px;
	margin-right: 5px;
	padding: 4px;
	font-family: verdana, arial, sans-serif;
	font-size: 14px;
	font-weight: bold;
	line-height: 14px;
	color: #FFF;
	border: 1px solid #0F3974;
	background-color: #2153AA;
}

h4 {
	margin-top: 0px;
	margin-bottom: 0px;
	margin-left: 10px;
	font-family: verdana, arial, sans-serif;
	font-size: 12px;
	font-weight: bold;
	line-height: 12px;
	color: #2153AA;
}
</style>
<script type="text/javascript" src="/resources/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="/resources/js/util.js"></script>
</head>
<body>
<div>
<h3>사용자 정보</h3>
<p>
<!-- 
ID : <c:out value="${user.userId}"/>
<br/>
-->
별명 : <c:out value="${user.nickNm}"/>
<br/>
<!-- 
이메일 : <c:out value="${user.email}"/>
<br/>
소속 : <c:out value="${user.deptId}"/>
<br/>
-->
가입일자 : <fmt:formatDate pattern="yy/MM/dd HH:mm" value="${user.regDt}" type="both"/>
</p>
<button onclick="javascript:self.close();">닫기</button>
</div>
</body>
</html>