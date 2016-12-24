<%@page import="java.util.ArrayList"%>
<%@page import="Models.MovieModel"%>
<%@page import="java.util.HashMap"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en-US" xmlns="http://www.w3.org/1999/xhtml" dir="ltr">
<head>
	<title>Movie Hunter</title>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" href="/Movie-Rental/assets/css/Home.css" type="text/css" media="all" />
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
		<h1 id="logo"><a href="#">Movie Hunter</a></h1>
		<!-- Registeration -->
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
					<input type="submit" value="GO!" class="search-button" />
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

			
			<!-- end Search -->
            <%
                ArrayList<HashMap<String, String>> result = new MovieModel().returnMovies();

                    for (int i = 0; i < result.size(); i++) {
                        int countcolumn=6;
            %>
                <!-- Box -->
                <div class="box">
                        <div class="head">
                                <h2></h2>
                                <p class="text-right"><a href="#"></a></p>
                        </div>
                     <% for(;countcolumn > 0 && i<result.size();i++,countcolumn--){
                         
                          HashMap<String,String>curr=(HashMap<String,String>)result.get(i);
                         
                     %>
                        <!-- Movie -->
                        <div class="movie">
                                <div class="movie-image">
                                    <a href="#">
                                        <span class="play"><span class="name"><%= curr.get("name")%></span>
                                          
                                        </span><img src=<%= curr.get("img")%> alt="movie" /></a>
                                </div>
                                <div class="rating">
                                        <p>RATING</p>
                                        <span ><%=" : "+curr.get("rate")%></span>
                                        <span >
                                            <form action="/Movie-Rental/ShowMovie">
                                                <input type="text" name="id" value="<%= curr.get("id")%>" style="display: none;">
                                                <input type="submit" value="show" style="width: 50px;">
                                            </form>
                                        </span>
                                </div>
                        </div>
                        <!-- end Movie -->
                        <% }%>
                    
                        <div class="cl">&nbsp;</div>
                </div>
                <!-- end Box -->



                <%i--;}%>
			
			
			
			
		</div>
		<!-- end Content -->

		<!-- NEWS -->
		<div id="news">
			<div class="head">
				<h3>NEWS</h3>
				<p class="text-right"><a href="#">See all</a></p>
			</div>
			
			<div class="content">
				<p class="date">12.04.09</p>
				<h4>Disney's A Christmas Carol</h4>
				<p>&quot;Disney's A Christmas Carol,&quot; a multi-sensory thrill ride re-envisioned by Academy Award&reg;-winning filmmaker Robert Zemeckis, captures... </p>
				<a href="#">Read more</a>
			</div>
			<div class="content">
				<p class="date">11.04.09</p>
				<h4>Where the Wild Things Are</h4>
				<p>Innovative director Spike Jonze collaborates with celebrated author Maurice Sendak to bring one of the most beloved books of all time to the big screen in &quot;Where the Wild Things Are,&quot;...</p>
				<a href="#">Read more</a>
			</div>
			<div class="content">
				<p class="date">10.04.09</p>
				<h4>The Box</h4>
				<p>Norma and Arthur Lewis are a suburban couple with a young child who receive an anonymous gift bearing fatal and irrevocable consequences.</p>
				<a href="#">Read more</a>
			</div>
		</div>
		<!-- end NEWS -->
		
		<!-- Coming -->
		<div id="coming">
			<div class="head">
				<h3>COMING SOON<strong>!</strong></h3>
				<p class="text-right"><a href="#">See all</a></p>
			</div>
			<div class="content">
				<h4>The Princess and the Frog </h4>
					<a href="#"><img src="/Movie-Rental/images/coming-soon1.jpg" alt="coming soon" /></a>
				<p>Walt Disney Animation Studios presents the musical "The Princess and the Frog," an animated comedy set in the great city of New Orleans...</p>
				<a href="#">Read more</a>
			</div>
			<div class="cl">&nbsp;</div>
			<div class="content">
				<h4>The Princess and the Frog </h4>
					<a href="#"><img src="/Movie-Rental/images/coming-soon2.jpg" alt="coming soon" /></a>
				<p>Walt Disney Animation Studios presents the musical "The Princess and the Frog," an animated comedy set in the great city of New Orleans...</p>
				<a href="#">Read more</a>
			</div>
			
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
		// Get the modal
		var modal1 = document.getElementById('login');
		var modal2 = document.getElementById('signup');
		// When the user clicks anywhere outside of the modal, close it
		window.onclick = function(event) {
			// console.log("inside function");
			// console.log(modal);
			//console.log(event.target);
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