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
<title>AUCTION DEPOSIT</title>
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
<form action="auctionDepositPro.auction?num=<%=num %>" method="post">
  <h3>AUCTION DEPOSIT</h3>
  <input type="hidden" name="stock_code" value="1">
  <input type="hidden" name="auction_code" value="3">
  <table>
  <tr>
  	<td>경매 번호 : <%=auctionBean.getNum() %></td>
  	
  </tr>
  <tr>
  	<td>입금하시겠습니까?</td>
  </tr>
  <tr>
  	<td><input type="submit" value="입금"></td>
  </tr>
  </table>
</form>
</article>
</section>
</body>
</html>