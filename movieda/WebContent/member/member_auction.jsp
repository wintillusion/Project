<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../menu/menu2.jsp" flush="false"/>
<jsp:include page="../menu/member_side2.jsp" flush="false"/>
<%@page import="auction.vo.PageInfo" %>

<%@page import="auction.vo.AuctionInfoBean" %>
<%@page import="auction.vo.AuctionBean" %>
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
    

	PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");	
	int listCount=pageInfo.getListCount();
	int nowPage=pageInfo.getPage();	
	int maxPage=pageInfo.getMaxPage();
	int startPage=pageInfo.getStartPage();
	int endPage=pageInfo.getEndpage();
	
	
	ArrayList<AuctionInfoBean> auctionInfoList=(ArrayList<AuctionInfoBean>)request.getAttribute("auctionInfoList");
	ArrayList<AuctionBean> auctionEndList=(ArrayList<AuctionBean>)request.getAttribute("auctionEndList");
	

	
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
  <h3>MY AUCTION RECORD</h3>
   <table>
  <tr>
  <% for(int i=0;i<auctionInfoList.size();i++){ //사이즈크기만큼 찍어줌%>
					<tr><td><a style="color:black" href="auctionDetail.auction?num=<%=auctionInfoList.get(i).getOrder_num() %>&page=<%=nowPage %>#begin">
					경매번호 : <%=auctionInfoList.get(i).getOrder_num() %>&nbsp; | 입찰금액 : <%=auctionInfoList.get(i).getMymoney() %>	
					</a></td></tr>
  <%} %>

  </table>
</article>
<article>  
  <h3>MY AUCTION WINNNIG</h3>
  <table>
  <tr>
  <% for(int i=0;i<auctionEndList.size();i++){ //사이즈크기만큼 찍어줌%>
					<tr><td><a style="color:black" href="auctionDetail.auction?num=<%=auctionEndList.get(i).getNum() %>&page=<%=nowPage %>#begin">
					경매이름 : <%=auctionEndList.get(i).getSubject() %>&nbsp; | 낙찰금액 : <%=auctionEndList.get(i).getInstmoney() %>	
					</a> &nbsp;<a style="color : black;" href="auctionDepositForm.auction?num=<%=auctionEndList.get(i).getNum() %>">[입금하기]</a></td></tr>
  <%} %>

  </table>
</article>
</section>
</body>
</html>