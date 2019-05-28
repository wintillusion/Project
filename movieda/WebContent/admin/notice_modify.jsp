<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ page import="notice.vo.NoticeBean" %>
<jsp:include page="../adminMenu/adminMenu.jsp"/>
<jsp:include page="../adminMenu/admin_side.jsp"/>


    <%	
    	
		String nowPage = (String) request.getAttribute("page");
		if ((session.getAttribute("id") == null) || (!((String) session.getAttribute("id")).equals("admin"))) {
		out.println("<script>");
		out.println("alert('관리자로 로그인해주세요.')");
		out.println("location.href='loginForm.member'");
		out.println("</script>");
	}else{
    String id=(String)session.getAttribute("id");
    NoticeBean noticeBean = (NoticeBean)request.getAttribute("noticeBean");

    int num=noticeBean.getNum();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AUCTION WRITE</title>
</head>
<style>
	
	h2{
		text-align:center;
	}
	
	table{
		margin:0 auto;
		width:800px;
	}
	
	div.button{

   margin: auto;

   width: 30%;
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
          $("#noticemodifyPro").submit();
      });    
});

function pasteHTML(filepath){
    var sHTML = '<img src="<%=request.getContextPath()%>/path에서 설정했던 경로/'+filepath+'">';
    oEditors.getById["textAreaContent"].exec("PASTE_HTML", [sHTML]);
}



</script>
<body>
	<section>
		<h2>NOTICE MODIFY</h2>
		<form action="noticeModifyPro.admin" method="post"id="noticemodifyPro"name="noticemodifyPro">
					
					<input type="hidden" name="writer" value="<%=id %>">
					<input type="hidden" name="num" value="<%=num%>">
						<table style="width:1000px">
				<tr>
					<td><label for="subject">제목</label></td>
					<td ><input type="text" name="subject" id="subject" value="<%=noticeBean.getSubject()%>" required="required"/></td>
				</tr>
				<tr>
					<td><label for="content">내용</label></td>
					<td><textarea id="content" name="content"cols="40" rows="15" required="required" style="width:766px; height:412px;">
					<%=noticeBean.getContent()%>
					</textarea></td>
				</tr>
				
				<tr>
					<td><input type="hidden" name="auction_code" id="auction_code" value="0"></td>
				</tr>

			</table>
			<div class="button">
				<input type="submit" name="save" id="save" value="공지 수정">&nbsp;&nbsp;
				<input type="reset" value="다시 쓰기"/>
			</div>
			
		</form>
	</section>

</body>
</html>
<%}%>