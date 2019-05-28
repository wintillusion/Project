<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="auction.vo.AuctionBean" %>
    <jsp:include page="../menu/menu2.jsp"/>
    <jsp:include page="../menu/member_side2.jsp"/>
<%
	AuctionBean auctionBean = (AuctionBean)request.getAttribute("auctionBean");
	int num=auctionBean.getNum();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
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
<body>
<section>
<article> 
  <h3>AUCTION DELIVERY</h3>
  <%=auctionBean.getNum() %>의 경매가 배송출발했습니다.
</article>
</section>
</body>
</html>