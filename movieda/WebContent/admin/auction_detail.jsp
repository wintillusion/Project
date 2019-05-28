<%@ page import="auction.vo.AuctionBean" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.ParseException" %>
<%@ page import="auction.vo.StockBean" %>
<%@ page import="auction.vo.AuctionInfoBean" %>
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


	AuctionBean auctionBean = (AuctionBean)request.getAttribute("auctionBean");
	StockBean stockBean = (StockBean)request.getAttribute("stockBean");
	ArrayList<AuctionInfoBean> auctionInfoList=(ArrayList<AuctionInfoBean>)request.getAttribute("auctionInfoList");	//입찰한 사용자 내역
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
//하나의 함수 안에 timeinterval과 timecheck를 넣어놓고 시간이 마감되는 경우에 settimeout으로 경매종료 띄워주기
 $(document).ready(function () 
		 {
		     setInterval(function()
		     {
		    	 timecheck(); //1초마다 찍어주는 것.
		    	 
		    	 
		     },1000);
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
		document.getElementById("save").style.display = "none";
	}
}

function deleteAuction(){
	location.href="auctionDelete.admin?num="+<%=num%>;
}
</script>
</head>
<body>
	<article>
	<form action = "auctionModifyForm.admin" method="post" >
	<h2 style="text-align:center" id="begin">AUCTION</h2>

		<div>
		<section>
		<input type="hidden" name="num" id="num" value=<%=auctionBean.getNum()%>>
		
		<h3><%=auctionBean.getSubject() %></h3>
		<%if(auctionBean.getMem_id()==null){%>
		<span>현재 입찰자 : NONE</span> 
		<%}else{ %>
		<span>현재 입찰자 : <%=auctionBean.getMem_id()%></span><%} %> | <span>종료 : <%=auctionBean.getEdate() %></span>
		<span class="ri"> 시작가 : <%=auctionBean.getFstmoney() %> | 입찰가 : <%=auctionBean.getInstmoney() %></span>
		<span class="ri"> 경매물품 : <%=stockBean.getProduct() %>|</span>
		<hr>
		</section>
		<section class="content">
		 <%=auctionBean.getContent()%>
		</section>
		
		<br>
		<hr>
		<p>남은시간 :<span id="checkDay"></span></p>
		<hr>
		입찰기록<br>
		<%for(int i=0;i<auctionInfoList.size();i++){ %>
		<%=i+1 %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		|<%=auctionInfoList.get(i).getMem_id() %> 님  |&nbsp; <span class="ri"><%=auctionInfoList.get(i).getMymoney() %> 원</span>


		<%} %>
		<hr>
		</div>
		<section class="center">
		</section>
		<input type="submit" value="수정하기">
		<input type="button" value="경매삭제" onclick="javascript:deleteAuction()">
		
</form>

</article>		
</body>
</html>