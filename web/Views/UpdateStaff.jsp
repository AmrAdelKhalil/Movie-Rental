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

        <div >
            <%
                HashMap<String,String> values=new MovieModel().getValues(request.getParameter("movieId")); //id movie;
                 
            %>
            <form mtehod="post" action="/Movie-Rental/UpdateStaff">
                <input type="hidden" name="id" value=<%=request.getParameter("movieId")%> >
                <label>Number Of Staff</label>
                <input type="number" name="Number"  min=<%= values.get("number")%> max=<%= values.get("number")%> value=<%= values.get("number")%>>
                <ul>
                    <% for(Integer i=0;i<Integer.parseInt( values.get("number"));i++){
                    
                    %>
                    <li>
                        
                            <input type="hidden" name="id<%=i.toString()%>"  value=<%=values.get("id"+i.toString())%> >
                            <input type="text" name="memberName<%=i.toString()%>" placeholder="Member Name" required value=<%=values.get("name"+i.toString())%>>
                            <input type="text" name="role<%=i.toString()%>" placeholder="Role" required value=<%=values.get("role"+i.toString())%>>
                            <label>------------------------------------------------------------------------------------</label>
                    </li>
                    <%}%>
                </ul> 
                <input type="submit" name="submit" value="Update Staff" style="width:10%;">
            </form>
	</div>
</div>
<script src="/Movie-Rental/assets/angular.js"></script>
</body>
</html>