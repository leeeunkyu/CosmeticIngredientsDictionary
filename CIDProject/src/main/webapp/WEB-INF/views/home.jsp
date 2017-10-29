<%@page import="com.work.dto.Cosmetic"%>
<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<!-- 세션 객체 쓸려면 true -->
<html>
<head>
	<title>CID</title>
	<script type="text/javascript" src="resources/js/common.js"></script>
	<link type="text/css" rel="stylesheet" href="resources/css/core.css">
</head>
<body>
	<%ArrayList<Cosmetic> cosmeticRankList = (ArrayList)request.getAttribute("cosmeticRankList"); %>
	<h1>
	</h1>
	<table width="1340" height="650" >
	  <tr>
	  <td rowspan="3" width="10%" ></td>
	   <td colspan="4" width="80%" height="15%">
			<%@ include file="user/header.jsp" %>
		</td>
	   <td rowspan="3" width="10%" ></td>
	  </tr>
	  <tr>
	   <td width="80%" height="70%">
	   
	 	  <!-- 검색 영역 -->
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
		
			<input type="submit" onclick="return checkSelectValue()">
			</form>
			<input type="button" value="add" onclick="searchBarAdd()">
			<input type="button" value="delete" onclick="searchBarDelete()">
	 	  </div>
	 	  	<div style="position: relative;text-align: right;float: right;top: -18%;">
				<%for(int i=0;i<cosmeticRankList.size();i++){ %>
				<div>
				<img class="cosmeticMini" src="${pageContext.request.contextPath}/resources/images/cosmeticlist/<%=cosmeticRankList.get(i).getCosmeticName()%>.png">
					<div onclick="infoItem('<%=cosmeticRankList.get(i).getCosmeticName() %>')"><%=cosmeticRankList.get(i).getCosmeticName()%></div>
					<%=cosmeticRankList.get(i).getCosmeticPrice()%>				
					<%=cosmeticRankList.get(i).getCosmeticBrand()%>				
				</div>
				<%} %>
			</div>
	   </td>
	  </tr>
	  <tr>
	   <td colspan="4" width="100%" height="15%">
	       <div class="footer"><%@ include file="user/footer.jsp" %></div>
	   </td>
	  </tr>
	 </table>
	<script type="text/javascript">
 	document.getElementById('searchOne').style.display='inline';
	document.getElementById('searchTwo').style.display='none';
	document.getElementById('searchThree').style.display='none';
	document.getElementById('searchFour').style.display='none';
	</script>
	<script type="text/javascript" src="resources/js/searchFunction.js"></script>
	
</body>
</html>
