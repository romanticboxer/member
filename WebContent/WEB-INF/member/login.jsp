<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- login.jsp-->
<%@include file = "top.jsp" %>
<link rel="stylesheet" type="text/css" href="style.css">
<script type="text/javascript">
	 function search(mode){
		 window.open("search.do?mode="+mode,"","width=600,height=400")
	 }
	 function loginCheck(){
		 if(f.id.value ==""){
			 alert("아이디를 입력해 주세요.")
			 f.id.focus()
			 return
		 }
		 if(f.passwd.value==""){
			 alert("비밀번호를 입력해 주세요")
			 f.passwd.focus()
			 return
		 }
		 document.f.submit()
	 }
</script>
<c:if test = "${mbId == null }">
<br>
<img src="img/bottom.gif" width=570 height="40" border="0" alt="">
<br>
<p>
	<img src="img/tm_login.gif" width=100 height="13" border="0"
		align=center ALT="회원 로그인">
		</c:if>
<form name="f" action="login.do" method="post">
	<table width="60%" align="center" height="120">
	<c:if test = "${mbId ==null }">
		<tr>
			<td align="right" width="30%"><img src="img/id01.gif"
				width="28" height="11" border="0" alt="아이디">&nbsp;&nbsp;</td>
			<td width="40%">
				<c:if test = "${cookie.saveId.value == null }">
				<input type="text" name="id" tabindex="1">
				</c:if>
				<c:if test = "${cookie.saveId.value !=null }">
				<input type="text" name="id" tabindex="1"
				value="${cookie.saveId.value }"> 
				</c:if>
			</td>
			<td rowspan="2" width="30%" valign="middle"><a
				href="javascript:loginCheck()"> <img src="img/bt_login.gif"
					border="0" alt="로그인" tabindex="3">&nbsp;&nbsp;<br>
			</a> <nobr>
					<c:if test= "${cookie.saveId.value ==null }">
					<input type="checkbox" name="saveId">
					</c:if>
					<c:if test = "${cookie.saveId.value != null }">
					<input type="checkbox" name="saveId" checked>
					</c:if>
					<font face="굴림" size="2">아이디 기억하기</font>
				</nobr></td>
		</tr>
		<tr>
			<td align="right"><img src="img/pwd.gif" width="37"
				height="11" alt="비밀번호"></td>
			<td><input type="password" name="passwd" tabindex="2"></td>
		</tr>
		<tr>
			<td colspan="3" align="center"><a
				href="memberSsn.do"> <img src="img/bt_join.gif"
					width="60" height="22" alt="회원가입">
			</a> <a href="javascript:search('id')"> <img
					src="img/bt_search_id.gif" width="60" height="22" alt="아이디 찾기">
			</a> <a href = "javascript:search('pw')"><img src="img/bt_search_pw.gif" width="60" height="22"
				alt="비밀번호 찾기"></a></td>
		</tr>
		</c:if>
		<c:if test = "${mbId != null }">
		<tr>
		<th>현재 ${mbId }님 로그인중</th>
		</tr>
		<tr>
		<td><a href = "logout.do">로그아웃</a></td></tr>
		</c:if>
	</table>
</form>
<%@include file = "bottom.jsp"%>