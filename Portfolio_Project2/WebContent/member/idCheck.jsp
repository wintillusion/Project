<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
<form action="../idCheck.member" method="post" name=f>
	<input type="text" name="id" id="id">
	<input type="submit" value="중복확인">
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