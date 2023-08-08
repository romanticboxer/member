<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--  memberSsn.jsp -->
<%@include file = "top.jsp" %>
<html>
<head>
<title>회원가입 유무</title>
<link rel="stylesheet" type="text/css" href="style.css">
<!--  ../ 부모폴더로 한단계업 -->
<script type = "text/javascript">
	function check(){
		if(f.name.value==""){
			alert("이름을 입력해주세요.")
			f.id.focus()
			return
		}
		if(f.ssn1.value ==""||f.ssn2.value==""){
			alert("주민등록번호를 입력해주세요")
			f.ssn1.focus()
			return
		}
		document.f.submit()
	}
</script>
</head>
<body>
	<div align="center">
		<hr color="green" width="300">
		<h2>회 원 가 입 유 무</h2>
		<hr color="green" width="300">
		<form name="f" action="checkMember.do" method="post">
			<table width="500" class="outline">
				<tr>
					<th>회원명</th>
					<td><input type="text" name="name" class="box"></td>
				</tr>
				<tr>
					<th>주민번호</th>
					<td><input type= "text" name = "ssn1" class = "box" maxlength = "6">-
					<input type = "password" name = "ssn2" class = "box" maxlength = "7">
				</tr>
				<tr>
				<td colspan = "2" align = "center">
				<input type = "button" value = "조회" onclick = "location.href = 'javascript:check()'">
				<input type = "reset" value= "다시 입력">
				</td>
			</table>
		</form>
	</div>
</body>
</html>
<%@include file = "bottom.jsp"%>