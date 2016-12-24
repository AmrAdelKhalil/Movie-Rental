<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en-US" xmlns="http://www.w3.org/1999/xhtml" dir="ltr">
<head>
	<title>Free CSS template by ChocoTemplates.com</title>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" href="/Movie-Rental/assets/css/ShowMovie.css" type="text/css" media="all" />
	<!--[if IE 6]>
		<link rel="stylesheet" href="css/ie6.css" type="text/css" media="all" />
	<![endif]-->
	<script type="text/javascript" src="/Movie-Rental/assets/js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="/Movie-Rental/assets/js/jquery-func.js"></script>
</head>
<body>
<!-- Shell -->
<div id="shell">
	<!-- Header -->
	<div id="header">
		<!-- <h1 id="logo"><a href="#">Movie Hunter</a></h1> -->
		<div class="social">
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
				<div class="head">
				</div>
				<!-- Movie -->
				<div class="movie">
					<div class="movie-image">
						<a href="#"><img src="/Movie-Rental/images/movie2.jpg" alt="movie" /></a>
                                </div>
                                <% 
                                   HashMap<String, String> movie = (HashMap<String, String>)request.getAttribute("movie"); 
                                   HashMap<String, String> staff = (HashMap<String, String>)request.getAttribute("staff");
                                %> 
                                <div class="movie-details">
                                    <!--<p>Spider Man</p>-->
                                    <p> <% out.print(movie.get("name")); %> </p>
                                    <ul>
                                        <li>category: <% out.print(movie.get("category")); %></li>
                                        <li>rate: <% out.print(movie.get("rate")); %></li>
                                        <li>renting price: $<% out.print(movie.get("renting_price_per_day")); %> per/day</li>
                                        <li>duration: 
                                            <%  
                                                Double minutes = Double.parseDouble(movie.get("duration"));
                                                int minutesPerHour = 60;
                                                int hours = (int)(minutes/60);
                                                out.print( hours+"h ");
                                                minutes %= 60;
                                                int integerMinutes = minutes.intValue();
                                                out.print(integerMinutes+"minutes");
                                             %>
                                        </li>
                                    </ul>
                                    
                                      <%
                                          request.setAttribute("movieId", movie.get("id"));
                                          request.setAttribute("totalPrice", movie.get("renting_price_per_day"));
                                          boolean currentRent = Boolean.parseBoolean(movie.get("currentRent"));
                                          boolean isRent = Boolean.parseBoolean(movie.get("isRent"));
                                           
                                          if(!currentRent && isRent) {
                                      %>
                                      <form action="RentMovie" >
                                      <input class="rent" type="text" name="rentPeriod">
                                      <input type="submit" name="rent" value="rent">		                          <input type="submit" name="rent" value="rent">
                                      </form>
                                      <% } 
                                       else if (!currentRent) {   
                                      %>
                                      <form action="ExtendRentingMovie" >
                                      <input class="rent" type="text" name="rentPeriod">
                                      <input type="submit" name="rent" value="extend renting">		                          <input type="submit" name="rent" value="rent">
                                      </form>
                                     <% } %>
                                </div>
				</div>
				<!-- end Movie -->
					<div class="cl">&nbsp;</div>
			</div>
			<!-- end Box -->
			

		</div>
		<!-- end Content -->

		<!-- NEWS -->
		<div id="news">
			<div class="head">
			</div>
			
			<div class="content">
				<h4>Description</h4>
				<p><% out.print(movie.get("description")); %></p>

			</div>
		</div>
		<!-- end NEWS -->
		
		<!-- Coming -->
		<div id="coming">
			<div class="head">
			</div>
			<div class="content">
			    <div class="actors">
                                <h4>Actors</h4>
                                <%
                                    
                                    for(Map.Entry<String, String> e : staff.entrySet()){
                                        out.print("<h3>"+e.getKey()+":</h3><br>");
                                        out.print("<p> &nbsp; "+e.getValue()+"</p><br>");
                                    }
                                %>
			    </div>
			</div>
			<div class="cl">&nbsp;</div>			
		</div>
		<!-- end Coming -->
		<div class="cl">&nbsp;</div>
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