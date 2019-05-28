<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MOVIDA</title>
	  <!-- <link rel="stylesheet" href="style.css"> 스타일 따로 옮기기-->
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
      <script src="https://code.jquery.com/jquery-3.3.1.js"></script> <!-- jquery쓸때 적어줘야함. -->
<style>


body {
      font-family: "Helvetica Neue",sans-serif;
      font-weight: lighter;
}

header {
      width: 100%;
      height: 5vh;

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
      box-sizing: border-box;
      padding: 15px 24px;
      cursor: pointer;
      color: #fff;
      display: none;
}

  nav a { text-decoration: none; color: white; }
    nav a:visited { text-decoration: none; }
   
    


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
            padding: 24px;
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
                              <a href="main.main">MOVIEDA</a>
                        </div>

                        <div class="menu">
                              <ul>
                              		<li><a style="color:#e2472f" href="auctionList.auction">AUCTION</a></li>
                                    <li><a href="requestList.request">REQUEST</a></li>
                                    <li><a href="reviewList.review">REVIEW</a></li>
                                    <li><a href="noticeList.notice">NOTICE</a></li>
                                   
                              </ul>
                        </div>
                  </nav>
			</header>
      </div>
</body>
</html>