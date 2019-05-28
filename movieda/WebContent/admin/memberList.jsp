<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="member.vo.MemberBean" %>
<%@page import="dao.MemberDAO" %>
<jsp:include page="/adminMenu/adminMenu.jsp"/>
<jsp:include page="../adminMenu/admin_side.jsp"/>
<%
	String id = null;
	ArrayList<MemberBean> articleList=(ArrayList<MemberBean>)request.getAttribute("articleList");
	if ((session.getAttribute("id") == null) || (!((String) session.getAttribute("id")).equals("admin"))) {
		out.println("<script>");
		out.println("alert()");
		out.println("location.href='loginForm.jsp'");
		out.println("</script>");
	}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 관리시스템 관리자모드(회원보기)</title>
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
		
table {
	margin: auto;
	width: 400px;
	text-align: center;
}

table a{
	color:black;
}

</style>
<body>
<h2 style="text-align : center">MEMBER LIST</h2>
<article>
	<table>
		<%
		for(int i=0; i<articleList.size(); i++) { 
		%>
		<tr>
			<td><a href="member_info.admin?id=<%=articleList.get(i).getId() %>"><%=articleList.get(i).getId() %></a></td>
			<td><a href="modifyForm.admin?id=<%=articleList.get(i).getId() %>">수정</a>
			<a href="member_pass.admin?id=<%=articleList.get(i).getId() %>">비밀번호 변경</a>
			<a href="delete.admin?id=<%=articleList.get(i).getId() %>">삭제</a></td>
		</tr>
		<%
			}			
		%>
	</table>
</article>
</body>
</html>