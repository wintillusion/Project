<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../menu/menu.jsp" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JOINFORM</title>
<style>
body{
	
	margin : 0;
	padding : 0;
	font-family : "Montserrat", sans-serif;
	background : url(img/menubg.jpg);/*이미지는 폴더 만들어서 넣기. 이미지 블러 처리 + 5초마다 바뀌도록처리 아니면 바디부분에 이미지 블러처리하고 컨테이너를 단색으로.*/
	background-repeat: no-repeat;
	background-size : 150%;

}

.container{
	width : 500px;
	height : 1200px;
	background : #333;
	position : absolute;
	top : 80%;
	left : 50%;
	transform : translate(-50%, -50%);
	color : white; 
	text-align: center;
	opacity: 0.8; /*반투명 정도 하위 클래스들에게 1.0을 줘서 잘 보이게 가능한가?*/
	z-index: -1;
}

.container h1{
	font-size : 40px;
	margin-top : 80px;
	margin-bottom : 30px;
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
	backgound: #000040;
}

table{
	margin : auto;
}
</style>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>


var checkId=false;
var idcheck;
	function checkValue(f) {
		if (f.id.value.trim()==""||f.id.value==null) {
			//alert("아이디를 입력해주세요.");
			document.getElementById('idChk').innerHTML = 'ID 입력 후 ID CHECK를 눌러주세요.';			
			f.id.focus();
			return false;
		}
		 if (!checkId || idcheck!=f.id.value.trim()) {
			alert("ID CHECK를 눌러주세요.");
			f.id.focus();
			return false;
		}
		 if (f.name.value.trim()==""||f.name.value==null) {
			document.getElementById('nameChk').innerHTML = '이름을 입력해주세요.';
			f.name.focus();
			return false;
		}
		if (f.password.value.trim()==""||f.password.value==null) {
			document.getElementById('psChk').innerHTML = '비밀번호를 입력해주세요.';
			f.password.focus();
			return false;
		}	
		if(f.password.value.trim() != f.passwordchk.value.trim()) {
			alert("비밀번호가 일치하지않습니다.");
			f.password.value="";
			f.passwordchk.value="";
			f.password.focus();
			return false;
		}
		if (f.postcode.value.trim()==""||f.postcode.value==null) {
			document.getElementById('postChk').innerHTML = '우편 주소를 검색해주세요.';
			f.postcode.focus();
			return false;
		}
		if (f.address2.value.trim()==""||f.address2.value==null) {
			document.getElementById('addChk').innerHTML = '상세주소를 입력해주세요.';
			f.address2.focus();
			return false;
		}
		if (f.phone.value.trim()==""||f.phone.value==null) {
			document.getElementById('phChk').innerHTML = '전화번호를 입력해주세요.';
			f.phone.focus();
			return false;
		}
		if (f.email.value.trim()==""||f.email.value==null) {
			document.getElementById('emChk').innerHTML = 'E-MAIL을 입력해주세요.';
			f.email.focus();
			return false;
		}	
		//f.submit();
    }

	//다음 주소 API
	function daumpostcod() {
		
    new daum.Postcode({
        oncomplete: function(data) {
        	  // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
        	 

         
            document.getElementById('postcode').value = data.zonecode; //5자리 새우편번호 사용
            
            if(data.userSelectedType !=("R")){
            document.getElementById('address1').value = data.jibunAddress;
            }else{
            document.getElementById('address1').value = data.roadAddress;;
            }
        }
    }).open();
	}


</script>
</head>
<body>

<div class="container">
<h1>SIGN UP</h1>

<form name="joinform" action="joinPro.member"  method="post" onsubmit="return checkValue(this)">
<table>
	<tr>
		<td >
			<input class="tbox" type="text" name="id" id="id" placeholder="@ID">
			<br><span id="idChk"></span>
			<input class= "btn" type="button" name="idcheck" id="idcheck" value="ID CHECK"
			onclick="window.open('member/idCheck.jsp?openInit=true','','width=280, height=300')">
			<!-- <input type="hidden" value="id"> -->
		</td>
	</tr>
	<tr>
		<td>
			<input class= "tbox" type="text" name="name" id="name" placeholder="@NAME"/>
			<br><span id="nameChk"></span>
		</td>
	</tr>
	<tr>
		<td>
			<input class="tbox" type="password" name="password" id="password" placeholder="@PASSWORD"/>
			<br><span id="psChk"></span>
		</td>
	</tr>
	<tr>
		<td>
			<input class= "tbox" type="password" name="passwdchk" id="passwordchk" placeholder="@PASSWORD CHECK"/>
		</td>
	</tr>

	<tr>
		<td>
			<input class="tbox" type="text" name="postcode" id="postcode" placeholder="@POSTCODE"/>
			<br><span id="postChk"></span>
			<input class="btn" type="button" value="POST SEARCH" onclick="daumpostcod()">
			<input class="tbox" type="text" name="address1" id="address1" placeholder="@ADDRESS"/>
			<input class="tbox" type="text" name="address2" id="address2" placeholder="@DETAIL ADDRESS"/>
			<br><span id="addChk"></span>
		</td>
	</tr>
	<tr>
		<td>
			<input class= "tbox" type="text" name="phone" id="phone" placeholder="@PHONE"/>
			<br><span id="phChk"></span>
		</td>
	</tr>
	<tr>
		<td>
			<input class= "tbox" type="text" name="email" id="email" placeholder="@E-MAIL"/>
			<br><span id="emChk"></span>
		</td>
	</tr>
</table>
	<a class="b1" href="#">AGREED? </a>
	<input class="btn" type="submit" value="SIGN UP!">
	<!-- <a href="#" onclick="checkValue(joinform);">"SIGN UP!"</a> a태그를 이용할 경우 자바스크립트에서 submint을 넣어줘야 한다. -->
</form>
</div>
</body>
</html>