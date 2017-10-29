<%@page import="java.util.regex.Pattern"%>
<%@page import="com.work.dto.Cosmetic"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script
  src="https://code.jquery.com/jquery-3.2.1.min.js"
  integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
  crossorigin="anonymous"></script>
<link type="text/css" rel="stylesheet" href="resources/css/core.css">
<script src="resources/js/tablesort.js" type="text/javascript"></script>

<script type="text/javascript">
	var newWindow;
	function infoItem(cosmeticName) {
		newWindow = window.open("iteminfo.do?cosmeticName="+cosmeticName,"iteminfo","height=100,widht=100,resizeable=yes");
	}
</script>
</head>
<body>
<%
ArrayList<Cosmetic> cosmeticList = (ArrayList)request.getAttribute("cosmeticList");
%>
<%@ include file="../user/header.jsp" %>
<%!int count = 0 ;%>
	 	 <div align="center" style="position: relative;top: 30%;">
	 	 	<span class="search">검색</span>
	 	 	
			<form action="searchBar.do" method="get">
			<div id="searchOne">
				<select name="op1" id="op1">
					<optgroup label="option">
						<option value="cosmeticType">화장품종류</option>
						<option value="cosmeticBrand">브랜드</option>
						<option value="ingreAdd">넣을성분</option>
						<option value="ingerDelete">뺄성분</option>
					</optgroup>
				</select>
				<input type="text" placeholder="one" name="one" id="one">
				<p>
			</div>
			<div id="searchTwo">
				<select name="op2" id="op2">
					<optgroup label="option">
						<option value="cosmeticType">화장품종류</option>
						<option value="cosmeticBrand">브랜드</option>
						<option value="ingreAdd">넣을성분</option>
						<option value="ingerDelete">뺄성분</option>
					</optgroup>
				</select>
				<input type="text" placeholder="two" name="two" id="two">
				<p>
			</div>
			<div id="searchThree">
				<select name="op3" id="op3">
					<optgroup label="option">
						<option value="cosmeticType">화장품종류</option>
						<option value="cosmeticBrand">브랜드</option>
						<option value="ingreAdd">넣을성분</option>
						<option value="ingerDelete">뺄성분</option>
					</optgroup>
				</select>
				<input type="text" placeholder="three" name="three" id="three">
				<p>
			</div>
			<div id="searchFour">
				<select name="op4" id="op4">
					<optgroup label="option">
						<option value="cosmeticType">화장품종류</option>
						<option value="cosmeticBrand">브랜드</option>
						<option value="ingreAdd">넣을성분</option>
						<option value="ingerDelete">뺄성분</option>
					</optgroup>
				</select>
				<input type="text" placeholder="four" name="four" id="four">
				<p>
			</div>
		
			<input type="submit" value="검색" onclick="return checkSelectValue()">
			</form>
			
			<input type="button" value="add" onclick="searchBarAdd()">
			<input type="button" value="delete" onclick="searchBarDelete()">
</div>
<div align="center">
	<table border="1">
		<thead>
			<tr>
				<th>상품 이미지</th>
				<th>상품 이름</th>
				<th>상품 종류</th>
				<th>상품 가격</th>
				<th>상품 브랜드</th>
				<th>상품 평점</th>
			</tr>
		</thead>
		<tbody>
			<%
				for(int i=0;i<cosmeticList.size();i++){
			%>
			<tr>
				<td><img class="cosmeticItem" src="${pageContext.request.contextPath}/resources/images/cosmeticlist/<%=cosmeticList.get(i).getCosmeticName()%>.png"></td>
				<td onclick="infoItem('<%=cosmeticList.get(i).getCosmeticName() %>')"><%=cosmeticList.get(i).getCosmeticName() %></td>
				<td><%=cosmeticList.get(i).getCosmeticType() %></td>
				<td><%=cosmeticList.get(i).getCosmeticPrice() %></td>
				<td><%=cosmeticList.get(i).getCosmeticBrand() %></td>
				<td><%="["+cosmeticList.get(i).getCosmeticScore() +"]"%>
				<%if(Pattern.matches("^[0-5]*$",(String)cosmeticList.get(i).getCosmeticScore())){%>
					<%for(int j=0;j<Integer.parseInt(cosmeticList.get(i).getCosmeticScore());j++){ %>
					<img class="star" alt="색칠된별" src="${pageContext.request.contextPath}/resources/images/star_score.PNG"/>
					<%count++;} %>
					<%for(int k=0;k<5-count;k++){ %>
					<img class="star" alt="색칠안된별" src="${pageContext.request.contextPath}/resources/images/star_notscore.PNG"/>
					<%} %>
					<%count=0; %>	
				<%}else{%>
						<%for(int j=0;j<Float.parseFloat(cosmeticList.get(i).getCosmeticScore())-1;j++){ %>
					<img class="star" alt="색칠된별" src="${pageContext.request.contextPath}/resources/images/star_score.PNG"/>
					<%count++;} %>
					<%for(int k=0;k<5-count;k++){ %>
					<img class="star" alt="색칠안된별" src="${pageContext.request.contextPath}/resources/images/star_notscore.PNG"/>
					<%} %>
					<%count=0; %>	
				<%} %>
				
				</td>
			</tr>
		<%} %>
	</tbody>
	</table>
</div>
<p style="padding-top: 30%">
<div class="footer"><%@ include file="../user/footer.jsp" %></div>
<script type="text/javascript">
 	document.getElementById('searchOne').style.display='inline';
	document.getElementById('searchTwo').style.display='none';
	document.getElementById('searchThree').style.display='none';
	document.getElementById('searchFour').style.display='none';
	</script>
	<script type="text/javascript" src="resources/js/searchFunction.js"></script>
</body>
</html>