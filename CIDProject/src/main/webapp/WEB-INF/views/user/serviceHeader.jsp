<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 회원전용서비스 header -->
<div>
	<div align="left"><a href="home.do"><img src="" alt="logo"></a></div>
	<div align="right">
		<span class="user_info">${userId}님</span>
		<span class="message">${successMessage}</span>
		<span class="a_seperate">|</span>
		<a class="nav_menu" href="#">마이페이지</a>
		
		<span class="a_seperate">|</span>
		<a class="nav_menu" href="#">로그아웃</a>
		
		<span class="a_seperate">|</span>
	</div>
</div>

