<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:if test="${!empty boardAttachList}">
	<span id="spanViewAttachFile" style="font-size: 9pt; cursor: pointer;">첨부파일</span>
	<div id="divViewAttachFile" class="viewAttach">
		<c:forEach var="item" items="${boardAttachList}">
			<div>
				<span style="font-size: 10pt; color: black;">
					<c:out value="${item.fileNm}" escapeXml="false" />
				</span>
				&nbsp;&nbsp;&nbsp;
				<span onclick="deleteFile(<c:out value="${item.fileId}" escapeXml="false" />)" style="font-size: 9pt; color: gray;cursor: pointer;"><strong>삭제</strong></span>
			</div>
		</c:forEach>
	</div>
</c:if>
