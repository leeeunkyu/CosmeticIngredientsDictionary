<%@page import="com.work.dto.Cosmetic"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%Cosmetic cosmetic = (Cosmetic)request.getAttribute("cosmetic"); %>

상품당 한번씩만 리뷰를 등록할수있습니다.ㅠㅠ
<a href="iteminfo.do?cosmeticName=${cosmeticName}">뒤로가기</a>
</body>
</html>