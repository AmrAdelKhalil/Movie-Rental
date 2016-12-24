<%-- 
    Document   : UpdateSettings
    Created on : Dec 21, 2016, 1:48:31 PM
    Author     : amr
--%>

<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en-US" xmlns="http://www.w3.org/1999/xhtml" dir="ltr">
<head>
	<title>Setting</title>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" href="/Movie-Rental/assets/css/UpdateSettings.css" type="text/css" media="all" />
	<!--[if IE 6]>
		<link rel="stylesheet" href="css/ie6.css" type="text/css" media="all" />
	<![endif]-->
	<script type="text/javascript" src="/Movie-Rental/assets/js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="/Movie-Rental/assets/js/jquery-func.js"></script>
</head>
<body>
<!-- Shell -->
<%HashMap<String, String> user = (HashMap<String, String>)request.getAttribute("user");%>
<div id="shell">
	<!-- Header -->
	<div id="header">
		<!-- <h1 id="logo"><a href="#">Movie Hunter</a></h1> -->
		<div class="social">
				<br>
				<a class="user-data" href="#"> <%user.get("name");%></a>
				<a class="user-data" href="#"> Settings</a>
				<a class="user-data" href="#"> Logout</a>
		</div>
		
		
		<!-- Navigation -->
		<div id="navigation">
			
			
			
			<ul>
			    <li><a class="active" href="#">HOME</a></li>
			    <li><a href="#">NEWS</a></li>
			    <li><a href="#">IN THEATERS</a></li>
			    <li><a href="#">COMING SOON</a></li>
			    <li><a href="#">CONTACT</a></li>
			    <li><a href="#">ADVERTISE</a></li>
			</ul>
		</div>
		<!-- end Navigation -->
		
		<!-- Sub-menu -->
		<div id="sub-navigation">
			<ul>
			    <li><a href="#">SHOW ALL</a></li>
			    <li><a href="#">LATEST TRAILERS</a></li>
			    <li><a href="#">TOP RATED</a></li>
			    <li><a href="#">MOST COMMENTED</a></li>
			</ul>
			<div id="search">
				<form action="home_submit" method="get" accept-charset="utf-8">
					<label for="search-field">SEARCH</label>					
					<input type="text" name="search field" value="Enter search here" id="search-field" title="Enter search here" class="blink search-field"  />
				</form>
			</div>
		</div>
		<!-- end Sub-Menu -->
		
	</div>
	<!-- end Header -->
	
	<!-- Main -->
	<div id="main">
		<!-- Content -->
		<div id="content">

			
			
			<!-- Box -->
			<div class="box">
				<br>
				<h1> Settings </h1>
				<br>
                                 
                                <form method="post" action="/Movie-Rental/UpdateSettings">
                                    <input type="text" name="id" value="<%=user.get("id")%>" style="display: none;">
                                    <label > Name:  </label>
                                    <input type="text" name="name" value="<%=user.get("name")%>" >

                                    <label > Email:  </label>
                                    <input type="text" name="email" value="<%=user.get("email")%>" >

                                    <label > Password:  </label>
                                    <input type="password" name="password" placeholder="************" >

                                    <label > New Password:  </label>
                                    <input type="password" name="newPassword" placeholder="" >

                                    <label > Credit Card:  </label>
                                    <input type="text" name="creditCard" value="<%=user.get("creditCard")%>" >
                                    <br>

                                    <input type="submit" name="submit" value="Update Info">
                                </form>
				
				
				
				
				
				
			</div>
			<!-- end Box -->
			
		</div>
		<!-- end Content -->

		
	</div>
	<!-- end Main -->

	<!-- Footer -->
	<div id="footer">
		<p>
			<a href="#">HOME</a> <span>|</span>
			<a href="#">NEWS</a> <span>|</span>
			<a href="#">IN THEATERS</a> <span>|</span>
			<a href="#">COMING SOON </a> <span>|</span>
			<a href="#">LATERS TRAILERS</a> <span>|</span>
			<a href="#">TOP RATED TRAILERS</a> <span>|</span>
			<a href="#">MOST COMMENTED TRAILERS</a> <span>|</span>
			<a href="#">ADVERTISE</a> <span>|</span>
			<a href="#">CONTACT </a>
		</p>
		<p> &copy; 2009 Movie Hunter, LLC. All Rights Reserved.  Designed by <a href="http://chocotemplates.com" target="_blank" title="The Sweetest CSS Templates WorldWide">ChocoTemplates.com</a></p>
	</div>
	<!-- end Footer -->
</div>
<!-- end Shell -->
</body>
</html>
