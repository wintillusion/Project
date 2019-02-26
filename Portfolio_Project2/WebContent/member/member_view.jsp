<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:include page="../menu/menu2.jsp" />
    <jsp:include page="../menu/member_side2.jsp" />
<%@ page import = "member.vo.MemberBean" %>
 <%
	MemberBean memberBean = (MemberBean)request.getAttribute("memberBean");
	String nowPage = (String) request.getAttribute("page");
	
	 String id=null;
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
<title>member_view</title>
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
function modifyForm(){
	location.href="./modifyForm.member?id=<%=id%>"
}
</script>

</head>
<body>
<h2>YOUR MEMBER INFORMATION</h2>
<article>
	<div class="tbox">
			<input type="text" name="name" id="name" value="<%=memberBean.getName() %>" disabled="disabled"/>
	</div>
	<div class="tbox">
			<input type="text" name="postcode" id="postcode" value="<%=memberBean.getPostcode() %>" disabled="disabled"/>
	</div>
	<div class="tbox">
			<input type="text" name="address1" id="address1" value="<%=memberBean.getAddress1() %>" disabled="disabled"/>
	</div>
	<div class="tbox">
			<input type="text" name="address2" id="address2" value="<%=memberBean.getAddress2() %>" disabled="disabled"/>
	</div>
	<div class="tbox">
			<input type="text" name="phone" id="phone" value="<%=memberBean.getPhone() %>" disabled="disabled">
	</div>	
	<div class="tbox">
			<input type="text" name="email" id="email" value="<%=memberBean.getEmail() %>" disabled="disabled"/>
	</div>
	<input class="btn" type="button" value="회원정보 수정하기" onclick="javascript:modifyForm()">
			<input class="btn" type="button" value="비밀번호 변경하기">
</article>
</body>
</html>