<%@ page import="auction.vo.AuctionBean" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.ParseException" %>
<%@ page import="auction.vo.StockBean" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../menu/menu2.jsp"></jsp:include>
<jsp:include page="../menu/board_side.jsp"></jsp:include>

<%
	AuctionBean auctionBean = (AuctionBean)request.getAttribute("auctionBean");
	StockBean stockBean = (StockBean)request.getAttribute("stockBean");
	//System.out.println(requestBean.getSubject()); //	값이 넘어오는지 확인
 	String nowPage = (String)request.getAttribute("page");

	if(nowPage==null) nowPage="1";	//페이지 값이 널이 넘어오면 1을 열어준다.
	int num = auctionBean.getNum();
	
	String session_id="";
	if(session.getAttribute("id")==null){
		    	session_id=(String)session.getAttribute("id");
	}
	//System.out.println(auctionBean.getEdate().getTime());
	long time = auctionBean.getEdate().getTime();
	int checktime = (int)(time/1000);	//int형 변환.
	//System.out.println(checktime);
	java.util.Date date = new java.util.Date();
	long time2 = date.getTime();
	int checktime2 = (int)(time2/1000);
	int endtime = checktime-checktime2;
	    
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
			height : 700px; auto;
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
var timer=$(document).ready(function () 
		 {
		     setInterval(function()
		     {
		    	 timecheck();
		     },1000);//1초마다 찍어주는 것.
		 });

function timecheck(){
	var time = parseInt(<%=checktime%>);
	var date = new Date();
	var today = parseInt(date.getTime()/1000);
	var checktime = parseInt(time-today);
	var checktime2 = parseInt(checktime);
	var checktimeDay = parseInt(checktime2/(60*60*24));
	var checktimeHour = parseInt((checktime2-checktimeDay * 60 * 60 * 24)/(60*60));
	var checktimeMin = parseInt((checktime2-checktimeDay * 60 * 60 * 24 - checktimeHour * 3600) / 60);
	var checktimeSec = parseInt(checktime2 % 60);
	document.getElementById('checkDay').innerHTML = checktimeDay+"일 "+checktimeHour+":"+checktimeMin+":"+checktimeSec;
	if(checktime<=0){
		document.getElementById('checkDay').innerHTML ="경매종료";
		document.auctionEnd.submit();	//서브밋되고 멈추게 해야함. 안되면 관리자가 경매종료 후 db에 종료됐다고 전달하는 수 밖에 없을 것 같다.
		clearInterval(timer);
	}
}




</script>
</head>
<body>
	<form action = "auctionUpdateForm.auction" method="post">
	<h2 style="text-align:center" id="begin">AUCTION</h2>
	<article>
		<div>
		<section>
		<input type="hidden" name="num" id="num" value=<%=auctionBean.getNum()%>>
		<h3><%=auctionBean.getSubject() %></h3>
		<%if(auctionBean.getMem_id().equals("admin")){%>
		<span>현재 입찰자 : NONE</span> 
		<%}else{ %>
		<span>현재 입찰자 : <%=auctionBean.getMem_id()%></span><%} %> | <span>종료 : <%=auctionBean.getEdate() %></span>
		<span class="ri"> 시작가 : <%=auctionBean.getFstmoney() %> | 입찰가 : <%=auctionBean.getInstmoney() %></span>
		<span>경매물품 : <%=stockBean.getProduct() %></span>
		<hr>
		</section>
		<section class="content">
		 <%=auctionBean.getContent()%>
		</section>

		<br>
		<hr>
		<p>남은시간 :<span id="checkDay"></span></p>
		<hr>
		
		</div>
		<section class="center">
		<%if(session_id!=null && endtime>=0){//세션 아이디가 null이 아니고 endtime이 0보다 클때%>
		<input type="submit" id="save" name="save" value="입찰하기">
		<input type="button" onclick="history.back()" value="뒤로">
		<%}else if(session_id!=null && endtime<=0){ //세션 아이디가 null이 아니고 경매종료시간이 끝났을때 %>
		<span>본 경매는 종료되었습니다.</span>
		<%}else if(session_id==null && endtime>=0 || endtime<=0 ){ //세션 id가 null이고 경매시간이 남아있을때%>
		<span>로그인 후 사용해주세요.</span>
		<%}%>
		</section>
		
</article>
</form>
<form action="auctionEnd.auction" method="post" id="auctionEnd" name="auctionEnd">	<!-- 경매종료되면 자동으로 폼 전송. -->
		<input type="hidden" id="num" name="num" value=<%=auctionBean.getNum()%>>
		<input type="hidden" id="mem_id" name="mem_id" value=<%=auctionBean.getMem_id()%>>
		<input type="hidden" id="instmoney" name="instmoney" value=<%=auctionBean.getInstmoney()%>>
		<input type="hidden" id="auctioncode" name="auctioncode" value=1>
	
		</form>
		<%System.out.println("auction_detail의 값 : " + auctionBean.getNum()); %>
		<%System.out.println("auction_detail의 값 : " + auctionBean.getMem_id()); %>
		<%System.out.println("auction_detail의 값 : " + auctionBean.getInstmoney()); %>
</body>
</html>