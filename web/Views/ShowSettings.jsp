<%-- 
    Document   : ShowSettings
    Created on : Dec 21, 2016, 1:30:19 PM
    Author     : amr
--%>

<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en-US" xmlns="http://www.w3.org/1999/xhtml" dir="ltr">
<head>
	<title>Setting</title>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" href="/Movie-Rental/assets/css/ShowSettings.css" type="text/css" media="all" />
	<!--[if IE 6]>
		<link rel="stylesheet" href="css/ie6.css" type="text/css" media="all" />
	<![endif]-->
        <link rel="shortcut icon" href="/Movie-Rental/images/Babasse-Old-School-Bobines-video.ico"/>
	<script type="text/javascript" src="/Movie-Rental/assets/js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="/Movie-Rental/assets/js/jquery-func.js"></script>
</head>
<body>
<!-- Shell -->
<% 
    HashMap<String, String> user = (HashMap<String, String>)request.getAttribute("user");
%>
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
                                <a class="user-data" href="/Movie-Rental/ShowSettings?id=<%=request.getSession().getAttribute("userId") %>"> <%=currentSession.getAttribute("name") %></a>
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
				<br>
				<h1> Settings </h1>
				<br>
				<label > Name:  <%= user.get("name") %></label>
                                <br>
				
				<label > Email:  <%= user.get("email") %></label>
				<br>
				<%if(request.getSession().getAttribute("isAdmin") == null){%>
                                    <label > Balance:  $<%= user.get("balance") %></label>
                                    <br>
                                    <label > Credit Card:  <%= user.get("creditCard") %></label>
                                    <br>
                                    <br>
                                <%}%>
                                <form action="/Movie-Rental/beforeUpdateSettings" method="post">
                                    <input type="text" name="id" value="<%= user.get("id") %>" style="display: none;"/>
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
		<p> &copy; 2016 Movie Hunter, LLC. All Rights Reserved.  Designed by GHOSTS TEAM CS_IS</p>
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
