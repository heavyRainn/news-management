<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Title</title>
    <style>
        <%@include file="/resources/css/adminLogin.css" %>
    </style>
</head>

<body>

<form action="news-client" id="login-form" method="post">
    <p>
        <label for="login">Login :</label>
        <input type="text" id="login" name="login"/>
    </p>
    <p>
        <label for="password">Password :</label>
        <input type="password" id="password" name="password"/>
    </p>
    <input type="hidden" name="command" value="login"/>
    <button type="submit" class="btn">Log in</button>
</form>

</body>

</html>
