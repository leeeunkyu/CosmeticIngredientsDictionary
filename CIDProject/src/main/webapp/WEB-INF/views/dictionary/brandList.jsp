<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- css -->
<link type="text/css" rel="stylesheet" href="resources/css/core.css">
<!-- js -->
<script type="text/javascript" src="resources/js/common.js"></script>
<title>화장품 브랜드 검색</title>
<script type="text/javascript">
 function child(){
	 window.opener;
	 window.close();
 }
</script>
</head>

<body>
<!-- 브랜드 검색 영역 -->
<h3>브랜드명 검색</h3>
<hr>
<form action="searchBrand.do" method="post">
	<input type="text" id="cosmeticBrand" name="cosmeticBrand">
	<input type="submit" value="검색"> 
</form>
<!-- 메세지 영역 -->
<span class="error_message">${errormessage}</span>
<span class="success_message">${message}</span>

<!-- 브랜드 리스트 영역 -->
<%ArrayList<String> list = (ArrayList)request.getAttribute("list"); %>
<table class="result" >
	
	<%if(list!=null){
	%>
	<tr>
		<th>번호</th>
		<th>브랜드명</th>
	</tr>
	<%
	for(int i=0 ; i<list.size() ; i++){
	%>
	<tr>
		<td><%=(i+1) %></td>
		<td><%=list.get(i) %></td>
	</tr>

	<%} }%>
</table>
</body>
</html>

 	  
 	 



