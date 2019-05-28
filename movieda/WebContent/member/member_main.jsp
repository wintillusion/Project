<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../menu/menu2.jsp" flush="false"/>
<jsp:include page="../menu/member_side2.jsp" flush="false"/>
<%@page import="request.vo.PageInfo" %>
<%@page import="request.vo.RequestBean" %>
<%@page import="review.vo.ReviewBean" %>
<%@page import="java.util.*" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    String id=null;
    if(session.getAttribute("id")!=null){
    	id=(String)session.getAttribute("id");
    	
    }else {
    	out.println("<script>");
    	out.println("alert('로그인을 해주세요!!')");
    	out.println("location.href='./loginForm.member'");
    	out.println("</script>");
    }
    

	ArrayList<RequestBean> requestList=(ArrayList<RequestBean>)request.getAttribute("requestList");	//사용하기 위해서 형변환을 시켜줘야함.
	ArrayList<ReviewBean> reviewList=(ArrayList<ReviewBean>)request.getAttribute("reviewList");	//사용하기 위해서 형변환을 시켜줘야함.
	PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");	//pageInfo로 형변환.
//	if(pageInfo!=null)	//null이 아닐때. 이페이지를 바로 열거나 그러지 않는 이상은 null이 아님.
	int listCount=pageInfo.getListCount();
	int nowPage=pageInfo.getPage();		//nowPage로 한 이유는? 현재 page라는 객체가 존재. 그래서 nowPage로.
	int maxPage=pageInfo.getMaxPage();
	int startPage=pageInfo.getStartPage();
	int endPage=pageInfo.getEndPage();
	
	
	
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
<script>
</script>
<body>
<section>
<article>  
  <h3>MY REQUEST</h3>
  <table>
  <tr>
  <% for(int i=0;i<requestList.size();i++){ //사이즈크기만큼 찍어줌%>
					<tr><td><a style="color:black" href="requestDetail.request?num=<%=requestList.get(i).getNum() %>&page=<%=nowPage %>#begin">
					<%=requestList.get(i).getSubject() %>
					<%//상세보기를 할때 보드 넘버로 넘겨주기.index번호를 넘겨주는 것이기 때문에 당연. 페이지를 넘겨주는 이유는? 만약 현재 3페이지를 본다면?? 다시 목록보기를 했을때 3페이지로 돌아가야하기때문 %>
					</a></td></tr>
  <%} %>
 
  </table>
</article>
<article>  
  <h3>MY REVIEW</h3>
    <table>
  <tr>
  <% for(int i=0;i<reviewList.size();i++){ //사이즈크기만큼 찍어줌%>
					<tr><td><a style="color:black" href="reviewDetail.review?num=<%=reviewList.get(i).getNum() %>&page=<%=nowPage %>#begin">
					<%=reviewList.get(i).getSubject() %>
					<%//상세보기를 할때 보드 넘버로 넘겨주기.index번호를 넘겨주는 것이기 때문에 당연. 페이지를 넘겨주는 이유는? 만약 현재 3페이지를 본다면?? 다시 목록보기를 했을때 3페이지로 돌아가야하기때문 %>
					</a></td></tr>
  <%} %>
 
  </table>
  
</article>
</section>
</body>
</html>