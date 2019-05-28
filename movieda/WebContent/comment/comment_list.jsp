<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="comment.vo.CommentBean" %>
<%@ page import="request.vo.PageInfo" %>
<%@ page import="java.util.*" %>
<% ArrayList<CommentBean> commentList=(ArrayList<CommentBean>)request.getAttribute("commentList");
	PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");	//pageInfo로 형변환.
//	if(pageInfo!=null)	//null이 아닐때. 이페이지를 바로 열거나 그러지 않는 이상은 null이 아님.
	int listCount=pageInfo.getListCount();
	int nowPage=pageInfo.getPage();		//nowPage로 한 이유는? 현재 page라는 객체가 존재. 그래서 nowPage로.
	int maxPage=pageInfo.getMaxPage();
	int startPage=pageInfo.getStartPage();
	int endPage=pageInfo.getEndPage();
	//System.out.println(commentList);
	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Comment</title>
</head>
<style>
table{
	border: 1px solid;
	width : 100%;
}
</style>
<body>
<table>
<%for(int i=0; i<commentList.size();i++){ %>
<tr><td><%=commentList.get(i).getMem_id()%></td><td><%=commentList.get(i).getContent() %></td>
<td><%=commentList.get(i).getDate() %></td></tr>
<%} %>
</table>
</body>
</html>