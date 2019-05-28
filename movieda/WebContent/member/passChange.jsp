<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   	<jsp:include page="../menu/menu2.jsp" />
    <jsp:include page="../menu/member_side2.jsp" />
<%@ page import = "member.vo.MemberBean" %>
 <%
	MemberBean memberBean = (MemberBean)request.getAttribute("memberBean");
	String nowPage = (String) request.getAttribute("page");
	
	 String id=null;
 	 String pass=(String)memberBean.getPassword();
 	 
 	 System.out.println("passChange Form : "+ pass); //패스워드 나오는지 체크
	    if(session.getAttribute("id")!=null){
	    	id=(String)session.getAttribute("id");
	    }else {
	    	out.println("<script>");
	    	out.println("alert('로그인을 해주세요!')");
	    	out.println("location.href='../loginForm.member'");
	    	out.println("</script>");
	    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>passChange</title>
<style>

html, body {
      margin: 0;
      padding: 0;
      width: 100%;
      background-color : #e0e0e0;
}


 article {
			width: 55%;
			height: auto;
			margin-left: 21.95%;
			margin-bottom: 20px;
			margin-top: 40px;
			padding: 0.5%;
			border: 1px dotted #333;
			border-radius: 10px;
			background-color : #fff;
			opacity: 0.85;
		}

h2{
	font-size : 20px;
	font-weight: bold;
	text-align : center;
	font-family : "Montserrat", sans-serif;
}

.tbox{
	width:260px;
	height:40px;
	background:#f1f1f1b3;
	border-radius : 10px;
	margin : 10px auto;
	position: relative;
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
	background : #e2472f;
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
</style>
<script>
var checkpass=false;
var pass=<%=pass%>;
function checkValue(f) {
	if (f.passChange1.value.trim()==""||f.passChange1.value==null) {
		alert("변경할 비밀번호를 입력해주세요.");
		f.passwordCChk.focus();
		return false;
	}
	if (f.passChange2.value.trim()==""||f.passChange2.value==null) {
		alert("비밀번호 확인을 입력해주세요.");
		f.passwordCChk.focus();
		return false;
	}
	if(f.passChange1.value!=f.passChange2.value){
		alert("비밀번호 확인을 다시 입력해주세요.");
		f.passwordCChk.focus();
		return false;
	}
}

</script>
</head>
<body>
<form action="./passChangePro.member" method="post" name="passChange" onsubmit="return checkValue(this)">
<h2>PASSWORD CHANGE</h2>
<article>
<input type="hidden" name="id" id="id" value="<%=memberBean.getId() %>"/>
	<div class="tbox">
			<input type="password" name="passChange1" id="passChange1" placeholder="@바꿀 비밀번호"/>
			
	</div>
	<div class="tbox">
			<input type="password" name="passChange2" id="passChange2" placeholder="@비밀번호 확인"/>
	</div>
			<input class="btn" type="submit" value="수정">
			<input class="btn" type="button" value="뒤로" onclick="javascript:history.go(-1)">
</article>
</form>
</body>
</html>