<%--
  Created by IntelliJ IDEA.
  User: faria
  Date: 10/5/2022
  Time: 6:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>OTP Verification</title>
</head>
<body>
<h1>
    Enter your OTP code
</h1>
<form action="<%= request.getContextPath()%>/otpManage" method="post">
    <input type="text" name="otp" placeholder="otp...">
    <input type="submit" name="submit" value="Login"/>
</form>

</body>
</html>
