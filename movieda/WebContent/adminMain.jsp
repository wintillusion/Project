<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/adminMenu/adminMenu.jsp"/>
<%@page import="java.util.*" %>
<%@page import="member.vo.MemberBean" %>
<%@page import="auction.vo.AuctionBean" %>
<%@page import="request.vo.RequestBean" %>
<%@page import="review.vo.ReviewBean" %>
<%@page import="notice.vo.NoticeBean" %>
<%@page import="member.vo.MemberBean" %>
<%@page import="dao.MemberDAO" %>
<%
if ((session.getAttribute("id") == null) || (!((String) session.getAttribute("id")).equals("admin"))) {
		out.println("<script>");
		out.println("alert('관리자로 로그인해주세요.')");
		out.println("location.href='loginForm.member'");
		out.println("</script>");
	}

ArrayList<AuctionBean> auctionList=(ArrayList<AuctionBean>)request.getAttribute("auctionList");
ArrayList<RequestBean> requestList=(ArrayList<RequestBean>)request.getAttribute("requestList");
ArrayList<ReviewBean> reviewList=(ArrayList<ReviewBean>)request.getAttribute("reviewList");
ArrayList<NoticeBean> noticeList=(ArrayList<NoticeBean>)request.getAttribute("noticeList");
ArrayList<MemberBean> memberList=(ArrayList<MemberBean>)request.getAttribute("memberList");


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
			width: 500px;
			height: 300px;
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

</style>
<script>
/* $(document).ready(function(){
	
    $.ajax({
        type : "GET", //전송방식을 지정한다 (POST,GET)
        url : "mainMember.admin?type=2",//호출 URL을 설정한다. GET방식일경우 뒤에 파라티터를 붙여서 사용해도된다.
        dataType : "html",//호출한 페이지의 형식이다. xml,json,html,text등의 여러 방식을 사용할 수 있다.
        error : function(){
            //alert("통신실패!!!!");
        },
        success : function(Parse_data){
            $("#Parse_Area").html(Parse_data); //div에 받아온 값을 넣는다.
            //alert("통신 데이터 값 : " + Parse_data); 
        }
         
    });
}); */

</script>
</head>
<body>

<section>
<article style="margin-left: 2.083%">  <!-- 첫번째 2.083% 띄우도록되어있으니까 맞춰줘야 함. -->
  <h3>ACUTION 관리</h3>
   <table>
  <tr>
  <% for(int i=0;i<auctionList.size();i++){ //사이즈크기만큼 찍어줌%>
					<tr><td><a style="color:black" href="auctionDetail.admin?num=<%=auctionList.get(i).getNum() %>#begin">
					경매이름 : <%=auctionList.get(i).getSubject() %>&nbsp; | 현재금액 : <%=auctionList.get(i).getInstmoney() %>	
					| 입찰자 : <%=auctionList.get(i).getMem_id() %>	
					</a></td></tr>
  <%} %>

  </table>


</article>
<article>  
  <h3>REQUEST 관리</h3>
    <table>
  <tr>
 <% for(int i=0;i<requestList.size();i++){ //사이즈크기만큼 찍어줌%>
					<tr><td><a style="color:black" href="requestDetail.admin?num=<%=requestList.get(i).getNum() %>#begin">
					글 : <%=requestList.get(i).getSubject() %>
					</a></td></tr>
  <%} %>

  </table>

</article>
<article>  
  <h3>REVIEW 관리</h3>
  <table>
  <tr>
  <% for(int i=0;i<reviewList.size();i++){ //사이즈크기만큼 찍어줌%>
					<tr><td><a style="color:black" href="reviewDetail.admin?num=<%=reviewList.get(i).getNum() %>#begin">
					글 : <%=reviewList.get(i).getSubject() %>
				
					</a></td></tr>
  <%} %>

  </table>
  
  
</article>
<article style="margin-left:2.083%">  
  <h3><a style="color:black;" href="list.admin">MEMBER 관리</a></h3>
  <table>
  <tr>
  <% for(int i=0;i<memberList.size();i++){ //사이즈크기만큼 찍어줌%>
					<tr><td><a style="color:black" href="member_info.admin?id=<%=memberList.get(i).getId() %>"><%=memberList.get(i).getId() %></a></td>
  								<td><a style="color:black" href="modifyForm.admin?id=<%=memberList.get(i).getId() %>">수정</a>
								<a style="color:red" href="delete.admin?id=<%=memberList.get(i).getId() %>">삭제</a></td>
								</tr>
  <%} %>

  </table>
</article>

<article>
  <h3>NOTICE 관리</h3>
    <table>
  <tr>
  <% for(int i=0;i<noticeList.size();i++){ //사이즈크기만큼 찍어줌%>
					<tr><td><a style="color:black" href="noticeDetail.admin?num=<%=noticeList.get(i).getNum() %>#begin">
					<%=noticeList.get(i).getSubject() %>&nbsp;
					
					</a></td></tr>
  <%} %>

  </table>
  
</article>
<article>  
  <h3>FREE 관리</h3>
  <table>
  <tr>
  <% for(int i=0;i<reviewList.size();i++){ //사이즈크기만큼 찍어줌%>
					<tr><td><a style="color:black" href="reviewDetail.admin?num=<%=reviewList.get(i).getNum() %>#begin">
					글 : <%=reviewList.get(i).getSubject() %>
				
					</a></td></tr>
  <%} %>

  </table>
  
  
</article>
 </section>
</body>
</html>