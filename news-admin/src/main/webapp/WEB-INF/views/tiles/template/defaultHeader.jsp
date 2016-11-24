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

<h1>News portal - Administration</h1>

<label class="logoutLblPos">
    <c:url value="/logout" var="logout"/>
    <form action="${logout}" method="post">
        Hello Admin !
        <input type="hidden"
               name="${_csrf.parameterName}"
               value="${_csrf.token}"/>
        <button type="submit" class="btn">Log out</button>
    </form>
</label>

</body>
</html>
