<%@page import="Models.MovieModel"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en-US" xmlns="http://www.w3.org/1999/xhtml" dir="ltr">
<% 
    HashMap<String, String> movie = (HashMap<String, String>) request.getAttribute("movie"); 
%>
<head>
	<title><% out.print(movie.get("name")); %> </title>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" href="/Movie-Rental/assets/css/listlateusers.css" type="text/css" media="all" />
	<!--[if IE 6]>
		<link rel="stylesheet" href="css/ie6.css" type="text/css" media="all" />
	<![endif]-->
        <link rel="shortcut icon" href="/Movie-Rental/images/Babasse-Old-School-Bobines-video.ico"/>
	<script type="text/javascript" src="/Movie-Rental/assets/js/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="/Movie-Rental/assets/js/jquery-3.1.1.min.js"></script>
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
				  <form id="signin" class="modal-content animate" method="POST">
				    <div class="container">
                                        <label><b>Email</b></label>
                                        <input type="email" placeholder="Enter Email" name="email" required>

                                        <label><b>Password</b></label>
                                        <input type="password" placeholder="Enter Password" name="password" required>
                                        <div id="invalidLogin" style="color:red; display: none;">Invalid Email/Password</div>
                                        <button type="submit">Login</button>
                                        <input type="checkbox"> Remember me
                                        <br>
                                        <input type="checkbox" name="isAdmin"> Login as admin
				    </div>

				    <div class="container" style="background-color:#f1f1f1">
				      <button type="button" onclick="document.getElementById('login').style.display='none'" class="cancelbtn">Cancel</button>
				      <span class="psw"><a href="#">Forgot password?</a></span>
				    </div>
				  </form>
				</div>
			    <li><button onclick="document.getElementById('signup').style.display='block'">Register</button></li>
			    <div id="signup" class="modal">
                                <form class="modal-content animate" action="SignUp" method="POST">
			    		<dir class="container">
			    			<label><b>Username</b></label>
						    <input type="text" placeholder="Username" name="name" required>

						    <label><b>Email</b></label>
						    <input type="email" placeholder="Email" name="email" required>

						    <label><b>Password</b></label>
						    <input id="password" type="password" placeholder="Password" name="password" required>
						    
						    <label><b>Confirm Password</b></label>
						    <div id="wrongpass" style="color: red; display: none;">Password does not match!!</div>
                                                    <input id="confirm_password" type="password" placeholder="Confirm Password" name="password" required>
						    
						    <label id="credit"><b>Credit Card</b></label>
                                                    <div id="wrongcode" style="color: red; display: none;">Wrong Activation Code! </div>
						    <input id="creditin" type="text" placeholder="Credit Card" name="credit" required>
                                                    <br>
                                                        
                                                    <input id="admin" type="checkbox" name="isAdmin"> Register as admin

						    <div class="container" style="background-color:#f1f1f1">
						    <button id="signupbtn" type="submit">Sign Up</button>
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
				<div class="head">
                                    <h1><%= movie.get("name")%> late users</h1>
				</div>
                                 
                                <div class="lateUsers">
				<%
            
                                    HashMap<String, HashMap<String, String> > users = (HashMap<String, HashMap<String, String> > )request.getAttribute("users");
                                    HashMap<String, String> single_user=null;
                                    for (Map.Entry<String, HashMap<String,String> > entry : users.entrySet())
                                    {
                                         single_user = entry.getValue();
                                %>
                                    <div class="detailsLateUsers">
                                        <form action="/Movie-Rental/SendMail" method="post">

                                                <input type="hidden" value=<%=single_user.get("id")%> name="userId">
                                                <input type="hidden" value=<%=movie.get("id")%> name="movieId">
                                                    <ul>
                                                        <li>
                                                            <%=(Integer.parseInt(entry.getKey())+1)%>-
                                                        </li>
                                                        <li>
                                                            Name: <span><%=single_user.get("name")%></span>
                                                        </li>
                                                        <li>
                                                            Email: <span><%=single_user.get("email")%></span>
                                                        </li>
                                                        <li>
                                                            <input class="movieBtn movieBtnShift" type="submit" name="" value="send mail">
                                                        </li>
                                                    </ul>
                                               

                                        </form>
                                    </div>
                                                        
                                <%
                                    }
                                %>
                                </div>
					<div class="cl">&nbsp;</div>
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
        $(document).ready(function(){
            
            $('#signin').submit(function(e) {
                e.preventDefault();     //prevent click
                
                $.ajax({
                   type: "POST",
                   url: 'Login',
                   data: $(this).serialize(),
                   success: function() {
                       window.location = window.location.href;
                   },
                   error : function(){
                       $("#invalidLogin").show();
                   }
               });
             });
            
            $("#admin").click(function(){
                if($("#admin").is(':checked')){
                    $("#credit").html("<label><b>Activation Code</b></label>");
                    $("#creditin").attr("placeholder", "Activation Code");
                }
                else{
                    $("#credit").html("<label><b>Credit Card</b></label>");
                    $("#creditin").attr("placeholder", "Credit Card");
                    $("#signupbtn").prop('disabled', false);
                    $("#wrongcode").hide();
                }
            });
            
            $("#confirm_password").keyup(function(){
                var pass1 = $("#password").val();
                var pass2 = $("#confirm_password").val();
                if(pass1 !== pass2){
                    $("#wrongpass").show();
                    $("#signupbtn").prop('disabled', true);
                }
                else{
                    $("#wrongpass").hide();
                    $("#signupbtn").prop('disabled', false);
                }
            });
            
            
            $("#creditin").keyup(function(){
               var code = $(this).val();
               
               if(code == "0000" && $("#admin").is(':checked')){
                   $("#signupbtn").prop('disabled', false);
                   $("#wrongcode").hide();
               }
               else {
                   if($("#admin").is(':checked')){
                        $("#wrongcode").show();
                        $("#signupbtn").prop('disabled', true);
                    }
               }
            }); 
        });
        
    </script>
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


