<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   	<%@page import="auction.vo.StockBean" %>
   	<%@page import="auction.vo.AuctionBean" %>
   	<%@page import="auction.vo.AuctionInfoBean" %>
   	<%@page import="auction.vo.PageInfo" %>
   	<%@page import="java.util.*" %>
   	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<jsp:include page="../adminMenu/adminMenu.jsp"/>
	<jsp:include page="../adminMenu/admin_side.jsp"/>
	<%
	if ((session.getAttribute("id") == null) || (!((String) session.getAttribute("id")).equals("admin"))) {
	out.println("<script>");
	out.println("alert('관리자로 로그인해주세요.')");
	out.println("location.href='loginForm.member'");
	out.println("</script>");
}

	ArrayList<AuctionBean> auctionList=(ArrayList<AuctionBean>)request.getAttribute("auctionList");	
	ArrayList<AuctionInfoBean> auctionInfoList=(ArrayList<AuctionInfoBean>)request.getAttribute("auctionInfoList");	
	ArrayList<StockBean> stockList=(ArrayList<StockBean>)request.getAttribute("stockList");
	PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");	//pageInfo로 형변환.
//	if(pageInfo!=null)	//null이 아닐때. 이페이지를 바로 열거나 그러지 않는 이상은 null이 아님.
	int listCount=pageInfo.getListCount();
	int nowPage=pageInfo.getPage();		//nowPage로 한 이유는? 현재 page라는 객체가 존재. 그래서 nowPage로.
	int maxPage=pageInfo.getMaxPage();
	int startPage=pageInfo.getStartPage();
	int endPage=pageInfo.getEndpage();
	
	System.out.println("auctionList : " + auctionList);
	System.out.println("auctionListInfo : " + auctionInfoList);
	System.out.println("stockList : " + stockList);
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AUCTION RECORD</title>
</head>

<style>
html, body {
      margin: 0;
      padding: 0;
      width: 100%;
      
}html, body {
      margin: 0;
      padding: 0;
      width: 100%;
      
}
table.blueTable {
  border: 1px solid #1C6EA4;
  background-color: #EEEEEE;
  width: 50%;
  margin-top : 0.5%;
  margin-left : 24.5%;
  text-align: left;
  border-collapse: collapse;
}
table.blueTable td, table.blueTable th {
  border: 1px solid #AAAAAA;
  padding: 3px 2px;
}
table.blueTable tbody td {
  font-size: 13px;
}
table.blueTable tr:nth-child(even) {
  background: #E2C0BC;
}
table.blueTable thead {
  background: #E2472F;
  background: -moz-linear-gradient(top, #e97563 0%, #e55943 66%, #E2472F 100%);
  background: -webkit-linear-gradient(top, #e97563 0%, #e55943 66%, #E2472F 100%);
  background: linear-gradient(to bottom, #e97563 0%, #e55943 66%, #E2472F 100%);
  border-bottom: 1px solid #444444;
}
table.blueTable thead th {
  font-size: 15px;
  font-weight: bold;
  color: #FFFFFF;
  text-align: center;
  border-left: 1px solid #F5F5F5;
}
table.blueTable thead th:first-child {
  border-left: none;
}

table.blueTable tfoot {
  font-size: 14px;
  font-weight: bold;
  color: #FFFFFF;
  background: #F3D2D0;
  background: -moz-linear-gradient(top, #f6dddc 0%, #f4d6d4 66%, #F3D2D0 100%);
  background: -webkit-linear-gradient(top, #f6dddc 0%, #f4d6d4 66%, #F3D2D0 100%);
  background: linear-gradient(to bottom, #f6dddc 0%, #f4d6d4 66%, #F3D2D0 100%);
  border-top: 1px solid #444444;
}
table.blueTable tfoot td {
  font-size: 14px;
}
table.blueTable tfoot .links {
  text-align: right;
}
table.blueTable tfoot .links a{
  display: inline-block;
  background: #AE3724;
  color: #FFFFFF;
  padding: 2px 8px;
  border-radius: 5px;
  
}
</style>
<body>
</body>
</html>