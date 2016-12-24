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
		<h1 id="logo"><a href="/Movie-Rental/Views/index.jsp">Movie Hunter</a></h1>
		<!-- Registeration -->
                <% 
                    HttpSession currentSession =request.getSession(true);
                    if(currentSession.getAttribute("userId") == null){
                %>
		<div id="registeration">
			<ul>
				<li><button onclick="document.getElementById('login').style.display='block'" >Login</button></li>
				<div id="login" class="modal">  
				  <form class="modal-content animate" action="../Login" method="GET">
				    <div class="container">
				      <label><b>Username</b></label>
				      <input type="text" placeholder="Enter Email" name="email" required>

				      <label><b>Password</b></label>
				      <input type="password" placeholder="Enter Password" name="password" required>
				        
				      <button type="submit">Login</button>
				      <input type="checkbox" checked="checked"> Remember me
				    </div>

				    <div class="container" style="background-color:#f1f1f1">
				      <button type="button" onclick="document.getElementById('login').style.display='none'" class="cancelbtn">Cancel</button>
				      <span class="psw"><a href="#">Forgot password?</a></span>
				    </div>
				  </form>
				</div>
			    <li><button onclick="document.getElementById('signup').style.display='block'">Register</button></li>
			    <div id="signup" class="modal">
			    	<form class="modal-content animate" action="../SignUp">
			    		<dir class="container">
			    			<label><b>Username</b></label>
						    <input type="text" placeholder="Username" name="name" required>

						    <label><b>E-Mail</b></label>
						    <input type="text" placeholder="E-Mail" name="email" required>

						    <label><b>Password</b></label>
						    <input type="password" placeholder="Password" name="password" required>
						    
						    <label><b>Confirm Password</b></label>
						    <input type="password" placeholder="Confirm Password" name="password" required>
						    
						    <label><b>Credit Card</b></label>
						    <input type="text" placeholder="Credit Card" name="credit" required>

						    <div class="container" style="background-color:#f1f1f1">
						    <button type="submit">Sign Up</button>
						    	<button type="button" onclick="document.getElementById('signup').style.display='none'" class="cancelbtn">Cancel</button>
						    	
						    </div>
			    		</dir>
			    	</form>
			    </div>
			</ul>
		</div>
		<%} else { %>
                    <div id="registeration">
			
				<br>
                                <a class="user-data" href="#"> <%=currentSession.getAttribute("name") %></a>
                                <a class="user-data" href="/Movie-Rental/ShowSettings?id=<%=request.getSession().getAttribute("userId") %>"> Settings</a>
				<a class="user-data" href="/Movie-Rental/logout"> Logout</a>	
		</div>
                <% }%>

		<!-- Sub-menu -->
		<div id="sub-navigation">
			<div id="search">
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
				<div class="head">
				</div>
                                <% 
                                   HashMap<String, String> movie = (HashMap<String, String>)request.getAttribute("movie"); 
                                   HashMap<String, String> staff = (HashMap<String, String>)request.getAttribute("staff");
                                %> 
				<!-- Movie -->
				<div class="movie">
					<div class="movie-image">
						<a href="#"><img src="<%= movie.get("img_url") %>" alt="movie" /></a>
                                </div>
                                <!--/Movie-Rental/images/movie2.jpg-->
                                
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
                                          
                                          
                                          boolean currentRent = Boolean.parseBoolean(movie.get("currentRent"));
                                          boolean isRent = Boolean.parseBoolean(movie.get("isRent"));
                                           
                                          if(!currentRent && isRent) {
                                      %>
                                      <form action="/Movie-Rental/RentMovie" >
                                      <input type="hidden" name="id" value="<%= movie.get("id") %>">
                                      <input type="hidden" name="totalPrice" value="<%= movie.get("renting_price_per_day") %>">
                                      <input class="rent" type="text" name="rentPeriod">
                                      <input type="submit" value="rent">
                                      </form>
                                      <% } 
                                       else if (!currentRent) {   
                                      %>
                                      <form action="/Movie-Rental/ExtendRentingMovie" >
                                      <input type="hidden" name="id" value="<%= movie.get("id") %>">
                                      <input type="hidden" name="totalPrice" value="<%= movie.get("renting_price_per_day") %>">
                                      <input class="rent" type="text" name="rentPeriod">
                                      <input type="submit" value="extend renting">
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
<script>
		var modal1 = document.getElementById('login');
		var modal2 = document.getElementById('signup');

		window.onclick = function(event) {

		    if (event.target == modal1) {
		        modal1.style.display = "none";
		    }
		    if(event.target == modal2){
		    	modal2.style.display = "none";
		    }
		}
	</script>
</body>
</html>