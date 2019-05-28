<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ID CHECK</title>
<style>
body{

	background : #333;
}
.tbox{
	width:260px;
	height:40px;
	
	background:#f1f1f1b3;
	border-radius : 10px;
	margin : 10px auto;
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
	color: #333;
}
.btn{
	width : 260px;
	height : 40px;
	background : #3498db;
	border-radius : 10px;
	margin : 14px auto;
	display : block;
	font-family : "Montserrat", sans-serif;
	font-weight : bold;	/* 글씨가 너무 작아서 bold로 처리. 나중에 뺄지말지 결정 */
	font-size : 16px;
	border : none;
	color : white;
}

h3{
	color : white;
	text-align : center;
	margin : auto;
	transition : 0.4s all;
}

a{
	text-decoration: none;
	text-align : center;
	color : #3498db;
	
}
</style>
</head>
	<c:choose>
		<c:when test="${passibleID==null }">
			<c:set var="openInit" value="true"/>
		</c:when>
		<c:otherwise>
			<c:set var="openInit" value="false"/>
		</c:otherwise>
	</c:choose>
<script>

function init(){
	if(${openInit}){
		document.getElementById("id").value=opener.document.getElementById("id").value;
	}
}
function ok(v){
	opener.idcheck=v.trim();
	opener.document.getElementById("id").value=v;
	opener.checkId=true;
	window.close();
}

</script>
<body onload="init()">
<form action="idCheck.member" method="post" name=f>
	<input class="tbox" type="text" name="id" id="id">
	<input class="btn" type="submit" value="중복확인">
</form>
<c:if test="${passibleID != null }">
<c:choose>
	<c:when test="${passibleID }">
		<h3>${id }는 사용가능한 아이디입니다. <a href='#' onclick="ok('${id }')">사용하기</a></h3>
	</c:when>
	<c:otherwise><h3>${id }는 사용불가능한 아이디입니다. 다시 검색하세요.</h3></c:otherwise>
</c:choose>
</c:if>
</body>
</html>