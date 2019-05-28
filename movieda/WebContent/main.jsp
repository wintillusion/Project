<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "auction.vo.AuctionBean" %>
<%@ page import = "request.vo.RequestBean" %>
<%@ page import = "review.vo.ReviewBean" %>
<%@ page import = "notice.vo.NoticeBean" %>
<jsp:include page="menu/menu2.jsp"/>

<%
    String id=null;
    if(session.getAttribute("id")!=null){
    	id=(String)session.getAttribute("id");
  
	 	if(session.getAttribute("id").equals("admin")){
			out.println("<script>");
			out.println("alert('관리자페이지로 이동합니다.')");
			out.println("location.href='adminMain.admin'");
			out.println("</script>");
		} 
    }
    
    
    
    ArrayList<AuctionBean> auctionList=(ArrayList<AuctionBean>)request.getAttribute("auctionList");
    ArrayList<RequestBean> requestList=(ArrayList<RequestBean>)request.getAttribute("requestList");	
    ArrayList<ReviewBean> reviewList=(ArrayList<ReviewBean>)request.getAttribute("reviewList");	
    ArrayList<NoticeBean> noticeList=(ArrayList<NoticeBean>)request.getAttribute("noticeList");	
  /*   ArrayList<FreeBean> FreeList=(ArrayList<FreeBean>)request.getAttribute("freeList"); */
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main</title>
<style>
html, body {
      margin: 0;
      padding: 0;
      width: 100%;
      background-color: #fff;
}

 article {
			width: 29.17%;
			height: auto;
			float: left;
			margin-right: 1.5625%;
			margin-bottom: 20px;
			margin-top: 20px;
			padding: 0.5%;
			border: 1px dotted #333;
			border-radius: 10px;
			background-color: #fff;
		}
		 article:first-child {
			margin-left: 3.083%;
		}

		dl {
			margin: 10px;
		}
		dt {
			font-weight: bold;
		}
 		
		/* footer {
			padding: 10px 0;
			background-color: #333;
			color: white;
			text-align: center;
			width:100%;
			position : relative;
			margin-top : 80px;

		} */
		/*공지사항 롤링*/
		.notice{width:100%; height:50px; overflow:hidden; background-color:#fff;}
		.rolling{position:relative; width:100%; height:auto;}
		.rolling{text-align:center; list-style:none;}
		.rolling li{width:100%; height:50px; line-height:30px;}
		
		/* 이벤트 경매 메인 */
		.event{width:100%; height:500px; overflow:hidden; background-color:#fff;z-index: -1; text-align : center; margin : 0 auto;}
	
		.banner {position: relative; width: 400px; height: 300px; top: 50px;  margin:0 auto; padding:0; overflow: hidden;}
		.banner ul {position: absolute; margin: 0px; padding:0; list-style: none; }
		.banner ul li {float: left; width: 340px; height: 210px; margin:30px; padding:0;}
		

	</style>
	<script>
	$(document).ready(function(){
		var height =  $(".notice").height(); //공지사항의 높이값
		var num = $(".rolling li").length; // 공지사항의 length로 개수 파악!
		var max = height * num; //총높이
		var move = 0; //초기값
		function noticeRolling(){
			move += height;// move = move + height
			$(".rolling").animate({"top":-move},600,function(){ // animate로 부드럽게 top값을 올리기.
				if( move >= max ){ //if문을 통해 최대값보다 top값을 많이 올렸으면 다시 0으로 실행되도록.
					$(this).css("top",0); //0으로 돌려주고~
					move = 0; //초기값도 다시 0으로!
				};
			});
		};
		noticeRollingOff = setInterval(noticeRolling,3000); //자동롤링답게 setInterval를 사용해서 1000 = 1초마다 함수 실행!!
		$(".rolling").append($(".rolling li").first().clone()); //올리다보면 마지막이 안보여서 clone을 통해 첫번째li 복사!

/* 		$(".rolling_stop").click(function(){
			clearInterval(noticeRollingOff); //stop을 누르면 clearInterval을 통해 자동롤링을 해제
		});
		$(".rolling_start").click(function(){
			noticeRollingOff = setInterval(noticeRolling,1000); //start를 누르면 다시 자동롤링 실행
		}); */
	});	
	
	//옥션
	
	$(document).ready(function() {
		//사용할 배너
		var $banner = $(".banner").find("ul");

		var $bannerWidth = $banner.children().outerWidth();//배너 이미지의 폭
		var $bannerHeight = $banner.children().outerHeight(); // 높이
		var $bannerLength = $banner.children().length;//배너 이미지의 갯수
		var rollingId;

		//정해진 초마다 함수 실행
		rollingId = setInterval(function() { rollingStart(); }, 3000);//다음 이미지로 롤링 애니메이션 할 시간차

		//마우스 오버시 롤링을 멈춘다.
		banner.mouseover(function(){
			//중지
			clearInterval(rollingId);
			$(this).css("cursor", "pointer");
		});
		//마우스 아웃되면 다시 시작
		banner.mouseout(function(){
			rollingId = setInterval(function() { rollingStart(); }, 3000);
			$(this).css("cursor", "default");
		});
		
		function rollingStart() {
			$banner.css("width", $bannerWidth * $bannerLength + "px");
			$banner.css("height", $bannerHeight + "px");
			//alert(bannerHeight);
			//배너의 좌측 위치를 옮겨 준다.
			$banner.animate({left: - $bannerWidth + "px"}, 2000, function() { //숫자는 롤링 진행되는 시간이다.
				//첫번째 이미지를 마지막 끝에 복사(이동이 아니라 복사)해서 추가한다.
				$(this).append("<li>" + $(this).find("li:first").html() + "</li>");
				//뒤로 복사된 첫번재 이미지는 필요 없으니 삭제한다.
				$(this).find("li:first").remove();
				//다음 움직임을 위해서 배너 좌측의 위치값을 초기화 한다.
				$(this).css("left", 0);
				//이 과정을 반복하면서 계속 롤링하는 배너를 만들 수 있다.
			});
		}
	}); 

	</script>
</head>
<body>
<div class="notice">
<ul class="rolling">
<%for(int i=0; i<noticeList.size(); i++){ %>
				<li><a style="color:black; font-weight:bold;" href="noticeDetail.notice?num=<%=noticeList.get(i).getNum() %>">★<%=noticeList.get(i).getSubject() %>★</a>
				
			<%} %>
</ul>
</div>

<div class="event">
<h2>EVENT ACUTION</h2>

<div class="contents">
<h4>배우들의 애장품 경매에 참여해보세요!</h4>
<h4>모인 금액들은 불우이웃에게 기부됩니다.</h4>
		<div class="banner">
			<ul>
			<%for(int i=0; i<auctionList.size(); i++){ %>
				<li><a style="color:black" href="auctionDetail.auction?num=<%=auctionList.get(i).getNum() %>">
				경매 이름  |  <%=auctionList.get(i).getSubject()%><br>
				현재 가격  |  <%=auctionList.get(i).getInstmoney()%> 원<br>
				종료 날짜  |  <%=auctionList.get(i).getEdate() %><br>
				<img src="auctionUpload/<%=auctionList.get(i).getFile() %>" width="340" height="210px">				
				</a></li>
				
			<%} %>
			
			</ul>
		</div>
	</div>


</div>
<br>
<hr style="width : 100%">
<section>
<article>  
  <h3><a  style="color:black" href="requestList.request">REQUEST ▶</a></h3>
  	
  <%for(int i=0; i<requestList.size(); i++){ %>
  				<ul style="list-style:none">
				<li>
				<a style="color : black;" href="requestDetail.request?num=<%=requestList.get(i).getNum()%>">
				<%=requestList.get(i).getSubject() %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| <%=requestList.get(i).getDate() %></a>
				</li>
				</ul>
				
			<%} %>

</article>
<article>  
  <h3><a style="color:black" href="reviewList.review">REVIEW ▶</a></h3>
    <%for(int i=0; i<reviewList.size(); i++){ %>
  				<ul style="list-style:none">
  			
				<li><a style="color : black;" href="reviewDetail.review?num=<%=reviewList.get(i).getNum()%>">
				<%=reviewList.get(i).getSubject() %> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|   <%=requestList.get(i).getDate() %> </a>
				</li>
				</ul>
				
			<%} %>
</article>

<article>  
  <h3><a style="color:black" href="reviewList.review">FREE ▶</a></h3>
    <%for(int i=0; i<reviewList.size(); i++){ %>
  				<ul style="list-style:none">
  			
				<li><a style="color : black;" href="reviewDetail.review?num=<%=reviewList.get(i).getNum()%>">
				<%=reviewList.get(i).getSubject() %> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|   <%=requestList.get(i).getDate() %> </a>
				</li>
				</ul>
				
			<%} %>
</article>

 </section>
</body>
</html>
