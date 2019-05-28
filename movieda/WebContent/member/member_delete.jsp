<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../menu/menu2.jsp" flush="false"/>
<jsp:include page="../menu/member_side2.jsp" flush="false"/>
<%@ page import="member.vo.MemberBean" %>
<%
    String id=null;
    if(session.getAttribute("id")!=null){
    	id=(String)session.getAttribute("id");
    	
    }else {
    	out.println("<script>");
    	out.println("alert('로그인을 해주세요!!')");
    	out.println("location.href='./loginForm.member'");//
    	out.println("</script>");
    }
    MemberBean memberBean = (MemberBean)request.getAttribute("memberBean");
	String nowPage = (String) request.getAttribute("page");
//	System.out.println("deleteForm session ID: " + id);
//	System.out.println("memberBean Delete ID : " + memberBean.getId());
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MEMBER DELETE</title>
<style>

html, body {
      margin: 0;
      padding: 0;
      width: 100%;
      background-color : #e0e0e0;
}


 article {
			width: 55%;
			height: auto;
			margin-left: 21.95%;
			margin-bottom: 20px;
			margin-top: 40px;
			padding: 0.5%;
			border: 1px dotted #333;
			border-radius: 10px;
			background-color : #fff;
			opacity: 0.85;
		}

h2{
	font-size : 20px;
	font-weight: bold;
	text-align : center;
	font-family : "Montserrat", sans-serif;
}

.tbox{
	width:260px;
	height:40px;
	background:#f1f1f1b3;
	border-radius : 10px;
	margin : 10px auto;
	position: relative;
}

.tbox input{
	background: none;
	border : none;
	outline : none;
	text-align : center;
	width:90%;
	line-height : 37px;
	font-family : "Montserrat", sans-serif;
	font-size : 14px;
	color: #333;	/* 있으나 없으나?; */
}

.btn{
	width : 260px;
	height : 40px;
	background : #e2472f;
	border-radius : 10px;
	margin : 14px auto;
	display : block;
	font-family : "Montserrat", sans-serif;
	font-weight : bold;	/* 글씨가 너무 작아서 bold로 처리. 나중에 뺄지말지 결정 */
	font-size : 16px;
	border : none;
	color : white;
	cursor : pointer;
}


</style>
 <script type="text/javascript">
   	 function CheckForm(){
        
        //체크박스 체크여부 확인 [하나]
        var chk1=document.deleteForm.delete_agreement.checked;
        
        if(!chk1){
            alert('동의를 눌러주세요!');
            return false;
    	}
    }
</script>
</head>
<body>
<form action="delete.member?id=<%=id%>" name="deleteForm" onsubmit="return CheckForm(this)">
<h2>회원 탈퇴 안내</h2>
<article>
<input type="hidden" name="id" id="id" value="<%=id%>">		<!-- action의 id 값이 아닌 hidden의 id 값이 넘어감. -->
	사용하고 계신 <%=id%>는 탈퇴할 경우 재사용 및 복구가 불가능합니다. 부디 신중하게 선택해주세요.
	<br>
	탈퇴 후 회원정보 및 서비스 이용 기록은 모두 삭제가 됩니다.
	삭제되는 내용을 확인해주세요.
<br>
<input type="checkbox" name="delete_agreement" id="delete_agreement">
안내 사항을 모두 확인하였으며, 이에 동의합니다.
<br>
<input type="submit" value="확인"> 
</article>
</form>
</body>
</html>