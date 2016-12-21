<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="Models.MovieModel"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en-US" xmlns="http://www.w3.org/1999/xhtml" dir="ltr">
<head>
	<title>Free CSS template by ChocoTemplates.com</title>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" href="/Movie-Rental/assets/css/style.css"  media="all" />
	
	<script type="text/javascript" src="/Movie-Rental/assets/js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="/Movie-Rental/assets/js/jquery-func.js"></script>
</head>
<body>
<!-- Shell -->
<div id="shell">
	<!-- Header -->
	<div id="header">
		<h1 id="logo"><a href="#">Movie Hunter</a></h1>
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
            
            <!-- Search -->
            <div class="form">
                <form method="post" action="#" accept-charset="UTF-8">
                    <input name="_token" type="hidden" value="#">
                    <div id="main-search-fields">
                        <div class="searchBar">
                            <p style="padding-bottom:3px;">Search Term:</p>
                            <input name="keyword" autocomplete="off" type="search">
                            <br>
                            <div class="selects-container">
                                <p style="padding-bottom:3px;">Quality:</p>
                                <select name="quality">
                                <option value="all">All</option>
                                <option value="720p">720p</option>
                                <option value="1080p">1080p</option>
                                <option value="3D">3D</option>
                            </select>
                            </div>
                            <div class="selects-container">
                                <p style="padding-bottom:3px;">Type:</p>
                                <select name="genre">
                                    <option value="all">All</option>
                                    <option value="action">Action</option>
                                    <option value="adventure">Adventure</option>
                                    <option value="animation">Animation</option>
                                    <option value="biography">Biography</option>
                                    <option value="comedy">Comedy</option>
                                    <option value="crime">Crime</option>
                                    <option value="documentary">Documentary</option>
                                    <option value="drama">Drama</option>
                                    <option value="family">Family</option>
                                    <option value="fantasy">Fantasy</option>
                                    <option value="film-noir">Film-Noir</option>
                                    <option value="game-show">Game-Show</option>
                                    <option value="history">History</option>
                                    <option value="horror">Horror</option>
                                    <option value="music">Music</option>
                                    <option value="musical">Musical</option>
                                    <option value="mystery">Mystery</option>
                                    <option value="news">News</option>
                                    <option value="reality-tv">Reality-TV</option>
                                    <option value="romance">Romance</option>
                                    <option value="sci-fi">Sci-Fi</option>
                                    <option value="sport">Sport</option>
                                    <option value="talk-show">Talk-Show</option>
                                    <option value="thriller">Thriller</option>
                                    <option value="war">War</option>
                                    <option value="western">Western</option>
                                </select>
                            </div>
                            <div class="selects-container">
                                <p style="padding-bottom:3px;">Rating:</p>
                                <select name="rating">
                                    <option value="0">All</option>
                                    <option value="9">9+</option>
                                    <option value="8">8+</option>
                                    <option value="7">7+</option>
                                    <option value="6">6+</option>
                                    <option value="5">5+</option>
                                    <option value="4">4+</option>
                                    <option value="3">3+</option>
                                    <option value="2">2+</option>
                                    <option value="1">1+</option>
                                </select>
                            </div>
                            <div class="selects-container selects-container-last">
                                <p style="padding-bottom:3px;">Order By:</p>
                                <select name="order_by">
                                    <option value="latest">Latest</option>
                                    <option value="oldest">Oldest</option>
                                    <option value="seeds">Seeds</option>
                                    <option value="peers">Peers</option>
                                    <option value="year">Year</option>
                                    <option value="rating">Rating</option>
                                    <option value="likes">Likes</option>
                                    <option value="alphabetical">Alphabetical</option>
                                    <option value="downloads">Downloads</option>
                                </select>
                            </div>
                            
                            
                        </div>
                    </div>
                        
                    <div id="main-search-btn">
                        <input class="button-green-download2-big" type="submit" value="Search">
                    </div>
                </form>
            </div>
            
            <!-- end Search -->
            <%
                HashMap<Integer,HashMap<String,String> >result=new MovieModel().returnMovies();
                Iterator it = result.entrySet().iterator();
                while (it.hasNext()) {
                    int countcolumn=6;
            %>
                <!-- Box -->
                <div class="box">
                        <div class="head">
                                <h2>LATEST TRAILERS</h2>
                                <p class="text-right"><a href="#">See all</a></p>
                        </div>
                     <% while(countcolumn-- > 0 && it.hasNext()){
                          Map.Entry pair = (Map.Entry)it.next();
                          HashMap<String,String>curr=(HashMap<String,String>)pair.getValue();
                         
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
                                        <span class="comments">12</span>
                                </div>
                        </div>
                        <!-- end Movie -->
                        <%}%>
                    
                        <div class="cl">&nbsp;</div>
                </div>
                <!-- end Box -->



                <%}%>
		</div>
		<!-- end Content -->

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