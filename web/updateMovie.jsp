<%@page import="java.util.HashMap"%>
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
            <h1 id="logo"><a href="#">Movie Hunter</a></h1>
                    <div class="social">

                            <br>
                            <a class="user-data" href="#"> Amr Alaa</a>
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
                                <input type="submit" value="GO!" class="search-button" />
                        </form>
                </div>
            </div>
            <!-- end Sub-Menu -->

	</div>
	<!-- end Header -->

        <div ng-app="myApp" ng-controller="controller">
            <%
                HashMap<String,String> values=new HashMap<String,String>();
                
            %>
            <form mtehod="" action="">
                <input type="text" name="movieName" placeholder="Movie Name" >
                <input type="text" name="category" placeholder="Category">
                <input type="text" name="description" placeholder="Description for Movie">
                <input type="text" name="duration" placeholder="Duration in min">
                <input type="text" name="price" placeholder="Price Per Day">
                <label>Number Of Staff</label>
                <input type="number" name="Number" ng-model="currNumber" min=0 >
                <ul>
                        <li ng-repeat="i in total(currNumber) track by $index">
                                <input type="text" name="memberName{{$index}}" placeholder="Member Name" >
                                <input type="text" name="role{{$index}}" placeholder="Role">
                                <label>------------------------------------------------------------------------------------</label>
                        </li>
                </ul>
                <input type="submit" name="submit" value="Update Movie" style="width:10%;">
            </form>
	</div>
</div>
<script src="/Movie-Rental/assets/angular.js"></script>
</body>
</html>