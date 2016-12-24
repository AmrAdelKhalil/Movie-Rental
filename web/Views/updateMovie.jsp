<%@page import="Models.MovieModel"%>
<%@page import="java.util.HashMap"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en-US" xmlns="http://www.w3.org/1999/xhtml" dir="ltr">
<head>
	<title>Free CSS template by ChocoTemplates.com</title>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" href="/Movie-Rental/assets/css/style.css"  media="all" />
	<script src="/Movie-Rental/assets/angular.min.js"></script>
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

<<<<<<< HEAD
            <!-- Navigation -->
            <div id="navigation">
            </div>
            <!-- end Navigation -->

            <!-- Sub-menu -->
            <div id="sub-navigation">
                <div id="search">
                </div>
            </div>
            <!-- end Sub-Menu -->
=======
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
>>>>>>> master

		<!-- Sub-menu -->
		<div id="sub-navigation">
			<div id="search">
					</div>
		</div>
		<!-- end Sub-Menu -->
		
	</div>
	<!-- end Header -->

        <div ng-app="myApp" ng-controller="controller">
            <%
                HashMap<String,String> values=new MovieModel().getValues(request.getParameter("movieId")); //id movie;
                 
            %>
            <form mtehod="post" action="/Movie-Rental/UpdateMovie">
<<<<<<< HEAD
                <input type="hidden" name="id" value=<%=request.getParameter("movieId")%> >
=======
                
>>>>>>> master
                <input type="text" name="movieName" placeholder="Movie Name" value= <%=values.get("name")%> >
                <input type="text" name="category" placeholder="Category" value=<%= values.get("category")%> >
                <input type="text" name="description" placeholder="Description for Movie" value=<%= values.get("description")%> >
                <input type="text" name="imgUrl" placeholder="Image URL" value=<%= values.get("imgUrl")%> required>
                <input type="text" name="duration" placeholder="Duration in min" value=<%= values.get("duration")%> >
                <input type="text" name="price" placeholder="Price Per Day" value=<%= values.get("price")%> >
                <input type="text" name="year" placeholder="year of release" value=<%= values.get("year")%> style="width:25%; display:inline-block;">
                <select name="quality" required>
                      <option <%= values.get("all")%> value="all">Quality</option>
                      <option <%= values.get("280p")%> value="280p">280p</option>
                      <option <%= values.get("360p")%> value="360p">360p</option>
                      <option <%= values.get("480p")%> value="480p">480p</option>
                      <option <%= values.get("720p")%> value="720p">720p</option>
                      <option <%= values.get("1080p")%> value="1080p">1080p</option>
                      <option <%= values.get("3D")%> value="3D">3D</option>
                  </select>
                    <br/>
<<<<<<< HEAD
                    <br/>
=======
                <label>Number Of Staff</label>
                <input type="number" name="Number" ng-init="currNumber=<%= values.get("number")%>" ng-model="currNumber" min=0 >
                <ul>
                    
                     <li ng-repeat="i in total(currNumber) track by $index">
 >
                        <input type="text" name="memberName{{i}}" placeholder="Member Name" value=<%=values.get("name")%>>
                        <input type="text" name="role{{i}}" placeholder="Role" value=""  >
                        <label>------------------------------------------------------------------------------------</label>
                       
                    </li>
                   
                </ul>
>>>>>>> master
                
                <input type="submit" name="submit" value="Update Movie" style="width:10%;">
            </form>
	</div>
</div>
<script src="/Movie-Rental/assets/angular.js"></script>
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