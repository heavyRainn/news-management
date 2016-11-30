<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Login page</title>
    <style>
        <%@include file="/resources/css/adminLogin.css" %>
    </style>
</head>

<body>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources.locale" var="lang"/>

<fmt:message bundle="${lang}" key="label.login" var="logIn"/>
<fmt:message bundle="${lang}" key="label.password" var="pass"/>

<form action="news-client" id="login-form" method="post">
    <p>
        <label for="login"><c:out value="${logIn}"/> :</label>
        <input type="text" id="login" name="login"/>
    </p>
    <p>
        <label for="password"><c:out value="${pass}"/> :</label>
        <input type="password" id="password" name="password"/>
    </p>
    <input type="hidden" name="command" value="login"/>
    <button type="submit" class="btn">Log in</button>
</form>

</body>

</html>
