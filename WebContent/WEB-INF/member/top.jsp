<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"  %>
<html>
<link rel="stylesheet" type="text/css" href="style.css">
<head>
<title>회원관리</title>
</head>
<body>
	<div align="center">
		<table border="1" width="800" height="600">
			<tr height="10%">
				<th width="25%"><a href = "memberSsn.do" >회원가입</a></th>
				<c:if test = "${mbId == null }">
				<th width="25%"><a href = "login.do">로그인</a></th>
				</c:if>
				<c:if test = "${mbId != null }">
				<th width="25%">${mbId }님 로그인중 ..<br><a href = "logout.do">로그아웃</a></th>
				</c:if>
				<th width="25%"><a href = "memberAll.do?mode=All">회원보기</a></th>
				<th width="25%"><a href = "memberAll.do?mode=find">회원찾기</a></th>
			</tr>
			<tr>
				<td colspan="4">