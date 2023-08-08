<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--  memberSsb.jsp -->
<html>
<head>
<title>회원찾기</title>
<link rel="stylesheet" type="text/css" href="../style.css">
<!--  ../ 부모폴더로 한단계업 -->
</head>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"  %>
<script type = "text/javascript">
function check(){
	if(f.mode.value == "pw" && f.id.mode.value == "id"){
		return true;
	}
}
</script>
<body>
	<div align="center">
		<hr color="green" width="300">
		<c:if test = "${mode == 'id' }">
		<h2>아 이 디 찾 기</h2>
		</c:if>
		<c:if test = "${mode != 'id' }">
		<h2>비 밀 번 호 찾 기</h2>
		</c:if>
		<hr color="green" width="300">

		<form name="f" action="check.do" method="post"
			onsubmit="return check()">
			<input type = "hidden" name = "mode" value  = "${mode }">
			<table width="500" class="outline">
				<tr>
					<th>회원명</th>
					<td><input type="text" name="name" class="box"></td>
				</tr>
				<tr>
					<th>주민번호</th>
					<td><input type="text" name="ssn1" class="box" maxlength="6">-
						<input type="password" name="ssn2" class="box" maxlength="7">
				</tr>
				<tr>
				<c:if test = "${mode == 'pw' }">
				<tr>
					<th>아이디</th>
					<td><input type="text" name="id" class="box">
					</td>
				</tr>
				</c:if>
				<td colspan="2" align="center"><input type="submit" value="조회">
					<input type="reset" value="다시 입력"></td>
			</table>
		</form>
	</div>
</body>
</html>
