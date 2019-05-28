<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="member.vo.MemberBean" %>
    <%

	MemberBean memberBean = (MemberBean)request.getAttribute("memberBean");
	String nowPage = (String) request.getAttribute("page");

%>
<jsp:include page="../adminMenu/adminMenu.jsp"/>
<jsp:include page="../adminMenu/admin_side.jsp"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MODIFY</title>
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

<style>
</style>
</head>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
//다음 주소 API
function daumpostcod() {
	
new daum.Postcode({
    oncomplete: function(data) {
    	  // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
    	 

     
        document.getElementById('postcode').value = data.zonecode; //5자리 새우편번호 사용
        
        if(data.userSelectedType !=("R")){
        document.getElementById('address1').value = data.jibunAddress;
        }else{
        document.getElementById('address1').value = data.roadAddress;;
        }
    }
}).open();
}
</script>
<body>
<form action="./modifypro.admin" method="post" name="modify">
<h2>MEMBER MODIFY</h2>
<article>
<input type="hidden" name="id" id="id" value="<%=memberBean.getId() %>"/>
<div class="tbox">
			<input type="text" name="name" id="name" value="<%=memberBean.getName() %>" />
	</div>
	<div class="tbox">
			<input type="text" name="postcode" id="postcode" value="<%=memberBean.getPostcode() %>"/>
	</div>
	<input class="btn" type="button" value="POST SEARCH" onclick="daumpostcod()">
	<div class="tbox">
			<input type="text" name="address1" id="address1" value="<%=memberBean.getAddress1() %>"/>
	</div>
	<div class="tbox">
			<input type="text" name="address2" id="address2" value="<%=memberBean.getAddress2() %>"/>
	</div>
	<div class="tbox">
			<input type="text" name="phone" id="phone" value="<%=memberBean.getPhone() %>" />
	</div>	
	<div class="tbox">
			<input type="text" name="email" id="email" value="<%=memberBean.getEmail() %>"/>
	</div>
			<input class="btn" type="submit" value="수정">
			<input class="btn" type="button" value="초기화" onclick="javascript:modify.rest()">
			<input class="btn" type="button" value="뒤로" onclick="javascript:history.go(-1)">
</article>
</form>
</body>
</html>