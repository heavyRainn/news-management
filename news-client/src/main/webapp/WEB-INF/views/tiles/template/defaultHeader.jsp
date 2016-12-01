<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Default header</title>
    <style>
        <%@include file="/resources/css/home.css" %>
    </style>
</head>
<body>

<h1>News portal </h1>

<label class="logoutLblPos">
    <form action="news-client" method="post">
        Hello User !
        <input type="hidden" name="command" value="logout"/>
        <button type="submit" class="btn">Log out</button>
    </form>
</label>
</body>
</html>
