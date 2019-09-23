<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <!-- Bootstrap 3.3.4 -->
    <link href="${pageContext.request.contextPath }/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <!-- Font Awesome Icons -->
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <!-- Ionicons -->
    <link href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css" rel="stylesheet" type="text/css" />
    <!-- Theme style -->
    <link href="${pageContext.request.contextPath }/resources/dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css" />
    <!-- AdminLTE Skins. Choose a skin from the css/skins 
         folder instead of downloading all of them to reduce the load. -->
    <link href="${pageContext.request.contextPath }/resources/dist/css/skins/_all-skins.min.css" rel="stylesheet" type="text/css" />

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
      <!-- jQuery 2.1.4 -->
    <script src="${pageContext.request.contextPath }/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>

<style>
* {
	margin: 0 auto;
}

form {
	margin-top: 100px;
	border-radius: 10px;
	width: 460px;
}

input {
	text-align: center;
}

p {
	margin: 20px;
}

span {
	float: left;
	margin-left: 120px;
	font-size: 10px;
}
</style>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/common.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/common.js"></script>
<script>
	$(function() {
		$("#f1").submit(
						function() {
							$(".error").css("display", "none");
							$(".error2").css("display", "none");

							//빈 input태그가 존재하면 submit을 막도록 한다.
							if (checkInputEmpty($("input[name]")) == false) {
								return false;
							}

							//id 입력 확인
							var idReg = /^[a-z0-9]{6,15}$/i;
							var id = $("input[name='memberid']").val();
							if (idReg.test(id) == false) {
								$("input[name='memberid']").next().css("display",
										"inline");
								return false;
							}

							/* 	//name 확인
								//===========================================
									$("input[name='name']").focusin(function{
								if(checkInputEmpty( $("input[name='name']") ) == false){
									 alert("이름을 입력해 주세요.");
									return false;
								}
									})
									//============================================ */
							var nameReg = /^[가-힣]{2,5}$/;
							var name = $("input[name='name']").val();
							if (nameReg.test(name) == false) {
								$("input[name='name']").next().css("display",
										"inline");
								return false;
							}

							//password 확인
							var passReg = /^[a-z0-9!@#$%^&]{8,20}$/;
							var pass = $("input[name='password']").val();
							if (passReg.test(pass) == false) {
								$("input[name='password']").next().css(
										"display", "inline");
								return false;
							}

							//password일치하지 않을때
							if ($("input[name='password']").val() != $(
									"input[name='confirmPassword']").val()) {
								$("input[name='confirmPassword']").next()
										.next().css("display", "inline");
								return false;
							}
							
							var telReg = /^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$/;
							var tel =$("input[name='tel']").val();
							if (telReg.test(tel) == false) {
								$("input[name='tel']").next().css("display",
										"inline");
								return false;
							}
							
							var emailReg = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
							var email =$("input[name='email']").val();
							if (emailReg.test(email) == false) {
								$("input[name='email']").next().css("display",
										"inline");
								return false;
							}
							

						});
	})

	//---------------------------------------------------------	
	var idck = 0;
		
	$(function() {
		//idck 버튼을 클릭했을 때 
		$("#btn1").click(function() {
			//=====================
			if (checkInputEmpty($("input[name='memberid']")) == false) {
				alert("ID를 입력해 주세요.");
				return false;
			}
			var idReg = /^[a-z0-9]{6,15}$/i;
			var id = $("input[name='memberid']").val();
			if (idReg.test(id) == false) {
				$("span[id='idchk']").css("display", "inline");
				return false;
			}
			//===================

			//userid 를 param.
			var memberid = $("input[name='memberid']").val();

			$.ajax({
				async : true,
				type : 'POST',
				data : {
					"userid" : memberid
				},
				url : "idCheck",
				dataType : "json",

				success : function(data) {

					if (data.cnt > 0) {

						alert("이미사용중인 ID입니다.");
						
						
						//아이디가 존제할 경우 빨깡으로 , 아니면 파랑으로 처리하는 디자인
						$("#divInputId").addClass("has-error")
						$("#divInputId").removeClass("has-success")
						$("#userid").focus();

					} else {
						alert("사용가능한 ID입니다.");
						           				
						//아이디가 존제할 경우 빨깡으로 , 아니면 파랑으로 처리하는 디자인
						$("#divInputId").addClass("has-success")
						$("#divInputId").removeClass("has-error")
						
						//아이디가 중복하지 않으면  idck = 1 
						idck = 1;

					}
				},
				error : function(data) {

					alert("알수없는 오류가 발생했습니다.\n관리자에게 문의해 주시기 바랍니다.");
                    return false;
				}
			});
		});
	});
	
	
	// --------------------------------------------------------
</script>
</head>
<body>
	<div>
		<form action="register" method="post" id="f1">
			<p>
				<label>아이디</label> <input type="text" name="memberid" value="${param.id }">
				<button type ="button" id="btn1">중복확인</button>
				<br> <span class="error" id="idchk">ID(영어,숫자 6~15)를
					입력하세요</span>

				<c:if test="${duplicatedId == true }">
					<span class="error2">이미 사용중인 ID입니다</span>
				</c:if>
			</p>
			<p>
				<label>이름</label> <input type="text" name="name" id="username"
					value="${param.name }"> <span class="error">이름(한글
					2~5)을 입력하세요</span>
			</p>
			<p>
				<label>비밀번호</label> <input type="password" name="password" id="userpwd">
				<span class="error">암호(영어,숫자,특수문자 8~20)를 입력하세요</span>
			</p>
			<p>
				<label>비밀번호 확인</label> <input type="password" name="confirmPassword">
				<span class="error">암호 확인란을 입력하세요</span> <span class="error">암호가
					일치하지 않습니다</span>
			</p>
			<p>
				<label>전화번호</label>  <input type="text" name="tel"
					value="${param.tel }">
					<span class="error">전화번호를 정확하게 입력해 주세요</span>
			</p>
			<p>
				<label>E-mail</label> <input type="text" name="email"
					value="${param.email }">
					<span class="error">E-mail 형식이 맞지 않습니다</span>
			</p>

			
			<p>
				<input type="submit" value="가입">
			</p>
		</form>
	</div>
</body>
</html>