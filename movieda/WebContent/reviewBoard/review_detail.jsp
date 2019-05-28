<%@ page import="review.vo.ReviewBean" %>
<%@ page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../menu/menu2.jsp"></jsp:include>
<jsp:include page="../menu/board_side.jsp"></jsp:include>

<%
	ReviewBean reviewBean = (ReviewBean)request.getAttribute("reviewBean");
	
 	String nowPage = (String)request.getAttribute("page");

	if(nowPage==null) nowPage="1";	//페이지 값이 널이 넘어오면 1을 열어준다.
	 
	String session_id=null;
	    if(session.getAttribute("id")!=null){
	    	session_id=(String)session.getAttribute("id");
	    }
	    
	  String request_id=null;
	  if(reviewBean.getMem_id()!=null){
	 	request_id = reviewBean.getMem_id();
	  }
	  
	  int num = reviewBean.getNum();
	 
	    
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
			height : auto;
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
$(document).ready(function(){
	
	  $.ajax({
	        type : "GET", //전송방식을 지정한다 (POST,GET)
	        url : "reviewViewList.review?type=2",//호출 URL을 설정한다. GET방식일경우 뒤에 파라티터를 붙여서 사용해도된다.
	        dataType : "html",//호출한 페이지의 형식이다. xml,json,html,text등의 여러 방식을 사용할 수 있다.
	        error : function(){
	            //alert("통신실패!!!!");
	        },
	        success : function(Parse_data){
	            $("#Parse_Area").html(Parse_data); //div에 받아온 값을 넣는다.
	           // alert("통신 데이터 값 : " + Parse_data); 
	        }
	         
	    });
	    
	    $.ajax({
	        type : "GET", //전송방식을 지정한다 (POST,GET)
	        url : "commentList.comment?num=<%=num%>",
	        dataType : "html",//호출한 페이지의 형식이다. xml,json,html,text등의 여러 방식을 사용할 수 있다.
	        error : function(){
	           // alert("통신실패!!!!");
	        },
	        success : function(Parse_data){
	            $("#Parse_Area2").html(Parse_data); //div에 받아온 값을 넣는다.
	           // alert("통신 데이터 값 : " + Parse_data); 
	        }
	         
	    });
	    

	});


function modifyForm(){
	location.href="reviewModifyForm.review?num=<%=reviewBean.getNum() %>&page=<%=nowPage %>";
	
	
}

function deleteForm(){
	location.href="reviewDeletePro.review?num=<%=reviewBean.getNum()%>&page=<%=nowPage%>";
}

function wrtieForm(){
	location.href="reviewWriteForm.review";
}

function likey(){
	location.href="reviewLikey.review?num=<%=reviewBean.getNum()%>&page=<%=nowPage%>";
}

function checkContent(f){
	if (f.content.value.trim()==""||f.id.value==null) {
		alert("댓글을 입력해주세요.");
		f.content.focus();
		return false;
	}
}

</script>
</head>
<body>
	<h2 style="text-align:center" id="begin">REVIEW</h2>
	<article>
		<div>
		<section>
		<h3><%=reviewBean.getSubject() %></h3>
		<span><%=reviewBean.getMem_id() %></span> | <span><%=reviewBean.getDate() %></span>
		<span class="ri"> 조회 <%=reviewBean.getRead_count()%> | 추천 <%=reviewBean.getLikey()%></span>
		<hr>
		</section>
		<section class="content">
		 <%=reviewBean.getContent()%>
		</section>

		<br>
		<br>
		<section>
		<p style="text-align:center">추천수 <br><%=reviewBean.getLikey()%></p><input type="button"value="추천" onclick="javascript:likey()">
		</section>
		<hr>
		
		<hr>
			<!-- 댓글 다는 곳. 비동기방식 바로바로 보이게? -->
		<section id="comment">
			<section id="cmt_list">
				<div id="Parse_Area2"gt;lt;/div>
			</section>
			
			<div>
			<%if(session_id!=null){//로그인 한 사람만 댓글을 남길 수 있다. %>
				<form method="post" action="commentWrite.comment" onsubmit="return checkContent(this)">
				<input type="hidden" id="id" name="id" value="<%=session_id%>">
				
				<input type="hidden" id="num" name="num" value="<%=reviewBean.getNum()%>">
				<textarea id="content" name="content" style="width:99.5%;height:100;border:1;overflow:visible;text-overflow:ellipsis;" cols="130" rows="5"></textarea>
				<input type="submit" value="등록">
				</form>
			</div>
			
			<%} %>

		</section>
		</div>
		<section class="center">
		<%if(session_id!=null && session_id.equals(request_id)){//세션 아이디가 null이 아니고 글 작성자와 같은 경우에만 수정과 삭제가 뜨도록 %>
		<input type="button" onclick="javascript:modifyForm()" value="수정">
		<input type="button" onclick="javascript:deleteForm()" value="삭제">
			<a href="reviewWriteForm.review">[글쓰기]</a>
		<%}else{ %>
		<a href="reviewWriteForm.review">[글쓰기]</a>
		<%} %> 
		</section>
</article>
<div id="Parse_Area"gt;lt;/div>
</body>
</html>