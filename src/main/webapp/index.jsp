<%@ page import="java.io.PrintWriter" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<%
    String userName = null;
//    PrintWriter out =null;
%>
<%
    Cookie cookie = null;
    Cookie[] cookies = null;


    // Get an array of Cookies associated with this domain
    cookies = request.getCookies();

    if( cookies != null ) {
//        out.println("<h2> Found Cookies Name and Value</h2>");

        for (int i = 0; i < cookies.length; i++) {

            cookie = cookies[i];
            if(cookie.getName().matches("username")){
                userName= "Welcome"+cookie.getValue();
            }
//            out.print("Name : " + cookie.getName( ) + ",  ");
//            out.print("Value: " + cookie.getValue( )+" <br/>");
        }
    } else {
//        out.println("<h2>No cookies founds</h2>");
    }
%>
<h1> <%= userName %></h1>
<a href="login.jsp">Go to Login Page</a>
</body>
</html>