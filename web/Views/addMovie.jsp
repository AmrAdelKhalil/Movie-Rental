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
            <form method="post" action="addMovie">
                <input type="text" name="movieName" placeholder="Movie Name" required>
                <input type="text" name="category" placeholder="Category" required>
                <input type="text" name="description" placeholder="Description for Movie">
<<<<<<< HEAD
=======
                <input type="text" name="imgUrl" placeholder="Image URL" required>
>>>>>>> updateAndSearch
                <input type="text" name="duration" placeholder="Duration in min" required>
                <input type="text" name="price" placeholder="Price Per Day" required>
                <input type="text" name="year" placeholder="year of release" style="width:25%; display:inline-block;">
                <select name="quality" required>
                      <option value="all">Quality</option>
                      <option value="280p">280p</option>
                      <option value="360p">360p</option>
                      <option value="480p">480p</option>
                      <option value="720p">720p</option>
                      <option value="1080p">1080p</option>
                      <option value="3D">3D</option>
                  </select>
                    <br/>
                <label>Number Of Staff</label>
                <input type="number" name="Number" ng-model="Number" min=0 value="0">
                <ul>
                    <li ng-repeat="i in total(Number) track by $index">
                            <input type="text" name="memberName{{$index}}" placeholder="Member Name" required>
                                <input type="text" name="role{{$index}}" placeholder="Role" required>
                            <label>------------------------------------------------------------------------------------</label>
                    </li>
                </ul>
                <input type="submit" name="submit" value="Add Movie">
            </form>
	</div>
</div>
<script src="/Movie-Rental/assets/angular.js"></script>
</body>
</html>