<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>

<% HttpSession session1 = request.getSession(false);
 String uname = (String)session1.getAttribute("Auther");
 if(uname == null){
	 out.print("null");
 }else{
	
 }
 
%>
 

	<form action="innerUploadPost" method="post" enctype="multipart/form-data">
		<input type="text" name="test"  readonly= "readonly" value="${Auther}">
		<input type='file' name='file'> 
		<input type='submit'>		
	</form>
	
	
</body>


</html>