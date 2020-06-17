<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error</title>
</head>
<body>
	<h1>Error Page</h1>
	error code : <span><c:out value="${code}" /></span>
	<br>error msg :	<span><c:out value="${msg}" /></span>
	<br>timestamp :	<span><c:out value="${timestamp}" /></span>
</body>
</html>