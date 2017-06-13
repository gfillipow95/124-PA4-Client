<%@page import="javax.servlet.http.HttpServlet" %>
<%@page import="javax.servlet.http.HttpServletRequest" %>
<%@page import="javax.servlet.http.HttpServletResponse" %>
<%@page import="java.io.IOException" %>
<%@page import="java.io.PrintWriter" %>
<%@page import="java.sql.Connection" %>
<%@page import="java.sql.ResultSet" %>
<%@page import="java.sql.SQLException" %>
<%@page import="java.sql.Statement" %>
<%@page import="javax.servlet.RequestDispatcher" %>
<%@page import="javax.servlet.ServletException" %>
<%@ page import="org.glassfish.jersey.client.ClientConfig" %>
<%@ page import="javax.ws.rs.client.Client" %>
<%@ page import="javax.ws.rs.client.ClientBuilder" %>
<%@ page import="javax.ws.rs.client.WebTarget" %>
<%@ page import="java.net.URI" %>
<%@ page import="javax.ws.rs.core.UriBuilder" %>
<%@ page import="javax.ws.rs.core.MediaType" %>
<%@ page import="org.codehaus.jackson.map.ObjectMapper" %>
<%@ page import="org.codehaus.jackson.type.TypeReference" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel='stylesheet' href='stylesheets/style.css' />
    <link href="https://fonts.googleapis.com/css?family=Lusitana" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Cousine:700" rel="stylesheet">
    <title>Coffee & Stuff</title>
</head>
<body>
    <div class="nav-bar-container">
        <ul class="nav-bar">
            <li><a id="navbar-checkout" href="displayCart">Check Out</a></li>
            <li><a id="navbar-about" href="#about">About</a></li>
            <li><a id="navbar-makers" href="#makers">Coffee Makers</a></li>
            <li><a id="navbar-mugs" href="#mugs">Mugs</a></li>
            <li><a id="navbar-coffee" href="#coffee">Coffee</a></li>
            <li><a id="navbar-home" href="#home">Home</a></li>
        </ul>
    </div>
    <div id="home" class="home section">
        <div class="hero-text">
            <h1>Coffee & Stuff</h1>
        </div>
    </div>
    <!-- Recently Viewed items go here from session -->
    <jsp:include page="/session"/>
    <!-- The different items go here -->
    <jsp:include page="/productTables"/>

    <div id="about" class="about section">
        <div class="section-title">
            <h2>About</h2>
            <table class="staff-table">
                <tr>
                    <td class="staff-description">
                        <div class="image-container">
                            <img class="staff-image" src="images/gen.jpg"/>
                        </div>
                        <div class="desc-container">
                            <h3>Gen Fillipow</h3>
                            <p>CEO&Founder</p>
                            <p>gfillipo@uci.edu</p>
                        </div>
                    </td>
                    <td class="staff-description">
                        <div class="image-container">
                            <img class="staff-image" src="images/yukino.jpg"/>
                        </div>
                        <div class="desc-container">
                            <h3>Yukino Nagasawa</h3>
                            <p>Secretary</p>
                            <p>nagasawy@uci.edu</p>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class="company-desc" colspan="2">
                        <p>
                            Gen and Yukino are 2 Informatics students who met during their sophmore year of college through their mutual friend. Over the years, they had numerous classes together and discovered their mutual love for coffee. In one of their classes, they were assigned a project with a start up, learning from an experienced, well versed entrepreneur the means to run a successful business. With this, and their background in web development, the two decided to translate their passion for coffee into a business. They only choose the best of the best to sell and quality is a 100% guarantee. Stay tuned for more, even better products!
                        </p>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</body>
<script src="javascript/image.js"></script>
</html>
