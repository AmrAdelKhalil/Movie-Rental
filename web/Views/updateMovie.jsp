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
                    <div class="social">

                            <br>
                            <a class="user-data" href="#"> Amr Alaa</a>
                            <a class="user-data" href="#"> Settings</a>
                            <a class="user-data" href="#"> Logout</a>


            </div>

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

	</div>
	<!-- end Header -->

        <div ng-app="myApp" ng-controller="controller">
            <%
                HashMap<String,String> values=new MovieModel().getValues(request.getParameter("movieId")); //id movie;
                 
            %>
            <form mtehod="post" action="/Movie-Rental/UpdateMovie">
                <input type="hidden" name="id" value=<%=request.getParameter("movieId")%> >
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
                    <br/>
                
                <input type="submit" name="submit" value="Update Movie" style="width:10%;">
            </form>
	</div>
</div>
<script src="/Movie-Rental/assets/angular.js"></script>
</body>
</html>