<%@page import="com.dhkim.sworks.sql.domain.BoardGroup"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<c:set var="showMainImage" value="<%= BoardGroup.SHOW_MAIN_IMAGE %>" />
<spring:eval expression="@environment.getProperty('config.main.type')" var="mainType" />

<script type="text/javascript">
$(document).ready(function(){
	var mainType = '<c:out value="${mainType}" />';
	if (mainType != '<c:out value="${showMainImage}" />'){
		$("#showMainBoard").show();
		$("#showMain").hide();
		$("#showMainBoard").load("/home/home_board_summary.do?mainType="+mainType);
	} else {
		$("#showMainBoard").hide();
		$("#showMain").show();
	}
	
});
</script>

<div id="showMain" class="center_size hidden">
	<h3></h3>
	<div class='featurebox_center' style="width: 710px; text-align: center;">
		<img alt="" src="/resources/images/main.jpg" width="700px;">
	</div>
</div>
<!-- 게시판 -->
<div id="showMainBoard" class="center_size hidden">
</div>

