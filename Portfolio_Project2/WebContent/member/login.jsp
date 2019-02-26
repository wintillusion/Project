<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../menu/menu.jsp" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LOGINFORM</title>
<!-- <link rel="stylesheet" href="style.css"> 따로 저장할때.-->
<style>
body{
	
	margin : 0;
	padding : 0;
	font-family : "Montserrat", sans-serif;
	background : url(img/menubg.jpg);/*이미지는 폴더 만들어서 넣기. 이미지 블러 처리 + 5초마다 바뀌도록처리 아니면 바디부분에 이미지 블러처리하고 컨테이너를 단색으로.*/
	background-repeat: no-repeat;
	background-size : 100%;
}

.container{
	width : 360px;
	height : 600px;
	background : #333;
	position : absolute;
	top : 50%;
	left : 50%;
	transform : translate(-50%, -50%);
	color : white; 
	text-align: center;
	opacity: 0.8; /*반투명 정도 하위 클래스들에게 1.0을 줘서 잘 보이게 가능한가?*/
}

.container h1{
	font-size : 40px;
	margin-top : 125px;
	margin-bottom : 60px;
}

.tbox{
	width:260px;
	height:40px;
	background:#f1f1f1b3;
	border-radius : 10px;
	margin : 10px auto;
	
}

.tbox input{
	background: none;
	border : none;
	outline : none;
	text-align : center;
	width:90%;
	line-height : 37px;
	font-family : "Montserrat", sans-serif;
	font-size : 14px;
	color: #333;	/* 있으나 없으나?; */
}

.btn{
	width : 260px;
	height : 40px;
	background : #3498db;
	border-radius : 10px;
	margin : 14px auto;
	display : block;
	font-family : "Montserrat", sans-serif;
	font-weight : bold;	/* 글씨가 너무 작아서 bold로 처리. 나중에 뺄지말지 결정 */
	font-size : 16px;
	border : none;
	color : white;
	cursor : pointer;
}

.b1{
	display : block;
	width : 260px;
	padding : 10px 0;
	text-decoration: none;
	color : white;
	text-align : center;
	margin : auto;
	transition : 0.4s all;
}

.b2{
	position: absolute;
	bottom : 80px;
	left : 50%;
	transform : translateX(-50%);
	width : 260px;
	padding : 10px 0;
	text-decoration: none;
	text-align : center;
	transition : 0.4 all;
	color : #3498db;
	
}

a:hover{
	backgound: #00000040;
}
</style>
</head>
<body>

<div class="container">
<h1>LOGIN</h1>
<form action="./login.member" method="post">
	<div class="tbox">
		<input type="text" placeholder="@ID" name="id">
	</div>
	<div class="tbox">
		<input type="password" placeholder="@PASSWORD" name="password">
	</div>
		<input class="btn" type="submit" value="LOGIN">
</form>
	<a class="b1" href="#">FORGOT PASSWORD ? </a>
	<a class="b2" href="./joinForm.member">CREATE AN ACCOUNT </a>
</div>
</body>
</html>