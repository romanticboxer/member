<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- memberAll.jsp -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file = "top.jsp" %>
<link rel="stylesheet" type="text/css" href="style.css">
<div align="center">
	<hr color="green" width="300">
	<c:if test = "${mode eq 'All' }">
	<h2>회 원 목 록 보 기</h2>
	<hr color="green" width="300">
	</c:if>
	<c:if test = "${mode != 'All' }">
	<h2>회 원 찾 기</h2>
	<hr color="green" width="300">
	<form name="f" method="post">
		<%--form태그에서 action을 안적으면 자신의 페이지로 전송 --%>
		<select name="search">
			<option value="id">아이디</option>
			<option value="name">회원명</option>
		</select> <input type="text" name="searchString"> <input type="submit"
			value="찾기">
	</form>
	</c:if>
	<table width="100%" class="outline">
		<tr>
			<th class="m2">번호</th>
			<th class="m2">이름</th>
			<th class="m2">아이디</th>
			<th class="m2">이메일</th>
			<th class="m2">전화번호</th>
			<th class="m2">가입일</th>
			<th class="m2">수정|삭제</th>
		</tr>
		<c:if test = "${list == null }">
		<tr>
			<td colspan="7">등록된 회원이 없습니다.</td>
		</tr>
		</c:if>
		<c:forEach var = "dto" items = "${list }">
		<tr>
			<td>${dto.no }</td>
			<td>${dto.name }</td>
			<td>${dto.id }</td>
			<td>${dto.email }</td>
			<td>${dto.allhp }</td>
			<td>${dto.joindate }</td>
			<td><a href="member_edit.do?no=${dto.no }">수정</a> | <a
				href="member_delete.do?no=${dto.no }">삭제</a></td>
		</tr>
		</c:forEach>
	</table>
</div>
<%@include file = "bottom.jsp" %>



