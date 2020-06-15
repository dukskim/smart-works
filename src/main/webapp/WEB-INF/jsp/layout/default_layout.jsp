<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><tiles:insertAttribute name="title" flush="false"/></title>
<tiles:insertAttribute name="header" flush="false"/>
<tiles:insertAttribute name="subHeader" flush="false"/>
</head>

<body>
<div id="page_wrapper">
	<div id="header_wrapper">
		<tiles:insertAttribute name="subject" flush="false"/>
	</div>
	<div class="main_center_layout main_center_size">
		<div id="left_side">
			<tiles:insertAttribute name="left" flush="false"/>
		</div>
		
		<div id="right_side">
			<tiles:insertAttribute name="right" flush="false" />
		</div>
		
		<div id="content" class="center_size">
			<tiles:insertAttribute name="content" flush="false"/>
		</div>
	</div>
	
	<div id="footer">
		<tiles:insertAttribute name="footer" flush="false"/>
	</div>
11
</div>
</body>

</html>