<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Melon</title>
<link rel="stylesheet" href="/resources/css/style.css">
</head>
<body>
<%@include file="/WEB-INF/jsp/includes/header.jsp" %>
	<table class="chart">
		<thead>
			<tr>
				<th>-</th>
				<th>순위</th>
				<th>제목</th>
				<th>아티스트명</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${chartList }" var="chartList">
			<tr>
				<td><img alt="${chartList.title }" src="${chartList.src }"></td>
				<td>${chartList.rank }</td>
				<td>${chartList.title }</td>
				<td>${chartList.artist }</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>
