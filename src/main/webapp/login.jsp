<%--
  Created by IntelliJ IDEA.
  User: faria
  Date: 10/3/2022
  Time: 6:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
</head>
<body>
<h1>
  <%= "Login Here"%>
</h1><br>
<form action="<%= request.getContextPath()%>/loginManage" method="post">
  <input type="text" name="username" placeholder="Name..." /><br/>
  <input type="password" name="pass"  placeholder="Password..." /><br/>
  <input type="submit" name="login" value="Login"/>
</form>
<a href="registration.jsp">Registration</a>
</body>
</html>
