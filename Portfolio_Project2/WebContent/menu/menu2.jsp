<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MOVIDA</title>
	  <link rel="stylesheet" href="style.css">
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
      <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<style>
html, body {
      margin: 0;
      padding: 0;
      width: 100%;
}


body {
      font-family: "Helvetica Neue",sans-serif;
      font-weight: lighter;
     
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
      width: 100%;
      line-height: 60px;
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
      padding: 16px 40px;;
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
      box-sizing: border-box;
      padding: 15px 24px;
      cursor: pointer;
      color: #fff;
      display: none;
}

  a { text-decoration: none; color: white; }
    a:visited { text-decoration: none; }

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
            max-height: 34em;
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

/* nav ul li a.active {
		background: #e2472f;
		color : #fff;
		border-radius :6px;
	}
 */
	
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
                             <a href="main.jsp">MOVIEDA</a>
                        </div>

                        <div class="menu">
                              <ul>
                                    <li><a href="#">REQUEST</a></li>
                                    <li><a href="#">REVIEW</a></li>
                                    <li><a href="#">COMMUNITY</a></li>
                                    <li><a href="#">RANKING</a></li>
 <%
    String id=null;
    if(session.getAttribute("id")!=null){
    	id=(String)session.getAttribute("id");    	

    %>
                                    <li><a href="./member_info.member"><%=id %>님</a></li>
                                    <li><a href="./logout.member">LOGOUT</a></li>
<%}else{ %>
									<li><a href="./loginForm.member">LOGIN</a></li>
									
<%} %>
                              </ul>
                        </div>
                  </nav>
			</header>
      </div>
</body>
</html>
