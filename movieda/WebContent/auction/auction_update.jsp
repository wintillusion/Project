<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="auction.vo.AuctionBean" %>
<%@ page import="auction.vo.StockBean" %>
<jsp:include page="../menu/menu2.jsp"></jsp:include>
<jsp:include page="../menu/board_side.jsp"></jsp:include>
<%
	AuctionBean auctionBean = (AuctionBean)request.getAttribute("auctionBean");
	StockBean stockBean = (StockBean)request.getAttribute("stockBean");
	String nowPage = (String) request.getAttribute("page");
	String id=null;
	    if(session.getAttribute("id")!=null){
	    	id=(String)session.getAttribute("id"); 
	    }
	    
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
#registForm{
		witdh : 500px;
		height : 600px;
		border : 1px solid red;
		margin : auto;
	}
	
	h2{
		text-align:center;
	}
	
	table{
		margin:auto;
		width:450px;
	}
	
	.td_left{
		width:150px;
		background:orange;
	}
	
	.td_right{
		width:300px;
		background:skyblue;
	}
	
	#commandCell{
		text-align:center;
	}
	
</style>
<script>
function updateAuction(){
	updateForm.submit();
}

function moneyCheck(f){
	//alert(f.instmoney.value);

	if (f.instmoney.value==<%=auctionBean.getInstmoney()%>) {
		alert('같은 금액으로 입찰할 수는 없습니다.');			
		f.instmoney.focus();
		return false;
	}
	if(f.instmoney.value.trim()=="" || f.instmoney.value==null){
		alert('금액을 입력해주세요.');
		f.instmoney.focus();
		return false;
	}
	if(f.instmoney.value<<%=auctionBean.getInstmoney()%>){
		alert('적은 금액으로 입찰할 수는 없습니다.');
		f.instmoney.focus();
		return false;
	}
	
}
</script>
<body>
<section class="writeForm">
<h2>입찰하기</h2>
<form action="auctionUpdate.auction" method="post" name="updateForm" onsubmit="return moneyCheck(this)">
<input type="hidden" name="num" id="num" value="<%=auctionBean.getNum() %>"/>	<!-- 경매번호 -->
<input type="hidden" name="mem_id" id="mem_id" value="<%=id %>">	<!-- 입찰자 이름 -->
<table class="registForm">
<tr>
		<td class="td_left">
			<label for ="instmoney">경매번호</label>
		</td>
		<td class="td_right">
			<input type="text" name="instmoney" id="instmoney" value="<%=auctionBean.getNum()%>" readonly="readonly" />
		</td>
</tr>
<tr>
		<td class="td_left">
			<label for ="instmoney">경매물품</label>
		</td>
		<td class="td_right">
			<input type="text" name="instmoney" id="instmoney" value="<%=stockBean.getProduct()%>" readonly="readonly"/>
		</td>
</tr>
<tr>
		<td class="td_left">
			<label for ="instmoney">현재가격</label>
		</td>
		<td class="td_right">
			<input type="text" name="instmoney" id="instmoney" placeholder="<%=auctionBean.getInstmoney()%>"/>	<!-- 현재 가격보다 적은 가격은 안되게 스크립트처리필수 -->
		</td>		
</tr>
	<tr>
	</tr>
</table>
	<section id="commandCell">
	<!-- 	<a href="javascript:updateAuction()">[입찰]</a>&nbsp;&nbsp; -->
		<!-- <input type="button" onclick="javascript:updateAuction()" value="입찰"> -->
		<input type="submit" value="입찰">
		<input type="button" onclick="javascript:history.go(-1)" value="뒤로">
	</section>
</form>
</section>
</body>
</html>