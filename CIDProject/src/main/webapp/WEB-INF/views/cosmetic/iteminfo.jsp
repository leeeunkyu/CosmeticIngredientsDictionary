<%@page import="com.work.dto.Review"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.work.dto.Cosmetic"%>
<%@page import="com.work.dto.Ingredient"%>
<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="resources/css/core.css">

</head>
<body>
	<%Cosmetic cosmetic = (Cosmetic)request.getAttribute("cosmetic"); %>
	<%ArrayList<Review> reviewList = (ArrayList)request.getAttribute("reviewList"); %>
	<%ArrayList<Ingredient> arryIngredientList = (ArrayList)request.getAttribute("arryIngredientList"); %>
	
	<table border="1">
		<tr>
			<td rowspan="6"><img class="cosmeticItemInfo" src="${pageContext.request.contextPath}/resources/images/cosmeticlist/<%=cosmetic.getCosmeticName()%>.png"></td>
		</tr>
		<tr><td><%=cosmetic.getCosmeticName()%></td></tr>
		<tr><td><%=cosmetic.getCosmeticPrice()%></td></tr>
		<tr><td><%=cosmetic.getCosmeticBrand()%></td></tr>
		<tr><td><%=cosmetic.getCosmeticPrice()%></td></tr>
		<tr><td><%=cosmetic.getCosmeticScore()%></td></tr>
	</table>
	<div>
	<%for(int j=0;j<arryIngredientList.size();j++){ %>
		<%=arryIngredientList.get(j).getIngredientName()+"|"%>
		<img class="cosmeticItem" src="${pageContext.request.contextPath}/resources/images/grade/<%=arryIngredientList.get(j).getIngredientRisk()%>.png">
	<%} %>
	</div>
	<p>
	<hr>
	<p>
	<%for(int i = 0;i<reviewList.size();i++){ %>
		<%="작성자: "+reviewList.get(i).getUserId()+
		"     |    "+reviewList.get(i).getReviewContent()+
		"           ["+reviewList.get(i).getReivewScore()+"]"%>
		<p>
		<hr>
		<%} %>
	<%if(session.getAttribute("userId") == null){ %>
		<h4>회원만 후기를 남길수 있습니다.</h4>
	<%}else{ %>
	<form action="reviewAdd.do" method="post">
		<input type="text" placeholder="후기내용" name="reviewContent">
		<select name="reviewScore">
			<optgroup label="화장품 점수달기">
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
			</optgroup>
		</select>
		<input type="hidden" name="cosmeticName" value="<%=cosmetic.getCosmeticName()%>">
		<input type="submit" value="후기달기">
	</form>
	<%} %>
</body>
</html>