<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#dropBox{
		width:400px;
		height:300px;
		border:1px dotted gray;	
		overflow: hidden;	
	}
	#dropBox div{
		position: relative;
		width:100px;
		height:130px;
		margin:5px;
		float:left;
	}
	#dropBox img{
		width:100px;
		height:130px;
	}
	#dropBox button{
		position: absolute;
		right:0;
		top:0;
	}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script> 
</head>
<body>
	<form id="f1" action="dragUpload" method="post" enctype="multipart/form-data">
		<input type="text" name="test">		
		<input type="submit">
	</form>
	<div id="dropBox"></div>
	
	<script>
		var formData = new FormData();//서버로 보낼 데이타를 담을 공간
		
		$("#f1").submit(function(e){
			e.preventDefault();//ajax로 처리하므로, submit안되게 막음
			formData.append("test", $("input[name='test']").val());
			
			$.ajax({
				url:'dragUpload',
				type:"post",
				data:formData,
				processData:false,//FormData 를 보낼 경우 processData:false, contentType:false처리 필요
				contentType:false,				
				success:function(res){
					console.log(res);
					
					formData = null;//초기화
					
					$("#dropBox").empty();
					$(res).each(function(i, obj){
						var $div = $("<div>");
						var $img = $("<img>").attr("src", "displayFile?filename="+obj);
						var $button = $("<button>").text("X").attr("data-src", obj);
						
						$div.append($img).append($button);
						$("#dropBox").append($div);
					})
				}
			})
		})
		
		$("#dropBox").on("dragenter dragover", function(e){
			e.preventDefault();
		})
		
		$("#dropBox").on("drop", function(event){
			event.preventDefault();
			
			var files = event.originalEvent.dataTransfer.files;
			var file = files[0];
			console.log(file);
			
			var reader = new FileReader();
			reader.addEventListener("load", function(e) {				
				var $img = $("<img>").attr("src", reader.result);
				$("#dropBox").append($img);
			}, false);
			
			if(file){
				reader.readAsDataURL(file);
			}
			
			if(formData == null){
				formData = new FormData();
			}
			
			formData.append("files" ,file );// files =[file, file, file ...]			
			
		})
		
		$(document).on("click", "#dropBox button", function(){
			// 지울 파일명
			var file = $(this).attr("data-src");
			var $button = $(this);
			
			$.ajax({
				url:"deleteFile",
				type:"post",
				data:{filename:file},
				dataType:"text",
				success:function(res){
					console.log(res);
					if(res == "success"){
						alert("삭제하였습니다.");
						$button.parent().remove();
					}
				}
			})
		})
		
			
		
		
		
	</script>

</body>
</html>


