<%@ page import="notice.vo.NoticeBean" %>
<%@ page import="java.util.*" %>
<%@ page import="notice.vo.PageInfo" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../adminMenu/adminMenu.jsp"></jsp:include>
<jsp:include page="../adminMenu/admin_side.jsp"></jsp:include>

<%
if ((session.getAttribute("id") == null) || (!((String) session.getAttribute("id")).equals("admin"))) {
	out.println("<script>");
	out.println("alert('관리자로 로그인해주세요.')");
	out.println("location.href='loginForm.member'");
	out.println("</script>");
}


	NoticeBean noticeBean = (NoticeBean)request.getAttribute("noticeBean");
 	String nowPage = (String)request.getAttribute("page");

	if(nowPage==null) nowPage="1";	//페이지 값이 널이 넘어오면 1을 열어준다.
	int num = noticeBean.getNum();
	
	String session_id="";
	if(session.getAttribute("id")==null){
		    	session_id=(String)session.getAttribute("id");
	}
	

	    
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table{
	marign : auto;
	width : 60%;
	float: right;
}
hr{
	width: 1000px auto;
}

 article {
			width: 50%;
			height: auto;
			margin-left: 24.5%;
			margin-bottom: 20px;
			margin-top: 20px;
			padding: 0.5%;
			/* border: 1px dotted #333; */
			border-radius: 10px;
		}
		
		dl {
			margin: 10px;
		}
		dt {
			font-weight: bold;
		}
		
		.ri {
			float : right
			
		}
		
		.content{
			height : 400px;
			padding-top: 10px;
		}
		
		.center{
			text-align:right;
		}
		
		.center a{
			color:black;
		}
 		

</style>
<script>


function deleteNotice(){
	location.href="NoticeDelete.admin?num="+<%=num%>;
}

</script>
</head>
<body>
	<article>
	<form action = "noticeModifyForm.admin" method="post" >
	<h2 style="text-align:center" id="begin">NOTICE</h2>
	<input type="hidden" name="num" value=<%=num%>>

		<div>
		<section>
		<h3><%=noticeBean.getSubject() %></h3>
		<span><%=noticeBean.getWriter() %></span> | <span><%=noticeBean.getDate() %></span>
		<span class="ri"> 조회 <%=noticeBean.getReadcount()%></span>
		<hr>
		</section>
		<section class="content">
		 <%=noticeBean.getContent()%>
		</section>
		<section>
		</section>
		<br>
		
		</div>
		<section class="center">
		</section>
		<input type="submit" value="공지 수정">
		<input type="button" value="공지 삭제" onclick="javascript:deleteNotice()">
		<input type="button" value="뒤로" onclick="history.back()">
</form>

</article>		
</body>
</html>