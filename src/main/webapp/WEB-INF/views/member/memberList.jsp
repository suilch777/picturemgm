<%@page import="javax.servlet.descriptor.TaglibDescriptor"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	table{
		width:auto;
		border-collapse: collapse;
	}
	th, td{
		padding:5px 10px;
		border:1px solid gray;
	}
</style>
</head>
<body>
	<table>
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>전화번호</th>
			<th>e-mail</th>
			<th>등록일자</th>
		</tr>
	
	<c:forEach var="member" items="${list }">
		<tr>
			<td>${member.memberid }</td>
			<td>${member.name }</td>
			
			<td><fmt:formatNumber var="licsNo" value="${member.tel }" pattern="###,####,#####"/>
			<c:out value="${fn:replace(licsNo, ',', '-')}" /></td>
			
			
			
			<td>${member.email}</td>
			<td><fmt:formatDate pattern="yyyy-MM-dd  HH : mm : ss" value="${member.regdate }"/> </td>
		</tr>
	</c:forEach>
	</table>
	<br>
	<a href="index.jsp">홈 화면</a>
</body>
</html>