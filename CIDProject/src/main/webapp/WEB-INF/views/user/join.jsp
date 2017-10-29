<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- css -->
<link type="text/css" rel="stylesheet" href="resources/css/core.css">
<!-- js -->
<script type="text/javascript" src="resources/js/common.js"></script>
<title>로그인</title>
</head>

<body>
	<table width="1340" height="650">
		<tr>
			<td rowspan="3" width="10%"></td>
			<td colspan="4" width="80%" height="15%">
			<!-- header -->
			<div class="header"><%@ include file="header.jsp"%></div>
			</td>
			<td rowspan="3" width="10%"></td>
		</tr>
		
		<tr>
			<td width="80%" height="70%">
				<!-- 회원가입 -->
				<div align="center">
					회원가입
					<form method="post" name="joinForm" action="join.do">
						<table>
							<tr>
								<td>이메일</td>
								<td><input type="text" name="userId" id="userId" autofocus required size="10" onblur="emailCheck()">@
									<input type="text" name="email2" id="email2" size="10" pattern="[a-z]+\.(com|net|co\.kr|go\.kr)">
									<select name="email3" id="email3"  onchange="selectEmail()">
										<option value="">직접입력</option>
										<option value="naver.com">naver.com</option>
										<option value="nate.com">nate.com</option>
										<option value="hanmail.net">hanmail.net</option>
										<option value="daum.com">daum.com</option>
										<option value="gmail.com">gmail.com</option>
									</select>
								</td>							</tr>
							<tr>
								<td>비밀번호</td>
								<td><input type="password" name="userPw" id="userPw"></td>
							</tr>
							<tr>
								<td>이름</td>
								<td><input type="text" name="userName" id="userName"></td>
							</tr>
							<tr>
								<td colspan="2" align="center">
								<div id="warning" class="error_message" ></div>
									<input type="submit" value="회원가입"> 
									<input type="button" value="가입취소" onclick="location='home.do'">
								</td>
							</tr>
						</table>
					</form>
				</div>
			</td>
		</tr>
		
		<tr>
			<td colspan="4" width="100%" height="15%">
				 <!-- footer area -->
 				<div class="footer"><%@ include file="footer.jsp" %></div>
			</td>
		</tr>
	</table>
	<script type="text/javascript">
function selectEmail() {
	if(document.getElementById("email3").value == ""){
		document.joinForm.email2.readOnly = false;
		document.getElementById("email2").value="";
	}
	else{
		document.getElementById("email2").value=document.getElementById("email3").value;
		document.joinForm.email2.readOnly = true;
		
	}
}

function emailCheck(){
	if(document.getElementById("userId").value.trim().length==0){
		document.getElementById("warning").innerHTML = "이메일을 입력해주시기 바랍니다.";
		document.joinForm.userId.focus();
	}
	else{
		document.getElementById("warning").innerHTML = "";
	}
}
</script>
</body>
</html>