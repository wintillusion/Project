<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="menu/menu2.jsp"/>

<%
    String id=null;
    if(session.getAttribute("id")!=null){
    	id=(String)session.getAttribute("id");
   // 	System.out.println("main session : " + id);
    }
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
		}
		 article:first-child {
			margin-left: 2.083%;
		}

		dl {
			margin: 10px;
		}
		dt {
			font-weight: bold;
		}
 		aside {
			clear: both;
			width: 80.75%;
			margin: 10px auto;
			padding: 10px;
			background-color: #fff;
			text-align : center;
			
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
		
		.notice{width:100%; height:50px; overflow:hidden; background-color:#fff;}
		.rolling{position:relative; width:100%; height:auto;}
		.rolling{text-align:center; list-style:none;}
		.rolling li{width:100%; height:50px; line-height:30px;}
		
		
		
		
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

	
	</script>
</head>
<body>
<div class="notice">
<ul class="rolling">
	<li>
		★<a style="color:black" href="#">공지사항 1내용입니다.</a>
	</li>
	<li>
		★<a style="color:black" href="#">공지사항 2내용입니다.</a>
	</li>
	<li>
		★<a style="color:black" href="#">공지사항 3내용입니다.</a>
	</li>
	<li>
		★<a style="color:black" href="#">공지사항 4내용입니다.</a>
	</li>
	<li>
		★<a style="color:black" href="#">공지사항 5내용입니다.</a>
	</li>
</ul>
</div>

<section>
<article>  
  <h3>REQUEST</h3>

</article>
<article>  
  <h3>REVIEW</h3>
  
</article>
<article>  
  <h3>RANKING</h3>
  
</article>
<article style="margin-left: 2.083%">  <!-- 첫번째 2.083% 띄우도록되어있으니까 맞춰줘야 함. -->
  <h3>EVENT</h3>

</article>
<article>
  <h3>FREE TALK</h3>
  
</article>
<!--  <article>
   <h3>자유게시판</h3>
   
 </article> -->
<aside>
<a href="#" style="color:gray">ABOUT ME</a>
</aside>
 </section>
<jsp:include page="footer/footer.jsp"></jsp:include>
</body>
</html>
