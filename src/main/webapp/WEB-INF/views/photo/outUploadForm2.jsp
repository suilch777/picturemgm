<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#box{
		width:200px;
		height: 200px;
		border:1px dotted #ccc;
		margin-top:10px;
		overflow: hidden;
	}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
</head>
<body>
	<form action="outUpload2" method="post" enctype="multipart/form-data">
		<input type="text" name="test" placeholder="작성자이름">
		<input type='file' name='file' id="file"> 
		<input type='submit'>		
	</form>
	<div id="box">
	</div>
	<script>
		$("#file").change(function(){
			var reader = new FileReader();
			reader.onload = function(e){
				var $img = $("<img>").attr("src", e.target.result);
				$("#box").append($img);
			}
			reader.readAsDataURL($(this)[0].files[0]);
		})
	</script>
	
</body>
</html>




