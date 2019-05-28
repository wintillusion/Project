<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<!DOCTYPE html>
<html>
 <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<head>
<meta charset="UTF-8">
<title>MemberSideMenu</title>
  <style>



#cssmenu,
#cssmenu ul,
#cssmenu li,
#cssmenu a {
  margin: 0;
  padding: 0;
  border: 0;
  list-style: none;
  font-weight: normal;
  text-decoration: none;
  line-height: 1;
  font-family: 'Open Sans', sans-serif;
  font-size: 14px;
  position: relative;
  font-weight : bold;
}
#cssmenu a {
  line-height: 1.3;
}
#cssmenu {
  width: 250px;
}
#cssmenu > ul > li > a {
  padding-right: 40px;
  font-size: 25px;
  font-weight: bold;
  display: block;
  background: #e2472f;
  color: #ffffff;
  border-bottom: 1px solid #5e071b;
  text-transform: uppercase;
  
}
#cssmenu > ul > li > a > span {
  background: #333;	/* 기본 메뉴색 */
  padding: 10px;
  display: block;
  font-size: 13px;
  font-weight: 300;
}
#cssmenu > ul > li > a:hover {
  text-decoration: none;
}
#cssmenu > ul > li.active {
  border-bottom: none;
}
#cssmenu > ul > li.active > a {
  color: #fff;
}
#cssmenu > ul > li.active > a span {
  background: #e2472f;
}
#cssmenu span.cnt {
  position: absolute;
  top: 8px;
  right: 15px;
  padding: 0;
  margin: 0;
  background: none;
}
/* Sub menu */
#cssmenu ul ul {
  display: none;
}
#cssmenu ul ul li {
  border: 1px solid #e0e0e0;
  border-top: 0;
}
#cssmenu ul ul a {
  padding: 10px;
  display: block;
  color: #ed1144;
  font-size: 13px;
}
#cssmenu ul ul a:hover {
  color: #bd0e36;
}
#cssmenu ul ul li.odd {
  background: #f4f4f4;
}
#cssmenu ul ul li.even {
  background: #fff;
}


#cssmenu{
position: absolute;
top : 50%;
}


  #floatMenu{
  position: relative;
  }


</style>
<script>
( function( $ ) {
	$( document ).ready(function() {
	$(document).ready(function(){

	$('#cssmenu > ul > li ul').each(function(index, e){
	  var count = $(e).find('li').length;
	  var content = '<span class=\"cnt\">' + count + '</span>';
	  $(e).closest('li').children('a').append(content);
	});
	$('#cssmenu ul ul li:odd').addClass('odd');
	$('#cssmenu ul ul li:even').addClass('even');
	$('#cssmenu > ul > li > a').click(function() {
	  $('#cssmenu li').removeClass('active');
	  $(this).closest('li').addClass('active');	
	  var checkElement = $(this).next();
	  if((checkElement.is('ul')) && (checkElement.is(':visible'))) {
	    $(this).closest('li').removeClass('active');
	    checkElement.slideUp('normal');
	  }
	  if((checkElement.is('ul')) && (!checkElement.is(':visible'))) {
	    $('#cssmenu ul ul:visible').slideUp('normal');
	    checkElement.slideDown('normal');
	  }
	  if($(this).closest('li').find('ul').children().length == 0) {
	    return true;
	  } else {
	    return false;	
	  }		
	});

	});

	});
	} )( jQuery );
	
$(document).ready(function() {

	 // 기존 css에서 플로팅 배너 위치(top)값을 가져와 저장한다.
	 var floatPosition = parseInt($("#floatMenu").css('top'));
	 // 250px 이런식으로 가져오므로 여기서 숫자만 가져온다. parseInt( 값 );

	 $(window).scroll(function() {
	  // 현재 스크롤 위치를 가져온다.
	  var scrollTop = $(window).scrollTop();
	  var newPosition = scrollTop + floatPosition + "px";

	  /* 애니메이션 없이 바로 따라감
	   $("#floatMenu").css('top', newPosition);
	   */

	  $("#floatMenu").stop().animate({
	   "top" : newPosition
	  }, 300);

	 }).scroll();

	});

</script>
</head>
<body>
<div id="floatMenu">
<div id='cssmenu'>
<ul>
   <li class='active'><a href='main.main'><span>HOME</span></a></li>
   <li><a href='auctionList.auction'><span>AUCTION</span></a></li>
   <li><a href='requestList.request'><span>REQEUST</span></a></li>
   <li><a href='reviewList.review'><span>REVIEW</span></a></li> 
   <li><a href='noticeList.notice'><span>NOTICE</span></a></li>
   <li class='last'><a href='#'><span>ABOUT MOVIEDA</span></a></li>
</ul>
</div>
</div>
</body>
</html>