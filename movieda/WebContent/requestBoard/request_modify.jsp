<%@ page import="request.vo.RequestBean" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:include page="../menu/menu2.jsp"></jsp:include>
<jsp:include page="../menu/board_side.jsp"></jsp:include>
<%
String id=(String)session.getAttribute("id");
if(id==null) {	
	response.setContentType("text/html;charset=UTF-8");
	out.println("<script>");
	out.println("alert('로그인이 필요합니다.')");
	out.println("location.href='loginForm.member'");
	out.println("</script>");
}else{

%>
<%
	RequestBean requestBean = (RequestBean)request.getAttribute("requestBean");
	String nowPage = (String) request.getAttribute("page");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function modifyboard(){
		modifyform.submit();
	}
</script>
<style>
section{
width:100%;
height:100%;
margin : 0 auto;
}

table{
width:100%;
margin:auto;
}
div.button{

   margin: auto;
   width: 50%;

}
</style>

<script type="text/javascript" src="./SE/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.9.0.min.js"></script>
<script type="text/javascript">
var oEditors = [];
$(function(){
      nhn.husky.EZCreator.createInIFrame({
          oAppRef: oEditors,
          elPlaceHolder: "content", //textarea에서 지정한 id와 일치해야 합니다. 
          //SmartEditor2Skin.html 파일이 존재하는 경로
          sSkinURI: "./SE/SmartEditor2Skin.html",  
          htParams : {
              // 툴바 사용 여부 (true:사용/ false:사용하지 않음)
              bUseToolbar : true,             
              // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
              bUseVerticalResizer : true,     
              // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
              bUseModeChanger : true,         
              fOnBeforeUnload : function(){
                   
              }
          }, 
          fOnAppLoad : function(){
              //기존 저장된 내용의 text 내용을 에디터상에 뿌려주고자 할때 사용
              oEditors.getById["content"].exec("PASTE_HTML", [""]);
          },
          fCreator: "createSEditor2"
      });
      
      //저장버튼 클릭시 form 전송
      $("#save").click(function(){
          oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
          $("#modifyform").submit();
      });    
});

function pasteHTML(filepath){
    var sHTML = '<img src="<%=request.getContextPath()%>/path에서 설정했던 경로/'+filepath+'">';
    oEditors.getById["textAreaContent"].exec("PASTE_HTML", [sHTML]);
}
</script>
</head>
<body>
<!-- 게시판 등록 -->
<section>
<h2 style="text-align:center">REQUEST MODIFY</h2>
<form action="requestModifyPro.request?page=<%=request.getParameter("page") %>" method="post" id="modifyform">
<input type="hidden" name="num" value="<%=requestBean.getNum() %>"/>
<table style="width:800px;">
<tr>
		<td>
			제목 : <input type="text" name="subject" id="subject" value="<%=requestBean.getSubject()%>"/>
		</td>
	</tr>
	<tr>
		<td >
			<textarea id = "content" name="content" rows="10" cols="100"><%=requestBean.getContent() %>
			</textarea>
		</td>
	</tr>
</table>
	<div class="button">
		<input type="button" id="save" value="수정">&nbsp;&nbsp;
		<input type="reset" value="다시 쓰기"/>

	</div>
</form>
</section>
</body>
</html>
<%}%>