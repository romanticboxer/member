<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
		<link rel="stylesheet" type="text/css" href="../style.css">
		<script type = "text/javascript">
		function check(){
			if(f.id.value==""){//free 자료형
				alert("아이디를 입력해 주세요")
				f.id.focus()
				return
			}
			if(!f.passwd.value){
				alert("비밀번호를 입력해 주세요")
				f.passwd.focus()
				return
			}
			document.f.submit()
		}
		</script>
	 </head>
	<body onload="f.id.focus()">
		<form name="f" method="POST" action="join_member.do">
			<table width="600" align="center" class="outline">
  				<tr>
					<td colspan="2" align=center class="m2">회원가입</td>
 				</tr>
				<tr>
					<td width="150" class="m3">이름</td>
					<td class="m3">
						<input type="text" name="name" class="box" value = "${sessionScope.name}" disabled>
						<input type = "hidden" name = "name" value = "${sessionScope.name}"/>
						
					</td>
				</tr>
				<tr>
					<td width="150" class="m3">아이디</td>
					<td class="m3">
						<input type="text" name="id" class="box">
					</td>
  				</tr>
  				<tr>
					<td width="150" class="m3">비밀번호</td>
					<td class="m3">
						<input type="password" name="passwd" class="box">
					</td>
  				</tr>
  				<tr>
					<td width="150" class="m3">주민번호</td>
					<td class="m3">
						<input type="text" name="ssn1" class="box" value = "${sessionScope.ssn1 }" readonly> -
				<input type="password" name="ssn2" class="box" value = "${sessionScope.ssn2 }" readonly>
					</td>
  				</tr>
  				<tr>
					<td width="150" class="m3">이메일</td>
					<td class="m3">
						<input type="text" name="email" class="box">
					</td>
  				</tr>
  				<tr>
					<td width="150" class="m3">연락처</td>
					<td class="m3">
						<input type="text" name="hp1" class="box"> -
						<input type="text" name="hp2" class="box"> -
						<input type="text" name="hp3" class="box">
					</td>
  				</tr>
  				<tr>
					<td colspan="2" align="center">
						<a href="javascript:check()">[전송]</a>
						<a href="#">[취소]</a>
					</td>
  				</tr>
  			</table>
		</form>
	</body>
