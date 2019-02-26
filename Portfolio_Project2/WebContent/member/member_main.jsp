<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../menu/menu2.jsp" flush="false"/>
<jsp:include page="../menu/member_side2.jsp" flush="false"/>
<%
    String id=null;
    if(session.getAttribute("id")!=null){
    	id=(String)session.getAttribute("id");
    	
    }else {
    	out.println("<script>");
    	out.println("alert('로그인을 해주세요!!')");
    	out.println("location.href='../loginForm.member'");
    	out.println("</script>");
    }
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
html, body {
      margin: 0;
      padding: 0;
      width: 100%;
}

 article {
			width: 55%;
			height: auto;
			margin-left: 20%;
			margin-bottom: 20px;
			margin-top: 20px;
			padding: 0.5%;
			border: 1px dotted #333;
			border-radius: 10px;
		}
		
		dl {
			margin: 10px;
		}
		dt {
			font-weight: bold;
		}
 		

</style>
</head>
<body>

<section>
<article>  
  <h3>MY REQUEST</h3>

</article>
<article>  
  <h3>MY REVIEW</h3>
  
</article>
<article>  
  <h3>MY RANKING</h3>
  
</article>
</section>
</body>
</html>