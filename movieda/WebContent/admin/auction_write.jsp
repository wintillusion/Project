<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<jsp:include page="../adminMenu/adminMenu.jsp"/>
<jsp:include page="../adminMenu/admin_side.jsp"/>

    <%
		if ((session.getAttribute("id") == null) || (!((String) session.getAttribute("id")).equals("admin"))) {
		out.println("<script>");
		out.println("alert('관리자로 로그인해주세요.')");
		out.println("location.href='loginForm.member'");
		out.println("</script>");
	}else{
    String id=(String)session.getAttribute("id");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AUCTION WRITE</title>
</head>
<style>
body{
height:700px;
}
	
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
          $("#auctionform").submit();
      });    
});

function pasteHTML(filepath){
    var sHTML = '<img src="<%=request.getContextPath()%>/path에서 설정했던 경로/'+filepath+'">';
    oEditors.getById["textAreaContent"].exec("PASTE_HTML", [sHTML]);
}

function dateCheck(f){
	 var today = new Date();
	 var eDate = null;
	 
	var year = f.edate.value.substr(0,4);
	 var month = f.edate.value.substr(4,2);
	 var day = f.edate.value.substr(6,2);
	 var eDate = new Date(year, month, day);

/* 	alert(today);
	alert("year : " + year);
	alert("month : " + (month-1));		
	alert("day : " + day);
	alert(eDate); */
	 
	if (eDate.getTime()<today.getTime()) {
		alert('날짜를 확인해주세요.');	
		f.edate.focus();
		return false;
	}
}  
</script>
<body>
	<section>
		<h2>AUCTION REGIST</h2>
		<form action="auctionWritePro.admin" method="post" enctype="multipart/form-data" id="auctionform"name="auctionform" onsubmit="return dateCheck(this)">
						<table style="width:1000px;">
				<tr>
					<td><input type="hidden" name="writer" id="writer" value="<%=id %>"></td>
					<td><input type="hidden" name="mem_id" id="mem_id" value="<%=id %>"></td>
				</tr>
				<tr>
					<td><label for="subject">제목</label></td>
					<td ><input type="text" name="subject" required="required"/></td>
				</tr>
				<tr>
					<td ><label for="edate">종료일</label></td>
					<td ><input type="text" name="edate" required="required" placeholder="@EX)20190301"/></td>
				</tr>
				<tr>
					<td><label for="edate">시작가</label></td>
					<td><input type="text" name="fstmoney" required="required"/>원</td>
				</tr>
				<tr>
					<td><label for="product">상품명</label></td>
					<td><input type="text" name="product" id="product" required="required"/></td>
					<td><input type="hidden" name="stock_code" id="stock_code" value="0"/></td>						
				</tr>
				
				<tr>
					<td><label for="content">내용</label></td>
					<td><textarea id="content" name="content" cols="40" rows="15" required="required" style="width:766px; height:412px;">
					</textarea></td>
				</tr>
				
				<tr>
					<td><input type="hidden" name="auction_code" id="auction_code" value="0"></td>
				</tr>

			</table>
			<div class="button">
				<input type="file" name="file" id="file">
				<input type="submit" name="save" id="save" value="경매등록">&nbsp;&nbsp;
				<input type="reset" value="다시 쓰기"/>
				
			</div>
			
		</form>
	</section>

</body>
</html>
<%}%>