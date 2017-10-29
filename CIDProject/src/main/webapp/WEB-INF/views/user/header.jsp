<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javascript">
function searchBrand(){
	var url='brandList.do';
	var winName='brandList';
	var winConfig='top=100,left=100,height=250,width=520,resizable=no,scrollbar=no';
	var childWin =window.open(url,winName,winConfig);
	
}

function searchIngredient(){
	var url='ingredientList.do';
	var winName='ingredientList';
	var winConfig='top=100,left=100,height=250,width=520,resizable=no,scrollbar=no';
	var childWin =window.open(url,winName,winConfig);
	
}
</script>

<%if(session.getAttribute("userId") == null){%>
		<!-- header -->
	<div>
		<div align="right">
			<a href="loginView.do">로그인</a> 
			<a href="joinView.do">회원가입</a>
		</div>
		<div class="logo">
			<a  class="logo" href="home.do"><span class="logoChar">C</span>osmetic <span class="logoChar">I</span>ngredients <span class="logoChar">D</span>ictionary 화장품 성분 사전</a>
		
			<span onclick="searchBrand()">브랜드</span>
			<span onclick="searchIngredient()">성분</span>
		</div>		
	</div>
	<hr>
<%}else{ %>
	<div>
		<div class="logo">
			<a  class="logo" href="home.do"><span class="logoChar">C</span>osmetic <span class="logoChar">I</span>ngredients <span class="logoChar">D</span>ictionary 화장품 성분 사전</a>
			
			<span onclick="searchBrand()">브랜드</span>
			<span onclick="searchIngredient()">성분</span>
		</div>
		<div align="right">
		<span class="user_info">${userId}님</span>
		<span class="message">${successMessage}</span>
		
		<span class="a_seperate">|</span>
		<a class="nav_menu" href="#">마이페이지</a>
		
		<span class="a_seperate">|</span>
		<a class="nav_menu" href="logout.do">로그아웃</a>
		
		<span class="a_seperate">|</span>
	</div>
</div>
<hr>
<%} %>





