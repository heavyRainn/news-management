<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<html>
<head>
    <title>Title</title>
    <style><%@include file="/resources/css/adminLogin.css" %></style>
</head>

<body>

<c:url value="/login" var="loginUrl"/>
<form action="${loginUrl}" method="post">
    <c:if test="${param.error != null}">
        <p style="color:red">
            Invalid username and password.
        </p>
    </c:if>
    <c:if test="${param.logout != null}">
        <p style="color:red">
            You have been logged out.
        </p>
    </c:if>
    <p>
        <label for="username">Login :</label>
        <input type="text" id="username" name="username"/>
    </p>
    <p>
        <label for="password">Password :</label>
        <input type="password" id="password" name="password"/>
    </p>
    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
    <button type="submit" class="btn">Log in</button>
</form>

</body>

</html>
