<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="notice.vo.PageInfo" %>
<%@page import="notice.vo.NoticeBean" %>
<%@page import="java.util.*" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../adminMenu/adminMenu.jsp"/>
<jsp:include page="../adminMenu/admin_side.jsp"/>
<%
	ArrayList<NoticeBean> noticeList=(ArrayList<NoticeBean>)request.getAttribute("noticeList");	//사용하기 위해서 형변환을 시켜줘야함.
	PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");	//pageInfo로 형변환.
	int listCount=pageInfo.getListCount();
	int nowPage=pageInfo.getPage();		//nowPage로 한 이유는? 현재 page라는 객체가 존재. 그래서 nowPage로.
	int maxPage=pageInfo.getMaxPage();
	int startPage=pageInfo.getStartPage();
	int endPage=pageInfo.getEndPage();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>REQUEST LIST</title>
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
<script>
function noticeWrite(){
	location.href="noticeWriteForm.admin";
}
</script>
</head>
<body>
<h1 style="text-align:center">공지사항 관리</h1>
<table class="blueTable">
<thead>
<tr>
<th style="width : 5%;">번호</th>
<th style="width : 65%;">제목</th>
<th style="width : 10%;">작성자</th>
<th style="width : 5%;">조회</th>
<th style="width : 10%;">날짜</th>
</tr>
</thead>
<tfoot>
<tr>
<td colspan="5">
<div class="links">
	
	<%if(nowPage<=1){ //현재페이지가 1보다 작으면 이전이 안되도록.%>
		&laquo;&nbsp;
		
		<%}else{ %>
<a href="noticeList.admin?page=<%=nowPage-1 %>&searchOption=${sO}&searchWord=${sW}">&laquo;</a> 
<%} %><%for(int a=startPage;a<=endPage;a++){
			if(a==nowPage){%>
			<%=a %><!-- 현재값은 하이퍼링크 안걸리게 -->
			<%}else{ %>
				<a href="noticeList.admin?page=<%=a %>&searchOption=${sO}&searchWord=${sW}"><%=a %></a>&nbsp;
			<%} %>
		<%} //그다음 페이지를 다시 찍어주는 것. startpage부터 endpage까지 찍어준다.%>
		<%if(nowPage>=maxPage){ %>
		&raquo;<%//마지막 페이지라면 다음이 활성화 안되게. else 활성화되서 +1이 되도록. %>
		<%}else{ %>
<a href="noticeList.admin?page=<%=nowPage+1 %>&searchOption=${sO}&searchWord=${sW}">&raquo;</a>
		<%} %>
</div>
</td>
</tr>
<tr>
				<td colspan="6" align="right">
					<form method="post" action="noticeList.admin">
						<select name="searchOption">
							<option value="subject">글제목</option>
							<option value="mem_id">작성자</option>
						</select>
						<input type="text" name="searchWord" style="width:100px"/>
						<input type="submit" value="검색">
						<input type="button" onclick="javascript:noticeWrite()" value="공지 쓰기">
					</form>
				</td>
			</tr>
</tfoot>
<tbody>
<% for(int i=0;i<noticeList.size();i++){ //사이즈크기만큼 찍어줌%>
<tr>
<td style="text-align:center"><%=noticeList.get(i).getNum() %></td>
<td>

					<a style="color:black" href="noticeDetail.admin?num=<%=noticeList.get(i).getNum() %>&page=<%=nowPage %>#begin">
					<%=noticeList.get(i).getSubject() %>
					<%//상세보기를 할때 보드 넘버로 넘겨주기.index번호를 넘겨주는 것이기 때문에 당연. 페이지를 넘겨주는 이유는? 만약 현재 3페이지를 본다면?? 다시 목록보기를 했을때 3페이지로 돌아가야하기때문 %>
					</a>
</td>
<td style="text-align:center"><%=noticeList.get(i).getWriter() %></td>
<td style="text-align:center"><%=noticeList.get(i).getReadcount() %></td>
<td style="text-align:center"><%=noticeList.get(i).getDate()%></td>
</tr>
<%} %>
<tr>
</tr>
</tbody>
</table>
</body>
</html>