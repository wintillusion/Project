<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@page import="java.util.*" %>
<%@page import="member.vo.MemberBean" %>
<%@page import="dao.MemberDAO" %>
<jsp:include page="/adminMenu/adminMenu.jsp"/>
<jsp:include page="/adminMenu/admin_side.jsp"/>
    <%
    String id=null;
    
    if((session.getAttribute("id")==null)||(!((String)session.getAttribute("id")).equals("admin"))) {
    	out.println("<script>");
    	out.println("location.href='loginForm.jsp'");
    	out.println("</script>");
    }
    MemberBean memberBean = (MemberBean)request.getAttribute("memberBean");
    String nowpage=(String)request.getAttribute("page");

  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원관리시스템 관리자모드(회원정보보기)</title>
</head>
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
function listForm(){
	location.href="list.admin";
}
</script>
<body>
<h3 style="text-align : center">MEMBER DETAIL</h3>
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
<input class="btn" type="button" onclick="javascript:listForm()" value="리스트"/>
</article>
</body>
</html>
