<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.work.dto.Ingredient" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- css -->
<link type="text/css" rel="stylesheet" href="resources/css/core.css">
<!-- js -->
<script type="text/javascript" src="resources/js/common.js"></script>
<title>성분 검색</title>
<script type="text/javascript">
 function child(){
	 window.opener;
 }
</script>
</head>

<body>
<!-- 성분명 검색 영역 -->
<h3>성분 검색</h3>
<hr>
<form action="searchIngredient.do" method="post">
	<input type="text" id="ingredientName" name="ingredientName">
	<input type="submit" value="검색"> 
</form>
<!-- 메세지 영역 -->
<span class="error_message">${errormessage}</span>
<span class="success_message">${message}</span>

<!-- 브랜드 리스트 영역 -->
<%ArrayList<Ingredient> list = (ArrayList)request.getAttribute("list"); %>
<table class="result" >
	
	<%if(list != null){
		%>
	<tr>
		<th>번호</th>
		<th>성분</th>
		<th>위험도 등급</th>
	</tr>
	<% 
	for(int i=0 ; i<list.size() ; i++){
	%>
	
	<tr>
		<td><%=(i+1) %></td>
		<td><%=list.get(i).getIngredientName() %></td>
		<td><%=list.get(i).getIngredientRisk() %></td>
	</tr>

	<%} }%>
</table>


</body>
</html>