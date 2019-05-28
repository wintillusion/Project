<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MOVIEDA</title>
	  <!-- <link rel="stylesheet" href="style.css"> -->
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
      <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<style>
body {
      font-family: Quicksand;
      font-weight: lighter;
      margin : 0 ;
      padding : 0;
}

header {
		width: 100%;
		height: 50vh;
		background: url(img/menu.jpg) no-repeat 50% 50%;
   	    background-size: cover;
 	   
}


.logo {
      line-height: 60px;
      position: fixed;
      float: left;
      margin: 16px 46px;
      color: #fff;
      font-weight: bold;
      font-size: 25px;
      letter-spacing: 2px;
      
}

nav {
      position: fixed;
      top : 0;
      left : 0;
      width: 100%;
      height : 100px;
      line-height: 60px;
     /*  box-sizing: border-box; */
      
}

nav ul {
      line-height: 60px;
      list-style: none;
      background: rgba(0, 0, 0, 0);
      overflow: hidden;
      color: #fff;
      padding: 0;
      text-align: right;
      margin: 0;
      padding-right: 40px;
      transition: 1s;
}

nav.black ul {
      background: #000;
}

nav ul li {
      display: inline-block;
      padding: 16px 40px;
}

nav ul li a {
      text-decoration: none;
      color: #fff;
      font-size: 16px;
      font-weight:bold;
}

.menu-icon {
      line-height: 60px;
      width: 100%;
      background: #000;
      text-align: right;
      /* box-sizing: border-box; */
      padding: 15px 24px;
      cursor: pointer;
      color: #fff;
      display: none;
     
}

  a { text-decoration: none; color: white; }
    a:visited { text-decoration: none; }
    
.hide ul ul{
	display:none;
	float:none;
}

.hide ul li:hover ul{
display:block;
}

nav ul li a.active {
		background: #e2472f;
		color : #fff;
		border-radius : 4px;
		
		
	}

 @media(max-width: 786px) {

      .logo {
            position: fixed;
            top: 0;
            margin-top: 16px;
      }

      nav ul {
            max-height: 0px;
            background: #000;
      }

      nav.black ul {
            background: #000;
      }

      .showing {
            max-height: 50em;
      }

      nav ul li {
            box-sizing: border-box;
            width: 100%;
            padding: 15px;
            text-align: center;
      }

      .menu-icon {
            display: block;
      }

}
 
	
	</style>
	
	
	
	
	<script type="text/javascript">

      // Menu-toggle button

      $(document).ready(function() {
            $(".menu-icon").on("click", function() {
                  $("nav ul").toggleClass("showing");
            });
      });

      // Scrolling Effect

      $(window).on("scroll", function() {
            if($(window).scrollTop()) {
                  $('nav').addClass('black');
            }

            else {
                  $('nav').removeClass('black');
            }
      })


      </script>
</head>
<body>
	  <div class="wrapper">
			<header>
                  <nav>
                        <div class="menu-icon">
                              <i class="fa fa-bars fa-2x"></i>
                        </div>

                        <div class="logo">
                             <a href="main.main">MOVIEDA</a>
                        </div>

                        <div class="menu">
                        
                              <ul>
                              		<li><a style="color:#e2472f" href="auctionList.auction">AUCTION</a></li>
                                    <li><a href="requestList.request">REQUEST</a></li>
                                    <li><a href="reviewList.review">REVIEW</a></li>
                                    <li><a href="noticeList.notice">NOTICE</a></li>
                                   
 <%
    String id=null;
    if(session.getAttribute("id")!=null){
    	id=(String)session.getAttribute("id");    	
		//System.out.println("Menu Session : " + id);
    %>
                                    <li><a href="member_main.member?id=<%=id%>"><%=id %>님</a></li>
                                    
                                    <li><a class="active" href="logout.member">LOGOUT</a></li>
<%}else{ %>
									<li><a class="active" href="loginForm.member">LOGIN/SIGN</a></li>
									
<%} %>
                              </ul>
                        </div>
                  </nav>
			</header>
      </div>
</body>
</html>
